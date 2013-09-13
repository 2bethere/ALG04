import java.util.Iterator;

public class Subset {
    public static void main(String[] args)
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        //StdOut.print(args[0]);
        int count = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            q.enqueue(item);
            
        }
        for (Iterator<String> i = q.iterator(); i.hasNext() && count > 0; count--)
        {
            StdOut.println(i.next());
        }
        
    }
}
