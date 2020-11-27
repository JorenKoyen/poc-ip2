package com.sixhead.poc.execution;

public interface SpecificationHandler<T extends GameEvent> {
  void handle(T event, Initiator initiator, Executor executor);
  Class<T> getGameEventType();
}
