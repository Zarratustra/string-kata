package com.michal.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            List<Integer> parsedNumbers = parseNumbers(numbers, separatorRegex);
            validateNoNegativeNumbers(parsedNumbers);

            return sumNumbers(parsedNumbers);
        }
    }

    private List<Integer> parseNumbers(String numbers, String separatorRegex) {
        try {
            String[] separatedNumbers = numbers.split(separatorRegex, -1);
            return Arrays
                    .stream(separatedNumbers)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Calculator input didn't contain numbers in proper format");
        }
    }

    private void validateNoNegativeNumbers(List<Integer> numbers) {
        List<String> negativeNumbers = numbers
                .stream()
                .filter(number -> number < 0)
                .map(Object::toString)
                .collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            String negativeNumbersString = String.join(",", negativeNumbers);
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbersString);
        }
    }

    private int sumNumbers(List<Integer> numbers) {
        return numbers
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
