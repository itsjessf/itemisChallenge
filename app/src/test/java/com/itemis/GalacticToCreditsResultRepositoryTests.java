package com.itemis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GalacticToCreditsResultRepositoryTests {

    private GalacticToCreditsResultRepository galacticToCreditsResultRepository;

    @Before
    public void beforeEach(){
        galacticToCreditsResultRepository = new GalacticToCreditsResultRepository( new GalacticToCreditsCalculator( new RomanExpressionBuilder( new GalacticRomanRepository()), new RomanToCreditsCalculator()));
    }

    @Test
    public void storeGalacticToCreditsResult(){
        galacticToCreditsResultRepository.storeGalacticToCreditsResult("glob glob");
        assertEquals("glob glob is 2", galacticToCreditsResultRepository.getGalacticToCreditsResult().get(0));
        galacticToCreditsResultRepository.storeGalacticToCreditsResult("pish tegj glob glob");
        assertEquals("pish tegj glob glob is 42", galacticToCreditsResultRepository.getGalacticToCreditsResult().get(1));
    }
}
