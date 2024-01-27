/*
* Link I used to learn about Tremaux's algorithm: https://en.wikipedia.org/wiki/Maze-solving_algorithm
*/

package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

public class Tremaux extends Algorithm {
    Point initialPoint = new Point(start);

    /**
     * Constructor
     * 
     * @param maze The maze to solve.
     */
    public Tremaux(Maze maze) {
        super(maze);
        this.steps = new ArrayList<>();
    }

    /**
     * Implements the Tremaux algorithm to find a path through the maze.
     * 
     * @param start The starting point in the maze.
     * @return A list of characters representing the steps taken to solve the maze.
     */
    @Override
    protected List<Character> algorithm(Point p) {
        while (p.x != endCol) {
            if (atJunction(p)) {
                handleJunction(p);
            } else {
                handleNonJunction(p);
            }
        }
        retraceSteps(initialPoint);
        return steps;
    }

    /**
     * Checks if the current point is at a junction in the maze.
     * 
     * @param p The current point in the maze.
     * @return True if the current point is at a junction; false otherwise.
     */
    private boolean atJunction(Point p) {
        // Avoid going out of bounds by checking behind yourself at the start point
        // Start tile is never a junction
        if (p.x == 0) {
            return false;
        }

        int countPaths = 0;

        if (toFwd(p) != '#') {
            countPaths++;
        }
        if (toRight(p) != '#') {
            countPaths++;
        }
        // A straight line with front and back available is not a junction, 
        // but a corner is
        if (toBehind(p) != '#' && toFwd(p) == '#') {
            countPaths++;
        }
        if (toLeft(p) != '#') {
            countPaths++;
        }

        return countPaths > 1;
    }

    /**
     * Handles the Tremaux logic when the explorer is at a junction.
     * 
     * @param p The current point in the maze.
     */
    private void handleJunction(Point p) {

        // Mark the entrance as single-crossed if it's blank
        if (toBehind(p) == ' ') {
            turnRight();
            turnRight();
            moveFwd(p);
            markSingleCrossed(p);
            turnRight();
            turnRight();
            moveFwd(p);
        }

        // Prefer to go to a blank tile otherwise double-cross
        if (anyPossibleBlank(p)) {
            goBlank(p);
        } else {
            doubleCross(p);
        }
    }

    /**
     * Handles the Tremaux logic when the explorer isn't at a junction.
     * 
     * @param p The current point in the maze.
     */
    private void handleNonJunction(Point p) {
        char tile = toFwd(p);

        switch (tile) {
            // Forward clear, go forward
            case ' ':
                moveFwd(p);
                break;
            // Forward has been crossed once, go forward and double-cross
            case 'O':
                moveFwd(p);
                markDoubleCrossed(p);
                break;
            // Wall, turn around
            case '#':
                turnRight();
                turnRight();
                break;
            default:
                break;
        }
    }

    /**
     * Looks around given point to see if any directions have never been crossed.
     * 
     * @param p The current point in the maze.
     * @return true if at least one possible direction is empty; false otherwise.
     */
    private boolean anyPossibleBlank(Point p) {
        return toFwd(p) == ' ' || toRight(p) == ' ' || toLeft(p) == ' ';
    }

    /**
     * Moves the algorithm forward on a blank path from the given point.
     * Marks the path as once-crossed ('O').
     * 
     * @param p The current point in the maze.
     */
    private void goBlank(Point p) {

        if (toFwd(p) == ' ') {
            moveFwd(p);
        } else if (toRight(p) == ' ') {
            turnRight();
            moveFwd(p);
        } else if (toLeft(p) == ' ') {
            turnLeft();
            moveFwd(p);
        }

        markSingleCrossed(p);
    }

    /**
     * Moves the algorithm forward on a once-crossed path from the given point.
     * Marks the path as double-crossed ('X').
     * 
     * @param p The current point in the maze.
     */
    private void doubleCross(Point p) {

        // Prefer to go back the way you came
        if (toBehind(p) == 'O') {
            turnRight();
            turnRight();
            moveFwd(p);
        } else if (toFwd(p) == 'O') {
            moveFwd(p);
        } else if (toRight(p) == 'O') {
            turnRight();
            moveFwd(p);
        } else if (toLeft(p) == 'O') {
            turnLeft();
            moveFwd(p);
        }

        markDoubleCrossed(p);
    }

    private void markDoubleCrossed(Point p) {
        mazeArr[(int) p.getY()][(int) p.getX()] = 'X';
    }

    private void markSingleCrossed(Point p) {
        mazeArr[(int) p.getY()][(int) p.getX()] = 'O';
    }

    /**
     * Retraces the steps taken by the algorithm to find the solution path.
     * 
     * @param p The initial starting point of the maze.
     */
    private void retraceSteps(Point p) {

        while (p.x != endCol) {

            // Prefer to follow Os
            if (toFwd(p) == 'O') {
                moveFwd(p);
                steps.add(FORWARD);
            } else if (toRight(p) == 'O') {
                turnRight();
                steps.add(RIGHT);
            } else if (toLeft(p) == 'O') {
                turnLeft();
                steps.add(LEFT);

            // Otherwise follow the blank spaces
            } else if (toFwd(p) == ' ') {
                moveFwd(p);
                steps.add(FORWARD);
            } else if (toRight(p) == ' ') {
                turnRight();
                steps.add(RIGHT);
            } else if (toLeft(p) == ' ') {
                turnLeft();
                steps.add(LEFT);

            // Dead end, turn around
            } else {
                turnRight();
                steps.add(RIGHT);
                turnRight();
                steps.add(RIGHT);
            }
        }

    }

}
