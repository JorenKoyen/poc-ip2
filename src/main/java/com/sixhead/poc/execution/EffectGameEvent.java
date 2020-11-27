package com.sixhead.poc.execution;

import com.sixhead.poc.effects.Effect;
import com.sixhead.poc.target.TargetReference;

public class EffectGameEvent extends ParameterizedGameEvent {
  private final Effect<?, ?> effect;
  private final TargetReference reference;

  public EffectGameEvent(Object[] args, Effect<?, ?> effect, TargetReference reference) {
    super(args);
    this.effect = effect;
    this.reference = reference;
  }

  public Effect<?, ?> getEffect() {
    return effect;
  }

  public TargetReference getReference() {
    return reference;
  }
}
