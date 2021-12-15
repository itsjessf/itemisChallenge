package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RomanExpressionBuilderTests {

    RomanExpressionBuilder romanExpressionBuilder;

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
    }

    @Test
    public void testCreateRomanExpressionWithGalacticValues() {
        assertArrayEquals(new String[]{"M", "M", "C", "C", "X", "X","I"}, romanExpressionBuilder.buildRomanExpression(new String[]{"mili", "mili", "cento", "cento", "pish", "pish", "glob"}));
        assertArrayEquals(new String[]{"M", "M"}, romanExpressionBuilder.buildRomanExpression(new String[]{"mili", "mili"}));
    }

}
