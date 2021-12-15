package com.itemis;

import java.util.Arrays;

public class GalacticToCreditsCalculator {

    private final RomanExpressionBuilder romanExpressionBuilder;
    private final RomanToCreditsCalculator romanToCreditsCalculator;

    public GalacticToCreditsCalculator(RomanExpressionBuilder romanExpressionBuilder, RomanToCreditsCalculator romanToCreditsCalculator) {
        this.romanExpressionBuilder = romanExpressionBuilder;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
    }

    public int calculateGalacticToCredits(String userInput){
        String[] userInputElements = userInput.split(" ");
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements,userInputElements.length+2, userInputElements.length));
        return romanToCreditsCalculator.calculateRomanToCredits(romanExpression);

    }
}
