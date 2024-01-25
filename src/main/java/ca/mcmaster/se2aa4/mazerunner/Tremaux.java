package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Tremaux extends Algorithm {
    Point initialPoint = new Point(start);

    public Tremaux(Maze maze) {
        super(maze);
        this.steps = new ArrayList<>();
    }

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

    private void handleJunction(Point p) {
        turnRight();
        turnRight();

        if (toFwd(p) == ' ') {
            moveFwd(p);
            markSingleCrossed(p);
            turnRight();
            turnRight();
            moveFwd(p);
        } else {
            turnRight();
            turnRight();
        }

        if (isPathBlank(p)) {
            goBlank(p);
        } else {
            doubleCross(p);
        }
    }

    private boolean isPathBlank(Point p) {
        return toFwd(p) == ' ' || toRight(p) == ' ' || toLeft(p) == ' ';
    }

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

    private void doubleCross(Point p) {

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

    private void handleNonJunction(Point p) {
        if (toFwd(p) == 'O') {
            moveFwd(p);
            markDoubleCrossed(p);
        } else {
            if (toFwd(p) == ' ') {
                moveFwd(p);
            } else if (toFwd(p) == '#') {
                turnRight();
                turnRight();
            }
        }
    }

    private boolean atJunction(Point p) {
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
        if (toBehind(p) != '#' && toFwd(p) == '#') {
            countPaths++;
        }
        if (toLeft(p) != '#') {
            countPaths++;
        }

        return countPaths > 1;
    }

    private void retraceSteps(Point p){
        
        while (p.x != endCol) {
            if(toFwd(p) == 'O'){
                moveFwd(p);
                steps.add('F');
            }else if (toRight(p) == 'O'){
                turnRight();
                steps.add('R');
            }else if (toLeft(p) == 'O'){
                turnLeft();
                steps.add('L');
            }else if(toFwd(p) == ' '){
                moveFwd(p);
                steps.add('F');
            }else if(toRight(p) == ' '){
                turnRight();
                steps.add('R');
            }else if(toLeft(p) == ' '){
                turnLeft();
                steps.add('L');
            }else{ 
                turnRight();
                steps.add('R');
                turnRight();
                steps.add('R');
            }
        }

    }

}
