package com.itemis;

import java.util.HashMap;
import java.util.List;


public class RomanToCreditsCalculator {

    InvalidQueryHandler invalidQueryHandler;

    public RomanToCreditsCalculator(InvalidQueryHandler invalidQueryHandler) {
        this.invalidQueryHandler = invalidQueryHandler;
    }

    private static final HashMap<String, Integer> romanToCreditsMap = new HashMap<>() {{
        put("I", 1);
        put("V", 5);
        put("X", 10);
        put("L", 50);
        put("C", 100);
        put("D", 500);
        put("M", 1000);

    }};

    public int calculateRomanToCredits(List<String> romanExpression) {

        int result = 0;
        int i = 0;

        for (i = 0; i < romanExpression.size(); i++) {
            try {
                if (i + 1 == romanExpression.size()) {
                    if (isForthIteration(romanExpression, i)) {
                        throw new HandledException();
                    }
                    result = result + romanToCreditsMap.get(romanExpression.get(i));

                } else if (romanToCreditsMap.get(romanExpression.get(i)) < romanToCreditsMap.get(romanExpression.get(i + 1)) && isValueSubtractable(romanExpression.get(i), romanExpression.get(i + 1))) {
                    //Next  value is higher and subtractable
                    result = result + subtractValue(romanExpression, i);
                    i = i + 1;

                } else if (romanToCreditsMap.get(romanExpression.get(i)) > romanToCreditsMap.get(romanExpression.get(i + 1))) {
                    //Next value is lower
                    result = result + romanToCreditsMap.get(romanExpression.get(i));

                } else if (romanToCreditsMap.get(romanExpression.get(i)) == romanToCreditsMap.get(romanExpression.get(i + 1)) && isValueRepeatable(romanExpression.get(i)) && !isForthIteration(romanExpression, i)) {
                    //Next value is the same
                    result = result + romanToCreditsMap.get(romanExpression.get(i));
                } else {
                    throw new HandledException();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                return 0;
            } catch (HandledException exception) {
                invalidQueryHandler.addInvalidQueryToResult(exception.getMessage());
                return 0;
            }

        }
        return result;
    }

    private static boolean isValueSubtractable(String lowerRoman, String higherRoman) {
        switch (lowerRoman) {
            case "I":
                return higherRoman.equals("V") || higherRoman.equals("X");
            case "X":
                return higherRoman.equals("L") || higherRoman.equals("C");
            case "C":
                return higherRoman.equals("D") || higherRoman.equals("M");
            default:
                return false;
        }
    }

    private static boolean isValueRepeatable(String romanValue) {
        return romanValue.equals("I") || romanValue.equals("X") || romanValue.equals("C") || romanValue.equals("M");
    }

    private static int subtractValue(List<String> romanExpression, int i) {
        return (romanToCreditsMap.get(romanExpression.get(i + 1))) - (romanToCreditsMap.get(romanExpression.get(i)));
    }

    private static boolean isForthIteration(List<String> romanExpression, int i) {
        if (i >= 3) {
            return (romanExpression.get(i).equals(romanExpression.get(i - 1)) && romanExpression.get(i).equals(romanExpression.get(i - 2)) && romanExpression.get(i).equals(romanExpression.get(i - 3)));
        }
        return false;
    }

}

