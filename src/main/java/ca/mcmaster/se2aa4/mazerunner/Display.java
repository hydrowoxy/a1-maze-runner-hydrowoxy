package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Display {

    public static void canonDisp(List<Character> steps) {
        StringBuilder result = new StringBuilder();
        for (char step : steps) {
            result.append(step);
        }
        System.out.println(result.toString());
    }

    public static void factorDisp(List<Character> steps) {
        if (steps.isEmpty()) {return;}
        StringBuilder result = new StringBuilder();
    
        char currentChar = steps.get(0);
        int count = 1;
    
        for (int i = 1; i < steps.size(); i++) {
            if (steps.get(i) == currentChar) {
                count++;
            } else {
                if (count > 1) {result.append(count);}
                result.append(currentChar);
                count = 1;
                currentChar = steps.get(i);
            }
        }

        if (count > 1) {result.append(count);}
        result.append(currentChar);
        System.out.println(result.toString());
    }
}
