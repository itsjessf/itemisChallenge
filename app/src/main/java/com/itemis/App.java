/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.itemis;

import java.io.PrintStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {


        RomanExpressionBuilder romanExpressionBuilder = new RomanExpressionBuilder(
                new GalacticRomanRepository());

        MetalCreditsRepository metalCreditsRepository = new MetalCreditsRepository(
                new RomanToCreditsCalculator(),
                romanExpressionBuilder);

        MetalToCreditsCalculator metalToCreditsCalculator = new MetalToCreditsCalculator(
                new RomanToCreditsCalculator(),
                romanExpressionBuilder, metalCreditsRepository);

        GalacticToCreditsCalculator galacticToCreditsCalculator = new GalacticToCreditsCalculator(
                romanExpressionBuilder,
                new RomanToCreditsCalculator());

        GalacticToCreditsResultRepository galacticToCreditsResultRepository = new GalacticToCreditsResultRepository(galacticToCreditsCalculator);

        MetalToCreditsResultRepository metalToCreditsResultRepository = new MetalToCreditsResultRepository(metalToCreditsCalculator);

        ResultDisplayer resultDisplayer = new ResultDisplayer(galacticToCreditsResultRepository, metalToCreditsResultRepository);

        InvalidQueryHandler invalidQueryHandler = new InvalidQueryHandler(resultDisplayer);

        UserInputHandler userInputHandler = new UserInputHandler(
                new GalacticRomanRepository(),
                metalCreditsRepository,
                galacticToCreditsResultRepository,
                metalToCreditsResultRepository,
                invalidQueryHandler,
                romanExpressionBuilder,
                new RomanToCreditsCalculator());

        UserInputReader userInputReader = new UserInputReader(
                new Scanner(System.in),
                userInputHandler,
                resultDisplayer,
                new PrintStream(System.out));

        userInputReader.readUserInput();
    }
}
