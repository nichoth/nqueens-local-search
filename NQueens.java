import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class NQueens {
	
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

		while (board.fitness() < board.maxFitness && iters < 10000) {
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
