package com.itemis;

import java.util.List;

public class MetalService {
    private final MetalCreditsRepository metalCreditsRepository;
    private final GalacticRomanRepository galacticRomanRepository;
    private final GalacticToRomanExpressionMapper galacticToRomanExpressionMapper;
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final AnswersRepository answersRepository;

    public MetalService(MetalCreditsRepository metalCreditsRepository, GalacticRomanRepository galacticRomanRepository, GalacticToRomanExpressionMapper galacticToRomanExpressionMapper, RomanToCreditsCalculator romanToCreditsCalculator, AnswersRepository answersRepository) {
        this.metalCreditsRepository = metalCreditsRepository;
        this.galacticRomanRepository = galacticRomanRepository;
        this.galacticToRomanExpressionMapper = galacticToRomanExpressionMapper;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.answersRepository = answersRepository;
    }

    public void storeMetalElements(List<String> galacticElements, String metal, int credits) throws HandledException {
        doesGalacticExist(galacticElements);
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(galacticElements);
        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        if(metalQuantity == 0){return;}
        float metalValue = (float)credits/metalQuantity;
        metalCreditsRepository.storeMetalCreditValues(metal, metalValue);
    }

    public void handleMetalElements(List<String> galacticElements, String metal) throws HandledException {
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
        for (String galacticElement : galacticElements) {
            if (galacticRomanRepository.getGalacticRomanValues(galacticElement) == null) {
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
