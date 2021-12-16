package com.itemis;

import java.util.ArrayList;
import java.util.Arrays;

public class GalacticToCreditsResultRepository {

    private final GalacticToCreditsCalculator galacticToCreditsCalculator;
    private final ArrayList<String> galacticToCreditsResult = new ArrayList<String>();

    public GalacticToCreditsResultRepository(GalacticToCreditsCalculator galacticToCreditsCalculator) {
        this.galacticToCreditsCalculator = galacticToCreditsCalculator;
    }

    public void storeGalacticToCreditsResult (String userInput){
        String[] userInputElements = userInput.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i = 3; i< userInputElements.length; i++){
            builder.append(userInputElements[i]).append(" ");
        }
        String str = builder.toString();
        int result = galacticToCreditsCalculator.calculateGalacticToCredits(userInputElements);
        galacticToCreditsResult.add(str.concat("is ").concat(String.valueOf(result)));
    }

    public ArrayList<String> getGalacticToCreditsResult() {
        return galacticToCreditsResult;
    }
}
