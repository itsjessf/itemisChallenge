package com.itemis;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class UserInputHandler {

    private final GalacticRomanRepository galacticRomanRepository;
    private final InvalidQueryHandler invalidQueryHandler;
    private final MetalService metalService;
    private final GalacticService galacticService;

    public UserInputHandler(GalacticRomanRepository galacticRomanRepository,
                            InvalidQueryHandler invalidQueryHandler,
                            MetalService metalService,
                            GalacticService galacticService) {

        this.galacticRomanRepository = galacticRomanRepository;
        this.invalidQueryHandler = invalidQueryHandler;
        this.metalService = metalService;
        this.galacticService = galacticService;
    }

    public void handleUserInput(String userInput) {

        try {
            if (hasMatchingRegex("^\\w+ is [IVXLCDM]$", userInput)) {
                String[] userInputElements = userInput.split(" ");
                this.galacticRomanRepository.storeGalacticRomanValues(userInputElements[0], userInputElements[2]);
                return;
            }

            if (hasMatchingRegex(" is \\d+ Credits$", userInput)) {
                List<String> userElements = Arrays.asList(userInput.split(" "));
                List<String> galacticElements = userElements.subList(0, userElements.size() - 4);
                String metal = userElements.get(userElements.size() - 4);
                int credits = parseInt(userElements.get(userElements.size() - 2));
                metalService.x(galacticElements, metal, credits);

                //this.metalCreditsRepository.storeMetalCreditValues(metal, calculateMetalCredits(userInputElements));
                return;
            }

            if (hasMatchingRegex("^How much is ", userInput)) {
                List<String> userElements = Arrays.asList(userInput.split(" "));
                List<String> galacticElements = userElements.subList(3, userElements.size());
                galacticService.x(galacticElements);

                //this.galacticToCreditsResultRepository.storeGalacticToCreditsResult(userInput);
                return;
            }

            if (hasMatchingRegex("^How many Credits is ", userInput)) {
                List<String> userElements = Arrays.asList(userInput.split(" "));
                List<String> galacticElements = userElements.subList(4, userElements.size()-1);
                String metal = userElements.get(userElements.size()-1);
                metalService.y(galacticElements, metal);
                //this.metalToCreditsResultRepository.storeMetalToCreditsResult(userInput);
                return;
            }
            throw new HandledException();

        } catch (HandledException exception) {
            //Add to answers repository exception.getMessage()
            this.invalidQueryHandler.addInvalidQueryToResult(exception.getMessage());
        }


    }

    public boolean hasMatchingRegex(String regex, String sentence) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sentence).find();
    }

   /* private float calculateMetalCredits(String[] userInputElements){
        String[] romanExpression = romanExpressionBuilder.buildRomanExpression(Arrays.copyOfRange(userInputElements, 0, userInputElements.length-4));
        int metalQuantity = romanToCreditsCalculator.calculateRomanToCredits(romanExpression);
        int metalValue = parseInt(userInputElements[userInputElements.length-2]);
        return (float)metalValue/metalQuantity;
    }*/
    // if two words separated by "is"
    // if sentence ends with "is" \number\ "Credits"
    // if sentence starts with "How much is"
    // if sentence starts with "How many credits is"
}
