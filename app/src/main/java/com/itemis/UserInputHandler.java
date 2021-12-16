package com.itemis;

import java.util.regex.Pattern;

public class UserInputHandler {

    private final GalacticRomanRepository galacticRomanRepository;
    private final MetalCreditsRepository metalCreditsRepository;
    private final GalacticToCreditsResultRepository galacticToCreditsResultRepository;
    private final MetalToCreditsResultRepository metalToCreditsResultRepository;
    private final InvalidQueryHandler invalidQueryHandler;

    public UserInputHandler(GalacticRomanRepository galacticRomanRepository,
                            MetalCreditsRepository metalCreditsRepository,
                            GalacticToCreditsResultRepository galacticToCreditsResultRepository,
                            MetalToCreditsResultRepository metalToCreditsResultRepository,
                            InvalidQueryHandler invalidQueryHandler) {

        this.galacticRomanRepository = galacticRomanRepository;
        this.metalCreditsRepository = metalCreditsRepository;
        this.galacticToCreditsResultRepository = galacticToCreditsResultRepository;
        this.metalToCreditsResultRepository = metalToCreditsResultRepository;
        this.invalidQueryHandler = invalidQueryHandler;
    }

    public void handleUserInput(String userInput) {

        if(hasMatchingRegex("^\\w+ is [IVXLCDM]$", userInput)){
            this.galacticRomanRepository.storeGalacticRomanValues(userInput);

        }else if (hasMatchingRegex(" is \\d+ Credits$", userInput)){
            this.metalCreditsRepository.storeMetalCreditValues(userInput);

        }else if (hasMatchingRegex("^How much is ", userInput)){
            this.galacticToCreditsResultRepository.storeGalacticToCreditsResult(userInput);

        }else if (hasMatchingRegex("^How many Credits is ", userInput)){
            this.metalToCreditsResultRepository.storeMetalToCreditsResult(userInput);

        }else{
            this.invalidQueryHandler.addInvalidQueryToResult();
        }
    }

    public boolean hasMatchingRegex (String regex, String sentence){
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sentence).find();
    }

    // if two words separated by "is"
    // if sentence ends with "is" \number\ "Credits"
    // if sentence starts with "How much is"
    // if sentence starts with "How many credits is"
}
