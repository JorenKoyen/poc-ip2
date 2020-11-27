package com.sixhead.poc.util;

public enum AnsiCode {
  RESET("\u001B[0m"),
  RED("\u001B[31m"),
  GREEN("\u001B[32m"),
  YELLOW("\u001B[33m"),
  BLUE("\u001B[34m"),
  PURPLE("\u001B[35m"),
  CYAN("\u001B[36m"),
  WHITE("\u001B[37m");

  private final String code;
  AnsiCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
