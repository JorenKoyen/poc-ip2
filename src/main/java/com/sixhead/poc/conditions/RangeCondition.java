package com.sixhead.poc.conditions;

import com.sixhead.poc.cards.Specification;
import com.sixhead.poc.execution.GameEvent;

import java.util.Collection;

public class RangeCondition extends BaseCondition<Integer> {
  private final int lower;
  private final int upper;

  public RangeCondition(int lower, int upper, Collection<GameEvent> events) {
    super(events);
    this.lower = lower;
    this.upper = upper;
  }


  @Override
  public boolean meetsCondition(Integer value) {
    return value <= upper && value >= lower;
  }

  @Override
  public Class<Integer> getInputClass() {
    return Integer.class;
  }

}
