import java.util.Iterator;


public class NQueens {
	
	int iterations;
	
	public NQueens() {
		this.iterations = 0;
	}
	
	// this doesn't find a solution, but reduces the cost to 1 or 2.
	// the stack overflows if we increase the number of iterations
	// how can we do this without a stack overflow?
	// Haskell uses a lazy recursion thing that would work. 
	public Board solve(Board board) {
    	// find the best neighbor
		// do this until board.fitness() == maxFitness
		// if none of the neighbors are more fit, try again
    	
    	if (board.fitness() == board.maxFitness || iterations > 7000) {
			return board;
		}
    	
		Iterator<Board> iter = board.makeNeighborhood().iterator();
		Board best = board;
		while (iter.hasNext()) {
			Board b = iter.next();
			if ( b.fitness() > best.fitness() ) {
				best = b;
			}
		}
		if (best.fitness() > board.fitness()) {
			iterations++;
			return solve(best);
		} else {
			iterations++;
			return solve(board);
		}
    }
	
	public static void main(String[] args) {
		Board board = new Board(8);
		board.printBoard();
		
		System.out.println("number of pairs of attacking Queens via cols: " + 
				board.countCol());
		System.out.println("number of pairs attacking via diagonal: " + 
				board.countDiagonal());
		System.out.println("cost: " + board.cost());
		
		NQueens q = new NQueens();
		Board solution = q.solve(board);
		
		solution.printBoard();
		System.out.println("number of pairs of attacking Queens via cols: " + 
				solution.countCol());
		System.out.println("number of pairs attacking via diagonal: " + 
				solution.countDiagonal());
		System.out.println("cost: " + solution.cost());
		System.out.println("iterations: " + q.iterations);
     }
	
}
