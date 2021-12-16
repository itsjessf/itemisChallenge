package com.itemis;

import java.util.HashMap;


public class RomanToCreditsCalculator {

    private static final HashMap<String, Integer> romanToCreditsMap = new HashMap<>() {{
        put("I", 1);
        put("V", 5);
        put("X", 10);
        put("L", 50);
        put("C", 100);
        put("D", 500);
        put("M", 1000);

    }};

    public static int calculateRomanToCredits(String[] romanExpression) {

        int result = 0;
        int i = 0;

        for (i = 0; i < romanExpression.length; i++) {
            try {
                if (i + 1 == romanExpression.length) {
                    if(isForthIteration(romanExpression, i)){
                        result = 0;
                        break;
                    }
                    result = result + romanToCreditsMap.get(romanExpression[i]);
                } else if (romanToCreditsMap.get(romanExpression[i]) < romanToCreditsMap.get(romanExpression[i + 1]) && isValueSubtractable(romanExpression[i], romanExpression[i + 1])) {
                    //Next  value is higher and subtractable
                    result = result + subtractValue(romanExpression, i);
                    i = i + 1;
                } else if (romanToCreditsMap.get(romanExpression[i]) > romanToCreditsMap.get(romanExpression[i + 1])) {
                    //Next value is lower
                    result = result + romanToCreditsMap.get(romanExpression[i]);
                } else if (romanToCreditsMap.get(romanExpression[i]) == romanToCreditsMap.get(romanExpression[i + 1]) && isValueRepeatable(romanExpression[i]) && !isForthIteration(romanExpression, i)) {
                    //Next value is the same
                    result = result + romanToCreditsMap.get(romanExpression[i]);
                } else {
                    //String is not valid
                    System.out.println("The Roman Expression provided is not valid");
                    return 0;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                break;
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

    private static int subtractValue(String[] romanExpression, int i) {
        return (romanToCreditsMap.get(romanExpression[i + 1])) - (romanToCreditsMap.get(romanExpression[i]));
    }

    private static boolean isForthIteration(String[] romanExpression, int i) {
        if (i >= 3) {
            return (romanExpression[i].equals(romanExpression[i - 1]) && romanExpression[i].equals(romanExpression[i - 2]) && romanExpression[i].equals(romanExpression[i - 3]));
        }
        return false;
    }

}

