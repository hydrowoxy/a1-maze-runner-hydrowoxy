package ca.mcmaster.se2aa4.mazerunner;
import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class RightHand extends Algorithm {

    public RightHand(Maze maze) {
        super(maze);
        this.steps = new ArrayList<>();
    }

    @Override
    protected List<Character> algorithm(Point p) {

        while (p.x != endCol) {
            if(toRight(p) == '#'){
                if (toFwd(p) != '#') {
                    moveFwd(p);
                    steps.add(FORWARD);
                }else if(toLeft(p) != '#'){
                    turnLeft();
                    steps.add(LEFT);
                }else{
                    turnRight();
                    steps.add(RIGHT);
                    turnRight();
                    steps.add(RIGHT);
                }
            }else if (toRight(p) != '#'){
                turnRight();
                steps.add(RIGHT);
                moveFwd(p);
                steps.add(FORWARD);
            }
        }

        return steps;

    }
    
}

