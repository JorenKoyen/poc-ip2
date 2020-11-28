package com.sixhead.poc;

import com.sixhead.poc.execution.Initiator;
import com.sixhead.poc.target.DamageableTarget;

public class Player implements DamageableTarget, Initiator {
  private int hp = 20;

  @Override
  public void damage(int amount) {
    this.hp -= amount;
  }

  @Override
  public void heal(int amount) {
    this.hp  += amount;
  }

  @Override
  public boolean isAlive() {
    return this.hp > 0;
  }

  public int getHp() {
    return hp;
  }
}
