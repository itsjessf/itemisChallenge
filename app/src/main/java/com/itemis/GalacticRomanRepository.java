package com.itemis;

import java.util.HashMap;
import java.util.Map;

public class GalacticRomanRepository {

    private static final Map<String, String> galacticRomanValues = new HashMap<>();


    public void storeGalacticRomanValues(String galacticValue, String romanValue) {
        galacticRomanValues.put(galacticValue, romanValue);
    }

    public String getGalacticRomanValues( String galacticWord) {
        return galacticRomanValues.get(galacticWord);
    }

}
