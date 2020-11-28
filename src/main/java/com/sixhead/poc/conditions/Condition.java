package com.sixhead.poc.conditions;

import com.sixhead.poc.cards.Specification;
import com.sixhead.poc.execution.GameEvent;

import java.util.Collection;

public interface Condition<IN> {
    boolean meetsCondition(IN value);
    Collection<GameEvent> getEvents();
    Class<IN> getInputClass();
}
