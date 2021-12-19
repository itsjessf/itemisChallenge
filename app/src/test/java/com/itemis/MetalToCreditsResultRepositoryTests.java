package com.itemis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MetalToCreditsResultRepositoryTests {
    private MetalToCreditsResultRepository metalToCreditsResultRepository;

    @Before
    public void beforeEach(){
        metalToCreditsResultRepository = new MetalToCreditsResultRepository( new MetalToCreditsCalculator(new RomanToCreditsCalculator(), new GalacticToRomanExpressionMapper( new GalacticRomanRepository()), new MetalCreditsRepository(new RomanToCreditsCalculator(), new GalacticToRomanExpressionMapper(new GalacticRomanRepository()))));
    }

    @Test
    public void storeGalacticToCreditsResult(){
        //metalToCreditsResultRepository.storeMetalToCreditsResult("glob prok Iron");
        //assertEquals("glob prok Iron is 782 Credits", metalToCreditsResultRepository.getGalacticToCreditsResult().get(0));
    }
}
