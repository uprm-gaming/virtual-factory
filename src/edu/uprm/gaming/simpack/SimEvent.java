package edu.uprm.gaming.simpack;

import edu.uprm.gaming.strategy.EventStrategy;

public class SimEvent
{
  private double time;
  private int id;
  private int priority;
  private TOKEN token;
  private EventStrategy eventGaming;

    public final EventStrategy getEventGaming() {
        return eventGaming;
    }

    public SimEvent(final double time,
                  final int id)
  {
    this.time = time;
    this.id = id;
    this.priority = -1;
    this.token = new TOKEN();
  }
    
  public SimEvent(final double time,
                  final int id,
                  final EventStrategy eventGame)
  {
    this.time = time;
    this.id = id;
    this.priority = -1;
    this.eventGaming = eventGame;
    this.token = new TOKEN();
  }
  
  public SimEvent(final double time,
                  final int id,
                  final int priority,
                  final TOKEN token)
  {
    this.time = time;
    this.id = id;
    this.priority = priority;
    this.token = new TOKEN(token);
  }

  public SimEvent(final SimEvent other)
  {
    this.time = other.time;
    this.id = other.id;
    this.token = new TOKEN(other.token);
    this.priority = other.priority;
  }

  public final double getTime()
  {
    return this.time;
  }
  
  public final void setTime(double time)
  {
      this.time = time;
  }

  public final int getId()
  {
    return this.id;
  }

  public final TOKEN getToken()
  {
    return this.token;
  }

  public final int getPriority()
  {
    return this.priority;
  }
}