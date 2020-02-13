package com.michal.kata;

public class StringCalculator {

    int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            try {
                int value = Integer.parseInt(numbers);
                return value;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Calculator input didn't contain numbers in proper format");
            }

        }

    }

}
