package com.itemis;

import java.util.ArrayList;

public class MetalToCreditsResultRepository {

   /* private final MetalToCreditsCalculator metalToCreditsCalculator;
    private final ArrayList<String> metalToCreditsResult = new ArrayList<>();

    public MetalToCreditsResultRepository(MetalToCreditsCalculator metalToCreditsCalculator) {
        this.metalToCreditsCalculator = metalToCreditsCalculator;
    }

    public void storeMetalToCreditsResult (String userInput){
        String[] userInputElements = userInput.split(" ");
        metalToCreditsResult.add(resultStringBuilder(userInput, calculateMetalCreditsValue(userInputElements)));
    }

    private int calculateMetalCreditsValue(String[] userInput){
        return (int)metalToCreditsCalculator.calculateMetalToCredits(userInput);
    }

    private String resultStringBuilder (String userInput, int result){
        String[] userInputElements = userInput.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i = 4; i< userInputElements.length; i++){
            builder.append(userInputElements[i]).append(" ");
        }
        return builder.toString().concat("is ").concat(String.valueOf(result)).concat(" Credits");
    }

    public ArrayList<String> getGalacticToCreditsResult() {
        return metalToCreditsResult;
    }*/
}
