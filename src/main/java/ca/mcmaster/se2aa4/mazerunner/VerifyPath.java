package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerifyPath {
    
    private Maze maze;
    private static final Logger logger = LogManager.getLogger(VerifyPath.class);

    public VerifyPath(Maze maze) {
        this.maze = maze;
    }

    public void verify() {
        logger.info("**** Verifying path");
        logger.info("PATH NOT VERIFIED");
    }

    public void disp() {

    }

}
