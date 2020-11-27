package com.sixhead.poc.execution;

public enum InputVariable {
  STATE(Object.class),
  UNIQUE_GLYPHS_IN_SPELL(Integer.class);

  private final Class<?> type;
  public Class<?> getType() {
    return type;
  }

  InputVariable(Class<?> type) {
    this.type = type;
  }
}
