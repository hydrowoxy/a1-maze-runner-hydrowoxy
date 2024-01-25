package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlgorithmRegistry {

    private static final Map<String, Class<? extends Algorithm>> ALGORITHMS = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(AlgorithmRegistry.class);

    static {
        ALGORITHMS.put("righthand", RightHand.class);
    }

    public static Algorithm getAlgorithm(String algorithmName, Maze maze) {
        Class<? extends Algorithm> algorithmClass = ALGORITHMS.get(algorithmName.toLowerCase());
        if (algorithmClass != null) {
            try {
                return algorithmClass.getDeclaredConstructor(Maze.class).newInstance(maze);
            } catch (Exception e) {
                logger.error("Error creating an instance of the algorithm: " + algorithmName, e);
            }
        } else {
            logger.error("Algorithm not found: " + algorithmName);
        }
        logger.error("Invalid algorithm: " + algorithmName + ". Defaulting to RightHand.");
        return new RightHand(maze);
    }
    
}
