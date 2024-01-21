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
                System.out.println("Right is a wall");
                if (toFwd(p) != '#') {
                    System.out.println("GO FORWARD");
                    moveFwd(p);
                    steps.add('F');
                }else if(toLeft(p) != '#'){
                    System.out.println("LEFT");
                    turnLeft();
                    steps.add('L');
                }else{
                    System.out.println("TURN AROUND");
                    turnRight();
                    steps.add('R');
                    turnRight();
                    steps.add('R');
                }
            }else if (toRight(p) != '#'){
                System.out.println("Right isnt a wall");
                System.out.println("TURN RIGHT");
                turnRight();
                steps.add('R');
                System.out.println("GO FORWARD");
                moveFwd(p);
                steps.add('F');
            }
        }

        return steps;
    }

    public List<Character> pathComp() {
        System.out.println("START: " + start);
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
        System.out.println("fgoing fwd");
        switch (fwd) {
            case 'N':
                System.out.println("facing n");
                return mazeArr[p.y - 1][p.x];
            case 'E':
                System.out.println("facing E");
                return mazeArr[p.y][p.x + 1];
            case 'S':
                System.out.println("facing S");
                return mazeArr[p.y + 1][p.x];
            default:
                System.out.println("idk west");
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
        mazeArr[p.y][p.x] = 'X';

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

        printMazeState();
        System.out.println(p);
    }

    
    private void printMazeState() {
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                System.out.print(mazeArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Facing direction: " + fwd);
        System.out.println();
    }

    public void canonDisp() {
        StringBuilder result = new StringBuilder();
        for (char step : steps) {
            result.append(step);
        }
        System.out.println(result.toString());
    }
    
}

