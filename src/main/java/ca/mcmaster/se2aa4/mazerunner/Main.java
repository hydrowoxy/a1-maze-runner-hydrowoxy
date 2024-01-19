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

                try {
                    
                    Maze maze = new Maze();
                    maze.readFile(inputFile);
                    maze.disp();

                    Path path = new Path(maze);
                    path.compute();
                    path.disp();

                } catch (Exception e) {
                    logger.error("/!\\ An error has occurred while reading the file /!\\");
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        }
    }
}

