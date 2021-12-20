package com.itemis;

import java.util.List;

public class GalacticService {

    private final GalacticRomanRepository galacticRomanRepository;
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final GalacticToRomanExpressionMapper galacticToRomanExpressionMapper;
    private final AnswersRepository answersRepository;

    public GalacticService(GalacticRomanRepository galacticRomanRepository,
                           RomanToCreditsCalculator romanToCreditsCalculator,
                           GalacticToRomanExpressionMapper galacticToRomanExpressionMapper,
                           AnswersRepository answersRepository) {

        this.galacticRomanRepository = galacticRomanRepository;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.galacticToRomanExpressionMapper = galacticToRomanExpressionMapper;
        this.answersRepository = answersRepository;
    }

    public void handleGalacticElements(List<String> galacticElements) throws HandledException {
        doesGalacticExist(galacticElements);
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(galacticElements);
        int galacticCreditsValue = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        if(galacticCreditsValue == 0){return;}
        String answer = buildStringResult(galacticElements, galacticCreditsValue);
        addResult(answer);
    }

    private void doesGalacticExist(List<String> galacticElements) throws HandledException {
        for (String galacticElement : galacticElements) {
            if (galacticRomanRepository.getGalacticRomanValues(galacticElement) == null) {
                throw new HandledException();
            }
        }
    }

    private String buildStringResult(List<String> galacticElements, int galacticCreditsValue) {
        String galactic = String.join(" ", galacticElements);
        return galactic.concat(" is ").concat(String.valueOf(galacticCreditsValue));
    }

    private void addResult(String answer) {
        answersRepository.addAnswer(answer);
    }
}
