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
        galacticRomanRepository.storeGalacticRomanValues("glob","I");
        galacticRomanRepository.storeGalacticRomanValues("prok","V");
        galacticRomanRepository.storeGalacticRomanValues("pish","X");
        galacticRomanRepository.storeGalacticRomanValues("tegj","L");
        galacticRomanRepository.storeGalacticRomanValues("cento","C");
        galacticRomanRepository.storeGalacticRomanValues("daemos","D");
        galacticRomanRepository.storeGalacticRomanValues("mili","M");
        romanExpressionBuilder = new RomanExpressionBuilder(galacticRomanRepository);
        metalCreditsRepository = new MetalCreditsRepository(romanToCreditsCalculator, romanExpressionBuilder);
    }

    @Test
    public void whenUserInputsISValid_StoreMetalCreditValue() {
        metalCreditsRepository.storeMetalCreditValues("Silver" ,10 );
        assertEquals(10, metalCreditsRepository.getMetalCredits("Silver"));
        metalCreditsRepository.storeMetalCreditValues("Gold",2);
        assertEquals(2, metalCreditsRepository.getMetalCredits("Gold"));
    }
}
