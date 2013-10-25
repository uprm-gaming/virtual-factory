package edu.uprm.gaming.simpack;

import java.util.ArrayList;

/**
 * Class that holds the state of the simulation at any given point.
 *
 * <p>The class is designed to be immutable, so that the state cannot be
 * changed after it is created with a snapshot in time.
 */
public class STATE
{
  /**
   * Virtual clock time of the simulation.
   */
  private final double time;

  /**
   * The future event list (FEL).
   */
  private final FutureEventList futureEventList;

  /**
   * List of facilities created in the simulation.
   */
  private final ArrayList<FACILITY> facilities;

  public STATE(final double time,
               final FutureEventList fel,
               final ArrayList<FACILITY> facilities)
  {
    this.time = time;
    this.futureEventList = fel.cloneFutureEventList();

    this.facilities = new ArrayList<FACILITY>();
    for (FACILITY f : facilities)
    {
      this.facilities.add(new FACILITY(f));
    }
  }

  /**
   * Print out the entire state.
   *
   * <p>Useful for debugging and learning how the simulation proceeds at
   * each step.
   */
  public final void print()
  {
    System.out.println("## TIME: " + this.time);
    System.out.println();

    this.futureEventList.print();

    // FIXME: Need to remove index; better to call f.print().
    int i = 0;
    for (FACILITY f : this.facilities)
    {
      f.print(++i);
    }
  }
}
