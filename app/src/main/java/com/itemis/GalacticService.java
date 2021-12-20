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

    public void x(List<String> galacticElements) throws HandledException {
        //C hecks if galactic exists
        // calculates galactic expression value
        // builds string to store in answers
        // stores string in answers
        doesGalacticExist(galacticElements);
        List<String> romanExpression = galacticToRomanExpressionMapper.buildRomanExpression(galacticElements);
        int galacticCreditsValue = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        String answer = buildStringResult(galacticElements, galacticCreditsValue);
        addResult(answer);
    }

    private void doesGalacticExist(List<String> galacticElements) throws HandledException {
        for (int i = 0; i < galacticElements.size(); i++) {
            if (galacticRomanRepository.getGalacticRomanValues(galacticElements.get(i)) == null) {
                throw new HandledException();
            }
        }
    }

    private String buildStringResult(List<String> galacticElements, int galacticCreditsValue) {
        String galactic = String.join(" ", galacticElements);
        return galactic.concat("is ").concat(String.valueOf(galacticCreditsValue));
    }

    private void addResult(String answer) {
        answersRepository.addAnswer(answer);
    }
}
