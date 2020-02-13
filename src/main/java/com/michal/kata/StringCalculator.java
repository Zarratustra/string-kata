package com.michal.kata;

import java.util.Arrays;

public class StringCalculator {

    private static String HEADER_START = "//";
    private static String DEFAULT_SEPARATOR = ",|\\n";

    int add(String numbers) {
        boolean hasHeader = numbers.startsWith(HEADER_START);
        if (hasHeader) {
            String[] headerAndRest = numbers.split("\n", 2);
            String firstLine = headerAndRest[0];
            String separatorRegex = firstLine.split(HEADER_START, 2)[1];
            String numbersToAdd = headerAndRest[1];
            return addUsingRegex(numbersToAdd, separatorRegex);
        } else {
            return addUsingRegex(numbers, DEFAULT_SEPARATOR);
        }
    }

    private int addUsingRegex(String numbers, String separatorRegex) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            try {
                String[] separatedNumbers = numbers.split(separatorRegex, -1);
                int sum = Arrays.stream(separatedNumbers)
                        .mapToInt(Integer::parseInt)
                        .sum();
                return sum;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Calculator input didn't contain numbers in proper format");
            }
        }
    }

}
