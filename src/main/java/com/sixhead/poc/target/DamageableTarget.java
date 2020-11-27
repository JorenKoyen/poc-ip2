package com.sixhead.poc.target;

public interface DamageableTarget extends Target {
  void damage(int amount);
  void heal(int amount);
  boolean isAlive();
}
