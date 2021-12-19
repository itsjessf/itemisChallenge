package com.itemis;

import java.util.Arrays;

public class GalacticToCreditsCalculator {

    private final GalacticToRomanExpressionMapper romanExpressionBuilder;
    private final RomanToCreditsCalculator romanToCreditsCalculator;

    public GalacticToCreditsCalculator(GalacticToRomanExpressionMapper romanExpressionBuilder, RomanToCreditsCalculator romanToCreditsCalculator) {
        this.romanExpressionBuilder = romanExpressionBuilder;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
    }

    public int calculateGalacticToCredits(String[] userInputElements){
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements,3, userInputElements.length));
        return romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
    }
}
