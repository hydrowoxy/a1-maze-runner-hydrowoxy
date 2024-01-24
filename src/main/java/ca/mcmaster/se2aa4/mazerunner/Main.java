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
        options.addOption("m", "method", true, "Path calculation  method");

        CommandLineParser parser = new DefaultParser();

        try {

            logger.info("** Starting Maze Runner");

            CommandLine cmd = parser.parse(options, args);

            String inputFile = cmd.getOptionValue("i");

            Maze maze = new Maze();
            Algorithm algorithm;

            maze.read(inputFile);

            if (cmd.hasOption("p")) { 

                // PATH VERIFICATION MODE
                logger.info("** Path verification mode");
            
                String inputPath = cmd.getOptionValue("p");

                Path path = new Path(inputPath, maze);
                path.verify();

            } else if (cmd.hasOption("i")) {

                // PATH CALCULATION MODE
                logger.info("** Path calculation mode");

                if (cmd.hasOption("m")) {

                    // User-selected algorithm
                    String algorithmName = cmd.getOptionValue("m");
                    algorithm = AlgorithmRegistry.getAlgorithm(algorithmName, maze);

                } else { 

                    // Default to right-hand algorithm
                    algorithm = new RightHand(maze);

                }

                algorithm.pathComp();
                algorithm.factorDisp();
                
            } 

            logger.info("** End of MazeRunner");

        } catch (ParseException e) {
            logger.error("Error parsing command line options: " + e.getMessage());
        }

    }
}

