package com.sixhead.poc.execution;

import com.sixhead.poc.actions.Action;

public class ActionGameEvent implements GameEvent {
  private final Action<?> action;

  public ActionGameEvent(Action<?> action) {
    this.action = action;
  }

  public Action<?> getAction() {
    return action;
  }
}
