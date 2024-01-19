package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private char[][] mazeArr;
    private static final Logger logger = LogManager.getLogger(Maze.class);
    
    public Maze() {
        // Constructor empty for now
    }

    public void read(String inputFile) throws IOException {
        logger.info("**** Reading the maze from file " + inputFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int idx = 0;
            while ((line = reader.readLine()) != null) {
                mazeArr[idx++] = line.toCharArray();
            }
        }
    }

    public void disp() {
        for(char[] row : mazeArr) {
            for(char item : row) {
                if (item == '#') {
                    System.out.print("WALL ");
                } else if (item == ' ') {
                    System.out.print("PASS ");
                }
            }
            System.out.print(System.lineSeparator());
        }
    }

}