/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac Board.java
 *  Execution:    java Board
 *  Dependencies: none
 *  
 *  Implementation of board object for program solving 8-puzzle
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdOut;

public class Board { //implements Iterable<Board> {
    private final int[][] board;
    private static int[][] goal;
    private final int dimension;
    
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
    //public Board twin() {
    //}
    
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
    //public Iterable<Board> neighbors() {
    //    return new BoardIterator();
    //}
   
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