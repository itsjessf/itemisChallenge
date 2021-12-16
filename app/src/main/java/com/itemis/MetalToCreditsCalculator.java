package com.itemis;

import java.util.Arrays;

public class MetalToCreditsCalculator {


    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final RomanExpressionBuilder romanExpressionBuilder;
    private final MetalCreditsRepository metalCreditsRepository;

    public MetalToCreditsCalculator(RomanToCreditsCalculator romanToCreditsCalculator, RomanExpressionBuilder romanExpressionBuilder, MetalCreditsRepository metalCreditsRepository) {
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.romanExpressionBuilder = romanExpressionBuilder;
        this.metalCreditsRepository = metalCreditsRepository;
    }

    public float calculateMetalToCredits(String[] userInputElements) {
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements, 4, userInputElements.length-1));
        int credits = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        float valueOfMetal = metalCreditsRepository.getMetalCredits(userInputElements[userInputElements.length-1]);
        return credits*valueOfMetal;
    }
}
