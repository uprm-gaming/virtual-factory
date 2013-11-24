package com.virtualfactory.simpack;

public class TOKEN
{
  public static final int MAX_NUM_ATTR = 10;

  private final double[] attr;

  public TOKEN()
  {
    this.attr = new double[TOKEN.MAX_NUM_ATTR];
  }

  public TOKEN(final TOKEN other)
  {
    // WARNING: This is a shallow copy and has independent storage only if
    // it is a primitive one-dimensional array.
    this.attr = other.attr.clone();
  }

  public final int getTokenId()
  {
    return (int) this.attr[0];
  }

  // FIXME: Temporary change until this is pushed to an application
  //        dependent sub-class.
  public final double getAttribute(final int index)
  {
    if (index < 0 || index >= TOKEN.MAX_NUM_ATTR)
    {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    return this.attr[index];
  }

  // FIXME: Temporary change until this is pushed to an application
  //        dependent sub-class.
  public final void setAttribute(final int index, final double value)
  {
    if (index < 0 || index >= TOKEN.MAX_NUM_ATTR)
    {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    this.attr[index] = value;
  }
}
