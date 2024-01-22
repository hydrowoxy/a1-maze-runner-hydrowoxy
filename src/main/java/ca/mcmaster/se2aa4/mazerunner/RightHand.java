package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import java.awt.Point;

public class RightHand extends Algorithm {

    public RightHand(Maze maze) {
        super(maze);
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
    
}

