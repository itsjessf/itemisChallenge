package com.itemis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetalCreditsRepository {
    private static final Map<String, Integer> metalCredits = new HashMap<>();
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final RomanExpressionBuilder romanExpressionBuilder;

    public MetalCreditsRepository( RomanToCreditsCalculator romanToCreditsCalculator, RomanExpressionBuilder romanExpressionBuilder) {
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.romanExpressionBuilder = romanExpressionBuilder;
    }

    public void storeMetalCreditValues(String userInput) {
        String[] userInputElements = userInput.split(" ");
        String metal = userInputElements[userInputElements.length-4];
        metalCredits.put(metal, calculateMetalCredits(userInputElements));
    }

    private int calculateMetalCredits(String[] userInputElements){
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements, 0, userInputElements.length-4));

        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        int metalValue = Integer.parseInt(userInputElements[userInputElements.length-2]);

        return metalValue/metalQuantity;
    }

    public int getMetalCredits( String metal) {
        return metalCredits.get(metal);
    }
}
