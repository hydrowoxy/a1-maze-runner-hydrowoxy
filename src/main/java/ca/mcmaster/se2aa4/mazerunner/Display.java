package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Display {

     /**
     * Displays a list of characters representing a path in factorized form.
     *
     * @param steps A list of characters describing a path
     */
    public static void factorDisp(List<Character> steps) {
        if (steps.isEmpty()) {return;}
        StringBuilder result = new StringBuilder();
    
        char currentChar = steps.get(0);
        int count = 1;
    
        for (int i = 1; i < steps.size(); i++) {
            // Same char
            if (steps.get(i) == currentChar) {
                count++;
            // Different char
            } else {
                if (count > 1) {result.append(count);}
                result.append(currentChar);
                count = 1;
                currentChar = steps.get(i);
            }
        }
        // Last character
        if (count > 1) {result.append(count);}
        result.append(currentChar);
        System.out.println(result.toString());
    }
}
