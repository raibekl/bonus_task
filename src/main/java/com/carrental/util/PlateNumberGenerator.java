package com.carrental.util;

import java.security.SecureRandom;
import java.time.LocalDate;

public final class PlateNumberGenerator {
  private static final SecureRandom RND = new SecureRandom();

  private PlateNumberGenerator() {}

  public static String generate() {
    int year = LocalDate.now().getYear();
    char a = (char) ('A' + RND.nextInt(26));
    char b = (char) ('A' + RND.nextInt(26));
    int num = 1000 + RND.nextInt(9000);
    return "KZ-" + year + "-" + a + b + "-" + num;
  }
}
