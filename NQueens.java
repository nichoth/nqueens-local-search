import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class NQueens {
	
	int maxIters = 10000;
	
	public NQueens() {
	}
	
	/**
	 * Solve the given board.
	 * @param board
	 * @return
	 */
	public Solution solve(Board board) {
		
		int iters = 0;
		HashMap<Integer,Integer> fitnessMap = new HashMap<Integer,Integer>();

		while (board.fitness() < board.maxFitness && iters < maxIters) {
			Iterator<Board> iter = board.makeNeighborhood().iterator();
			while (iter.hasNext()) {
				Board b = iter.next();
				if ( b.fitness() > board.fitness() ) {
					board = b;
					fitnessMap.put(board.fitness(), iters);
				}
			}
			iters++;
		}

		return new Solution(board, iters, fitnessMap);
    }

    /**
     * Generate a bunch of boards and then solve them.
     */
    public ArrayList<Solution> solve(int iters) {

    	ArrayList<Solution> solutions = new ArrayList<Solution>();
    	for (int i=0; i<iters; i++) {
    		Board b = new Board(8);
    		Solution solution = solve(b);
    		solutions.add(solution);
    	}
    	return solutions;
    }
    
    /**
     * Solve with simulated annealing.
     * @param b
     * @param schedule
     * @return
     */
    public Solution annealing(Board board, Map schedule) {
    	
    	int iters = 0;
    	Random r = new Random();
		HashMap<Integer,Integer> fitnessMap = new HashMap<Integer,Integer>();

		while (board.fitness() < board.maxFitness && iters < maxIters) {
			// do annealing here
			ArrayList<Board> neighbors = board.makeNeighborhood();
			Board n = neighbors.get(r.nextInt( neighbors.size() ));
			
			if (n.fitness() > board.fitness()) {
				// choose this neighbor
				board = n;
			} else {
				// choose this neighbor with a certain probability
				// Probability decreases based on the badness of the neighbor,
				// and according to the schedule.
			}
				
			
			fitnessMap.put(board.fitness(), iters);
			iters++;
		}

		return new Solution(board, iters, fitnessMap);
    }
	
    // create a board and solve it, print the solution
	public static void main(String[] args) {
		
		// create a random board and print it
		Board board = new Board(8);
		board.printBoard();
		
		System.out.println();
		
		// solve the board and print the solution
		NQueens q = new NQueens();
		Solution s = q.solve(board);
		s.printSolution();
    }
	
}
