package com.itemis;

import java.util.Arrays;
import java.util.regex.Pattern;

public class UserInputHandler {

    private final GalacticRomanRepository galacticRomanRepository;
    private final MetalCreditsRepository metalCreditsRepository;
    private final GalacticToCreditsResultRepository galacticToCreditsResultRepository;
    private final MetalToCreditsResultRepository metalToCreditsResultRepository;
    private final InvalidQueryHandler invalidQueryHandler;
    private final RomanExpressionBuilder romanExpressionBuilder;
    private final RomanToCreditsCalculator romanToCreditsCalculator;

    public UserInputHandler(GalacticRomanRepository galacticRomanRepository,
                            MetalCreditsRepository metalCreditsRepository,
                            GalacticToCreditsResultRepository galacticToCreditsResultRepository,
                            MetalToCreditsResultRepository metalToCreditsResultRepository,
                            InvalidQueryHandler invalidQueryHandler,
    RomanExpressionBuilder romanExpressionBuilder,
                            RomanToCreditsCalculator romanToCreditsCalculator) {

        this.galacticRomanRepository = galacticRomanRepository;
        this.metalCreditsRepository = metalCreditsRepository;
        this.galacticToCreditsResultRepository = galacticToCreditsResultRepository;
        this.metalToCreditsResultRepository = metalToCreditsResultRepository;
        this.invalidQueryHandler = invalidQueryHandler;
        this.romanExpressionBuilder = romanExpressionBuilder;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
    }

    public void handleUserInput(String userInput) {

        if (hasMatchingRegex("^\\w+ is [IVXLCDM]$", userInput)) {
            String[] userInputElements = userInput.split(" ");
            this.galacticRomanRepository.storeGalacticRomanValues(userInputElements[0], userInputElements[2]);
            return;
        }

        if (hasMatchingRegex(" is \\d+ Credits$", userInput)) {
            String[] userInputElements = userInput.split(" ");
            String metal = userInputElements[userInputElements.length-4];
            this.metalCreditsRepository.storeMetalCreditValues(metal, calculateMetalCredits(userInputElements) );
            return;
        }

        if (hasMatchingRegex("^How much is ", userInput)) {
            this.galacticToCreditsResultRepository.storeGalacticToCreditsResult(userInput);
            return;
        }

        if (hasMatchingRegex("^How many Credits is ", userInput)) {
            this.metalToCreditsResultRepository.storeMetalToCreditsResult(userInput);
            return;
        }
        this.invalidQueryHandler.addInvalidQueryToResult();

    }

    public boolean hasMatchingRegex(String regex, String sentence) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sentence).find();
    }

    private float calculateMetalCredits(String[] userInputElements){
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements, 0, userInputElements.length-4));
        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        int metalValue = Integer.parseInt(userInputElements[userInputElements.length-2]);
        return (float)metalValue/metalQuantity;
    }
    // if two words separated by "is"
    // if sentence ends with "is" \number\ "Credits"
    // if sentence starts with "How much is"
    // if sentence starts with "How many credits is"
}
