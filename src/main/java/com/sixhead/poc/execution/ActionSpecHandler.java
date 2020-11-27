package com.sixhead.poc.execution;

public class ActionSpecHandler implements SpecificationHandler<ActionGameEvent> {
  @Override
  public void handle(ActionGameEvent event, Initiator initiator, Executor executor) {
    // TODO: explicitly check class matching

    // TODO: determine output of action based in generic reference
//    action.getClass().getDeclaredMethod("activate")
    Object output = event.getAction().activate();
    executor.setBufferState(output);
  }

  @Override
  public Class<ActionGameEvent> getGameEventType() {
    return ActionGameEvent.class;
  }
}
