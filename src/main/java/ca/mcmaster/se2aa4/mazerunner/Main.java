package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");
        options.addOption("p", "path", true, "Input path (to be verified)");

        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String inputFile = cmd.getOptionValue("i");

                logger.info("** Starting Maze Runner");

                // NO LOGS ACTIVATED
                Maze maze = new Maze();
                maze.read(inputFile);

                RightHand rightHand = new RightHand(maze);
                rightHand.pathComp();
                rightHand.canonDisp();

                // PATH VERIFICATION MODE
                if (cmd.hasOption("p")) {
                    String inputPath = cmd.getOptionValue("p");
                    System.out.println("Read this from cmd line:" + inputPath);

                    logger.info("** Path verification mode");

                    Path path = new Path(inputPath, maze);
                    path.verify();
                }

                logger.info("** End of MazeRunner");

            }

        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        }

    }
}

