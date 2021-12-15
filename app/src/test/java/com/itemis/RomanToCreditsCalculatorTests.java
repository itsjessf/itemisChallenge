package com.itemis;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RomanToCreditsCalculatorTests {

    private RomanToCreditsCalculator romanToCreditsCalculator;

    @BeforeEach
    public void beforeEach() {
        romanToCreditsCalculator = new RomanToCreditsCalculator();
    }


    @Test
    public void whenRomanStringIsValid_DisplayCredits() {
        assertEquals(6,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","I"}));
        assertEquals(7,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","I","I"}));
        assertEquals(8,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","I","I","I"}));
        assertEquals(4,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","V"}));
        assertEquals(9,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","X"}));
        assertEquals(40,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"X","L"}));
        assertEquals(90,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"X","C"}));
        assertEquals(400,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"C","D"}));
        assertEquals(900,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"C","M"}));
        assertEquals(1944,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","C","M","X","L","I","V"}));
        assertEquals(3900,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","M","M","C","M"}));
        assertEquals(1903,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","C","M","I","I","I"}));
        assertEquals(2233,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","M","C","C","X","X","X","I","I","I"}));
        assertEquals(3999,romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","M","M","C","M","X","C","I","X"}));
    }

    @Test
    public void whenRomanCanNotBeSubtracted_ReturnZero(){
        //"I" can only be subtracted from V and X
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","L"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","C"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","D"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","M"}));
        //"X" can only be subtracted from L and C
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"X","D"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"X","M"}));
        //V can never be subtracted
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","X"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","L"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","C"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","D"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","M"}));
        //L can never be subtracted
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"L","C"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"L","D"}));
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"L","M"}));
        //D can never be subtracted
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"D","M"}));
    }

    @Test
    public void whenRomanCanNotBeRepeated_ReturnZero(){
        //"D" cant be repeated
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"D","D"}));
        //"L" cant be repeated
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"L","L"}));
        //"V" cant be repeated
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"V","V"}));
        //"I" cant be repeated 4 times
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"I","I", "I", "I"}));
        //"X" cant be repeated 4 times
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"X","X","X","X"}));
        //"C" cant be repeated 4 times
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"C","C","C","C"}));
        //"M" cant be repeated 4 times
        assertEquals(0, romanToCreditsCalculator.calculateRomanToCredits(new String[]{"M","M","M","M"}));

    }
}
