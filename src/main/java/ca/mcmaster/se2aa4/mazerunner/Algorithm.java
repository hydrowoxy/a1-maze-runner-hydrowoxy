package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public abstract class Algorithm extends Traversal {

    protected List<Character> steps;

    //constructor also has steps in addition to the stuff it inherits from traversal
    // it should already inherit all the other stuff from traversal but need to explicitly declare super maze
    // since it's a parametrized constructor, not a default one, in traversal superclass
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
