import java.util.*;

/**
 *	
 *
 *  Huang-Termination Random Array Generator. 
 *  Generates a array of integers of size 4. 
 *  It contains integers from 0 to 3 at random positions.
 *
 */
public class RandomArray
{

    /**
	 *	Holds random generator.
	 */
    private Random randomGenerator = new Random();
    
    /**
	 *	Holds random integers array.
	 */
    ArrayList<Integer> numbers = new ArrayList<Integer>();

    /**
	 *	Holds a random number from 0 to 3.
	 */
    private int random;

    /**
     *	Returns a array which contains integers from 0 to 3 at random positions in array.
     *  @return Random Array of integers
	 */
	public ArrayList<Integer> generateRandomArray() {
        
        while (numbers.size() < 4) {
        
            random = randomGenerator .nextInt(4);
            
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        
        return numbers;
	}
}
