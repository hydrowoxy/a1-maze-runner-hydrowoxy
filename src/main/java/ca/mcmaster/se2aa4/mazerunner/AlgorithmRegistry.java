/*
 * Links I used to learn about HashMap: https://www.w3schools.com/java/java_hashmap.asp
 *                             getDeclaredConstructor: https://www.tutorialspoint.com/java/lang/class_getdeclaredconstructor.htm
 *                             newInstance: https://www.tutorialspoint.com/java/lang/class_getdeclaredconstructor.htm
 *                             static blocks: https://www.javatpoint.com/static-block-in-java
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlgorithmRegistry {

    private static final Map<String, Class<? extends Algorithm>> ALGORITHMS = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(AlgorithmRegistry.class);

    /**
     * Stores available algorithms
     * 
     * key: Command line argument to recognize
     * value: Corresponding algorithm class to call
     */
    static {
        ALGORITHMS.put("righthand", RightHand.class);
        ALGORITHMS.put("tremaux", Tremaux.class);
    }

    /**
     * Retrieves an instance of the specified algorithm for the given maze.
     * 
     * @param algorithmName The name of the algorithm to retrieve.
     * @param maze The maze to which the algorithm will be applied.
     * @return An instance of the specified algorithm. Defaults to Right-Hand.
     */
    public static Algorithm getAlgorithm(String algorithmName, Maze maze) {
        Class<? extends Algorithm> algorithmClass = ALGORITHMS.get(algorithmName);
        if (algorithmClass != null) {
            try {
                return algorithmClass.getDeclaredConstructor(Maze.class).newInstance(maze);
            } catch (Exception e) {
                logger.error("Error creating an instance of the algorithm: " + algorithmName, e);
            }
        } else {
            logger.error("Invalid algorithm: " + algorithmName + ". Defaulting to RightHand.");
        }
        return new RightHand(maze);
    }

}
