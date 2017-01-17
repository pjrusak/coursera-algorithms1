/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac Board.java
 *  Execution:    java Board
 *  Dependencies: none
 *  
 *  Implementation of board object for program solving 8-puzzle
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import java.util.LinkedList;

public class Board {
    private static int[][] goal; // goal board settings
    private final int[][] board;
    private final int dimension;
    private int zeroI; // zero - ith position on board
    private int zeroJ; // zero - jth position on board
    
    /* construct a board from an n-by-n array of blocks 
     * (where blocks[i][j] = block in row i, column j) */
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }
        dimension = blocks.length;
        board = new int[dimension][dimension];
        goal = new int[dimension][dimension];
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = blocks[i][j];
                goal[i][j] = arrayIndex(i, j);
                if (blocks[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
        goal[dimension - 1][dimension - 1] = 0; // blank tile on last position
    }
    
    /* board dimension n */ 
    public int dimension() {
        return dimension;
    }
    
    /* number of blocks out of place */
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] != goal[i][j]) 
                    sum++;
            }
        }
        return sum;
    }
    
    /* sum of Manhattan distances between blocks and goal */
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) continue;
                else {
                    sum += manhattanDistance(board[i][j], i, j);
                    // StdOut.println("board = " + board[i][j] + 
                    // " distance = " + manhattanDistance(board[i][j], i, j));
                }
            }
        }
        return sum;
    }
    
    /* is this board the goal board? */
    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] != goal[i][j])
                    return false;
            }
        }
        return true;
    }
    
    /* a board that is obtained by exchanging any pair of blocks */
    public Board twin() {
        Board twin = new Board(board);
        int i, j, iBis, jBis;
        
        do {
            i = StdRandom.uniform(dimension);
            j = StdRandom.uniform(dimension);

        }
        while (board[i][j] == 0);
        
        do {

            iBis = StdRandom.uniform(dimension);
            jBis = StdRandom.uniform(dimension);
        }
        while (board[i][j] == 0 || (i == iBis && j == jBis));
          
        int swap = twin.board[i][j];
        twin.board[i][j] = twin.board[iBis][jBis];
        twin.board[iBis][jBis] = swap;
        return twin;
    }
    
    /* does this board equal y? */
    public boolean equals(Object y) {
        
        if (y == null) return false;
        
        if (this.getClass() != y.getClass()) return false;
        
        Board that = (Board) y;
        if (dimension != that.dimension) return false;
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] != that.board[i][j]) 
                    return false;
            }
        }
        return true;    
    }
        
    /* string representation of this board 
     * (in the output format specified below) */
    public String toString() {
        StringBuilder s = new StringBuilder(Integer.toString(dimension));
        String newline = System.lineSeparator();
        s.append(newline);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%4d", board[i][j]));
            }
            s.append(newline);
        }
        return s.toString();
    }
    
    /* all neighboring boards */
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighList = new LinkedList<Board>();
        Board b = null;
        // move upper tile to blank
        if (zeroI > 0) {
            b = new Board(board);
            b.board[zeroI][zeroJ] = b.board[zeroI - 1][zeroJ];
            b.board[zeroI - 1][zeroJ] = 0;
            neighList.add(b);
        }
        // move bottom tile to blank
        if (zeroI < dimension - 1) {
            b = new Board(board);
            b.board[zeroI][zeroJ] = b.board[zeroI + 1][zeroJ];
            b.board[zeroI + 1][zeroJ] = 0;
            neighList.add(b);
        }
        // move left tile to blank
        if (zeroJ > 0) {
            b = new Board(board);
            b.board[zeroI][zeroJ] = b.board[zeroI][zeroJ - 1];
            b.board[zeroI][zeroJ - 1] = 0;
            neighList.add(b);
        }
        // move right tile to blank
        if (zeroJ < dimension - 1) {
            b = new Board(board);
            b.board[zeroI][zeroJ] = b.board[zeroI][zeroJ + 1];
            b.board[zeroI][zeroJ + 1] = 0;
            neighList.add(b);
        }
        return neighList;
    }
   
    private int manhattanDistance(int value, int i, int j) {
        value--;
        int endI = value / dimension;
        int endJ = value % dimension;
        return Math.abs(endI - i) + Math.abs(endJ - j);
    }
    
    private int arrayIndex(int x, int y) {
        return dimension * x + y + 1;
    }

    /* unit tests (not graded) */
    public static void main(String[] args) {
    
    }
}