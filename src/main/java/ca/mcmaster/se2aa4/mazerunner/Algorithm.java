package ca.mcmaster.se2aa4.mazerunner;
import java.util.List;
import java.awt.Point;

public abstract class Algorithm extends Traversal {

    protected List<Character> steps;

    protected static final char FORWARD = 'F';
    protected static final char RIGHT = 'R';
    protected static final char LEFT = 'L';

    public Algorithm(Maze maze) { super(maze); }

    protected abstract List<Character> algorithm(Point p);

    public List<Character> pathComp() { return algorithm(start); }

}

