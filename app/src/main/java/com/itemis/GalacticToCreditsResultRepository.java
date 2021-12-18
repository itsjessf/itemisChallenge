package com.itemis;

import java.util.ArrayList;

public class GalacticToCreditsResultRepository {

    private final GalacticToCreditsCalculator galacticToCreditsCalculator;
    private final ArrayList<String> galacticToCreditsResult = new ArrayList<String>();

    public GalacticToCreditsResultRepository(GalacticToCreditsCalculator galacticToCreditsCalculator) {
        this.galacticToCreditsCalculator = galacticToCreditsCalculator;
    }

    public void storeGalacticToCreditsResult (String userInput){
        String[] userInputElements = userInput.split(" ");
        galacticToCreditsResult.add(resultStringBuilder(userInput, calculateGalacticCreditsValue(userInputElements)));
    }

    private int calculateGalacticCreditsValue(String[] userInput){
        return galacticToCreditsCalculator.calculateGalacticToCredits(userInput);
    }

    private String resultStringBuilder (String userInput, int result){
        String[] userInputElements = userInput.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i = 3; i< userInputElements.length; i++){
            builder.append(userInputElements[i]).append(" ");
        }
        return builder.toString().concat("is ").concat(String.valueOf(result));
    }

    public ArrayList<String> getGalacticToCreditsResult() {
        return galacticToCreditsResult;
    }
}
