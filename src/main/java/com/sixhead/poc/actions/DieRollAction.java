package com.sixhead.poc.actions;

import java.util.concurrent.ThreadLocalRandom;

public class DieRollAction implements Action<Integer> {
  @Override
  public Integer activate() {
    return ThreadLocalRandom.current().nextInt(6) + 1;
  }
}
