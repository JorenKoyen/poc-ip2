package com.sixhead.poc.execution.extractors;

import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.InputVariable;

public class StateExtractor implements InputVariableExtractor {
  @Override
  public InputVariable extractorFor() {
    return InputVariable.STATE;
  }

  @Override
  public Object extract(Object[] args, Executor executor) {
    //TODO: support parameterized state fetch (for multiple buffer values)
    return executor.getLastState();
  }
}
