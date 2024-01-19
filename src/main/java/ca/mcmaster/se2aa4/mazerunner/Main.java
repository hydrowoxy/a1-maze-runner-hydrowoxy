package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.*;

public class Main {

    // Logger for Main
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Create options object for command-line options
        Options options = new Options();
        // Adds a command line option to the Options object
        // Short form -i, long form --i, expects a value, description is Input file
        options.addOption("i", "input", true, "Input file");

        // DefaultParser is an implementation of CommandLineParser to parse command-line arguments
        CommandLineParser parser = new DefaultParser();
        try {
            // Parse method to parse command line arguments based on the options we set
            // Obtains command-line object cmd
            CommandLine cmd = parser.parse(options, args);

            // Check if i or input is in that command-line object
            if (cmd.hasOption("i") || cmd.hasOption("input")) {
                // Retrieve associeted value i.e. input file path
                String inputFile = cmd.getOptionValue("i", cmd.getOptionValue("input"));
                
                logger.info("** Starting Maze Runner");
                logger.info("**** Reading the maze from file " + inputFile);

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                System.out.print("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                System.out.print("PASS ");
                            }
                        }
                        System.out.print(System.lineSeparator());
                    }
                } catch (Exception e) {
                    logger.error("/!\\ An error has occurred while reading the file /!\\");
                    e.printStackTrace();
                }

                logger.info("**** Computing path");
                logger.info("PATH NOT COMPUTED");
                logger.info("** End of MazeRunner");
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        }
    }
}