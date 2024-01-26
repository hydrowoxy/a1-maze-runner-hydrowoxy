package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;

public class Maze {

    private char[][] mazeArr;
    private static final Logger logger = LogManager.getLogger(Maze.class);

    public Maze() {
    }

    /**
     * Reads the maze from the specified input file.
     * 
     * @param inputFile The path to the file containing the maze structure.
     * @throws FileNotFoundException The system can not find the input file
     * @throws IOException There was an issue reading the file
     */
    public void read(String inputFile) throws FileNotFoundException, IOException {
        logger.info("**** Reading the maze from file " + inputFile);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        int rows = 0;
        int maxCols = 0;

        while ((line = reader.readLine()) != null) {
            rows++;
            maxCols = Math.max(maxCols, line.length());
        }

        reader.close();
        reader = new BufferedReader(new FileReader(inputFile));

 
            mazeArr = new char[rows][maxCols];

            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                char[] rowChars = line.toCharArray();
                for (int col = 0; col < maxCols; col++) {
                    mazeArr[currentRow][col] = (col < rowChars.length) ? rowChars[col] : ' ';
                }
                currentRow++;
            }
        
    }

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
        if (mazeArr == null || mazeArr.length == 0 || mazeArr[0].length == 0) {
            return -1;
        }
        return mazeArr[0].length - 1;
    }

}
