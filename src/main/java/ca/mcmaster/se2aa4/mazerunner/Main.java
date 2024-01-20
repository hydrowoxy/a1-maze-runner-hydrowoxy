package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "Input file");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i") || cmd.hasOption("input")) {
                String inputFile = cmd.getOptionValue("i", cmd.getOptionValue("input"));
                
                logger.info("** Starting Maze Runner");

                Maze maze = new Maze();
                maze.read(inputFile);
                maze.disp();

                Path path = new Path(maze);
                path.compute();
                path.disp();

                logger.info("** End of MazeRunner");

            }

        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        }
    }
}

