package com.itemis;

import java.util.Arrays;
import java.util.HashMap;

public class MetalCreditsRepository {
    private HashMap<String, Integer> metalCredits;
    private final GalacticRomanRepository galacticRomanRepository;
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final RomanExpressionBuilder romanExpressionBuilder;

    public MetalCreditsRepository(HashMap<String, Integer> metalCredits, GalacticRomanRepository galacticRomanRepository, RomanToCreditsCalculator romanToCreditsCalculator, RomanExpressionBuilder romanExpressionBuilder) {
        this.metalCredits = metalCredits;
        this.galacticRomanRepository = galacticRomanRepository;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.romanExpressionBuilder = romanExpressionBuilder;
    }

    public void storeMetalCreditValues(String userInput) {
        String[] userInputElements = userInput.split(" ");
        String metal = userInputElements[userInput.length()-3];
        metalCredits.put(metal, calculateMetalCredits(userInputElements));
    }

    private int calculateMetalCredits(String[] userInputElements){
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements,0,userInputElements.length-4));

        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        int metalValue = Integer.parseInt(userInputElements[userInputElements.length-2]);

        return metalQuantity/metalValue;
    }

    public int getMetalCredits( String metal) {
        return metalCredits.get(metal);
    }
}
