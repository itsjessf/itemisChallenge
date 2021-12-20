package com.itemis;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RomanExpressionBuilderTests {

    GalacticToRomanExpressionMapper galacticToRomanExpressionMapper;

    @Before
    public void beforeEach() {
        GalacticRomanRepository galacticRomanRepository = new GalacticRomanRepository();
        galacticRomanRepository.storeGalacticRomanValues("glob" , "I");
        galacticRomanRepository.storeGalacticRomanValues("prok","V");
        galacticRomanRepository.storeGalacticRomanValues("pish","X");
        galacticRomanRepository.storeGalacticRomanValues("tegj","L");
        galacticRomanRepository.storeGalacticRomanValues("cento","C");
        galacticRomanRepository.storeGalacticRomanValues("daemos","D");
        galacticRomanRepository.storeGalacticRomanValues("mili","M");
        galacticToRomanExpressionMapper = new GalacticToRomanExpressionMapper(galacticRomanRepository);
    }

    @Test
    public void testCreateRomanExpressionWithGalacticValues() {
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(Arrays.asList("mili", "mili", "cento", "cento", "pish", "pish", "glob"));
        assertEquals(Arrays.asList("M", "M", "C", "C", "X", "X","I"), romanExpression);
    }


}
