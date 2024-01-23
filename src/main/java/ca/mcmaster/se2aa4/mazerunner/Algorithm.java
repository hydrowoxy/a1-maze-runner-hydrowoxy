package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

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

}
