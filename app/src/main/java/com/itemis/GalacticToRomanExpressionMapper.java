package com.itemis;

public class GalacticToRomanExpressionMapper {
    private final GalacticRomanRepository galacticRomanRepository;

    public GalacticToRomanExpressionMapper(GalacticRomanRepository galacticRomanRepository) {
        this.galacticRomanRepository = galacticRomanRepository;
    }

    public String[] buildRomanExpression(String[] galacticValues){
        String[] romanExpression = new String[galacticValues.length];
        for(int i=0; i< galacticValues.length; i++){
            romanExpression[i] = (galacticRomanRepository.getGalacticRomanValues(galacticValues[i]));
        }

        return romanExpression;
    }
}
