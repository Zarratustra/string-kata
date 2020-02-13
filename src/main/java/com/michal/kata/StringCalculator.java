package com.michal.kata;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static String HEADER_START = "//";
    private static String QUOTE_START = "\\[";
    private static String QUOTE_END = "]";
    private static String DEFAULT_SEPARATOR = ",|\\n";
    private static int MAX_ALLOWED_VALUE = 1000;


    int add(String numbers) {
        boolean hasHeader = numbers.startsWith(HEADER_START);
        if (hasHeader) {
            String[] headerAndRest = numbers.split("\n", 2);
            String firstLine = headerAndRest[0];
            String numbersToAdd = headerAndRest[1];
            String separatorRegex = getSeparatorRegex(firstLine);
            return addUsingRegex(numbersToAdd, separatorRegex);
        } else {
            return addUsingRegex(numbers, DEFAULT_SEPARATOR);
        }
    }

    private String getSeparatorRegex(String firstLine) {
        boolean hasOneCharHeader = firstLine.length() == 3;
        if (hasOneCharHeader) {
            String separator = firstLine.substring(HEADER_START.length(), HEADER_START.length() + 1);
            return Pattern.quote(separator);
        } else {
            Pattern pattern = Pattern.compile(HEADER_START + QUOTE_START+"(.*)"+QUOTE_END);
            Matcher matcher = pattern.matcher(firstLine);
            matcher.find();
            String extractedDelimiters = matcher.group(1);
            return Arrays.stream(
                    extractedDelimiters.split(QUOTE_END+QUOTE_START)
            ).map(Pattern::quote)
                    .collect(Collectors.joining("|"));
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
                .filter(num -> num <= MAX_ALLOWED_VALUE)
                .mapToInt(Integer::intValue)
                .sum();
    }

}
