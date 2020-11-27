package com.sixhead.poc.effects;

import com.sixhead.poc.cards.Specification;
import com.sixhead.poc.target.Target;

public interface Effect<IN, TAR extends Target> extends Specification {
    void activate(IN input, TAR target);
}
