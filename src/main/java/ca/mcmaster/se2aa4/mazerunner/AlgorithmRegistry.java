/*
 * Link I used to learn about HashMap: https://www.w3schools.com/java/java_hashmap.asp
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlgorithmRegistry {

    private static final Map<String, Class<? extends Algorithm>> ALGORITHMS = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(AlgorithmRegistry.class);

    static {
        ALGORITHMS.put("righthand", RightHand.class);
        ALGORITHMS.put("tremaux", Tremaux.class);
    }

    /**
     * Retrieves an instance of the specified algorithm for the given maze.
     * 
     * @param algorithmName The name of the algorithm to retrieve.
     * @param maze          The maze to which the algorithm will be applied.
     * @return An instance of the specified algorithm. Defaults to Right-Hand
     *         algorithm.
     */
    public static Algorithm getAlgorithm(String algorithmName, Maze maze) {
        Class<? extends Algorithm> algorithmClass = ALGORITHMS.get(algorithmName.toLowerCase());
        if (algorithmClass != null) {
            try {
                return algorithmClass.getDeclaredConstructor(Maze.class).newInstance(maze);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error("Error creating an instance of the algorithm: " + algorithmName, e);
            }
        } else {
            logger.error("Algorithm not found: " + algorithmName);
        }
        logger.error("Invalid algorithm: " + algorithmName + ". Defaulting to RightHand.");
        return new RightHand(maze);
    }

}
