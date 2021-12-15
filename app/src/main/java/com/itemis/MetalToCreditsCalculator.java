package com.itemis;

import java.util.Arrays;

public class MetalToCreditsCalculator {


    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final RomanExpressionBuilder romanExpressionBuilder;

    public MetalToCreditsCalculator(RomanToCreditsCalculator romanToCreditsCalculator, RomanExpressionBuilder romanExpressionBuilder) {
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.romanExpressionBuilder = romanExpressionBuilder;
    }

    public int calculateMetalToCredits(String userInput) {
        String[] userInputElements = userInput.split(" ");
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements, userInputElements.length + 2, userInputElements.length));
        return romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
    }
}
