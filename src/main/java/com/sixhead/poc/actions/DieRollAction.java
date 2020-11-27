package com.sixhead.poc.actions;

import java.util.concurrent.ThreadLocalRandom;

public class DieRollAction implements Action {

    @Override
    public Object output() {
        return ThreadLocalRandom.current().nextInt(6) + 1;
    }
}
