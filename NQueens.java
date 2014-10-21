/**
I used rows instead of cols because it was easier to 
print the board
 */

import java.util.Scanner;
import java.util.Random;

public class NQueens{
    private int size = 8;
    int[] state = new int[size];  // rows 0 to size - 1
    Random rand = new Random();


    public NQueens(int s) {
		size = s;
		// initialize board
		for (int i=0; i<size; i++) {
		    state[i] = rand.nextInt(size);  // position of Q in row i
		}
    }

    public void printBoard() {
		for (int row=0; row<size; row++) {
		    for (int col=0; col<size; col++) {
				if (state[row] == col) {
				    System.out.print("1 ");
				} else {
				    System.out.print("0 ");
				}
		    }
		    System.out.println("");
		}
    }
    
    /**
       Compute the number of pairs that don't conflict
     */
    public int fitness() {
		// fill in
		return 0;
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
       Neighborhood of a state is all states that differ in exactly one row
       for local search.
     */



    public static void main(String[] args) {
		NQueens board = new NQueens(8);
		board.printBoard();
		System.out.println("number of pairs of attacking Queens via cols: " + 
				board.countCol());
		System.out.println("number of pairs attacking via diagonal: " + 
				board.countDiagonal());
		System.out.println("cost: " + board.cost());
	
	
		/*
		 Random rand = new Random();
		 for (int i=0; i<10; i++) {
		     System.out.println(rand.nextInt(5) + 1);
		 }
	
		 /*
	        String msg;
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Enter a line of text");
	        msg = scan.nextLine();
	        System.out.println(msg);
		 */
     }
  }
