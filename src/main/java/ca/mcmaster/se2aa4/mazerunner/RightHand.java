package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;

public class RightHand {

    private char[][] mazeArr;
    private Point start;
    private int endCol;

    private char[] dir = { 'N', 'E', 'S', 'W' };
    private int dirIdx = 1;
    private char fwd = dir[dirIdx];
    
    private List<Character> steps;

    public RightHand(Maze maze) {
        this.mazeArr = maze.getMazeArr();
        this.start = maze.getStartPoint();
        this.endCol = maze.getEndCol();
        this.steps = new ArrayList<>();
    }

    private List<Character> rightHand(Point p) {

        while (p.x != endCol) {
            if(toRight(p) == '#'){
                if (toFwd(p) != '#') {
                    moveFwd(p);
                    steps.add('F');
                }else if(toLeft(p) != '#'){
                    turnLeft();
                    steps.add('L');
                }else{
                    turnRight();
                    steps.add('R');
                    turnRight();
                    steps.add('R');
                }
            }else if (toRight(p) != '#'){
                turnRight();
                steps.add('R');
                moveFwd(p);
                steps.add('F');
            }
        }

        return steps;
    }

    public List<Character> pathComp() {
        return rightHand(start);
    }

    private void turnRight() {
        dirIdx = (dirIdx + 1) % 4;
        fwd = dir[dirIdx];
    }

    private void turnLeft() {
        dirIdx = (dirIdx + 3) % 4;
        fwd = dir[dirIdx];
    }

    private char toRight(Point p) {
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

    private char toLeft(Point p) {
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

    private void moveFwd(Point p) {
        //mazeArr[p.y][p.x] = 'X';

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

        //printMazeState();
    }

    /* 
    private void printMazeState() {
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                System.out.print(mazeArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    */
    
}

