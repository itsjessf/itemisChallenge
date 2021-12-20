package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RomanToCreditsCalculatorTests {

    private RomanToCreditsCalculator romanToCreditsCalculator;
    private AnswersRepository answersRepository;

    @Before
    public void beforeEach() {
        answersRepository = new AnswersRepository();
        romanToCreditsCalculator = new RomanToCreditsCalculator( new InvalidQueryHandler(answersRepository));
    }


    @Test
    public void whenRomanStringIsValid_DisplayCredits() {
        //check if error is thrown
        assertEquals(6,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","I")));
        assertEquals(7,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","I","I")));
        assertEquals(8,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","I","I","I")));
        assertEquals(4,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","V")));
        assertEquals(9,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","X")));
        assertEquals(40,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("X","L")));
        assertEquals(90,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("X","C")));
        assertEquals(400,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("C","D")));
        assertEquals(900,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("C","M")));
        assertEquals(1944,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","C","M","X","L","I","V")));
        assertEquals(3900,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","M","M","C","M")));
        assertEquals(1903,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","C","M","I","I","I")));
        assertEquals(2233,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","M","C","C","X","X","X","I","I","I")));
        assertEquals(3999,romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","M","M","C","M","X","C","I","X")));
    }

    @Test
    public void whenRomanCanNotBeSubtracted_StoreErrorMessageInAnswerRepository(){
        //"I" can only be subtracted from V and X
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","L"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","C"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","D"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","M"));
        //"X" can only be subtracted from L and C
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("X","D"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("X","M"));
        //V can never be subtracted
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","X"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","L"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","C"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","D"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","M"));
        //L can never be subtracted
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("L","C"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("L","D"));
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("L","M"));
        //D can never be subtracted
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("D","M"));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(1));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(2));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(3));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(4));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(5));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(6));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(7));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(8));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(9));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(10));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(11));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(12));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(13));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(14));

    }

    @Test
    public void whenRomanCanNotBeRepeated_ReturnZero(){
        //"D" cant be repeated
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("D","D"));
        //"L" cant be repeated
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("L","L"));
        //"V" cant be repeated
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("V","V"));
        //"I" cant be repeated 4 times
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("I","I", "I", "I"));
        //"X" cant be repeated 4 times
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("X","X", "X", "X"));
        //"C" cant be repeated 4 times
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("C","C", "C", "C"));
        //"M" cant be repeated 4 times
        romanToCreditsCalculator.calculateRomanToCredits(Arrays.asList("M","M", "M", "M"));

        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(1));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(2));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(3));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(4));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(5));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(6));

    }
}
