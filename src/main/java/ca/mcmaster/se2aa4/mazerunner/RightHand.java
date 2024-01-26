 /*
 * Link I used to learn about the Right-Hand algorithm: https://en.wikipedia.org/wiki/Maze-solving_algorithm
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.awt.Point;

import java.util.ArrayList;

public class RightHand extends Algorithm {

    /**
     * Constructor
     * 
     * @param maze The maze to solve
     */
    public RightHand(Maze maze) {
        super(maze);
        this.steps = new ArrayList<>();
    }

     /**
     * Implements the Right-Hand algorithm to find a path through the maze.
     * 
     * @param start The starting point in the maze.
     * @return A list of characters representing the steps taken to solve the maze.
     */
    @Override
    protected List<Character> algorithm(Point p) {

        while (p.x != endCol) {
            if(toRight(p) == '#'){
                if (toFwd(p) != '#') {
                    moveFwd(p);
                    steps.add(FORWARD);
                }else if(toLeft(p) != '#'){
                    turnLeft();
                    steps.add(LEFT);
                }else{
                    turnRight();
                    steps.add(RIGHT);
                    turnRight();
                    steps.add(RIGHT);
                }
            }else if (toRight(p) != '#'){
                turnRight();
                steps.add(RIGHT);
                moveFwd(p);
                steps.add(FORWARD);
            }
        }

        return steps;

    }
    
}

