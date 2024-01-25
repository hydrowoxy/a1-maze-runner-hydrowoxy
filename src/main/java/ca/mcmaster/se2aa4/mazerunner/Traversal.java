package ca.mcmaster.se2aa4.mazerunner;
import java.awt.Point;

public abstract class Traversal {
    protected char[][] mazeArr;
    protected Point start;
    protected int endCol;

    protected enum Directions{
        NORTH, EAST, SOUTH, WEST
    }

    protected Directions fwd = Directions.EAST;

    public Traversal(Maze maze){
        this.mazeArr = maze.getMazeArr();
        this.start = maze.getStartPoint();
        this.endCol = maze.getEndCol();
    }

    protected void turnRight() {
        fwd = Directions.values()[(fwd.ordinal() + 1) % 4];
    }

    protected void turnLeft() {
        fwd = Directions.values()[(fwd.ordinal() + 3) % 4];

    }

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
