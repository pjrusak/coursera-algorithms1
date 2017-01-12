/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints
 *  Dependencies: none
 *  
 *  Faster method to find every line segment that connects a subset
 *  of 4 or more of the points. Method based on sorting set of points.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.ResizingArrayQueue;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ResizingArrayQueue<LineSegment> ls; 
   
    /* finds all line segments containing 4 or more points */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException();
        }
        int len = points.length; // number of points
   
        for (int i = 0; i < len; i++) {
            if (points[i] == null)
                throw new java.lang.NullPointerException();
            for (int j = 0; j < len; j++) {
                if (points[i].compareTo(points[j]) == 0 && i != j)
                    throw new java.lang.IllegalArgumentException();
            }
        }       
        
        // calculate line segments      
        ls = new ResizingArrayQueue<LineSegment>();
        
        for (int p = 0; p < len - 1; p++) {
            StdOut.println(points[p] + ": index = " + p);
            StdOut.println();
          
            // sort by slope from originating point p
            Arrays.sort(points, points[p].slopeOrder());
                                    
            double slopeFirst = points[0].slopeTo(points[1]);
            double slopeCur;
            int count = 0;
            
            debugFastCollinear(points);
                    
            for (int q = 1; q < len; q++) {
                slopeCur = points[0].slopeTo(points[q]);
                if (Double.compare(slopeFirst, slopeCur) == 0) count++;
                else {
                    // at least three additional points with originating creates line
                    if (count >= 3) {
                        StdOut.println("Segment found: len = " + count);
                        
                        // we have to sort this points and get end points
                    }
                    slopeFirst = slopeCur;
                    count = 1;
                }
                StdOut.println(points[q] + " " + points[0].slopeTo(points[q]) + " count: " + count);       
            }
            
            // only one segment, so we don't have to look for another lines
            if (count != 1) {
               StdOut.println("Segment found: len = " + count);
               Arrays.sort(points);
               ls.enqueue(new LineSegment(points[0], points[len - 1]));
               break;
            }
                
            // find every segment of four or more points
            StdOut.println();
        }
    }
    
    /* the number of line segments */
    public int numberOfSegments() {
        return ls.size();
    }      
    
    /* the line segments */
    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[numberOfSegments()];
        int i = 0;
        
        for (LineSegment l : ls) {
            ret[i++] = l;
        }   
        return ret;
    }
    
    private void debugFastCollinear(Point[] p) {       
        for (int i = 0; i < p.length; i++) {
            StdOut.print(p[i] + " ");
        }
        StdOut.println();
        for (int i = 0; i < p.length; i++) {
            StdOut.print(p[0].slopeTo(p[i]) + " ");
        }
        StdOut.println();
        StdOut.println();
    }
}