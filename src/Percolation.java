public class Percolation {
    private int top, nInternal;
    private WeightedQuickUnionUF QU;
    private WeightedQuickUnionUF quDisplay;
    private boolean[][] open; //default init to 0
  
   //private int[] bottom; //use an array to avoid backwash
    private int bottom; //use an array to avoid backwash
      
   public Percolation(int N) // create N-by-N grid, with all sites blocked
   {
      open = new boolean[N][N];
      QU = new WeightedQuickUnionUF(N*N+1+N); //top, bottom array + N*N
      quDisplay = new WeightedQuickUnionUF(N*N+N); //top only
      top = 0;
      /*
      bottom = new int[N];
      for (int i = 0; i < N; i++)
      {
         bottom[i] = N*N+1+i;
      }
      */
      bottom = N*N+1;
      nInternal = N;
            
   }
    // open site (row i, column j) if it is not already
   public void open(int i, int j)
   {
      if (i < 1 || i > nInternal || j < 1 || j > nInternal)
      {
         throw new IndexOutOfBoundsException();
      }
      open[i-1][j-1] = true;
      int q = (i-1)*nInternal+j;
      //open means connect to all adjacent cells
      if (i <= 1)
      {
         //hit top row
         QU.union(top, q);
         quDisplay.union(top, q);
      }
      else if (isOpen(i-1, j))
      {
         QU.union((i-2)*nInternal+j, q);
         quDisplay.union((i-2)*nInternal+j, q);
      } 
      
      if (i >= nInternal)
      {
         //hit bottom, union the cell directly below
         //QU.union(bottom[j-1], q);
          QU.union(q, bottom);
      }
      else if (isOpen(i+1, j))
      {
         QU.union((i)*nInternal+j, q);
         quDisplay.union((i)*nInternal+j, q);
      }
      
      //left and right
      if (j > 1)
      {
         if (isOpen(i, j-1))
         {
            QU.union((i-1)*nInternal+j-1, q);
            quDisplay.union((i-1)*nInternal+j-1, q);
         }
      }
      if (j < nInternal)
      {
         if (isOpen(i, j+1))
         {
            QU.union((i-1)*nInternal+j+1, q);
            quDisplay.union((i-1)*nInternal+j+1, q);
         }
      }

   }
    public boolean isOpen(int i, int j)    // is site (row i, column j) open?
    {
        if (i < 1 || i > nInternal || j < 1 || j > nInternal)
           {
               throw new IndexOutOfBoundsException();
           }
       return open[i-1][j-1];
    }
    public boolean isFull(int i, int j)    // is site (row i, column j) full?
    {
        if (i < 1 || i > nInternal || j < 1 || j > nInternal)
           {
               throw new IndexOutOfBoundsException();
           }
       return quDisplay.connected(top, (i - 1) * nInternal + j);
    }
    
    public boolean percolates()            // does the system percolate?
    {     
        /*
       for (int i = 0; i < nInternal; i++)
       {
          if (QU.connected(top, bottom[i]))
          {
             return true;
          }
       }
        
       return false;
       */
        return QU.connected(top, bottom);
    }
}