/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.itemis;

import java.io.PrintStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        GalacticRomanRepository galacticRomanRepository = new GalacticRomanRepository();
        GalacticToRomanExpressionMapper galacticToRomanExpressionMapper = new GalacticToRomanExpressionMapper(
                galacticRomanRepository);
        AnswersRepository answersRepository = new AnswersRepository();
        InvalidQueryHandler invalidQueryHandler = new InvalidQueryHandler(answersRepository);
        RomanToCreditsCalculator romanToCreditsCalculator = new RomanToCreditsCalculator(invalidQueryHandler);
        MetalCreditsRepository metalCreditsRepository = new MetalCreditsRepository(
                romanToCreditsCalculator,
                galacticToRomanExpressionMapper);


        ResultDisplayer resultDisplayer = new ResultDisplayer(answersRepository);


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

        UserInputReader userInputReader = new UserInputReader(
                new Scanner(System.in),
                userInputHandler,
                resultDisplayer,
                new PrintStream(System.out));

        userInputReader.readUserInput();

    }
}
