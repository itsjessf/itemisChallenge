package com.itemis;

import java.util.regex.Pattern;

public class UserInputHandler {

    private final GalacticRomanRepository galacticRomanRepository;
    private final MetalCreditsRepository metalCreditsRepository;
    private final RomanToCreditsCalculator romanToCreditsCalculator;
    private final MetalToCreditsCalculator metalToCreditsCalculator;
    private final GalacticToCreditsCalculator galacticToCreditsCalculator;

    public UserInputHandler(GalacticRomanRepository galacticRomanRepository, MetalCreditsRepository metalCreditsRepository, RomanToCreditsCalculator romanToCreditsCalculator, MetalToCreditsCalculator metalToCreditsCalculator, GalacticToCreditsCalculator galacticToCreditsCalculator) {
        this.galacticRomanRepository = galacticRomanRepository;
        this.metalCreditsRepository = metalCreditsRepository;
        this.romanToCreditsCalculator = romanToCreditsCalculator;
        this.metalToCreditsCalculator = metalToCreditsCalculator;
        this.galacticToCreditsCalculator = galacticToCreditsCalculator;
    }

    public void handleUserInput(String userInput) {

        if(hasMatchingRegex("^\\w+ is [IVXLCDM]$", userInput)){
            this.galacticRomanRepository.storeGalacticRomanValues(userInput);

        }else if (hasMatchingRegex("is \\d+ Credits", userInput)){
            this.metalCreditsRepository.storeMetalCreditValues(userInput);

        }else if (hasMatchingRegex("^How much is", userInput)){
            this.galacticToCreditsCalculator.calculateGalacticToCredits(userInput);

        }else if (hasMatchingRegex("^How many Credits is", userInput)){
            this.metalToCreditsCalculator.calculateMetalToCredits(userInput);

        }else{
            System.out.println("The note provided is not valid");
        }
    }

    public boolean hasMatchingRegex (String regex, String sentence){
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sentence).matches();
    }

    // if two words separated by "is"
    // if sentence ends with "is" \number\ "Credits"
    // if sentence starts with "How much is"
    // if sentence starts with "How many credits is"
}
