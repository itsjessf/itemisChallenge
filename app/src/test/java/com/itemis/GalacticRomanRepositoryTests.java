package com.itemis;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GalacticRomanRepositoryTests {

    private HashMap<String, String> romanRepository;
    private GalacticRomanRepository galacticRomanRepository;

    @BeforeEach
    public void beforeEach(){
        galacticRomanRepository = new GalacticRomanRepository();
    }

    @Test
    public void storesUserInputInRepository(){
        galacticRomanRepository.storeGalacticRomanValues("glob is I");
        assertEquals("I", galacticRomanRepository.getGalacticRomanValues("glob"));
    }
}
