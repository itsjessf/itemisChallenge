package com.itemis;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInputReaderTests {

    @Mock
    private Scanner scanner;

    private UserInputHandler userInputHandler;
    @Mock
    private ResultDisplayer resultDisplayer;
    @Mock
    private PrintStream consoleOutput;
    @InjectMocks
    private UserInputReader userInputReader;

    @Before
    public void beforeEach() {
        RomanExpressionBuilder romanExpressionBuilder = new RomanExpressionBuilder(
                new GalacticRomanRepository());

        MetalCreditsRepository metalCreditsRepository = new MetalCreditsRepository(
                new RomanToCreditsCalculator(),
                romanExpressionBuilder);

        GalacticToCreditsCalculator galacticToCreditsCalculator = new GalacticToCreditsCalculator(
                romanExpressionBuilder,
                new RomanToCreditsCalculator());

        MetalToCreditsCalculator metalToCreditsCalculator = new MetalToCreditsCalculator(
                new RomanToCreditsCalculator(),
                romanExpressionBuilder, metalCreditsRepository);
        GalacticToCreditsResultRepository galacticToCreditsResultRepository = new GalacticToCreditsResultRepository(galacticToCreditsCalculator);

        MetalToCreditsResultRepository metalToCreditsResultRepository = new MetalToCreditsResultRepository(metalToCreditsCalculator);

        InvalidQueryHandler invalidQueryHandler = new InvalidQueryHandler(new ResultDisplayer(galacticToCreditsResultRepository, metalToCreditsResultRepository));

        userInputHandler = new UserInputHandler(
                new GalacticRomanRepository(),
                metalCreditsRepository,
                galacticToCreditsResultRepository,
                metalToCreditsResultRepository,
                invalidQueryHandler,
                romanExpressionBuilder,
                new RomanToCreditsCalculator());
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
    public void whenUserInputIsValid_ShouldCallUserInputHandler(){
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("blob is I").thenReturn("exit");
        userInputReader.readUserInput();
        verify(this.userInputHandler, times(1)).handleUserInput("blob is I");
    }

    @Test
    public void whenConsoleIsClosed_ShouldCallResultDisplayer() {
        when(this.scanner.hasNextLine()).thenReturn(true);
        when(this.scanner.nextLine()).thenReturn("exit");
        userInputReader.readUserInput();
        verify(this.resultDisplayer, times(1)).displayResult();
    }

}