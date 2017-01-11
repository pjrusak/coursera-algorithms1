import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

class PointTest {
    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        System.out.println("Points: ");
        for (Point p : points) {
            System.out.println(p + " ");
        }
        System.out.println();
        System.out.println("Random points from array:");
        for (int i = 0; i < n; i++) {
            Point p = points[StdRandom.uniform(n)];
            Point q = points[StdRandom.uniform(n)];
            System.out.print(p + " ");
            System.out.print(q + " ");
            System.out.println();
            System.out.println("p.compareTo(q): " + p.compareTo(q));
            System.out.println("slopeTo(p,q): " + p.slopeTo(q));
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Random points from array:");
        Point p = points[StdRandom.uniform(n)];
        for (int i = 0; i < n; i++) {
            Point q = points[StdRandom.uniform(n)];
            Point r = points[StdRandom.uniform(n)];
            System.out.print(q + " ");
            System.out.print(r + " ");
            System.out.println();
            System.out.println("slopeOrder to p: " + p + " " + p.slopeOrder().compare(q, r));
            System.out.println();
        }
    }
}