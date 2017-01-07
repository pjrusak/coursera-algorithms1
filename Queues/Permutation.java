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
 *  Update:
 *  - added reservoir sampling -> it is technique that gives every element from
 *  input stream equal probability to be put in reservoir of the size k
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        
        for (int i = 1; !StdIn.isEmpty(); i++) {
            String s = StdIn.readString();
            // accept first k elements
            if (i <= k) {
                q.enqueue(s);
            } // calculate probability for ith element to replace one from [1..k] 
            else if (StdRandom.uniform() <  (double) k/ i) { // keep new element with probability size of reservoir / i
                q.dequeue();
                q.enqueue(s);
            }
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }   
}