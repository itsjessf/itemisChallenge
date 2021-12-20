package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInputReaderTests {

    @Mock
    private Scanner scanner;

    private GalacticRomanRepository galacticRomanRepository;

    @Mock
    private PrintStream consoleOutput;
    private UserInputReader userInputReader;

    @Before
    public void before() {


        galacticRomanRepository = new GalacticRomanRepository();
        GalacticToRomanExpressionMapper galacticToRomanExpressionMapper = new GalacticToRomanExpressionMapper(
                galacticRomanRepository);
        RomanToCreditsCalculator romanToCreditsCalculator = new RomanToCreditsCalculator(new InvalidQueryHandler(new AnswersRepository()));


        MetalCreditsRepository metalCreditsRepository = new MetalCreditsRepository(
                romanToCreditsCalculator,
                galacticToRomanExpressionMapper);

        AnswersRepository answersRepository = new AnswersRepository();
        ResultDisplayer resultDisplayer = new ResultDisplayer(answersRepository);
        InvalidQueryHandler invalidQueryHandler = new InvalidQueryHandler(answersRepository);

        MetalService metalService = new MetalService(metalCreditsRepository,
                galacticRomanRepository,
                galacticToRomanExpressionMapper,
                romanToCreditsCalculator,
                answersRepository);

        GalacticService galacticService = new GalacticService(galacticRomanRepository,
                romanToCreditsCalculator,
                galacticToRomanExpressionMapper,
                answersRepository);

        UserInputHandler userInputHandler = new UserInputHandler(
                galacticRomanRepository,
                invalidQueryHandler,
                metalService,
                galacticService);

        userInputReader = new UserInputReader(scanner, userInputHandler, resultDisplayer, consoleOutput);
    }


    @Test
    public void whenUserTypesExit_ShouldCallClose() {
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("exit");
        userInputReader.readUserInput();
        when(this.scanner.nextLine()).thenReturn("Exit");
        userInputReader.readUserInput();
        verify(this.scanner, times(2)).close();
    }

    @Test
    public void whenUserInsertsEmptyInput_ShouldReturnMessage() {
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("").thenReturn("exit");
        userInputReader.readUserInput();
        verify(this.consoleOutput, times(1)).println("Type in your Galactic notes. Type 'exit' when you are done!");
    }

    @Test
    public void whenUserInsertsSpaces_ShouldReturnMessage() {
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("           ").thenReturn("exit");
        userInputReader.readUserInput();
        verify(this.consoleOutput, times(1)).println("Type in your Galactic notes. Type 'exit' when you are done!");
    }

    @Test
    public void whenUserInputIsValid_ShouldBeProcessedCorrectly(){
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("blob is I").thenReturn("exit");
        userInputReader.readUserInput();
        assertEquals("I", galacticRomanRepository.getGalacticRomanValues("blob"));
    }


}