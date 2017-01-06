/******************************************************************************
 *  Name:    Pawel Rusak
 *  Compilation:  javac-algs4 PercolationStats.java
 *  Execution:  java-algs4 PercolationStats <grid_size> <nb_of_trials>
 *  Dependencies: Percolation.java StdStats.java StdRandom.java
 *  Data files:   -
 *
 *  Performing simulation for T trials on N-by-N matrices using Percolation 
 *  grid and Union-Find data structures to determine the threshold.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] xt; // results from T experiments
    private double mean; // mean from results
    private double stddev; // standard deviation for results
    private double confidence; // confidence interval for mean
    private int size; // size of ma
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("Arguments should be greater than 0: " + n);
        }
        xt = new double[trials];
        size = n * n;
        // perform T trials on NxN matrix
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n); // experiment matrix
            // open new sites until system percolates
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                }
            }
            // remember p* for Ti
            xt[i] = (double) p.numberOfOpenSites() / size;
            mean = StdStats.mean(xt);
            stddev = StdStats.stddev(xt);
            confidence = calcConfidence(stddev, trials);
        }
    }
    
    // confidenceInterval = 1.96 * sqrt(stddev^2) / sqrt(trials)
    private double calcConfidence(double s, double t) {
        return 1.96 * s / Math.sqrt(t);
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - confidence;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + confidence;
    }                 
    
    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() 
                               + "," + stats.confidenceHi());
    }
}