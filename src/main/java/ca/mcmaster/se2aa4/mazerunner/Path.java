package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    
    private Maze maze;
    private static final Logger logger = LogManager.getLogger(Path.class);

    public Path(Maze maze) {
        this.maze = maze;
    }

    public void compute() {
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    public void disp() {
        
    }

}
