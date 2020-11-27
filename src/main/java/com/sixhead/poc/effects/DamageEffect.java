package com.sixhead.poc.effects;

import com.sixhead.poc.target.DamageableTarget;

public class DamageEffect implements Effect<Integer, DamageableTarget> {
  @Override
  public void activate(Integer input, DamageableTarget target) {
    target.damage(input);
  }
}
