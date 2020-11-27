package com.sixhead.poc;

import com.sixhead.poc.target.Damageable;

public class Player implements Damageable {
    private int hp;

    @Override
    public void heal(int amount) {
        this.hp += amount;
    }

    @Override
    public void damage(int amount) {
        this.hp -= amount;
    }

    @Override
    public boolean isDead() {
        return this.hp <= 0;
    }
}
