package com.itemis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MetalCreditsRepositoryTests {
    private MetalCreditsRepository metalCreditsRepository;


    @Before
    public void beforeEach() {
        RomanToCreditsCalculator romanToCreditsCalculator = new RomanToCreditsCalculator(new InvalidQueryHandler(new AnswersRepository()));
        GalacticRomanRepository galacticRomanRepository = new GalacticRomanRepository();
        galacticRomanRepository.storeGalacticRomanValues("glob","I");
        galacticRomanRepository.storeGalacticRomanValues("prok","V");
        galacticRomanRepository.storeGalacticRomanValues("pish","X");
        galacticRomanRepository.storeGalacticRomanValues("tegj","L");
        galacticRomanRepository.storeGalacticRomanValues("cento","C");
        galacticRomanRepository.storeGalacticRomanValues("daemos","D");
        galacticRomanRepository.storeGalacticRomanValues("mili","M");
        GalacticToRomanExpressionMapper romanExpressionBuilder = new GalacticToRomanExpressionMapper(galacticRomanRepository);
        metalCreditsRepository = new MetalCreditsRepository(romanToCreditsCalculator, romanExpressionBuilder);
    }

    @Test
    public void whenUserInputsISValid_StoreMetalCreditValue() {
        metalCreditsRepository.storeMetalCreditValues("Silver" ,10 );
        assertEquals(10, metalCreditsRepository.getMetalCredits("Silver"), 0.0f);
        metalCreditsRepository.storeMetalCreditValues("Gold",2);
        assertEquals(2, metalCreditsRepository.getMetalCredits("Gold"), 0.0f);
    }
}
