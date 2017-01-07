import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DequeTest {

    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        Deque<String> p = new Deque<String>();
        Deque<String> r = new Deque<String>();
        int k = Integer.parseInt(args[0]);
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("")) {
                q.addFirst(item);
                p.addLast(item);
                r.addFirst(item);
            }
        }
        StdOut.println("List q: ");
        for (String s : q) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + q.size());
        StdOut.println();
        StdOut.println();
        for (int i = 0; i < k; i++) {
            StdOut.println("Remove last: " + q.removeLast());
        }
        StdOut.println();
        StdOut.println("List q: ");
        for (String s : q) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + q.size());
        StdOut.println();
        int n = q.size();
        for (int i = 0; i < n; i++) {
            StdOut.println("Remove last: " + q.removeLast());
        }
        StdOut.println();
        StdOut.println("List q: ");
        for (String s : q) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + q.size());
        StdOut.println();
        StdOut.println("-----------------------------------------------");
        StdOut.println("List p: ");
        for (String s : p) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + p.size());
        StdOut.println();
        StdOut.println();
        for (int i = 0; i < k; i++) {
            StdOut.println("Remove first: " + p.removeFirst());
        }
        StdOut.println();
        StdOut.println("List p: ");
        for (String s : p) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + p.size());
        StdOut.println();
        n = p.size();
        for (int i = 0; i < n; i++) {
            StdOut.println("Remove last: " + p.removeLast());
        }
        StdOut.println();
        StdOut.println("List p: ");
        for (String s : p) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + p.size());
        StdOut.println();
        StdOut.println();
        StdOut.println("-----------------------------------------------");
        StdOut.println("List r: ");
        for (String s : r) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + r.size());
        StdOut.println();
        StdOut.println();
        for (int i = 0; i < k; i++) {
            StdOut.println("Remove last: " + r.removeLast());
        }
        StdOut.println();
        StdOut.println("List r: ");
        for (String s : r) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + r.size());
        StdOut.println();
        n = r.size();
        for (int i = 0; i < n; i++) {
            StdOut.println("Remove first: " + r.removeFirst());
        }
        StdOut.println();
        StdOut.println("List r: ");
        for (String s : r) {
            StdOut.print(s + "; ");
        }
        StdOut.println();
        System.out.println("Size: " + r.size());
        StdOut.println();
        StdOut.println();
    }   
}