package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

public class Path extends Traversal {

    private String inputPath;

    public Path(String inputPath, Maze maze){ 
        super(maze);
        this.inputPath = inputPath;
    }

    private void runPath(Point p, String pathStr) {
        pathStr = clean(pathStr);

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

    private String clean(String pathStr) {
        pathStr = pathStr.replaceAll("\\s", "");
        if (pathStr == null || pathStr.isEmpty() || !pathStr.matches(".*\\d.*")) {return pathStr;}
    
        StringBuilder result = new StringBuilder();
        StringBuilder countStr = new StringBuilder();
    
        for (char c : pathStr.toCharArray()) {
            if (Character.isDigit(c)) {countStr.append(c);} 
            
            else {

                if (countStr.length() > 0) {

                    int count = Integer.parseInt(countStr.toString());
                    for (int i = 0; i < count; i++) {result.append(c);}
                    countStr.setLength(0); 

                } else {result.append(c);}

            }
        }
        return result.toString();
    }
    
    public void verify(){
        runPath(start, inputPath);
    }

}