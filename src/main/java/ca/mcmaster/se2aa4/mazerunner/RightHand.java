package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        char r;
        char f;
        char l;

        while (!isExit(p)) {

            r = toRight(p);
            f = toFwd(p);
            l = toLeft(p);

            System.out.println("Current position: " + p + ", Direction: " + fwd);
            System.out.println("Right: " + r + ", Forward: " + f + ", Left: " + l);

            if (f == ' ') {
                System.out.println("fwd avail");
                moveFwd(p);
                steps.add('F');
            } else if (r == ' ') {
                System.out.println("right avail");
                turnRight();
                steps.add('R');
            } else if (f == 'X') {
                System.out.println("fwd avail");
                moveFwd(p);
                steps.add('F');
            } else if (r == 'X') {
                System.out.println("right avail");
                turnRight();
                steps.add('R');
            } else if (l == ' ') {
                System.out.println("left avail");
                turnLeft();
                steps.add('L');
            } else if (l == 'X') {
                System.out.println("left avail");
                turnLeft();
                steps.add('L');
            } else {
                System.out.println("double turn time");
                turnRight();
                steps.add('R');
                turnRight();
                steps.add('R');
            }

            System.out.println("New position: " + p + ", Direction: " + fwd);
        }

        return steps;
    }

    public List<Character> pathComp() {
        System.out.println("START IS" + start);
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
        if (fwd == 'N') {
            return mazeArr[p.y][p.x + 1];
        } else if (fwd == 'E') {
            return mazeArr[p.y + 1][p.x];
        } else if (fwd == 'S') {
            return mazeArr[p.y][p.x - 1];
        } else {
            return mazeArr[p.y - 1][p.x];
        }
    }

    private char toFwd(Point p) {
        if (fwd == 'N') {
            return mazeArr[p.y - 1][p.x];
        } else if (fwd == 'E') {
            return mazeArr[p.y][p.x + 1];
        } else if (fwd == 'S') {
            return mazeArr[p.y + 1][p.x];
        } else {
            return mazeArr[p.y][p.x - 1];
        }
    }

    private char toLeft(Point p) {
        if (fwd == 'N' && p.y - 1 >= 0) {
            return mazeArr[p.y - 1][p.x];
        } else if (fwd == 'E' && p.x + 1 < mazeArr[0].length) {
            return mazeArr[p.y][p.x + 1];
        } else if (fwd == 'S' && p.y + 1 < mazeArr.length) {
            return mazeArr[p.y + 1][p.x];
        } else {
            return mazeArr[p.y][p.x - 1];
        }
    }

    private void moveFwd(Point p) {
        mazeArr[p.y][p.x] = 'X';

        if (fwd == 'N')
            p.y--;
        else if (fwd == 'E')
            p.x++;
        else if (fwd == 'S')
            p.y++;
        else
            p.x--;

        printMazeState();
    }

    private boolean isExit(Point p) {
        return p.x == endCol;
    }

    private void printMazeState() {
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[0].length; j++) {
                System.out.print(mazeArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
