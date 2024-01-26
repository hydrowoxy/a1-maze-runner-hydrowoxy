package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Options options = new Options();
            options.addOption("i", "input", true, "Input maze file");
            options.addOption("p", "path", true, "Input path (to be verified)");
            options.addOption("m", "method", true, "Path calculation method");

        CommandLineParser parser = new DefaultParser();

        try {

            logger.info("** Starting Maze Runner");

            CommandLine cmd = parser.parse(options, args, true);

            String inputFile = cmd.getOptionValue("i");
            Maze maze = new Maze();
            maze.read(inputFile);

            Algorithm algorithm;

            // PATH VERIFICATION MODE
            if (cmd.hasOption("p") || cmd.hasOption("path")) {

                logger.info("** Path verification mode");

                String inputPath = cmd.getOptionValue("p");

                VerifyPath path = new VerifyPath(inputPath, maze);
                path.verify();

            // PATH CALCULATION MODE
            } else if (cmd.hasOption("i") || cmd.hasOption("input")) {

                logger.info("** Path calculation mode");

                // User-selected algorithm
                if (cmd.hasOption("m") || cmd.hasOption("method")) {

                    String algorithmName = cmd.getOptionValue("m");
                    algorithm = AlgorithmRegistry.getAlgorithm(algorithmName, maze);

                // Default to right-hand algorithm
                } else { algorithm = new RightHand(maze); }

                List<Character> result = algorithm.pathComp();
                Display.factorDisp(result);

            }

            logger.info("** End of MazeRunner");

        } catch (ParseException e) {
            logger.error("Error parsing command line options: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("Maze file not found: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Error accessing maze file: " + e.getMessage());
        }

    }
}
