package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

public class Path {

    private String inputPath;
    private char[][] mazeArr;
    private Point start;
    private int endCol;

    private char[] dir = { 'N', 'E', 'S', 'W' };
    private int dirIdx = 1;
    private char fwd = dir[dirIdx];
    
    public Path(String inputPath, Maze maze){ //in future inherit all from traversal but also has inputpath, and takes in inputpath too
        this.mazeArr = maze.getMazeArr();
        this.start = maze.getStartPoint();
        this.endCol = maze.getEndCol();
        this.inputPath = inputPath;
    }

    public void runPath(Point p, String pathStr) {
        for (char instruction : pathStr.toCharArray()) {
            switch (instruction) {
                case 'R':
                        turnRight();
                        break;
                case 'F':
                    if(toFwd(p) != '#'){
                        moveFwd(p);
                        break;
                    }else{break;}
                case 'L':
                        turnLeft();
                        break;
                default:
                    break;
            }
        }
    
        if (p.x == endCol) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }
    
    public void verify(){
        runPath(start, inputPath);
    }

    private void turnRight() {
        dirIdx = (dirIdx + 1) % 4;
        fwd = dir[dirIdx];
    }

    private void turnLeft() {
        dirIdx = (dirIdx + 3) % 4;
        fwd = dir[dirIdx];
    }

    private char toFwd(Point p) {
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

    private void moveFwd(Point p) {
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
