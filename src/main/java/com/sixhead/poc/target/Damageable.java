package com.sixhead.poc.target;

public interface Damageable extends Target {
    void heal(int amount);
    void damage(int amount);
    boolean isDead();
}
