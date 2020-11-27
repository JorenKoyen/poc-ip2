package com.sixhead.poc.execution;

public abstract class ParameterizedGameEvent implements GameEvent {
  private final Object[] args;

  protected ParameterizedGameEvent(Object[] args) {
    this.args = args;
  }

  public Object[] getArgs() {
    return args;
  }
}
