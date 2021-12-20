package com.itemis;

import java.util.*;
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
            if (hasMatchingRegex("^\\s*\\w+\\s*is\\s* [IVXLCDM]\\s*$", userInput)) {
                List<String> userElements = removeEmptySpaces(Arrays.asList(userInput.split(" ")));
                this.galacticRomanRepository.storeGalacticRomanValues(userElements.get(0), userElements.get(2));
                return;
            }

            if (hasMatchingRegex("\s*is\s*\\d+\s*Credits$", userInput)) {
                List<String> userElements = removeEmptySpaces(Arrays.asList(userInput.split(" ")));
                List<String> galacticElements = userElements.subList(0, userElements.size() - 4);
                String metal = userElements.get(userElements.size() - 4);
                int credits = parseInt(userElements.get(userElements.size() - 2));
                metalService.storeMetalElements(galacticElements, metal, credits);

                //this.metalCreditsRepository.storeMetalCreditValues(metal, calculateMetalCredits(userInputElements));
                return;
            }

            if (hasMatchingRegex("^\s*How\s*much\s*is\s*", userInput)) {
                List<String> userElements = removeEmptySpaces(Arrays.asList(userInput.split(" ")));
                List<String> galacticElements = userElements.subList(3, userElements.size());
                galacticService.handleGalacticElements(galacticElements);

                //this.galacticToCreditsResultRepository.storeGalacticToCreditsResult(userInput);
                return;
            }

            if (hasMatchingRegex("^\s*How\s*many\s*Credits\s*is\s*", userInput)) {
                List<String> userElements = removeEmptySpaces(Arrays.asList(userInput.split(" ")));
                List<String> galacticElements = userElements.subList(4, userElements.size()-1);
                String metal = userElements.get(userElements.size()-1);
                metalService.handleMetalElements(galacticElements, metal);
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

    private List<String> removeEmptySpaces(List<String> userInput){
        List<String> noSpacesUserInput = new ArrayList<>();
        for (String s : userInput){
            if(s.equals("")){continue;}
            noSpacesUserInput.add(s);
        }
        return noSpacesUserInput;
    }

}
