package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
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

