package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

import java.awt.Point;

public class Maze {

    private char[][] mazeArr;
    private static final Logger logger = LogManager.getLogger(Maze.class);

    public Maze(){} 

    public void read(String inputFile) {
        try {
            if(!isValid(inputFile)){
                logger.error("Invalid maze input.");
            } else {
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
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading the file /!\\");
            e.printStackTrace();
        }
    }

    public char[][] getMazeArr() {
        return mazeArr;
    }

    public Point getStartPoint() {
        for (int row = 0; row < mazeArr.length; row++) {
            if (mazeArr[row][0] == ' ') {
                return new Point(0, row);
            }
        }
        return null;
    }

    public int getEndCol() {
        if (mazeArr == null || mazeArr.length == 0 || mazeArr[0].length == 0) {
            return -1;
        }
        return mazeArr[0].length - 1;
    }


    private boolean isValid(String inputFile){
        return inputFile != null && inputFile.toLowerCase().endsWith(".txt");
    }

}
