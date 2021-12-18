package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RomanExpressionBuilderTests {

    RomanExpressionBuilder romanExpressionBuilder;

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
        romanExpressionBuilder = new RomanExpressionBuilder(galacticRomanRepository);
    }

    @Test
    public void testCreateRomanExpressionWithGalacticValues() {
        assertArrayEquals(new String[]{"M", "M", "C", "C", "X", "X","I"}, romanExpressionBuilder.buildRomanExpression(new String[]{"mili", "mili", "cento", "cento", "pish", "pish", "glob"}));
        assertArrayEquals(new String[]{"M", "M"}, romanExpressionBuilder.buildRomanExpression(new String[]{"mili", "mili"}));
    }

}
