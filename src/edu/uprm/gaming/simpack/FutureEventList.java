package edu.uprm.gaming.simpack;

import java.util.Queue;

/**
 * Future event list (FEL).
 *
 * @author Miguel A. Figueroa-Villanueva
 */
public interface FutureEventList
{
  /**
   * Insert event into the FEL sorted by time.
   *
   * @param event The event to insert into the FEL.
   */
  void insert(SimEvent event);

  /**
   * Retrieves, but does not remove, the head of this queue.
   *
   * @return The retrieved event; null if FEL is empty.
   */
  SimEvent peek();

  /**
   * Retrieves and removes the head of this queue.
   *
   * @return The retrieved event; null if FEL is empty.
   */
  SimEvent poll();

  /**
   * Remove the event with the given token id.
   *
   * @param tokenId The id of the token to be removed.
   * @return The event removed; otherwise null.
   */
  SimEvent remove(final int tokenId);

  /**
   * Print the FEL (not necessarily in order).
   *
   * FIXME: Clarify comment.
   */
  void print();

  /**
   * Returns a cloned copy of the FEL with independent storage.
   *
   * <p>Not using cloneable interface because of its ill-natured design.
   *
   * @return The cloned FEL.
   */
  FutureEventList cloneFutureEventList();
  
  /*
   * Returns the principal Queue
   */
  Queue<SimEvent> getQueue();
}
