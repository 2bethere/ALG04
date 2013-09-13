import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    //
    private class NodeIterator implements Iterator<Item> 
    {
        private Node<Item> current;
        private Deque<Item> q;
        
        
        public NodeIterator(Deque<Item> qInput)
        {
            q = qInput;
            current = q.tail;
        
        }
        
        @Override
        public boolean hasNext() {
          return current != null;
        }
       
        @Override
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
          Node<Item> obj = current;
          current = current.getNext();
          return (Item) obj.getObject();
        }
       
        @Override
        public void remove() {
          throw new java.lang.UnsupportedOperationException();
        }
    }
    ////////////////////////////////////////////////////////////////
    private class Node<K>
    {
        private K object;
        private Node<K> prev;
        private Node<K> next;
        Node(K t)
        {
            object = t;
        }
        public K getObject()
        {
            return object;
        }
        public Node<K> getNext()
        {
            return next;
        }
        public void setNext(Node<K> n)
        {
            next = n;
        }
        public Node<K> getPrev()
        {
            return prev;
        }
        public void setPrev(Node<K> n)
        {
            prev = n;
        }
        
    }
    ////////////////////////////////////////////////////////////////
    private Node<Item> head;
    private Node<Item> tail;
    private int nodeCount;
    //
    public Deque()                     // construct an empty deque
    {
        head = null;
        tail = null;
        nodeCount = 0;
    }
    public boolean isEmpty()           // is the deque empty?
    {
        if (nodeCount == 0)
        {
            return true;
        }
        return false;        

    }
    public int size()                  // return the number of items on the deque
    {
        return nodeCount;
    }
    public void addFirst(Item item)    // insert the item at the front
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node<Item> n = new Node<Item>(item);
        if (nodeCount == 0)
        {
            head = n;
            tail = n;
            nodeCount++;
        }
        else
        {
            head.setNext(n);
            n.setPrev(head);
            head = n;
            nodeCount++;
        }
        
    }
    public void addLast(Item item)     // insert the item at the end
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node<Item> n = new Node<Item>(item);
        if (nodeCount == 0)
        {
            head = n;
            tail = n;
            nodeCount++;
        }
        else
        {
            tail.setPrev(n);
            n.setNext(tail);            
            tail = n;
            nodeCount++;
        }
    }
    public Item removeFirst()          // delete and return the item at the front
    {
        if (nodeCount == 0)
            throw new java.util.NoSuchElementException();
        Node<Item> n = head;
        if (head == tail)
        {
            tail.setPrev(null);
            head.setNext(null); 
            tail = null;
        }
        head = head.getPrev();
        head.setNext(null);
        nodeCount--;
        return n.getObject();
    }
    
    public Item removeLast()           // delete and return the item at the end
    {
        if (nodeCount == 0)
            throw new java.util.NoSuchElementException();
        Node<Item> n = tail;
        if (head == tail)
        {
            tail.setPrev(null);
            head.setNext(null);
            head = null;
        }
        
        tail = tail.getNext();
        tail.setPrev(null);
        nodeCount--;        
        return n.getObject();
    }
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()   
    {
        return new NodeIterator(this);
    }
    
    //Testing function
    public static void main(String[] args)
    {
        Deque<String> queue = new Deque<String>();        
        queue.addFirst("string1");
        queue.addFirst("string2");
        queue.addFirst("string3");
        queue.addFirst("string4");
        
        queue.addFirst("string5");
        queue.addFirst("string6");
        
        queue.addFirst("string7");
        queue.addFirst("string8");
        
        
        queue.removeFirst();
        queue.removeLast();
        
        Iterator<String> i = queue.iterator();
        while (i.hasNext()) {
           String s = i.next();
           StdOut.println(s);
        }
        StdOut.println("t");
    }
}

