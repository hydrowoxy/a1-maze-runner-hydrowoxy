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

    protected abstract List<Character> algorithm(Point p);
    
    public List<Character> pathComp() {
        return algorithm(start);
    }

}

