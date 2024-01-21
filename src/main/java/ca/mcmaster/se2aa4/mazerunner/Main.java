package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");
        options.addOption("p", "path", false, "Input path (to be verified)");

        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i") || cmd.hasOption("input")) {
                String inputFile = cmd.getOptionValue("i", cmd.getOptionValue("input"));

                logger.info("** Starting Maze Runner");

                // NO LOGS ACTIVATED
                Maze maze = new Maze();
                maze.read(inputFile);
                
                RightHand rightHand = new RightHand(maze);
                List<Character> path = rightHand.pathComp();
                
                System.out.println("Path: " + path);

                // PATH VERIFICATION MODE
                if (cmd.hasOption("p") || cmd.hasOption("path")) {

                }

                logger.info("** End of MazeRunner");

            }

        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        }

    }
}

