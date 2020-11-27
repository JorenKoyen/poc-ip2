package com.sixhead.poc.conditions;

import com.sixhead.poc.cards.Specification;
import com.sixhead.poc.execution.GameEvent;

import java.util.Collection;

public abstract class BaseCondition<T> implements Condition<T> {
  protected final Collection<GameEvent> events;

  public BaseCondition(Collection<GameEvent> events) {
    this.events = events;
  }

  @Override
  public Collection<GameEvent> getEvents() {
    return events;
  }
}
