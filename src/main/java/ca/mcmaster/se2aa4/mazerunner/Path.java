package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

public class Path extends Traversal {

    private String inputPath;

    public Path(String inputPath, Maze maze){ 
        super(maze);
        this.inputPath = inputPath;
    }

    public void runPath(Point p, String pathStr) {
        pathStr = pathStr.replaceAll("\\s", "");

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

    public void unFactor(){

    }
    
    public void verify(){
        runPath(start, inputPath);
    }

}

// could have a path class w unfactor, verify, verify implemented in path and unfactor implemented in path
// child class inherits from path and runs it but like...idk like it will always run it the same way so no point