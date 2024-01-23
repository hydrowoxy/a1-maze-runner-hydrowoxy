package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;

public abstract class Algorithm extends Traversal {

    protected List<Character> steps;

    public Algorithm(Maze maze){
        super(maze);
        this.steps = new ArrayList<>();
    }

    public void canonDisp() {
        StringBuilder result = new StringBuilder();
        for (char step : steps) {
            result.append(step);
        }
        System.out.println(result.toString());
    }

    public void factorDisp() {
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

    protected abstract List<Character> algorithm(Point p);
    
    public List<Character> pathComp() {
        return algorithm(start);
    }

}
