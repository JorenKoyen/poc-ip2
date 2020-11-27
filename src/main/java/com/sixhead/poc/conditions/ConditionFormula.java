package com.sixhead.poc.conditions;

import com.sixhead.poc.effects.Effect;
import com.sixhead.poc.Specification;

import java.util.Collection;

public class ConditionFormula implements Specification {
    private final Collection<Condition> conditions;

    public ConditionFormula(Collection<Condition> conditions) {
        this.conditions = conditions;
    }

    public Effect decide(Object input) {
        for (Condition condition : conditions) {
            if (condition.meetsCondition(input))
                return condition.getEffect();
        }

        return null;
    }
}
