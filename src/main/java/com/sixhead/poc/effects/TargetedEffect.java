package com.sixhead.poc.effects;

import com.sixhead.poc.target.Target;

public abstract class TargetedEffect implements Effect {
    protected final Target target;

    public TargetedEffect(Target target) {
        this.target = target;
    }


    public Target getTarget() {
        return target;
    }


}
