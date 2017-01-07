/******************************************************************************
 *  Name:    Pawel Rusak
 *  Compilation:  javac-algs4 Permutation.java
 *  Execution:  java-algs4 Permutation
 *  Dependencies: StdIn StdOut
 *  Data files:   -
 *
 *  Implementation Permutation class from Programming assignment week 2 for
 *  Algorithms part 1 course. Program reads all strings from file and based
 *  on a given integer 0 < k <= n, prints k values in random order. 
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }   
}