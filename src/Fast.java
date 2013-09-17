import java.util.Arrays;

public class Fast {
   public static void main(String[] args)
   {
       // rescale coordinates and turn on animation mode
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       StdDraw.show(0);
     //input processing.
       String filename = args[0];
       In in = new In(filename);
       int N = in.readInt();
       Point[] points = new Point[N];
       for (int i = 0; i < N; i++) {
           int x = in.readInt();
           int y = in.readInt();
           Point p = new Point(x, y);
           points[i] = p;
           p.draw();
       }
       //
      
       
       //////////////////////////////////////////////////
       
       for (int p = 0; p < N; p++)
       {
           //Step 1: Sort
           Arrays.sort(points, p + 1, N, points[p].SLOPE_ORDER);
           //Step 2: Look for longest chain
           for (int t = 0; t < N; t++)
           {
               StdOut.print(points[t].toString());
           }
           StdOut.print("\n");
           int index1, index2;
           index1 = p + 1;
           index2 = p + 2;
           
           while (index2 < N -1)
           {
           StdOut.println("Searching... index1:"+index1+" index2:"+index2);   
           while (points[p].slopeTo(points[index1]) 
                   == points[p].slopeTo(points[index2]) && index2 < N - 1)
           {
               index2++;
               StdOut.println("Equal:" + index2);
           }
           if ((index2 - index1) >= 3)
           {
               int searchToken = index2-index1;
            // Construct buffer
               Point[] outputBuffer = new Point[searchToken + 1];
               outputBuffer[0] = points[p];
               int outCount = searchToken;
               while (index1 < index2)
               {
                   outputBuffer[searchToken - (index2-index1)+1] = points[index1];
                   index1++;
               }
               // sort
               Arrays.sort(outputBuffer);
               for (int o = 0; o < outCount; o++)
               {
                   StdOut.print(outputBuffer[o].toString() + "->");
               }
               StdOut.print(
           outputBuffer[outCount].toString() + "\n");
               outputBuffer[0].drawTo(outputBuffer[outCount]);
           }
           index1 = index2;
           
           }
       }
           
       //////////////////////////////////////////////////
       // display to screen all at once
       StdDraw.show(0);
   }
}
