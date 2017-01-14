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
import java.util.HashMap;
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
        Point[] pointsCopy = Arrays.copyOf(points, len);
        HashMap<Double, ArrayList<Point>> slopes = new HashMap<Double, ArrayList<Point>>(); 

        for (Point origin : points) {
            // sort by slope from originating point p
            Arrays.sort(pointsCopy, origin.slopeOrder());
            
            ArrayList<Point> collinear = new ArrayList<Point>();
            double slopePrev = Double.NEGATIVE_INFINITY;
            double slopeCur = 0.0;
            
            for (int q = 1; q < len; q++) {
                slopeCur = pointsCopy[q].slopeTo(origin);
                if (Double.compare(slopePrev, slopeCur) == 0) {
                    collinear.add(pointsCopy[q]);
                } 
                else {
                    addLine(slopes, origin, slopePrev, collinear);
                    collinear.clear();
                    collinear.add(pointsCopy[q]);
                }
                slopePrev = slopeCur;
            }
            
            addLine(slopes, origin, slopeCur, collinear);
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
    
    private void addLine(HashMap<Double, ArrayList<Point>> slopes, Point origin, 
                         double slopeCur, ArrayList<Point> collinear) {
        
        if (collinear.size() < 3) {
            return;
        }
        collinear.add(origin);
        
        ArrayList<Point> lines = slopes.get(slopeCur);

        Collections.sort(collinear);
        Point start = collinear.get(0);
        Point end = collinear.get(collinear.size() - 1);
        
        if (lines == null) {
            lines = new ArrayList<Point>();
            lines.add(start);
            slopes.put(slopeCur, lines);
        } 
        else {
            // line has been added previously
            for (Point p : lines) {
                if (start.compareTo(p) == 0) return;
            }
            lines.add(start);
        }
        ls.add(new LineSegment(start, end));
    }
}