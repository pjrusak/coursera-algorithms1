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
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ArrayList<LineSegment> ls; 
   
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
        ls = new ArrayList<LineSegment>();
        ArrayList<Point> collinear = new ArrayList<Point>();

        for (int p = 0; p < len - 1; p++) {
            StdOut.println(points[p] + ": index = " + p);
            StdOut.println();
          
            // sort by slope from originating point p
            Arrays.sort(points, points[p].slopeOrder());
                        
            double slopePrev = Double.POSITIVE_INFINITY;
            debugFastCollinear(points);
                    
            for (int q = 0; q < len; q++) {
                if (Double.compare(slopePrev, points[q].slopeTo(points[0])) != 0 || q == 0 || q == len - 1) {
                    // at least three additional points with originating creates line
                    StdOut.print("Collinear: ");
                    for (Point g : collinear) {
                        StdOut.print(g + " ");
                    }
                    StdOut.println();
                    
                    if (collinear.size() >= 3) {
                        StdOut.println("Segment found: len = " + collinear.size() + " q = " + q);
                        collinear.add(points[0]);
                        // we have to sort this points and get end points
                        Collections.sort(collinear);
                        ls.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));        
                    }
                    collinear.clear();
                }            
                collinear.add(points[q]);
                slopePrev = points[q].slopeTo(points[0]);
                StdOut.println(points[q] + " " + points[0].slopeTo(points[q]) + " count: " + collinear.size());             
            }
            collinear.clear();
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