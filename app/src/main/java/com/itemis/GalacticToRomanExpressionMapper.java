package com.itemis;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class GalacticToRomanExpressionMapper {
    private final GalacticRomanRepository galacticRomanRepository;

    public GalacticToRomanExpressionMapper(GalacticRomanRepository galacticRomanRepository) {
        this.galacticRomanRepository = galacticRomanRepository;
    }

    public List<String> buildRomanExpression(List<String> galacticValues){
        List<String> romanExpression = new ArrayList<>();
        for (String galacticValue : galacticValues) {
            romanExpression.add(galacticRomanRepository.getGalacticRomanValues(galacticValue));
        }

        /*
        String[] romanExpression = new String[galacticValues.size()];
        for(int i=0; i< galacticValues.size(); i++){
            romanExpression[i] = (galacticRomanRepository.getGalacticRomanValues(galacticValues[i]));
        }*/

        return romanExpression;
    }
}
