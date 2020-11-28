package com.sixhead.poc.execution.extractors;

import com.sixhead.poc.execution.Executor;
import com.sixhead.poc.execution.InputVariable;

public interface InputVariableExtractor {
  InputVariable extractorFor();

  // TODO: add parameter for context (players, decks, table, ...)
  Object extract(Object[] args, Executor executor);
}
