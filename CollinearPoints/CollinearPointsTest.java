/******************************************************************************
 *  Author: Pawel Rusak
 *  Compilation:  javac CollinearPointsTest.java
 *  Execution:    java CollinearPointsTest < input.txt
 *  Dependencies: In StdDraw StdOut
 *  
 *  Collinear points test client
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

class CollinearPointsTest {
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
       
        // print and draw the line segments
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        int i = 1;
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(i + ": " + segment);
            segment.draw();
            i++;
        }
        StdDraw.show();
    }
}