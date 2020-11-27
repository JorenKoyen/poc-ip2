package com.sixhead.poc.conditions;

import com.sixhead.poc.cards.Specification;
import com.sixhead.poc.execution.GameEvent;

import java.util.Collection;

public class ConditionFormula<T> implements Specification {
    private T input;
    private final Collection<Condition<T>> conditions;

    public ConditionFormula(Collection<Condition<T>> conditions) {
        this.conditions = conditions;
    }

    public Collection<GameEvent> decide(T input) {
        for (Condition<T> condition : conditions) {
            if (condition.meetsCondition(input))
                return condition.getEvents();
        }

        return null;
    }
}
