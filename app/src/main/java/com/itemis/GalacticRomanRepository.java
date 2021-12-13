package com.itemis;

import java.util.HashMap;

public class GalacticRomanRepository {
    static HashMap<String, String> galacticRomanValues = new HashMap<String, String>();

    public static void storeGalacticRomanValues(String userInput) {
        String[] userInputElements = userInput.split(" ");
        galacticRomanValues.put(userInputElements[0], userInputElements[2]);
        System.out.println(galacticRomanValues);
    }

    public static String getGalacticRomanValues( String galacticWord) {
        return galacticRomanValues.get(galacticWord);
    }
}
