/**
 * Link I used to learn about System.arraycopy: https://www.tutorialspoint.com/java/lang/system_arraycopy.htm
 */

package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Maze {

    private char[][] mazeArr;
    private static final Logger logger = LogManager.getLogger(Maze.class);

    /**
     * Constructor
     */
    public Maze() {
    }

    /**
     * Reads the maze from the specified input file.
     * 
     * @param inputFile The path to the file containing the maze structure.
     * @throws FileNotFoundException The system can not find the input file.
     * @throws IOException There was an issue reading the file.
     */
    public void read(String inputFile) throws FileNotFoundException, IOException {
        logger.info("**** Reading the maze from file " + inputFile);

        List<char[]> rowsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] rowChars = line.toCharArray();
                rowsList.add(rowChars);
            }
        }

        int rows = rowsList.size();
        int cols = rowsList.get(0).length;

        mazeArr = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            char[] rowChars = rowsList.get(i);
            System.arraycopy(rowChars, 0, mazeArr[i], 0, cols);
        }
    }

    /**
     * Retrieves the 2D array representation of the maze.
     * 
     * @return The 2D array representation of the maze.
     */
    public char[][] getMazeArr() {
        return mazeArr;
    }

    /**
     * Finds and returns the starting point of the maze.
     * 
     * @return The starting point as a Point object.
     */
    public Point getStartPoint() {
        for (int row = 0; row < mazeArr.length; row++) {
            if (mazeArr[row][0] == ' ') {
                return new Point(0, row);
            }
        }
        return null;
    }

    /**
     * Retrieves the column index of the last column in the maze.
     * 
     * @return The column index of the last column in the maze.
     */
    public int getEndCol() {
        return mazeArr[0].length - 1;
    }

}
