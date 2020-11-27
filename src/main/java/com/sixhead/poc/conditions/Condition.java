package com.sixhead.poc.conditions;

import com.sixhead.poc.effects.Effect;

public interface Condition {
    boolean meetsCondition(Object value);
    Effect getEffect();
}
