package com.sixhead.poc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Logger {
  private final String name;
  private final int classColor;


  public static Logger getLogger(String origin) {
    return new Logger(origin);
  }

  private Logger(String name) {
    this.name = name;
    // exclude first index to not get reset code
    this.classColor = 1 + ThreadLocalRandom.current().nextInt(AnsiCode.values().length - 1);
  }

  public void info(String message) {
    System.out.println(formatMessage(message));
  }
  public void info(String format, Object ...args) {
    this.info(String.format(format, args));
  }
  public void error(String message) {
    System.err.println(formatMessage(message));
  }
  public void error(String format, Object ...args) {
    this.error(String.format(format, args));
  }

  private String formatMessage(String message) {
    Date date = new Date(System.currentTimeMillis());;
    var sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    String dateText = sdf.format(date);

    return String.format("%-30s %s\t\t%s",
        formatName(),
        TextColor.printAsColor(AnsiCode.YELLOW, dateText),
        message);
  }

  private String formatName() {
    return String.format("[%s]", TextColor.printAsColor(AnsiCode.values()[this.classColor], this.name));
  }
}
