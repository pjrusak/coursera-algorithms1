/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac BoardTest.java
 *  Execution:    java BoardTest
 *  Dependencies: none
 *  
 *  Testing client for Board class 
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BoardTest {
  
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        StdOut.println(initial);
        
        StdOut.println(initial.isGoal());
        
        StdOut.println(initial.hamming());
        
        StdOut.println(initial.manhattan());
    }
}