package com.sixhead.poc.execution.handlers;

import com.sixhead.poc.execution.ActionGameEvent;
import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.Initiator;
import com.sixhead.poc.util.Logger;

public class ActionSpecHandler implements SpecificationHandler<ActionGameEvent> {
  private final Logger log = Logger.getLogger("ActionHandler");

  @Override
  public void handle(ActionGameEvent event, Initiator initiator, Executor executor) {
    // TODO: determine output of action based in generic reference
    Object output = event.getAction().activate();
    executor.setBufferState(output);
  }

  @Override
  public Class<ActionGameEvent> getGameEventType() {
    return ActionGameEvent.class;
  }
}
