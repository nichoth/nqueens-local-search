import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;


public class NQueens {
	
	static final int maxIters = 10000;
	
	public NQueens() {
	}
	
	/**
	 * Solve the given board.
	 * @param board
	 * @return
	 */
	public static Solution solve(Board board) {
		
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
    public static ArrayList<Solution> solve(int iters) {

    	ArrayList<Solution> solutions = new ArrayList<Solution>();
    	for (int i=0; i<iters; i++) {
    		Board b = new Board(8);
    		Solution solution = solve(b);
    		solutions.add(solution);
    	}
    	return solutions;
    }
    
    // map iterations to temperature
    // how do we make this good?
    public static int schedule(int iter) {
    	return maxIters - (2*iter);
    }
    
    /**
     * Solve with simulated annealing.
     * @param board
     * @return
     */
    public static Solution annealing(Board board) {
    	
    	int iters = 0;
    	Random r = new Random();
		HashMap<Integer,Integer> fitnessMap = new HashMap<Integer,Integer>();

		while (board.fitness() < board.maxFitness && iters < maxIters) {
			// do annealing here
			// see page 126 for algorithm
			// * pick a random neighbor
			// * if the move improves our fitness, accept it
			// * if it's worse, accept it with a probability
			// 		* prob decreases exponentially as the neighbor gets less 
			//		  fit
			//		* prob also decreases according to a *schedule* that maps
			//		  iterations to 'temperature', (decreases over time). 
			//		  (Bad moves are more likely to be accepted at the 
			//		  beginning).
			ArrayList<Board> neighbors = board.makeNeighborhood();
			Board n = neighbors.get(r.nextInt( neighbors.size() ));
			
			if (n.fitness() > board.fitness()) {
				board = n;
			} else {
				// the probability is e ^ deltaF/schedule(iter)
				int deltaF = n.fitness() - board.fitness();
				double prob = Math.exp(deltaF/(float)schedule(iters));
				
//				System.out.println( schedule(iters) );
//				System.out.println( deltaF/(float)schedule(iters) );
//				System.out.println( (int)Math.floor(prob*1000) );
				
				
				if (r.nextInt(10000) <= (int)Math.floor(prob*10000)) {
					board = n;
				}
				
			}
				
			
			fitnessMap.put(board.fitness(), iters);
			iters++;
		}

		return new Solution(board, iters, fitnessMap);
    }
	
    // create a board and solve it, print the solution
	public static void main(String[] args) {
		
		Board board = new Board(8);
		board.printBoard();
		
		System.out.println();
		
		// solve the board and print the solution
//		Solution s = solve(board);
		Solution s = annealing(board);
		s.printSolution();
    }
	
}
