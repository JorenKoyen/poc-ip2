package com.sixhead.poc.execution.handlers;

import com.sixhead.poc.conditions.Condition;
import com.sixhead.poc.execution.*;
import com.sixhead.poc.execution.extractors.Extractor;
import com.sixhead.poc.util.Logger;

import java.util.Collection;

public class ConditionSpecHandler implements SpecificationHandler<ConditionGameEvent> {
  private final Logger log = Logger.getLogger("ConditionHandler");

  @Override
  public void handle(ConditionGameEvent event, Initiator initiator, Executor executor) {
    // retrieve input definition
    var argDefinition = event.getArgs();
    var inputs = new Extractor(executor).extractInputValues(argDefinition);

    // compare first input with expected condition input
    Class<?> inputType = inputs[0].getClass();
    Class<?> argType = this.extractConditionInputType(event.getConditions());
    if (inputType != argType) throw new RuntimeException("unable resolve condition with received input");

    // resolve condition
    @SuppressWarnings("unchecked") // inputs and condition should match because checked above
    var events = this.decide(inputs[0], (Condition<Object>[]) event.getConditions());
    if (events == null) throw new RuntimeException("unable to resolve condition to receive new events");

    // insert events in queue (in front of the other registered events)
    log.info("enqueueing %d event(s) for follow up execution", events.size());
    executor.enqueue(events, initiator, true);
  }

  @Override
  public Class<ConditionGameEvent> getGameEventType() {
    return ConditionGameEvent.class;
  }

  private Class<?> extractConditionInputType(Condition<?>[] conditions) {
    Class<?> type = null;

    for (Condition<?> condition : conditions) {
      var inputType = condition.getInputClass();

      // set value of type
      if (type == null) {
        type = inputType;
        continue;
      }

      // compare values
      if (type != inputType)
        throw new RuntimeException("input types do not match on conditions");
    }

    return type;
  }
  private <T> Collection<GameEvent> decide(T input, Condition<T>[] conditions) {
    for (Condition<T> c : conditions) {
      if (c.meetsCondition(input))
        return c.getEvents();
    }

    return null;
  }
}
