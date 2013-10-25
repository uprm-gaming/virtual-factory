package edu.uprm.gaming.simpack;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueue implementation of future event list (FEL).
 *
 * @author Miguel A. Figueroa-Villanueva
 */
public class PriorityQueueFutureEventList implements FutureEventList
{
  private final Queue<SimEvent> futureEventList;

  public PriorityQueueFutureEventList(final int initialCapacity)
  {
    this.futureEventList = new PriorityQueue<SimEvent>(initialCapacity,
      new Comparator<SimEvent>()
      {
        @Override
        public int compare(final SimEvent a, final SimEvent b)
        {
          if (a.getTime() > b.getTime())
          { return  1; }
          else if (a.getTime() < b.getTime())
          { return -1; }
          else
          { return  0; }
        }
      }
    );
  }

  public PriorityQueueFutureEventList(
      final PriorityQueueFutureEventList other)
  {
    this(other.futureEventList.size());
    for (SimEvent event : other.futureEventList)
    {
      this.futureEventList.add(new SimEvent(event));
    }
  }

  @Override
  public final void insert(final SimEvent event)
  {
    this.futureEventList.add(event);
  }

  @Override
  public final SimEvent peek()
  {
    return this.futureEventList.peek();
  }

  @Override
  public final SimEvent poll()
  {
    return this.futureEventList.poll();
  }

  @Override
  public final SimEvent remove(final int tokenId)
  {
    final Iterator<SimEvent> iterator = this.futureEventList.iterator();
    while (iterator.hasNext())
    {
      final SimEvent next = iterator.next();
      if (next.getToken().getTokenId() == tokenId)
      {
        iterator.remove();
        return next;
      }
    }

    return null;
  }

  @Override
  public final void print()
  {
    System.out.println("## PRIORITY QUEUE");
    for (SimEvent event : this.futureEventList)
    {
      System.out.print(event.getTime() + " ");
    }
    System.out.println();
  }

  @Override
  public final FutureEventList cloneFutureEventList()
  {
    return new PriorityQueueFutureEventList(this);
  }
  
  @Override
  public final Queue<SimEvent> getQueue()
  {
    return this.futureEventList;
  }
}
