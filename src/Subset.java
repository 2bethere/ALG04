public class Subset {
    public static void main(String[] args)
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        //StdOut.print(args[0]);
        int count = Integer.parseInt(args[0]);
        while (count > 0)
        {
            String item = StdIn.readString();
            q.enqueue(item);
            count--;
        }
        for (String s:q)
            StdOut.println(s);

    }
}
