package com.michal.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculator calculator = new StringCalculator();

    @Test
    void emptyString() {
        assertEquals(0, calculator.add(""), "Wrong output for empty string input");
    }


}
