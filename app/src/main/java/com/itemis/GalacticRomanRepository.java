package com.itemis;

import java.util.HashMap;
import java.util.Map;

public class GalacticRomanRepository {

    private static final Map<String, String> galacticRomanValues = new HashMap<>();


    public void storeGalacticRomanValues(String userInput) {
        String[] userInputElements = userInput.split(" ");
        galacticRomanValues.put(userInputElements[0], userInputElements[2]);
    }

    public String getGalacticRomanValues( String galacticWord) {
        return galacticRomanValues.get(galacticWord);
    }

}
