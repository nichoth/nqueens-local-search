/**
I used rows instead of cols because it was easier to 
print the board
 */

import java.util.HashSet;
import java.util.Random;

public class Board{
	int maxFitness;
    private int size;
    int[] state = new int[size];  // rows 0 to size - 1
    Random rand = new Random();

    
    /**
     * Create board with a random state.
     * @param s
     */
	public Board(int s) {
		int[] state = new int[s];
		for (int i=0; i<s; i++) {
		    state[i] = rand.nextInt(s);  // position of Q in row i
		}
		construct(state);
	}

	/**
	 * Create a board with the given state.
	 * @param state	Array of board state.
	 */
	public Board(int[] state) {
		construct(state);
	}
	
	private void construct(int[] state) {
		this.size = state.length;
		this.state = state;
		this.maxFitness = ((size-1) * size) / 2;
	}

    public void printBoard() {
		for (int row=0; row<size; row++) {
		    for (int col=0; col<size; col++) {
				if (state[row] == col) {
				    System.out.print("1  ");
				} else {
				    System.out.print("0  ");
				}
		    }
		    System.out.println("");
		}
		
		System.out.println("number of pairs of attacking Queens via cols: " + 
				countCol());
		System.out.println("number of pairs attacking via diagonal: " + 
				countDiagonal());
		System.out.println("cost: " + cost() + ",    fitness: " + fitness());
    }
    
    /**
       Compute the number of pairs that don't conflict
     */
    public int fitness() {
		// there are 28 pairs of queens total
		return maxFitness - cost();
	}
    
    /**
     * 
     * @return A count of the number of pairs of queens attacking each other
     * 		via diagonal.
     */
    public int countDiagonal() {
    	
    	int attacking = 0;
    	for (int i = 0; i<size; i++) { // for each row (for each queen)
    		// check if row[i] + 1 == row[i+1]
    		for (int j = i+1; j<size; j++) {
    			if (state[i] + (j-i) == state[j]) { // check right diags
    				attacking++;
    			}
    			if (state[i] + (i-j) == state[j]) { // check left diags
    				attacking++;
    			}
    		}
    	}
    	return attacking;
    }



    /**
       Each queen is specified by its row, e.g. queen 0 is in row 0
       The method countCol(i) returns a count of the number of queens
       in the same column as the queen in row i.
       This is just the condition that state[j] == state[i]
     */
    public int countCol() {
		int count = 0;
		for (int i=0; i<size; i++) {
		    for (int j=i+1; j<size; j++) {
				if (state[i] == state[j]) {
				    count++;
				}
		    }
		}
		return count;
    }


    /**
       Compute the number of pairs of queens that are attacking each other.
     */
    public int cost() {
		return countCol() + countDiagonal();
    }
    
    /**
     * Create a copy of this game, with the given row changed to a
     * random spot.
     * @param row	The row to mutate.
     * @return NQueens object identical to this one but 
     * 	with the given row changed randomly.
     */
    private Board createNeighbor(int row) {
    	int[] board = new int[size];
     	for (int i=0; i<size; i++) {
     		board[i] = this.state[i];
     	}
     	board[row] = rand.nextInt(size);
     	return new Board(board);
    }
    
    /**
     * Neighborhood of a state is all states that differ in exactly one row
     * for local search.
     * @return Boards that differ in one row from this one.
     */
    public HashSet<Board> makeNeighborhood() {
    	HashSet<Board> nHood = new HashSet<Board>(size);
    	for (int i=0; i<size; i++) {
    		nHood.add( createNeighbor(i) );
    	}
    	return nHood;
    }
}
