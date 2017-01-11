/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: none
 *  
 *  Brute force method to find every line segment that connects a subset
 *  of 4 or more of the points.
 *  For use on Coursera, Algorithms Part I programming assignment.
 * 
 *  Remark: Program won't work properly if in input file are more than 4 
 *  collinear points
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.ResizingArrayQueue;

public class BruteCollinearPoints {
    private ResizingArrayQueue<LineSegment> ls;
       
    /* finds all line segments containing 4 points */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException();
        }
        int len = points.length; // number of points
        assert (len >= 4) : "Points array hasn't got enough points. Minimal number of points is 4.";
        
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
        
        // iterate over all tupples (p, q, r, s) from points[]
        for (int p = 0; p < len - 3; p++) {
            for (int q = p + 1; q < len - 2; q++) {
                for (int r = q + 1; r < len - 1; r++) {
                    for (int s = r + 1; s < len; s++) {
                        if (isCollinear(points[p], points[q], points[r], points[s])) {
                            Point[] line = {points[p], points[q], points[r], points[s]};
                            Point min = points[p]; 
                            Point max = points[s];
                            
                            // find min, max in set p, q, r, s
                            for (Point cur : line) {
                                if (cur.compareTo(min) < 0) min = cur;
                                if (cur.compareTo(max) > 0) max = cur;
                            }
                            ls.enqueue(new LineSegment(min, max));     
                        }
                    }
                }
            }
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
    
    /* private helper method to check if 4 points are colinear */
    private boolean isCollinear(Point p, Point q, Point r, Point s) {
        double slopePQ = p.slopeTo(q);
        double slopePR = p.slopeTo(r);
        double slopePS = p.slopeTo(s);
        
        if (slopePQ != slopePR) return false;
        else if (slopePQ != slopePS) return false;
        else return true; 
    }
}