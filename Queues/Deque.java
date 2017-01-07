/******************************************************************************
 *  Name:    Pawel Rusak
 *  Compilation:  javac-algs4 Deque.java
 *  Execution:  java-algs4 Deque
 *  Dependencies: java.util.Iterator java.util.NoSuchElementException
 *  Data files:   -
 *
 *  Basic queue implementation based on double linked list. 
 *  Queue supports enque, sample and deque operation. Implementation
 *  supports inserting and removing elements from both ends.
 *
 ******************************************************************************/

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item>  implements Iterable<Item> {
    private Node first, last;
    private int size;
   
    private class Node {
        private Item value;
        private Node next, prev;
       
        public Node(Item item) {
            if (item == null) {
                throw new NullPointerException();
            }
            value = item;
            next = null;
            prev = null;
        }
    }
   
    /* construct an empty deque */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }
   
    /* is the deque empty? */
    public boolean isEmpty() {
        return first == null;
    }

    /* return the number of items on the deque */
    public int size() {
        return size;
    }
   
    /* add the item to the front */
    public void addFirst(Item item) {
        Node node = new Node(item);
        
        if (first == null) {
            last = node;
        } 
        else {
            first.prev = node;
            node.next = first;
        }
        first = node;
        size++;
    }
   
   /* add the item to the end */
    public void addLast(Item item) {
        Node node = new Node(item);
       
        if (first == null) {
            first = node;
        }
        else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        size++;
    } 
         
    /* remove and return the item from the front */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        size--;
        /* has only one element */
        if (first.next == null) {
            last = null;
            first = null;
        }
        else {
            first.next.prev = null;
            first = first.next;
            oldFirst.next = null;
        }
        return oldFirst.value;
    }
       
    /* remove and return the item from the end */   
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldLast = last;
        size--;
        /* has only one element */
        if (last.prev == null) {
            last = null;
            first = null;
        }
        else {
            last.prev.next = null;
            last = last.prev;
        }
        return oldLast.value;
    }
         
    /* return an iterator over items in order from front to end */    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
   
    private class DequeIterator implements Iterator<Item> {
        private Node current;
       
        public DequeIterator() {
            current = first;
        }
       
        public boolean hasNext() {
            return current != null;
        }
       
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.value;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
   
    /* unit testing */
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();  
        q.addFirst(1);
        q.addLast(2);
        System.out.println("Size: " + q.size());
        System.out.println(q.removeLast());
        System.out.println(q.removeLast());
        
        // 5 3 1 2 4 6
        q.addFirst(1);
        q.addLast(2);
        q.addFirst(3);
        q.addLast(4);
        q.addFirst(5);
        q.addLast(6);
      
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeLast());
        System.out.println(q.removeFirst());
        System.out.println(q.removeLast());
        System.out.println(q.removeFirst());
        System.out.println(q.removeLast());
        System.out.println(q.removeFirst());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 1 2 3 4
        q.addLast(1);
        q.addLast(2);
        q.addLast(3);
        q.addLast(4);
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeFirst());
        System.out.println(q.removeFirst());
        System.out.println(q.removeFirst());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeFirst());
        System.out.println("Size: " + q.size());
        System.out.println("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 1 2 3 4
        q.addLast(1);
        q.addLast(2);
        q.addLast(3);
        q.addLast(4);
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeLast());
        System.out.println(q.removeLast());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeLast());
        System.out.println(q.removeLast());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 1 2 3 4
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.addFirst(4);
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeLast());
        System.out.println(q.removeLast());       
        System.out.println(q.removeLast());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(q.removeLast());
        System.out.println("Size: " + q.size());
        System.out.print("List: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        // q.addFirst(null);
        // System.out.println(q.removeLast()); 
    } 
}