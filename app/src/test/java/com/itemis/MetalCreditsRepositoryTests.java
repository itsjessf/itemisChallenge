package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MetalCreditsRepositoryTests {
    private MetalCreditsRepository metalCreditsRepository;
    private RomanToCreditsCalculator romanToCreditsCalculator;
    private RomanExpressionBuilder romanExpressionBuilder;


    @Before
    public void beforeEach() {
        GalacticRomanRepository galacticRomanRepository = new GalacticRomanRepository();
        galacticRomanRepository.storeGalacticRomanValues("glob is I");
        galacticRomanRepository.storeGalacticRomanValues("prok is V");
        galacticRomanRepository.storeGalacticRomanValues("pish is X");
        galacticRomanRepository.storeGalacticRomanValues("tegj is L");
        galacticRomanRepository.storeGalacticRomanValues("cento is C");
        galacticRomanRepository.storeGalacticRomanValues("daemos is D");
        galacticRomanRepository.storeGalacticRomanValues("mili is M");
        romanExpressionBuilder = new RomanExpressionBuilder(galacticRomanRepository);
        metalCreditsRepository = new MetalCreditsRepository(romanToCreditsCalculator, romanExpressionBuilder);
    }

    @Test
    public void whenUserInputsISValid_StoreMetalCreditValue() {
        metalCreditsRepository.storeMetalCreditValues("glob glob Silver is 20 Credits");
        assertEquals(10, metalCreditsRepository.getMetalCredits("Silver"));
        metalCreditsRepository.storeMetalCreditValues("cento pish pish Gold is 240 Credits");
        assertEquals(2, metalCreditsRepository.getMetalCredits("Gold"));
    }
}
