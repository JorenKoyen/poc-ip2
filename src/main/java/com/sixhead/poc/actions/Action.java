package com.sixhead.poc.actions;

import com.sixhead.poc.cards.Specification;

public interface Action<OUT> extends Specification {
    OUT activate();
}
