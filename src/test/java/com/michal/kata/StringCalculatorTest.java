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
        assertEquals(value, calculator.add(""+value), "Wrong output for input '"+value+"'");
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
