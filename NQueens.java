import java.util.ArrayList;
import java.util.Iterator;


public class NQueens {
	
	int iterations;
	
	public NQueens() {
		this.iterations = 0;
	}
	
	/**
	 * Solve the given board.
	 * @param board
	 * @return
	 */
	public Board solve(Board board) {

		while (board.fitness() < board.maxFitness && iterations < 10000) {
			Iterator<Board> iter = board.makeNeighborhood().iterator();
			while (iter.hasNext()) {
				Board b = iter.next();
				if ( b.fitness() > board.fitness() ) {
					board = b;
				}
			}
			iterations++;
		}

		return board;
    }

    /**
     * Generate a bunch of baords and then solve them.
     */
    public ArrayList<Board> solve(int iters) {

    	ArrayList<Board> solutions = new ArrayList<Board>();
    	for (int i=0; i<iters; i++) {
    		Board b = new Board(8);
    		Board solution = solve(b);
    		solutions.add(solution);
    	}
    	return solutions;
    }
	
	public static void main(String[] args) {
		
		// create a random board and print it
		Board board = new Board(8);
		board.printBoard();
		
		System.out.println("number of pairs of attacking Queens via cols: " + 
				board.countCol());
		System.out.println("number of pairs attacking via diagonal: " + 
				board.countDiagonal());
		System.out.println("cost: " + board.cost());
		
		// solve the board and print the solution
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
