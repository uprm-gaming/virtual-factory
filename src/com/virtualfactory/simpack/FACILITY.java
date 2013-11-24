package com.virtualfactory.simpack;

import java.util.LinkedList;
import java.util.ListIterator;

public class FACILITY {
  /** Type that specifies the facility status. */
  public enum Status
  { FREE, BUSY }

  /** Type that specifies the priority based insertion order policy. */
  public enum PriorityOrder
  { aheadOfEqualPriorities, behindOfEqualPriorities }

  private LinkedList<SimEvent> queue;
  private Status status;
  private String name;
  private int total_servers;
  private int busy_servers;
  private double totalBusyTime;
  private double start_busy_time;
  private int preemptions;
  private int server_info[][];

  public FACILITY(final String name, final int numberOfServers)
  {
    this.queue = new LinkedList<SimEvent>();
    this.status = FACILITY.Status.FREE;
    this.name = new String("name");
    this.total_servers = numberOfServers;
    this.busy_servers = 0;
    this.totalBusyTime = 0;
    this.start_busy_time = Double.NaN;
    this.preemptions = 0;

    // FIXME: server_info should be a class object.
    this.server_info = new int[numberOfServers+1][2];
    for (int i = 1; i <= numberOfServers; ++i)
    {
      this.server_info[i][0] = 0;
      this.server_info[i][1] = 0;
    }
  }

  public FACILITY(final FACILITY other)
  {
    this.queue = new LinkedList<SimEvent>();
    for (SimEvent e : other.queue)
    {
      this.queue.add(new SimEvent(e));
    }

    this.status = other.status;
    this.name = other.name;
    this.total_servers = other.total_servers;
    this.busy_servers = other.busy_servers;
    this.totalBusyTime = other.totalBusyTime;
    this.start_busy_time = other.start_busy_time;
    this.preemptions = other.preemptions;

    this.server_info = new int[other.server_info.length][];
    for (int index = 0; index < other.server_info.length; ++index)
    {
      this.server_info[index] = other.server_info[index].clone();
    }
  }

  public final void insertInQueue(final SimEvent event)
  {
    this.insertInQueue(event,
                       FACILITY.PriorityOrder.behindOfEqualPriorities);
  }

  public final void insertInQueue(final SimEvent event,
                                  final PriorityOrder priorityOrder)
  {
    final ListIterator<SimEvent> iterator = this.queue.listIterator();
    while (iterator.hasNext())
    {
      final SimEvent next = iterator.next();
      if (priorityOrder == FACILITY.PriorityOrder.behindOfEqualPriorities
          ? event.getPriority() <= next.getPriority()
          : event.getPriority() <  next.getPriority())
      {
        if (iterator.hasPrevious())
        {
          iterator.previous();
        }
        break;
      }
    }
    iterator.add(new SimEvent(event));
  }

  public final SimEvent removeFirstInQueue()
  {
    return this.queue.removeFirst();
  }

  public final int getQueueSize()
  {
    return this.queue.size();
  }

  /**
   * Request service from this facility.
   *
   * <p>An incoming token requests a server in the facility. If at least
   * one server is available then the facility status is
   * FACILITY.Status.FREE, otherwise it is FACILITY.Status.BUSY. If the
   * facility is busy, the token is inserted into the queue in a
   * priority-sorted order (larger numbers represent higher priorities).
   *
   * @param token the token that is requesting service.
   * @param priority the priority level for this token.
   * @return True if the request was granted; false otherwise (e.g., if it
   * was queued).
   */
  public final boolean request(final TOKEN token, final int priority)
  {
    if (this.status == FACILITY.Status.FREE)
    {
      if (this.busy_servers == 0)
      {
        this.start_busy_time = Sim.time();
      }
      ++this.busy_servers;

      // Find the first free server.
      int serverNum = 1;
      while (this.server_info[serverNum][0] != 0)
      {
        ++serverNum;
      }

      // Occupy the server found.
      this.server_info[serverNum][0] = token.getTokenId();
      this.server_info[serverNum][1] = priority;

      // Change the facility status if it is the last free server.
      if (this.busy_servers == this.total_servers)
      {
        this.status = FACILITY.Status.BUSY;
      }

      return true;
    }
    else
    {
      final SimEvent anEvent = new SimEvent(Sim.time(),
                                            Sim.getCurrentEventId(),
                                            priority,
                                            token);
      this.insertInQueue(anEvent);

      return false;
    }
  }

  /**
   * Preempt a token being serviced to tend to this one.
   *
   * <p>An incoming token requests a facility, and will usually preempt (or
   * replace) the token already being serviced. The difference between
   * preempt and request is that with preemption, a token doesn't normally
   * wait in the queue at all. The idea is to bypass the queue completely
   * to obtain immediate service.
   *
   * <p>If the incoming token's priority is less than or equal to all
   * tokens currently being served in the facility, then that token will be
   * queued. But, if the incoming token's priority is greater, the server
   * token will be preempted and placed back at the head of the facility
   * queue, time-tagged with its remaining time for service.
   *
   * @param token the token that is requesting service.
   * @param priority the priority level for this token.
   * @return True if the preemption was granted; false otherwise (e.g., if
   * it was queued).
   */
  public final boolean preempt(final TOKEN token, final int priority)
  {
    if (this.status == FACILITY.Status.FREE
        || priority <= this.findServerWithMinimumPriorityToken()[1])
    {
      return this.request(token, priority);
    }

    // Find the tended token with least priority.
    final int[] preemptServer = this.findServerWithMinimumPriorityToken();

    ++this.preemptions;
    final int preemptedToken = preemptServer[0];
    final int preemptedTokenPriority = preemptServer[1];

    preemptServer[0] = token.getTokenId();
    preemptServer[1] = priority;

    // Remove release event associated with preempted token.
    final SimEvent anEvent = Sim.removeEventFromFEL(preemptedToken);

    // Put preempted token back in facility queue. Note that the time
    // will be reduced by the amount already serviced. It will also be
    // negated to identify it as a preempted token.
    // FIXME: Instead of encrypting the fact that it has been preempted
    //        by negating the time, make a boolean variable part of the
    //        token or event (e.g., isPreempted).
    assert Sim.time() - anEvent.getTime() < 0
      : "Preempted token time not negative.";
    this.insertInQueue(new SimEvent(Sim.time() - anEvent.getTime(),
                                    anEvent.getId(),
                                    preemptedTokenPriority,
                                    anEvent.getToken()),
                       FACILITY.PriorityOrder.aheadOfEqualPriorities);

    return true;
  }

  private int[] findServerWithMinimumPriorityToken()
  {
    int[] serverTendingMinimumPriority = this.server_info[1];
    for (int i = 2; i <= this.total_servers; ++i)
    {
      if (this.server_info[i][1] < serverTendingMinimumPriority[1])
      {
        serverTendingMinimumPriority = this.server_info[i];
      }
    }

    return serverTendingMinimumPriority;
  }

  /**
   * Release the server in the facility that is tending this token.
   *
   * <p>This is generally performed after a token has obtained service.
   * That token then releases the facility to the next waiting token at the
   * head of the queue.
   *
   * <p>This is done by automatically scheduling the token on the head of
   * the queue if there is any. The automatic scheduling uses Delta time
   * equal to zero and the type of event that was used by the token that
   * made the facility request (i.e., prior to having to wait).
   *
   * @param token the token that is releasing the facility.
   * @throws IllegalStateException
   *         if facility is not servicing the specified token.
   */
  public final void release(final TOKEN token)
  {
    if (this.busy_servers < 1)
    {
      // FIXME: Facility needs to be self-aware of its id.
      this.print(-1);
      throw new IllegalStateException("Facility has no busy servers.");
    }
    assert !Double.isNaN(this.start_busy_time)
      : "FACILITY.start_busy_time has not been reset.";

    // Find server that is tending this token.
    boolean found = false;
    int i = 1;
    while (!found && i <= this.total_servers)
    {
      if (this.server_info[i][0] == token.getTokenId())
      {
        this.server_info[i][0] = 0;
        this.server_info[i][1] = 0;
        found = true;
      }
      ++i;
    }
    if (!found)
    {
      // FIXME: Facility needs to be self-aware of its id.
      this.print(-1);
      throw new IllegalStateException(
        "Facility is not tending this token: " + token.getTokenId());
    }

    --this.busy_servers;
    if (this.busy_servers == 0)
    {
      this.totalBusyTime += (Sim.time() - this.start_busy_time);
      // This will be reset by a future request.
      this.start_busy_time = Double.NaN;
    }
    this.status = FACILITY.Status.FREE;

    // Start tending the next token in the queue.
    if (this.getQueueSize() > 0)
    {
      final SimEvent anEvent = this.removeFirstInQueue();
      if (anEvent.getTime() >= 0.0)
      {
        Sim.schedule(new SimEvent(Sim.time(),
                                  anEvent.getId(),
                                  anEvent.getPriority(),
                                  anEvent.getToken()));
      }
      else
      {
        // Preempted event; time is negative and represents the remaining
        // time to complete servicing.

        // NOTE: If there are events in queue, the facility will be busy
        //       after occupying the released server.
        this.status = FACILITY.Status.BUSY;

        // Occupy the free server (the one just released).
        int index = 1;
        while (this.server_info[index][0] != 0)
        {
          ++index;
        }
        this.server_info[index][0] = anEvent.getToken().getTokenId();
        this.server_info[index][1] = anEvent.getPriority();
        ++this.busy_servers;

        // Re-schedule the adjusted release event.
        Sim.schedule(new SimEvent(Sim.time() - anEvent.getTime(),
                                  anEvent.getId(),
                                  anEvent.getPriority(),
                                  anEvent.getToken()));
      }
    }
  }

  public final void traceFacility(final int facilityId)
  {
    System.out.println("Time: " + Sim.time());
    System.out.print("Queue " + facilityId + ": ");
    for (SimEvent event : this.queue)
    {
      System.out.print("(TM " + event.getTime());
      System.out.print(" EV " + event.getId());
      System.out.print(" TK " + event.getToken().getTokenId() + ")");
    }
    System.out.println();
    System.out.println("----------------------------------------");
  }

  public final void print(final int facilityId)
  {
    System.out.println("## FACILITY " + facilityId
                       + ": (" + this.name + "), "
                       + this.total_servers + " Server(s), "
                       + this.busy_servers + " Busy.");
    System.out.print("Server(s): ");
    for (int j = 1; j <= this.total_servers; ++j)
    {
      System.out.print("(" + j + ") TK " + this.server_info[j][0]
                       + " PR " + this.server_info[j][1]);
    }
    System.out.println();

    System.out.print("         ");
    for (int i = 0; i < this.queue.size(); ++i)
    {
      System.out.print("  ");
      System.out.print("+----+");
    }
    System.out.println();

    System.out.print("Token    ");
    for (SimEvent event : this.queue)
    {
      System.out.print("  ");
      System.out.print("|   " + event.getToken().getTokenId() + "|");
    }
    System.out.println();

    System.out.print("Time     ");
    for (SimEvent event : this.queue)
    {
      System.out.print("<=");
      System.out.print("|   " + event.getTime() + "|");
    }
    System.out.println();

    System.out.print("Event    ");
    for (SimEvent event : this.queue)
    {
      System.out.print("  ");
      System.out.print("|   " + event.getId() + "|");
    }
    System.out.println();

    System.out.print("Priority ");
    for (SimEvent event : this.queue)
    {
      System.out.print("  ");
      System.out.print("|   " + event.getPriority() + "|");
    }
    System.out.println();

    System.out.print("         ");
    for (int i = 0; i < this.queue.size(); ++i)
    {
      System.out.print("  ");
      System.out.print("+----+");
    }
    System.out.println();
  }

  public final double getInServiceTime()
  {
    // If in service, update total in service time.
    if (this.busy_servers > 0)
    {
      this.totalBusyTime += Sim.time() - this.start_busy_time;
      this.start_busy_time = Sim.time();
    }

    return this.totalBusyTime;
  }

  public final String getName()
  {
    return this.name;
  }

  public final int getPreemptions()
  {
    return this.preemptions;
  }
}
