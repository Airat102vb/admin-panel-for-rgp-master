package com.example.demo.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class CommonUtils {

    public static LocalDate convertLongToLocalDate(long timeStamp) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
    }

    public static int calculateLevel(int experience) {
        return (int) (Math.sqrt(2500 + 200 - experience)) / 100;
    }

    public static int calculateUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }
}
