package com.itemis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetalCreditsRepository {
    private static final Map<String, Float> metalCredits = new HashMap<>();
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final RomanExpressionBuilder romanExpressionBuilder;

    public MetalCreditsRepository( RomanToCreditsCalculator romanToCreditsCalculator, RomanExpressionBuilder romanExpressionBuilder) {
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.romanExpressionBuilder = romanExpressionBuilder;
    }

    public void storeMetalCreditValues(String metal, float credits) {
        metalCredits.put(metal, credits);
    }



    public float getMetalCredits( String metal) {
        return metalCredits.get(metal);
    }
}
