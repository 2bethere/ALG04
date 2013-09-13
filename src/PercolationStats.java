public class PercolationStats {
   private double[] result;
// perform T independent computational experiments on an N-by-N grid
   public PercolationStats(int N, int T)   
   {
      if (N <= 0 || T <= 0)
         throw new IllegalArgumentException(); 
      
      result = new double[T];
      for (int i = 0; i < T; i++) //Main loop
      {
         Percolation P = new Percolation(N);
         int c = 0;
         while (!P.percolates())
         {
            int d = StdRandom.uniform(N*N);
            if (!P.isOpen(d / N + 1, d % N + 1))
            {
               c++;
            }
            P.open(d/N+1, d % N + 1);
            
         }
         result[i] = (double) c / (double) (N*N);
      }
   }
   public double mean()                // sample mean of percolation threshold
   {      
      if (result.length == 0)
         return 0;
      double sum = 0;
      for (int i = 0; i < result.length; i++)
      {
         sum += result[i];
      }
      return (double) sum / (double) result.length;
   }
// sample standard deviation of percolation threshold
   public double stddev()               
   {
      if (result.length < 2)
      {
         return Double.NaN;
      }
      double error;
      double mean = this.mean();
      double sum = 0;
      for (int i = 0; i < result.length; i++)
      {
         error = result[i] - mean;
         sum += error*error;
      }
      return Math.sqrt(sum/(result.length-1));
   }
   // returns lower bound of the 95% confidence interval
   public double confidenceLo()          
   {
      return (double) (mean() - (1.96 * stddev() / Math.sqrt(result.length)));
   }
   // returns upper bound of the 95% confidence interval
   public double confidenceHi()
   {
      return (double) (mean() + (1.96 * stddev() / Math.sqrt(result.length)));
   }
   public static void main(String[] args)   // test client, described below
   {
      PercolationStats S = new PercolationStats(Integer.parseInt(args[0]), 
                                     Integer.parseInt(args[1]));
      System.out.println(S.mean());
      System.out.println(S.stddev());
      System.out.println(S.confidenceLo());
      System.out.println(S.confidenceHi());      
   }
}