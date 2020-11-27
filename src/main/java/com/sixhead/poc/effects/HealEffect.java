package com.sixhead.poc.effects;

import com.sixhead.poc.target.Damageable;
import com.sixhead.poc.target.Target;

public class HealEffect extends TargetedEffect {

    public HealEffect(Target target) {
        super(target);
    }

    @Override
    public void activate(Object input) {
        // check if target is damageable
        if (!(target instanceof Damageable)) {
            throw new RuntimeException("needs a damageable target");
        }

        // check input is integer
        if (!(input instanceof Integer)) {
            throw new RuntimeException("expected integer as input");
        }

        // do effect
        ((Damageable) target).heal((Integer) input);
    }

}
