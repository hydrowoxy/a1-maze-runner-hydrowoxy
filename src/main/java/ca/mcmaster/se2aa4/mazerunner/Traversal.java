/*
 * Link I used to learn about Point: https://docs.oracle.com/javase/8/docs/api/java/awt/Point.html
 */

package ca.mcmaster.se2aa4.mazerunner;
import java.awt.Point;

public abstract class Traversal {
    protected char[][] mazeArr;
    protected Point start;
    protected int endCol;

    protected enum Directions{
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * Choose how to represent each direction in the path string
     */
    protected static final char FORWARD = 'F';
    protected static final char RIGHT = 'R';
    protected static final char LEFT = 'L';

    protected Directions fwd = Directions.EAST;

    /**
     * Constructor
     * 
     * @param maze The maze to be explored
     */
    public Traversal(Maze maze){
        this.mazeArr = maze.getMazeArr();
        this.start = maze.getStartPoint();
        this.endCol = maze.getEndCol();
    }

    /**
     * Turns the explorer right
     */
    protected void turnRight() {
        fwd = Directions.values()[(fwd.ordinal() + 1) % 4];
    }

    /**
     * Turns the explorer left
     */
    protected void turnLeft() {
        fwd = Directions.values()[(fwd.ordinal() + 3) % 4];

    }

    /**
     * Checks what is to the right of the explorer
     * 
     * @param p The current point
     * @return The character to the right of the point
     */
    protected char toRight(Point p) {
        switch (fwd) {
            case NORTH:
                return mazeArr[p.y][p.x + 1];
            case EAST:
                return mazeArr[p.y + 1][p.x];
            case SOUTH:
                return mazeArr[p.y][p.x - 1];
            default:
                return mazeArr[p.y - 1][p.x];
        }
    }

    /**
     * Checks what is in front of the explorer 
     * 
     * @param p The current point
     * @return The character in front of the point
     */
    protected char toFwd(Point p) {
        switch (fwd) {
            case NORTH:
                return mazeArr[p.y - 1][p.x];
            case EAST:
                return mazeArr[p.y][p.x + 1];
            case SOUTH:
                return mazeArr[p.y + 1][p.x];
            default:
                return mazeArr[p.y][p.x - 1];
        }
    }

    /**
     * Checks what is to the left of the explorer
     * 
     * @param p The current point
     * @return The character to the left of the point
     */
    protected char toLeft(Point p) {
        switch (fwd) {
            case NORTH:
                return mazeArr[p.y][p.x - 1];
            case EAST:
                return mazeArr[p.y - 1][p.x];
            case SOUTH:
                return mazeArr[p.y][p.x + 1];
            default:
                return mazeArr[p.y + 1][p.x];
        }
    }

    /**
     * Checks what is behind the explorer 
     * 
     * @param p The current point
     * @return The character behind the point
     */
    protected char toBehind(Point p) {
        switch (fwd) {
            case NORTH:
                return mazeArr[p.y + 1][p.x];
            case EAST:
                return mazeArr[p.y][p.x - 1];
            case SOUTH:
                return mazeArr[p.y - 1][p.x];
            default:
                return mazeArr[p.y][p.x + 1];
        }
    }

    /**
     * Moves the explorer forwards
     * 
     * @param p The current point
     */
    protected void moveFwd(Point p) {
        switch (fwd) {
            case NORTH:
                p.y--;
                break;
            case EAST:
                p.x++;
                break;
            case SOUTH:
                p.y++;
                break;
            default:
                p.x--;
                break;
        }

    }

}

// Maybe make some variables for WALL, SPACE so it's not hard coded to recognize # and ' '