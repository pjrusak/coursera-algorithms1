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
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> ls;
       
    /* finds all line segments containing 4 points */
    public BruteCollinearPoints(Point[] points) {
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
        Point[] pointsCopy = Arrays.copyOf(points, len);
        
        Arrays.sort(pointsCopy); 
        // iterate over all tupples (p, q, r, s) from points[]
        for (int p = 0; p < len - 3; p++) {
            for (int q = p + 1; q < len - 2; q++) {
                for (int r = q + 1; r < len - 1; r++) {
                    for (int s = r + 1; s < len; s++) {
                        if (isCollinear(pointsCopy[p], pointsCopy[q], pointsCopy[r], pointsCopy[s])) {
                            ls.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
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
        
        if (Double.compare(slopePQ, slopePR) == 0 && 
            Double.compare(slopePQ, slopePS) == 0) return true;
        return false; 
    }
}