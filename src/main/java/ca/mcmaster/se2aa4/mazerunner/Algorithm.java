package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import java.awt.Point;

public abstract class Algorithm extends Traversal {

    protected List<Character> steps;

    /**
     * Constructor 
     * 
     * @param maze The maze to traverse using the algorithm
     */
    public Algorithm(Maze maze) { super(maze); }

    /**
     * To implement the algorithm logic 
     * 
     * @param p The starting point in the maze
     * @return The list of steps taken to solve the maze
     */
    protected abstract List<Character> algorithm(Point p);

    /**
     * Executes the logic to solve the maze
     * 
     * @return The list of steps taken to solve the maze
     */
    public List<Character> pathComp() { return algorithm(start); }

}

