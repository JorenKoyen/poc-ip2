package com.sixhead.poc.execution.handlers;

import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.GameEvent;
import com.sixhead.poc.execution.Initiator;

public interface SpecificationHandler<T extends GameEvent> {
  void handle(T event, Initiator initiator, Executor executor);
  Class<T> getGameEventType();
}
