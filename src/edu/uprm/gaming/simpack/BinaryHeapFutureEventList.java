package edu.uprm.gaming.simpack;

import java.util.Queue;

/**
 * Binary heap implementation of future event list (FEL).
 *
 * This implementation is a refactoring based on the original SimPack
 * heap implementation. The {@link java.util.PriorityQueue} based
 * implementation, namely {@link PriorityQueueFutureEventList}, should be
 * preferred.
 *
 * @author Miguel A. Figueroa-Villanueva
 * @deprecated Prefer {@link PriorityQueueFutureEventList}.
 */
@Deprecated
public class BinaryHeapFutureEventList implements FutureEventList
{
  private SimEvent[] futureEventList;
  private int size;

  public BinaryHeapFutureEventList(final int capacity)
  {
    if (capacity <= 0)
    {
      throw new IllegalArgumentException("Invalid capacity: " + capacity);
    }

    // FIXME: This may throw, right?
    this.futureEventList = new SimEvent[capacity];
    this.size = 0;
  }

  public BinaryHeapFutureEventList(final BinaryHeapFutureEventList other)
  {
    this.futureEventList = new SimEvent[other.futureEventList.length];
    this.size = other.size;
    for (int index = 0; index < other.size; ++index)
    {
      this.futureEventList[index] =
        new SimEvent(other.futureEventList[index]);
    }
  }

  @Override
  public final void insert(final SimEvent event)
  {
    if (this.size == this.futureEventList.length)
    {
      throw new IllegalStateException(
        "Queue has exceeded its full capacity limit.");
    }

    ++this.size;
    this.futureEventList[this.size - 1] = new SimEvent(event);
    this.bubbleUp(this.size - 1);
  }

  @Override
  public final SimEvent peek()
  {
    if (this.size == 0)
    {
      return null;
    }

    return this.futureEventList[0];
  }

  @Override
  public final SimEvent poll()
  {
    if (this.size == 0)
    {
      return null;
    }

    final SimEvent event = this.futureEventList[0];
    this.futureEventList[0] = null;
    this.swapNodes(0, this.size - 1);
    --this.size;
    this.bubbleDown(0);

    return event;
  }

  @Override
  public final SimEvent remove(final int tokenId)
  {
    if (this.size == 0)
    {
      return null;
    }

    for (int index = 0; index < this.size; ++index)
    {
      if (this.futureEventList[index].getToken().getTokenId() == tokenId)
      {
        final SimEvent event = this.futureEventList[index];
        this.futureEventList[index] = null;
        this.swapNodes(index, this.size - 1);
        --this.size;

        this.bubbleUp(index);
        this.bubbleDown(index);

        return event;
      }
    }

    return null;
  }

  @Override
  public final void print()
  {
    System.out.println("## BINARY HEAP");
    for (SimEvent event : this.futureEventList)
    {
      System.out.print(event.getTime() + " ");
    }
    System.out.println();
  }

  private void bubbleUp(final int index)
  {
    assert index >= 0 && index < this.size : "Invalid index: " + index;

    int childIndex = index;
    int parentIndex = (index - 1) / 2;
    while (childIndex > 0
        && this.futureEventList[parentIndex].getTime()
        > this.futureEventList[childIndex].getTime())
    {
      this.swapNodes(parentIndex, childIndex);

      childIndex = parentIndex;
      parentIndex = (childIndex - 1) / 2;
    }
  }

  private void bubbleDown(final int index)
  {
    int parentIndex = index;
    int childIndex = 2 * parentIndex + 1;
    while (childIndex < this.size)
    {
      if (childIndex + 1 < this.size
          && this.futureEventList[childIndex + 1].getTime()
          < this.futureEventList[childIndex].getTime())
      {
        ++childIndex;
      }

      if (this.futureEventList[parentIndex].getTime()
          < this.futureEventList[childIndex].getTime())
      {
        break;
      }
      else
      {
        this.swapNodes(parentIndex, childIndex);
        parentIndex = childIndex;
        childIndex = 2 * parentIndex + 1;
      }
    }
  }

  private void swapNodes(final int index1, final int index2)
  {
    final SimEvent temp = this.futureEventList[index1];
    this.futureEventList[index1] = this.futureEventList[index2];
    this.futureEventList[index2] = temp;
  }

  @Override
  public final FutureEventList cloneFutureEventList()
  {
    return new BinaryHeapFutureEventList(this);
  }
  
  /*
   * This method does not work for this Class
   */
  @Override
  public final Queue<SimEvent> getQueue()
  {
    return null;
  }
}
