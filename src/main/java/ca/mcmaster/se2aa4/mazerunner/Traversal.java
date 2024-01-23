package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

public abstract class Traversal {
    protected char[][] mazeArr;
    protected Point start;
    protected int endCol;

    protected char[] dir = { 'N', 'E', 'S', 'W' };
    protected int dirIdx = 1;
    protected char fwd = dir[dirIdx];

    public Traversal(Maze maze){
        this.mazeArr = maze.getMazeArr();
        this.start = maze.getStartPoint();
        this.endCol = maze.getEndCol();
    }

    protected void turnRight() {
        dirIdx = (dirIdx + 1) % 4;
        fwd = dir[dirIdx];
    }

    protected void turnLeft() {
        dirIdx = (dirIdx + 3) % 4;
        fwd = dir[dirIdx];
    }

    protected char toRight(Point p) {
        switch (fwd) {
            case 'N':
                return mazeArr[p.y][p.x + 1];
            case 'E':
                return mazeArr[p.y + 1][p.x];
            case 'S':
                return mazeArr[p.y][p.x - 1];
            default:
                return mazeArr[p.y - 1][p.x];
        }
    }

    protected char toFwd(Point p) {
        switch (fwd) {
            case 'N':
                return mazeArr[p.y - 1][p.x];
            case 'E':
                return mazeArr[p.y][p.x + 1];
            case 'S':
                return mazeArr[p.y + 1][p.x];
            default:
                return mazeArr[p.y][p.x - 1];
        }
    }

    protected char toLeft(Point p) {
        switch (fwd) {
            case 'N':
                return mazeArr[p.y][p.x - 1];
            case 'E':
                return mazeArr[p.y - 1][p.x];
            case 'S':
                return mazeArr[p.y][p.x + 1];
            default:
                return mazeArr[p.y + 1][p.x];
        }
    }

    protected void moveFwd(Point p) {
        switch (fwd) {
            case 'N':
                p.y--;
                break;
            case 'E':
                p.x++;
                break;
            case 'S':
                p.y++;
                break;
            default:
                p.x--;
                break;
        }

    }

}
