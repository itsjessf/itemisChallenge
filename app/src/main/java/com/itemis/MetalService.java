package com.itemis;

import java.util.List;

public class MetalService {
    private MetalCreditsRepository metalCreditsRepository;
    private GalacticRomanRepository galacticRomanRepository;
    private GalacticToRomanExpressionMapper galacticToRomanExpressionMapper;
    private RomanToCreditsCalculator romanToCreditsCalculator;
    private AnswersRepository answersRepository;

    public MetalService(MetalCreditsRepository metalCreditsRepository, GalacticRomanRepository galacticRomanRepository, GalacticToRomanExpressionMapper galacticToRomanExpressionMapper, RomanToCreditsCalculator romanToCreditsCalculator, AnswersRepository answersRepository) {
        this.metalCreditsRepository = metalCreditsRepository;
        this.galacticRomanRepository = galacticRomanRepository;
        this.galacticToRomanExpressionMapper = galacticToRomanExpressionMapper;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.answersRepository = answersRepository;
    }

    public void x(List<String> galacticElements, String metal, int credits) throws HandledException {
        // Verify if galactic value already exists
        // Calculated metal credits
        // store metal credits value in repository
        doesGalacticExist(galacticElements);
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(galacticElements);
        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        if(metalQuantity == 0){return;}
        float metalValue = (float)credits/metalQuantity;
        metalCreditsRepository.storeMetalCreditValues(metal, metalValue);
    }

    public void y (List<String> galacticElements, String metal) throws HandledException {
        //Verify if galactic value already exists
        //Verify if metal exists
        // calculates galactic expression value
        // builds string to store in answers
        //stores string in answers
        doesGalacticExist(galacticElements);
        doesMetalExist(metal);
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(galacticElements);
        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        if(metalQuantity == 0){return;}
        float metalValue = metalCreditsRepository.getMetalCredits(metal);
        float result = metalQuantity * metalValue;
        String answer = buildStringResult(galacticElements, metal, (int)result);
        addResult(answer);
    }

    private void doesGalacticExist(List<String> galacticElements) throws HandledException {
        for (int i = 0; i < galacticElements.size(); i++) {
            if (galacticRomanRepository.getGalacticRomanValues(galacticElements.get(i)) == null) {
                throw new HandledException();
            }
        }
    }

    private void doesMetalExist(String metal) throws HandledException {
        if (!metalCreditsRepository.isMetal(metal)) {
            throw new HandledException();
        }
    }

    private String buildStringResult(List<String> galacticElements, String metal, int result) {
        String galactic = String.join(" ", galacticElements);
        return galactic.concat(" ").concat(metal).concat(" is ").concat(String.valueOf(result)).concat(" Credits");
    }

    private void addResult(String answer) {
        answersRepository.addAnswer(answer);
    }

}
