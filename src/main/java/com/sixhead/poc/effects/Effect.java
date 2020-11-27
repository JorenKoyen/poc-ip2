package com.sixhead.poc.effects;

import com.sixhead.poc.Specification;

public interface Effect extends Specification {
    void activate(Object input);
}
