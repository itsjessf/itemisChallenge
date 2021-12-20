package com.itemis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UserInputHandlerTests {

    private GalacticRomanRepository galacticRomanRepository;
    private AnswersRepository answersRepository;
    private MetalCreditsRepository metalCreditsRepository;

    private UserInputHandler userInputHandler;

    @Before
    public void before() {
        galacticRomanRepository = new GalacticRomanRepository();

        GalacticToRomanExpressionMapper galacticToRomanExpressionMapper = new GalacticToRomanExpressionMapper(
                galacticRomanRepository);

        answersRepository = new AnswersRepository();
        InvalidQueryHandler invalidQueryHandler = new InvalidQueryHandler(answersRepository);
        RomanToCreditsCalculator romanToCreditsCalculator = new RomanToCreditsCalculator(invalidQueryHandler);
        metalCreditsRepository = new MetalCreditsRepository(
                romanToCreditsCalculator,
                galacticToRomanExpressionMapper);

        MetalService metalService = new MetalService(metalCreditsRepository,
                galacticRomanRepository,
                galacticToRomanExpressionMapper,
                romanToCreditsCalculator,
                answersRepository);

        GalacticService galacticService = new GalacticService(galacticRomanRepository,
                romanToCreditsCalculator,
                galacticToRomanExpressionMapper,
                answersRepository);

        userInputHandler = new UserInputHandler(
                galacticRomanRepository,
                invalidQueryHandler,
                metalService,
                galacticService);

    }

    @Test
    public void whenUserInputsValidGalacticRomanValue_StoreInRepository() {
        //when user inputs valid galactic-credits, store the values in galacticRomanRepository
        userInputHandler.handleUserInput("glob is I");
        assertEquals("I", galacticRomanRepository.getGalacticRomanValues("glob"));
        userInputHandler.handleUserInput("bli is X");
        assertEquals("X", galacticRomanRepository.getGalacticRomanValues("bli"));
    }

    @Test
    public void whenUserInputsInvalidGalacticRomanValue_StoreErrorMessageInAnswerRepository() {
        //when user inputs invalid galactic-credits, store the error message in AnswerRepo
        userInputHandler.handleUserInput("glob is H");
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
    }

    @Test
    public void whenUserInputsValidGalacticMetalCreditsValue_StoreInRepository() {
        //when user inputs valid galactic-credits, store the values in galacticRomanRepository
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("glob glob Silver is 20 Credits");
        assertEquals(10, metalCreditsRepository.getMetalCredits("Silver"), 0.0f);
        assertTrue(metalCreditsRepository.isMetal("Silver"));

    }

    @Test
    public void whenUserInputsInvalidGalacticMetalCreditsValue_StoreErrorMessageInAnswerRepository() {
        //when user inputs invalid galactic-credits, store the error message in AnswerRepo
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("invalid glob Silver is 20 Credits");
        userInputHandler.handleUserInput("glob Silver is invalid Credits");
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(1));
    }

    @Test
    public void whenUserInputsValidGalacticExpression_StoreInAnswerRepository() {
        //when user inputs valid galactic expression, store the answer on answerrepo
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("pish is X");
        userInputHandler.handleUserInput("How much is glob glob");
        userInputHandler.handleUserInput("How much is glob pish");
        assertEquals("glob glob is 2", answersRepository.getAnswers().get(0));
        assertEquals("glob pish is 9", answersRepository.getAnswers().get(1));
    }

    @Test
    public void whenUserInputsInvalidGalacticExpression_StoreErrorMessageInAnswerRepository() {
        //when user inputs invalid galactic expression, store the error message in answerrepo
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("pish is L");
        userInputHandler.handleUserInput("xae is X");
        userInputHandler.handleUserInput("deamon is D");
        userInputHandler.handleUserInput("How much is glob glob glob glob");
        userInputHandler.handleUserInput("How much is glob pish");
        userInputHandler.handleUserInput("How much is invalid invalis");
        userInputHandler.handleUserInput("How much is xae deamon glob");
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(1));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(2));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(3));
    }

    @Test
    public void whenUserInputsValidGalacticMetalExpression_StoreInRepository() {
        //when user inputs valid galactic-metal, store the answer in answer repository
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("glob Silver is 10 Credits");
        userInputHandler.handleUserInput("How many credits is glob glob Silver");
        userInputHandler.handleUserInput("How many credits is glob glob glob Silver");
        assertEquals("glob glob Silver is 20 Credits", answersRepository.getAnswers().get(0));
        assertEquals("glob glob glob Silver is 30 Credits", answersRepository.getAnswers().get(1));
    }

    @Test
    public void whenUserInputsInvalidGalacticMetalExpression_StoreErrorMessageInAnswerRepository() {
        //when user inputs invalid galactic-metal, store the error message in answerrepo
        userInputHandler.handleUserInput("glob is I");
        userInputHandler.handleUserInput("pish is L");
        userInputHandler.handleUserInput("xae is X");
        userInputHandler.handleUserInput("deamon is D");
        userInputHandler.handleUserInput("glob Silver is 10 Credits");
        userInputHandler.handleUserInput("How many credits is invalid Silver");
        userInputHandler.handleUserInput("How many credits is glob invalid");
        userInputHandler.handleUserInput("How many credits is glob glob glob glob Silver");
        userInputHandler.handleUserInput("How many credits is xae deamon glob Sliver");
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(0));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(1));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(2));
        assertEquals("I have no idea what you are talking about", answersRepository.getAnswers().get(3));
    }

    @Test
    public void theUserInputCanHaveEmptySpaces(){
        userInputHandler.handleUserInput("glob   is  I");
        assertEquals("I", galacticRomanRepository.getGalacticRomanValues("glob"));
        userInputHandler.handleUserInput("glob   glob Silver is 20 Credits");
        assertEquals(10, metalCreditsRepository.getMetalCredits("Silver"), 0.0f);
        userInputHandler.handleUserInput("glob glob glob Gold is 30 credits");
        userInputHandler.handleUserInput("how many credits is glob    glob Gold");
        assertEquals("glob glob Gold is 20 Credits", answersRepository.getAnswers().get(0));
        assertEquals(10, metalCreditsRepository.getMetalCredits("Gold"), 0.0f);
        userInputHandler.handleUserInput("how much is glob   glob");
        assertEquals("glob glob is 2", answersRepository.getAnswers().get(1));
        userInputHandler.handleUserInput("how many credits is glob    glob Silver");
        assertEquals("glob glob Silver is 20 Credits", answersRepository.getAnswers().get(2));
    }

}
