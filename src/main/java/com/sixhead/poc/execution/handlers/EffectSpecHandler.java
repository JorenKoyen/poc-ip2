package com.sixhead.poc.execution.handlers;

import com.sixhead.poc.Player;
import com.sixhead.poc.effects.Effect;
import com.sixhead.poc.execution.EffectGameEvent;
import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.Initiator;
import com.sixhead.poc.execution.extractors.ArgumentExtractor;
import com.sixhead.poc.util.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EffectSpecHandler implements SpecificationHandler<EffectGameEvent> {
  private final Logger log = Logger.getLogger("EffectHandler");

  @Override
  public void handle(EffectGameEvent event, Initiator initiator, Executor executor) {
    log.info("applying effect '%s'", event.getEffect().getClass().getSimpleName());

    // retrieve input definition
    var argDefinition = event.getArgs();
    var inputs = new ArgumentExtractor(executor).extractInputValues(argDefinition);

    // extract 'activate()' method
    Method activate = this.extractActivateMethod(event.getEffect());
    if (activate == null)
      throw new RuntimeException("unable to retrieve activate method from effect");

    // compare expected type
    Class<?> inputType = inputs[0].getClass();
    if (inputType != activate.getParameterTypes()[0])
      throw new RuntimeException("unable to activate effect, incorrect input types");

    // TODO: resolve target based on event input
    Player player = (Player) initiator;

    try {
      // invoke action with extracted arguments
      activate.invoke(event.getEffect(), inputs[0], player);
    } catch (IllegalAccessException | InvocationTargetException e) {
      log.error("something went wrong during the activation of an effect");
    }

  }

  @Override
  public Class<EffectGameEvent> getGameEventType() {
    return EffectGameEvent.class;
  }

  private Method extractActivateMethod(Effect<?, ?> effect) {
    for (Method m : effect.getClass().getDeclaredMethods()) {
      if (m.isBridge()) continue; // skip bridged methods
      if (!m.getName().equals("activate")) continue; // skip wrong named method

      return m;
    }

    return null;
  }
}
