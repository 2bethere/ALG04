import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;         // array of items
    private int N;            // number of elements on stack
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot
    
    
    public RandomizedQueue()           // construct an empty randomized queue
    {
        q = (Item[]) new Object[2];
    }
    
    // resize the underlying array holding the elements
    private void resize(int max) {
        assert max >= N;
         Item[] temp = (Item[]) new Object[max];
         for (int i = 0; i < N; i++) {
             temp[i] = q[(first + i) % q.length];
         }
         q = temp;
         first = 0;
         last  = N;
    }
    public boolean isEmpty()           // is the queue empty?
    {
        return N == 0;
    }
    
    public int size()                  // return the number of items on the queue
    {
        return N;
    }
    
    public void enqueue(Item item)     // add the item
    {
        if (item == null) throw new NullPointerException(); 
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        N++;                   
    }
    
    public Item dequeue()              // delete and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int random = (first+StdRandom.uniform(N)) % q.length;
        //do a switch with first and remove the first
        Item temp = q[first];
        q[first] = q[random];
        q[random] = temp;
        //
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
    }
    
    public Item sample()               // return (but do not delete) a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int random = (first+StdRandom.uniform(N)) % q.length;
        return q[random];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()   
    {
        return new ArrayIterator();
    }
    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] p;
        public ArrayIterator()
        {
            Item temp;
            p =  (Item[]) new Object[q.length];
            int random;
            //duplicate queue into p first
            for (int t = 0; t < q.length; t++) {
                p[t] = q[t];
            }
            //now implement shuffle
            for (int t = 0; t < N; t++) {
                random =  (StdRandom.uniform(N-t) + first) % p.length;
                temp = p[random];
                p[random] = p[(first+t) % p.length];
                p[(first+t) % p.length] = temp;
            }
            
            
        }
        
        public boolean hasNext()  { return i < N;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = p[(first+i) % p.length];
            i++;
            return item;
        }
    }
    public static void main(String[] args) {
     RandomizedQueue<String> q = new RandomizedQueue<String>();
     q.enqueue("string 1");
     q.enqueue("string 2");
     q.enqueue("string 3");
     q.enqueue("string 4");
     q.enqueue("string 5");
     for (String s:q)
         StdOut.println(s+" | (" + q.size() + " left on queue)");
     StdOut.println("--------------Dequeu-----------");
     q.dequeue();
     for (String s:q)
        StdOut.println(s+" | (" + q.size() + " left on queue)");
    }

}