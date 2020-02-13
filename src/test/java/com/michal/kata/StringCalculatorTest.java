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
    @ValueSource(ints = {-45,0,23})
    void oneNumber(int value) {
        calculatorAssertion(""+value,value);
    }

    @Test
    void twoNumbers() {
        calculatorAssertion("2,3",5);
    }

    @Test
    void multipleNumbers() {
        calculatorAssertion("1,2,3,4,5",15);
    }

    void calculatorAssertion(String input, int expectedOutput) {
        assertEquals(expectedOutput, calculator.add(input), "Wrong output for input '"+input+"'");
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
