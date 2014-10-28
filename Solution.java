import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;


public class Solution {
	Board board;
	int iterations;
	HashMap<Integer,Integer> fitnessMap;
	
	public Solution(Board b, int iters, HashMap<Integer,Integer> fm) {
		this.board = b;
		this.iterations = iters;
		this.fitnessMap = fm;
	}
	
	public void printSolution() {
		
		// print board and board state details.
		board.printBoard();
		System.out.println("total iterations: " + iterations);
		
		// print fitness vs. # of iterations
		System.out.println();
		System.out.println("fitness | iteration");
		SortedSet<Integer> fs = new TreeSet<Integer>(fitnessMap.keySet());
		for (Integer f : fs) {
			System.out.println("   " + f + "          " + fitnessMap.get(f));
		}
	}
}
