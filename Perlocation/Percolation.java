/******************************************************************************
 *  Name:    Pawel Rusak
 *  Compilation:  javac-algs4 Percolation.java
 *  Execution:  java-algs4 Percolation < input.txt
 *  Dependencies: WeightedQuickUnionUF.java StdIn.java StdOut.java
 *  Data files:   -
 *
 *  Modeling Percolation using an N-by-N grid and Union-Find data structures to 
 *  determine the threshold. Threshold mean number of open sites when 
 *  system percolates.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufOneVirt;
    private int virtTopNode;
    private int virtBottomNode;
    private boolean[][] sites;
    private int nbOfOpen;
    private int len;
    
    /* create N-by-N grid, with all sites blocked */
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Matrix size has to be greater than 0: " + n);
        }
        virtTopNode = n * n;
        virtBottomNode = virtTopNode + 1;
        // N^2 + 1 elements in union-find uses virtual nodes for connections to top and bottom row
        uf = new WeightedQuickUnionUF(virtBottomNode + 1); 
        ufOneVirt = new WeightedQuickUnionUF(virtTopNode + 1); 
        sites = new boolean[n][n];
        len = n;
        nbOfOpen = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
    }
    
    private void validateIndex(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) {
            throw new java.lang.IndexOutOfBoundsException("Both indexes: (i,j) has to be in range 1..N. Given: (" 
                                                              + row + "," + col + ")");
        }
    }
    
    private int index(int row, int col) {
        return row * len + col;
    }
    
    /* open site (row i, column j) if it is not open already */
    public void open(int i, int j) {
        validateIndex(i, j);
        int x = i - 1;
        int y = j - 1;
        // if site is open nothing to do
        if (sites[x][y]) {
            return;
        }
        nbOfOpen++;
        sites[x][y] = true;
               
        // first check if it is possible to connect to the same row
        if (y > 0 && sites[x][y - 1]) {
            uf.union(index(x, y), index(x, y - 1));
            ufOneVirt.union(index(x, y), index(x, y - 1));
        }
        if (y < len - 1 && sites[x][y + 1]) {
            uf.union(index(x, y), index(x, y + 1));
            ufOneVirt.union(index(x, y), index(x, y + 1));
        } 
        
        if (x == 0) {
            // elements from top row connect to virtual node and second row
            uf.union(virtTopNode, index(x, y));
            ufOneVirt.union(virtTopNode, index(x, y));
            if (x < len - 1 && sites[x + 1][y]) {
                uf.union(index(x, y), index(x + 1, y));
                ufOneVirt.union(index(x, y), index(x + 1, y));
            } 
            else if (x == len - 1) {
                uf.union(virtBottomNode, index(x, y));
            }
        }
        else { 
            // connect to row above
            if (sites[x - 1][y]) {
                uf.union(index(x, y), index(x - 1, y));
                ufOneVirt.union(index(x, y), index(x - 1, y));
            }
            if (x == len - 1) {
                // last row connect to virtual node
                uf.union(virtBottomNode, index(x, y));
            }
            else if (sites[x + 1][y]) {
                // we are in the middle so connect to row below
                uf.union(index(x, y), index(x + 1, y));
                ufOneVirt.union(index(x, y), index(x + 1, y));
            }
        }
    } 
    
    /* is site (row i, column j) open? */
    public boolean isOpen(int i, int j) {
        validateIndex(i, j);
        return sites[i-1][j-1];
    }
    
    /* is site (row i, column j) full? */
    public boolean isFull(int i, int j) {
        validateIndex(i, j);
        return ufOneVirt.connected(index(i - 1, j - 1), virtTopNode);
    }
    
    /* number of open sites */
    public int numberOfOpenSites() {
        return nbOfOpen;
    }
    
    /* does the system percolate? */
    public boolean percolates() { 
        return uf.connected(virtTopNode, virtBottomNode);
    }            
    
    public static void main(String[] args) {   
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            p.open(row, col);
            StdOut.println("Open site: (" + row + "," + col + ")");
        }
        StdOut.println("Percolates: " + p.percolates());    
    }
}