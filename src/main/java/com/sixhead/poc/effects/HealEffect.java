package com.sixhead.poc.effects;

import com.sixhead.poc.target.DamageableTarget;

public class HealEffect implements Effect<Integer, DamageableTarget> {
  @Override
  public void activate(Integer input, DamageableTarget target) {
    target.damage(input);
  }
}
