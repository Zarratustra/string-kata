package com.michal.kata;

import java.util.Arrays;

public class StringCalculator {

    int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            try {
                String separatorRegex = ",|\\n";
                String[] separatedNumbers = numbers.split(separatorRegex,-1);
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
