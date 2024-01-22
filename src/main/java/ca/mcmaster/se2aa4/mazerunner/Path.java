package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

public class Path extends Traversal {

    private String inputPath;

    public Path(String inputPath, Maze maze){ //in future inherit all from traversal but also has inputpath, and takes in inputpath too
        super(maze);
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

}
