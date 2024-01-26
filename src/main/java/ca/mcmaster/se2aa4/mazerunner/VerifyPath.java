package ca.mcmaster.se2aa4.mazerunner;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerifyPath extends Traversal {

    private String inputPath;
    private static final Logger logger = LogManager.getLogger(VerifyPath.class);

    /**
     * Constructor
     * 
     * @param inputPath The path to verify.
     * @param maze The maze through which to verify the path.
     */
    public VerifyPath(String inputPath, Maze maze){ 
        super(maze);
        this.inputPath = inputPath;
    }

     /**
     * Executes the specified path in the maze starting from the given point.
     * 
     * @param p The starting point for the traversal.
     * @param pathStr The string representation of the path to be executed.
     */
    private void runPath(Point p, String pathStr) {

        if(!isValid(pathStr)) { 
            logger.error("Invalid path input."); 
        } else {
            pathStr = clean(pathStr);

            // Step through the given path
            for (char instruction : pathStr.toCharArray()) {
                switch (instruction) {
                    case RIGHT:
                            turnRight();
                            break;
                    case FORWARD:
                        // Only move forward if you aren't going into a wall
                        if(toFwd(p) != '#'){ 
                            moveFwd(p);
                            break;
                        }else{break;}
                    case LEFT:
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
    }

     /**
     * Cleans the input path by removing whitespace and un-factorizing it.
     * 
     * @param pathStr The string representation of the path to be cleaned.
     * @return The clean path string, in canonical form. 
     */
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
    
     /**
     * Verifies the validity of the input path by executing it in the maze.
     */
    public void verify(){
        runPath(start, inputPath);
    }

    /**
     * Checks if the input path string is valid.
     * i.e., it contains only FORWARD, RIGHT, LEFT characters (as assigned in
     * Traversal), digits, and spaces, and does not end on a number.
     * 
     * @param pathStr The string representation of the path to be validated.
     * @return True if the path is valid; false otherwise.
     */
    private boolean isValid(String pathStr) {
        char[] allowedChars = { FORWARD, RIGHT, LEFT };
        boolean isValid = pathStr.chars().allMatch(c -> new String(allowedChars).indexOf(c) != -1 || Character.isDigit(c) || c == ' ');
        isValid = isValid && !Character.isDigit(pathStr.charAt(pathStr.length() - 1));
        return isValid;

    }

}