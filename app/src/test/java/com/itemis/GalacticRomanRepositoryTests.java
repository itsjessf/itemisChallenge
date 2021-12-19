package com.itemis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GalacticRomanRepositoryTests {

    private GalacticRomanRepository galacticRomanRepository;

    @Before
    public void beforeEach(){
        galacticRomanRepository = new GalacticRomanRepository();
    }

    @Test
    public void storesUserInputInRepository(){
        galacticRomanRepository.storeGalacticRomanValues("glob","I");
        assertEquals("I", galacticRomanRepository.getGalacticRomanValues("glob"));
    }
}
