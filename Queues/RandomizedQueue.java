/******************************************************************************
 *  Name:    Pawel Rusak
 *  Compilation:  javac-algs4 RandomizedQueue.java
 *  Execution:  java-algs4 RandomizedQueue
 *  Dependencies: StdRandom java.util.Iterator java.util.NoSuchElementException
 *  Data files:   -
 *
 *  Basic randomized queue implementation based on resizable arrays. Elements
 *  are removed from random position from the array. Inserting is implemented
 *  at the end of the queue.
 *
 ******************************************************************************/
import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue; // resizable array of elements
    private int index; // index of next available element
   
    /* construct an empty randomized queue */
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        index = 0;
    }
   
    private void resize(int size) {
        Item[] newQueue = (Item[]) new Object[size];
        
        for (int i = 0; i < index; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }
    
    /* is the queue empty? */
    public boolean isEmpty() {
        return index == 0;
    }
   
    /* return the number of items on the queue */   
    public int size() {
        return index;
    }
    
    /* add the item */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        queue[index++] = item;
        if (index == queue.length) {
            resize(2 * queue.length);
        }
    }
    
    /* remove and return a random item */
    public Item dequeue() {
        if (index == 0) {
            throw new NoSuchElementException();
        }
        Item ret;
        int randomIndex = StdRandom.uniform(index);
        
        if (randomIndex == index - 1) {
            ret = queue[randomIndex];
            queue[randomIndex] = null;
        }
        else {
            ret = queue[randomIndex];
            queue[randomIndex] = queue[index - 1];
            queue[index - 1] = null;
        }
        index--;
        if (index == queue.length / 4) {
            resize(queue.length / 2);
        }
        return ret;
    }
    
    /* return (but do not remove) a random item */
    public Item sample() {
        if (index == 0) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniform(index)];
    }
    
    /* return an independent iterator over items in random order */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] q;
        private int cur, size;
        
        public RandomizedQueueIterator() {
            q = (Item[]) new Object[index];
            size = index;
            cur = 0;
            for (int i = 0; i < size; i++) {
                q[i] = queue[i];
            }
  
        }
        
        public boolean hasNext() {
            return size > 0;
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int k = StdRandom.uniform(size);
            Item ret = q[k];
            q[k] = q[size - 1];
            q[size - 1] = null;
            cur++;
            size--;
            return ret;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
   
    /* unit testing */
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        System.out.println("Size: " + q.size());
        System.out.println("Is empty: " + q.isEmpty());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Sample: " + q.sample());
        System.out.println("Is empty: " + q.isEmpty());
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.println("Is empty: " + q.isEmpty());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Sample: " + q.sample());
        System.out.println("Sample: " + q.sample());
        System.out.println("Sample: " + q.sample());
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.println("Is empty: " + q.isEmpty());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.println("Is empty: " + q.isEmpty());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.println("Is empty: " + q.isEmpty());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
        q.enqueue(10);
        q.enqueue(2000);
        System.out.println();
        System.out.println("Size: " + q.size());
        System.out.print("Queue: ");        
        for (Integer i : q) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}