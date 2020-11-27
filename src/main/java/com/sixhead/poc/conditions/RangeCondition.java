package com.sixhead.poc.conditions;

import com.sixhead.poc.effects.Effect;

public class RangeCondition implements Condition {
    private final int lower;
    private final int upper;
    private final Effect effect;

    public RangeCondition(int lower, int upper, Effect effect) {
        this.lower = lower;
        this.upper = upper;
        this.effect = effect;
    }

    @Override
    public boolean meetsCondition(Object value) {
        // check if value is integer
        if (!(value instanceof Integer)) {
            throw new RuntimeException("unable to check range for non integer");
        }

        // parse to integer
        int i = (Integer)value;

        // check range parameters
        return i >= lower && i <= upper;
    }

    @Override
    public Effect getEffect() {
        return this.effect;
    }
}
