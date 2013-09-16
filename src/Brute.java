import java.util.Arrays;



public class Brute {
   public static void main(String[] args)
   {
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
       // rescale coordinates and turn on animation mode
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       StdDraw.show(0);
       //
       double result1, result2, result3;
       for (int p = 0; p < N; p++)
       {
           for (int q = p + 1; q < N; q++)
           {
               for (int r = q + 1; r < N; r++)
               {
                   for (int s = r + 1; s < N; s++)
                   {
                       
                       result1 = points[p].slopeTo(points[q]);
                       result2 = points[p].slopeTo(points[r]);
                       result3 = points[p].slopeTo(points[s]);
                       
                       /*
                       result1 = points[p].slopeTo(points[q]);
                       result2 = points[p].slopeTo(points[r]);
                       result3 = points[p].slopeTo(points[s]);
                      
                       result1 = points[p].SLOPE_ORDER .compareTo(points[q]);
                       result2 = points[p].compareTo(points[r]);
                       result3 = points[p].compareTo(points[s]);
                       */
                       //
                       if (result1 == result2 && result2 == result3 
                               && result1 == result3)
                       {
                           Point[] outputBuffer = new Point[4];
                           outputBuffer[0] = points[p];
                           outputBuffer[1] = points[q];
                           outputBuffer[2] = points[r];
                           outputBuffer[3] = points[s];
                           Arrays.sort(outputBuffer);
                           //
                           outputBuffer[0].drawTo(outputBuffer[3]);
                           //
                           StdOut.println(outputBuffer[0].toString() + "->" 
                       + outputBuffer[1].toString() 
                       + "->" + outputBuffer[2].toString() + "->" 
                       + outputBuffer[3].toString());
                       }
                   }   
               }   
           }   
       }
    // display to screen all at once
       StdDraw.show(0);
   }
}
