package com.sixhead.poc.execution;

import com.sixhead.poc.conditions.Condition;

import java.util.Collection;

public class ConditionGameEvent extends ParameterizedGameEvent {
  private final Condition<?>[] conditions;

  public ConditionGameEvent(Condition<?>[] conditions, Object[] params) {
    super(params);
    this.conditions = conditions;
  }

  public Condition<?>[] getConditions() {
    return conditions;
  }
}
