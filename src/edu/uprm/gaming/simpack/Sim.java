package edu.uprm.gaming.simpack;

import java.util.*;

/**
 * Main SimPack class.
 * 
 * This class is divided in:
 * <ul>
 * <li><b>Event Scheduling Methods.</b> Core of SimPackJ, and deal with
 * managing the future event list.
 * <li><b>Resource Allocation Methods.</b> Used to deal with queuing for a
 * resource or server.
 * <li><b>Simulation State and Tracing.</b> Used to get state of FEL and
 * facilities, or print their contents.
 * <li><b>Random Number Generators.</b> Create random numbers for different
 * probability density functions.
 * </ul>
 * 
 * Known Bugs and Improvements:
 * <ul>
 * <li>Allow priority to be used in Sim.schedule() so that tokens with the
 * same time are ordered by priority, with higher priority tokens appearing
 * before others with the same time.
 * </ul>
 */
public class Sim
{
  /** Type that specifies the simulation mode. */
  public enum Mode
  { SYNC, ASYNC }

  private static List<Integer> current_event_id_list = new ArrayList<Integer>();
  private static double current_time,last_event_time;
  private static double total_token_time;
  private static int current_event_id,arrivals,completions;
  private static int tokens_in_system;
  private static FutureEventList futureEventList;
  private static ArrayList<FACILITY> facility;

    public static FutureEventList getFutureEventList() {
        return futureEventList;
    }

    public static void setFutureEventList(FutureEventList futureEventList) {
        Sim.futureEventList = futureEventList;
    }

  /**
   * Initialize a discrete event simulation.
   * 
   * Initializes the discrete event simulation, and so should be placed
   * at the top of the code. A good place is in "setup()", which is
   * executed once before other code. 
   * 
   * @param set_time The set time for the simulation's virtual clock.
   *                 This is usually set to zero, but for synchronize
   *                 operation with the physical clock, it is usually set
   *                 to the current time. In Processing, this time is set
   *                 as "minute()*60+second()" or even
   *                 "hour()*3600 + minute()*60 + second()" if the
   *                 simulation is being executed near the top of the
   *                 hour.
   * 
   * @param flags The flag choices are "Const.LINKED" or "Const.HEAP".
   *              They refer to the data structure to be used for the
   *              future event list (FEL). In most cases, the HEAP
   *              structure is the most efficient, except perhaps for
   *              very short FELs, where few events are scheduled at any
   *              one time. The event times within the FEL are absolute
   *              virtual times when events are to occur.
   */
  public static void init(double set_time, final FutureEventList fel)
  {
    Sim.facility = new ArrayList<FACILITY>();

    Sim.futureEventList = fel;
    current_time = set_time;
    last_event_time = current_time;

    arrivals = 0;
    completions = 0;

    total_token_time = 0;
    tokens_in_system = 0;
  }

  /**
   * Schedule an event to occur in the future.
   * 
   * <p>This inserts the event within the future event list (FEL) according
   * to increasing time for each event, so events with smaller times are
   * placed toward the head of the list since they will be processed
   * first. The FEL times are absolute virtual times when events are to
   * occur in the simulation.
   * 
   * @param event The event to be added to the FEL.
   * @param interTime Amount of time in the future, in which to schedule
   *        the event.
   */
  public static void schedule(final SimEvent event)
  {
    // FIXME: Removed mechanism that checks whether the event was
    //        already scheduled. This responsibility is now delegated
    //        to the caller (i.e., don't try to schedule the same event
    //        twice). If it is needed the mechanism can be incorporated
    //        back through a Map or HashTable.

    Sim.insertEventToFEL(event);
  }

  /**
   * Inserts an event into the priority queue ordered by time.
   *
   * @param anEvent SimEvent to be added to the queue.
   */
  private static void insertEventToFEL(final SimEvent anEvent)
  {
    Sim.futureEventList.insert(anEvent);
  }

  /**
   * Removes the event identified by token from the FEL.
   *
   * @param tokenId the identifier of the event to be removed.
   * @return The event removed if found; null otherwise.
   */
  public static SimEvent removeEventFromFEL(final int tokenId)
  {
    return Sim.futureEventList.remove(tokenId);
  }

  /**
   * Removes the next event in the FEL.
   *
   * @return The event removed if FEL is not empty; null otherwise.
   */
  private static SimEvent removeFrontElementInFEL()
  {
    return Sim.futureEventList.poll();
  }

  /**
   * Create a new facility, that will develop a service queue behind it.
   * 
   * A facility is a shared resource where tokens vie for service. For
   * example, a facility can be a row of cashiers, a door (for entering
   * and exiting a room), or a machine that accepts raw parts for
   * processing. A facility is a container, or collection, of servers
   * greater than or equal to 1.
   *
   * There is only one queue per facility to be shared among the number
   * of servers.
   * 
   * @param name a meaningful identifier for the facility.
   * @param numberOfServers the number of servers inside this facility.
   */
  public static void create_facility(final String name,
                                     final int numberOfServers)
  {
    if (numberOfServers < 1)
    {
      throw new IllegalArgumentException(
        "Invalid number of servers: " + numberOfServers);
    }
    
    Sim.facility.add(new FACILITY(name, numberOfServers));
  }

  /**
   * Take the next events with duplicate times from the future event list.
   * 
   * This operates similarly to next_event except that a list is returned.
   * This list is a list of all events of the same priority level that have
   * duplicate (i.e., identical) times from the FEL. The order of
   * activities for this function is:
   * <ol>
   * <li>Check to see if the list, the FEL, is empty. If the condition is
   * true, this function returns a list containing the  abnormal event with
   * -1 for time, id, and priority.
   * <li>Check the event list until the event list is not empty and exit
   * condition is false.
   *   <ol>
   *   <li>Read an event of the event list. If failed, return a list
   *   containing the abnormal event.
   *   <li>If the event is the first one of the event list, save the event
   *   time (compared_time).
   *   <li>Compare the event time of the current list with the value of
   *   "compared_time". If the two values are same OR the event is the
   *   first one of the event list, then:
   *     <ol>
   *     <li>Check to see whether there is to be synchronization with the
   *     physical clock and the virtual time is greater than the physical
   *     time. If the condition is true, this function returns a list
   *     containing the abnormal event. Otherwise, remove the event from
   *     the event list and add to the returning list (return_list).
   *     <li>Repeat Step 2. If the two values are different, set exit
   *     condition true.
   *     </ol>
   *   </ol>
   * <li>Return the returning list (return_list) containing events.
   * </ol>
   * The FEL times are absolute virtual times when events are to occur in
   * the simulation. FEL events are each composed of:
   * <ol>
   * <li>time for the event to occur,
   * <li>the id of the event to occur, 
   * <li>the token (which stores attribute data and information for this
   * event), and
   * <li>the priority of this token. Higher priority tokens get scheduled
   * before lower priority ones in the FEL.
   * </ol>
   *  
   * @param ptime This is the physical clock time passed to next_event_dup.
   *        For Processing, this is something like minute()*60+second().
   *        ptime is relevant only when mode = Sim.Mode.SYNC.
   * @param mode Either Sim.Mode.ASYNC or Sim.Mode.SYNC. Asynchronous simulation
   *        refers to the virtual clock time being advanced as fast as
   *        events are removed from the FEL, as the the speed of the
   *        computer will permit. There is no waiting. Synchronous
   *        simulation is where the virtual clock and physical clock are
   *        synchronized. This is valuable within Processing so that
   *        audiovisual events may be synchronized to occur at specific
   *        "real" times. In Sim.Mode.ASYNC  mode, the virtual clock time is
   *        whatever it happens to be when an item is removed from the FEL.
   *        Then the virtual time is set to the token time. In Sim.Mode.SYNC
   *        mode, the virtual time is taken from the event time, for the
   *        event at the head of the list, and compared with ptime. If the
   *        virtual time is greater than ptime, next_event_dup does not remove
   *        anything from the FEL, and returns a list containing the
   *        abnormal event.
   * @return This is the returned list containing all events of the same
   *         priority level that have duplicate (i.e., identical) times.
   */
  public static List<SimEvent> next_event_dup(double ptime, Mode mode)
  {
    final List<SimEvent> returnList = new ArrayList<SimEvent>();

    final SimEvent anEvent = Sim.futureEventList.peek();
    if (anEvent == null)
    {
      // FEL is empty.
      return null;
    }
    final double vtime = anEvent.getTime();

    if (mode == Sim.Mode.SYNC && ptime < vtime)
    {
      return null;
    }

    boolean exitLoop = false;
    while (!exitLoop)
    {
      // This should always succeed; we already verified the FEL is not empty.
      final SimEvent event = Sim.futureEventList.poll();

      Sim.updateClock(event);
      Sim.current_event_id_list.add(new Integer(event.getId()));
      returnList.add(event);

      final SimEvent next = Sim.futureEventList.peek();
      if (next == null || vtime != next.getTime())
      {
        exitLoop = true;
      }
    }

    Sim.updateTokensTime();

    return returnList;
  }

  /**
   * Take the next event from the future event list.
   * 
   * Whereas Sim.schedule inserts events into the FEL, next_event removes
   * the head of the list. Within the same priority level, this means the
   * smallest time value for a token. The FEL times are absolute virtual
   * times when events are to occur in the simulation. FEL events are each
   * composed of:
   * <ol>
   * <li>time for the event to occur,
   * <li>the id of the event to occur,
   * <li>the token (which stores attribute data and information for this
   * event), and
   * <li>the priority of this token. Higher priority tokens get scheduled
   * before lower priority ones in the FEL.
   * </ol>
   * The order of activities for this function is:
   * <ol>
   * <li>Check to see if:
   *   <ul>
   *   <li>the list is empty, or
   *   <li>whether there is to be synchronization with the physical clock
   *   and the virtual time is greater than the physical time.
   *   </ul>
   *   If either of these conditions is true, this function returns an
   *   abnormal event with -1 for time, id, and priority. Otherwise, it
   *   returns the normal event object.
   * <li>If an normal event is to be returned, the virtual clock time is
   * set to the time of the event which has been removed from the head of
   * the FEL.
   * </ol>
   * 
   * @param ptime This is the physical clock time passed to next_event.
   *        For Processing, this is something like minute()*60+second().
   *        ptime is relevant only when mode = Sim.Mode.SYNC.
   * @param mode Either Sim.Mode.ASYNC or Sim.Mode.SYNC. Asynchronous simulation
   *        refers to the virtual clock time being advanced as fast as
   *        events are removed from the FEL, as the the speed of the
   *        computer will permit. There is no waiting. Synchronous
   *        simulation is where the virtual clock and physical clock are
   *        synchronized. This is valuable within Processing so that
   *        audiovisual events may be synchronized to occur at specific
   *        "real" times. In Sim.Mode.ASYNC mode, the virtual clock time is
   *        whatever it happens to be when an item is removed from the FEL.
   *        Then the virtual time is set to the token time. In Sim.Mode.SYNC
   *        mode, the virtual time is taken from the token time, for the
   *        token at the head of the list, and compared with ptime.
   *        If the virtual time is greater than ptime, next_event does not
   *        remove anything from the FEL, and returns a -1. 
   * @return This value will either be an abnormal event, which is defined
   *         above, or an normal event that requires processing. 
   */
  public static SimEvent next_event(double ptime, Mode mode)
  {
    SimEvent anEvent = Sim.futureEventList.peek();
    if (anEvent == null)
    {
      // FEL is empty.
      return null;
    }
    final double vtime = anEvent.getTime();
    //System.out.println("pTime: " + ptime + ", vtime: " + vtime);
    if (mode == Sim.Mode.ASYNC || (mode == Sim.Mode.SYNC && ptime >= vtime))
    {
      // This should always succeed; we already verified the FEL is not empty.
      Sim.futureEventList.poll();
      Sim.updateClock(anEvent);
      Sim.current_event_id = anEvent.getId();
      Sim.updateTokensTime();
    }
    else
    {
      anEvent = null;
    }

    return anEvent;
  }

  /**
   * Updates the times of the discrete event simulation machine.
   */
  private static void updateTokensTime()
  {
    Sim.total_token_time
      += Sim.tokens_in_system * (Sim.time() - Sim.last_event_time);
    Sim.last_event_time = Sim.time();
  }

  /**
   * Updates the times of the discrete event simulation machine.
   *
   * @param anEvent the current event being processed.
   */
  private static void updateClock(final SimEvent anEvent)
  {
    Sim.current_time = anEvent.getTime();
  }

  /**
   * Request service from a facility.
   * 
   * <p>An incoming token requests a server in the facility.
   * 
   * @param facilityId the identifier for the facility.
   * @param token the token that is requesting service.
   * @param priority the priority level for this token.
   * @return True if the request was granted; false otherwise (e.g., if it
   * was queued).
   *
   * @see FACILITY#request(TOKEN, int)
   */
  public static boolean request(final int facilityId,
                                final TOKEN token,
                                final int priority)
  {
    return Sim.facility.get(facilityId).request(token, priority);
  }

  /**
   * Preempt a token being serviced to tend to this one.
   * 
   * @param facilityId the identifier for the facility.
   * @param token the token that is requesting service.
   * @param priority the priority level for this token.
   * @return True if the preemption was granted; false otherwise (e.g., if
   * it was queued).
   *
   * @see FACILITY#preempt(TOKEN, int)
   */
  public static boolean preempt(final int facilityId,
                                final TOKEN token,
                                final int priority)
  {
    return Sim.facility.get(facilityId).preempt(token, priority);
  }

  /**
   * Release the server in the facility that is tending this token.
   * 
   * @param facilityId the identifier for the facility.
   * @param token the token that is releasing the facility.
   *
   * @see FACILITY#release(TOKEN)
   */
  public static void release(final int facilityId, final TOKEN token)
  {
    Sim.facility.get(facilityId).release(token);
  }

  /**
   * Request the queue size from a facility.
   * 
   * @param facilityId The identifier for the facility.
   * @return The size of the queue of the specified facility.
   */
  public static int getFacilityQueueSize(final int facilityId)
  {
    return Sim.facility.get(facilityId).getQueueSize();
  }

  /**
   * Returns the id of the event that is currently currently processed.
   * 
   * @return The id of the event that is currently currently processed.
   */
  public static int getCurrentEventId()
  {
    return Sim.current_event_id;
  }

  /**
   * Returns the simulation state at invocation time.
   *
   * @return The simulation state at invocation time.
   *
   * @see STATE
   */
  public final STATE state()
  {
    return new STATE(Sim.time(), Sim.futureEventList, Sim.facility);
  } 

  public static double time()
  {
    return(current_time);
  }

  public static void update_arrivals() {
    arrivals++;
    tokens_in_system++;
  }

  public static void update_completions() {
    completions++;
    tokens_in_system--;
  }

  /**
   * Report usage statistics for the modeled facilities and servers.
   */
  public static void report_stats()
  {
    System.out.println("+-----------------------------+");
    System.out.println("| SimPackJS SIMULATION REPORT |");
    System.out.println("+-----------------------------+");

    if (completions == 0) completions = 1;
    double total_sim_time = time();
    double total_busy_time = 0.0;
    for (FACILITY f : Sim.facility)
    {
      total_busy_time += f.getInServiceTime();
    }
    total_busy_time /= Sim.facility.size();
    double total_utilization = total_busy_time / total_sim_time;
    double arrival_rate = Sim.arrivals / total_sim_time;
    double throughput = Sim.completions / total_sim_time;
    double mean_service_time = total_busy_time / completions;
    double mean_num_tokens = total_token_time/total_sim_time;
    double mean_residence_time = mean_num_tokens / throughput;

    System.out.println("Total Simulation Time: "+total_sim_time);
    System.out.println("Total System Arrivals: "+arrivals);
    System.out.println("Total System Completions: "+completions);

    System.out.println("System Wide Statistics");
    System.out.println("----------------------");
    System.out.println("System Utilization: "+100.0*total_utilization);
    System.out.println("Arrival Rate: "+arrival_rate+"  Throughput: "+throughput);
    System.out.println("Mean Service Time per Token: "+mean_service_time);
    System.out.println("Mean # of Tokens in System: "+mean_num_tokens);
    System.out.println("Mean Residence Time for each Token: "+mean_residence_time);

    System.out.println("Facility Statistics");
    System.out.println("-------------------");
    int i = 1;
    for (FACILITY f : Sim.facility)
    {
      final double utilization = 100.0 * f.getInServiceTime() / Sim.time();
      final double idle =
        100.0 * (Sim.time() - f.getInServiceTime()) / Sim.time();
      System.out.println("F " + i + " (" + f.getName()
                         + ") : Idle: " + idle
                         + "%, Util: " + utilization
                         + "%, Preemptions: " + f.getPreemptions());
      ++i;
    }
  }
}
