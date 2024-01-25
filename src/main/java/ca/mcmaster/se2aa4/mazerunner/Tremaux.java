package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Tremaux extends Algorithm {

    public Tremaux(Maze maze) {
        super(maze);
        this.steps = new ArrayList<>();
    }

    @Override
    protected List<Character> algorithm(Point p) {
        System.out.println("START IS" + start);

        while (p.x != endCol) {
            printMazeState();

            if (atJunction(p)) {
                handleJunction(p);
            } else {
                handleNonJunction(p);
            }
        }

        retraceSteps(start);
        return steps;
    }

    private void handleJunction(Point p) {
        System.out.println("at a junction");
        turnRight();
        turnRight();

        if (toFwd(p) == ' ') {
            moveFwd(p);
            System.out.println("turned and went back to entrance");
            markSingleCrossed(p);
            printMazeState();
            System.out.println("marked the entrance");
            turnRight();
            turnRight();
            moveFwd(p);
            System.out.println("returned to junction");
        } else {
            turnRight();
            turnRight();
        }

        if (isPathBlank(p)) {
            System.out.println("at least one path in proximity is blank");
            goBlank(p);
        } else {
            System.out.println("no paths in front of me are blank");
            doubleCross(p);
        }
    }

    private boolean isPathBlank(Point p) {
        return toFwd(p) == ' ' || toRight(p) == ' ' || toLeft(p) == ' ';
    }

    private void goBlank(Point p) {
        System.out.println("GOBLANK");

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
        printMazeState();
    }

    private void doubleCross(Point p) {
        System.out.println("DOUBLECROSS");

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
        printMazeState();
    }

    private void markDoubleCrossed(Point p) {
        System.out.println("MARKDOUBLECROSSED");
        mazeArr[(int) p.getY()][(int) p.getX()] = 'X';
    }

    private void markSingleCrossed(Point p) {
        System.out.println("MARKSINGLECROSSED");
        mazeArr[(int) p.getY()][(int) p.getX()] = 'O';
    }

    private void handleNonJunction(Point p) {
        System.out.println("not at a junction");
        if (toFwd(p) == 'O') {
            System.out.println("there's an O in front of me");
            moveFwd(p);
            markDoubleCrossed(p);
            System.out.println("marked it as an X and moved to it");
        } else {
            if (toFwd(p) == ' ') {
                System.out.println("there's a blank space in front of me");
                moveFwd(p);
                System.out.println("moved fwd once");
            } else if (toFwd(p) == '#') {
                System.out.println("DEAD END");
                turnRight();
                turnRight();
                System.out.println("turned around in place");
            }
        }
    }

    private boolean atJunction(Point p) {
        System.out.println("in ATJUNCTION check");
        if (p.x == 0) {
            System.out.println("ISJUNCTION p==start");
            return false;
        }

        System.out.println("ISJUNCTION p!=star");
        int countPaths = 0;
        if (toFwd(p) != '#') {
            countPaths++;
        }
        if (toRight(p) != '#') {
            countPaths++;
        }
        if (toBehind(p) != '#' && toFwd(p) == '#') {
            countPaths++;
        }
        if (toLeft(p) != '#') {
            countPaths++;
        }

        return countPaths > 1;
    }

    private void retraceSteps(Point p){
        
    }

    private void printMazeState() {
        for (int i = 0; i < mazeArr.length; i++) {
            for (int j = 0; j < mazeArr[i].length; j++) {
                System.out.print(mazeArr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
