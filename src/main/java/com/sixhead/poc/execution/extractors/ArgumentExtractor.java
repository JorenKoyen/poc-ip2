package com.sixhead.poc.execution.extractors;

import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.InputVariable;

import java.util.HashMap;
import java.util.Map;

public class ArgumentExtractor {
  private final Executor executor;
  private final Map<InputVariable, InputVariableExtractor> inputVariableExtractors;

  public ArgumentExtractor(Executor executor) {
    this.executor = executor;
    this.inputVariableExtractors = new HashMap<>();

    // register input variable extractors
    this.inputVariableExtractors.put(InputVariable.STATE, new StateExtractor());
  }

  /**
   * Extract the defined input variables from the received
   * input structure.
   * @param structure An input structure defined in an event
   * @return The actual input values
   */
  public Object[] extractInputValues(Object[] structure) {
    Object[] input = new Object[structure.length];

    // loop over structure
    for (int i = 0; i < structure.length; i++) {
      input[i] = this.extractInputValue(structure[i]);
    }

    return input;
  }

  private Object extractInputValue(Object definition) {
    // handle standard input variables
    if (definition instanceof InputVariable) {
      InputVariableExtractor extractor = this.inputVariableExtractors.get(definition);
      if (extractor == null) throw new RuntimeException("input variable extractor is not supported");
      return extractor.extract(null, this.executor);
    }

    // TODO: add support for different definitions

    // return normal object (static value)
    return definition;
  }

}
