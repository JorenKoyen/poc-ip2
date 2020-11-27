package com.sixhead.poc.execution;

import com.sixhead.poc.util.Logger;

public class ConditionSpecHandler implements SpecificationHandler<ConditionGameEvent> {
  private final Logger log = Logger.getLogger("ConditionHandler");

  @Override
  public void handle(ConditionGameEvent event, Initiator initiator, Executor executor) {
    // TODO: check if first input matches type requested by condition
    var c = event.getConditions()[0];
    log.info(c.getClass().getSimpleName());
    log.info(event.getArgs()[0].getClass().getSimpleName());

    log.info(executor.getLastState().toString());
//    ConditionFormula<?> formula = (ConditionFormula<?>) event.getSpecification();
//    var decidedEvents = formula.decide(event.getInputs()[0])
  }

  @Override
  public Class<ConditionGameEvent> getGameEventType() {
    return ConditionGameEvent.class;
  }
}
