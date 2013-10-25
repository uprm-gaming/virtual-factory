package edu.uprm.gaming.simpack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
    import java.util.Queue;

/**
 * LinkedList implementation of future event list (FEL).
 *
 * @author Miguel A. Figueroa-Villanueva
 */
public class LinkedListFutureEventList implements FutureEventList
{
  private final Queue<SimEvent> futureEventList;

  public LinkedListFutureEventList()
  {
    this.futureEventList = new LinkedList<SimEvent>();
  }

  public LinkedListFutureEventList(final LinkedListFutureEventList other)
  {
    this.futureEventList = new LinkedList<SimEvent>();
    for (SimEvent event : other.futureEventList)
    {
      this.futureEventList.add(new SimEvent(event));
    }
  }

  @Override
  public final void insert(final SimEvent event)
  {
    final ListIterator<SimEvent> iterator =
      ((LinkedList<SimEvent>) this.futureEventList).listIterator();
    while (iterator.hasNext())
    {
      final SimEvent next = iterator.next();
      if (event.getTime() <= next.getTime())
      {
        if (iterator.hasPrevious())
        {
          iterator.previous();
        }
        break;
      }
    }
    // FIXME: When SimEvent is changed to immutable, then no need for new.
    iterator.add(new SimEvent(event));
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
    System.out.println("## EVENT LIST");
    System.out.print("      ");
    for (int counter = 0; counter < this.futureEventList.size(); ++counter)
    {
      System.out.print("  ");
      System.out.print("+----+");
    }
    System.out.println();

    System.out.print("Token ");
    for (SimEvent event : this.futureEventList)
    {
      System.out.print("  ");
      System.out.print("|   " + event.getToken().getTokenId() + "|");
    }
    System.out.println();

    System.out.print("Time  ");
    for (SimEvent event : this.futureEventList)
    {
      System.out.print("<=");
      System.out.print("|   " + event.getTime() + "|");
    }
    System.out.println();

    System.out.print("Event ");
    for (SimEvent event : this.futureEventList)
    {
      System.out.print("  ");
      System.out.print("|   " + event.getId() + "|");
    }
    System.out.println();

    System.out.print("      ");
    for (int counter = 0; counter < this.futureEventList.size(); ++counter)
    {
      System.out.print("  ");
      System.out.print("+----+");
    }
    System.out.println();
  }

  @Override
  public final FutureEventList cloneFutureEventList()
  {
    return new LinkedListFutureEventList(this);
  }
  
  @Override
  public final Queue<SimEvent> getQueue()
  {
    return this.futureEventList;
  }
}
