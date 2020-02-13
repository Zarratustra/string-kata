package com.michal.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator calculator = new StringCalculator();

    @Test
    void emptyString() {
        assertEquals(0, calculator.add(""), "Wrong output for empty string input");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 23, 275})
    void oneNumber(int value) {
        calculatorAssertion("" + value, value);
    }

    @Test
    void twoNumbers() {
        calculatorAssertion("2,3", 5);
    }

    @Test
    void multipleNumbers() {
        calculatorAssertion("1,2,3,4,5", 15);
    }

    @Test
    void commasAndNewlines() {
        calculatorAssertion("1\n2,3\n4", 10);
    }

    @Test
    void arbitraryDelimiter() {
        calculatorAssertion("//;\n1;2;3;4", 10);
    }

    @Test
    void arbitraryDelimiterNoNumbers() {
        calculatorAssertion("//;\n", 0);
    }

    @Test
    void delimitersNextToEachOther() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1\n2,3,\n4,5"),
                "Calculator didn't throw exception on input with trailing comma"
        );
    }

    @Test
    void trailingComma() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,2,3,4,5,"),
                "Calculator didn't throw exception on input with trailing comma"
        );
    }

    void calculatorAssertion(String input, int expectedOutput) {
        assertEquals(expectedOutput, calculator.add(input), "Wrong output for input '" + input + "'");
    }

    @Test
    void rejectOneNegative() {
        negativeNumbersTestCase("-1","-1");
    }

    @Test
    void rejectMultipleNegatives() {
        negativeNumbersTestCase("-1\n2,-3\n4","-1,-3");
    }

    private void negativeNumbersTestCase(String input, String negativeNumbers) {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(input),
                "Calculator didn't throw exception on input with negative number(s)"
        );

        try {
            calculator.add(input);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            String expectedMessage = "Negative numbers not allowed: " + negativeNumbers;
            assertEquals(message, expectedMessage);
        }
    }

    @Test
    void badFormat() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("ybfg"),
                "Calculator didn't throw exception on malformed input"
        );
    }


}
