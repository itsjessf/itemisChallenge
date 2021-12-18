package com.itemis;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GalacticRomanRepositoryTests {

    private HashMap<String, String> romanRepository;
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
