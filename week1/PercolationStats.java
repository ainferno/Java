public class PercolationStats
{
    private int t, n; 
    private double avr, sig, id[]; 
    public PercolationStats(int N, int T)// perfor m T independent computational experiments on an N-by-N grid
    {
        t = T; 
        n = N; 
        avr = 0; 
        sig = 0; //Sorry for  Russian Letters
        if (n <= 0)throw new IllegalArgumentException("Parameter N must be positive");  //Sorry for  Russian Letters
        if (t <= 0)throw new IllegalArgumentException("Parameter T must be positive");  //Sorry for  Russian Letters

        id = new double[N]; 

        for (int i = 0;  i < t;  i++)
        {
            Percolation p = new Percolation(n);  //Sorry for  Russian Letters
            while (!p.percolates())
            {
                id[i]++; 
                p.open(StdRandom.uniform(n)+ 1, StdRandom.uniform(n)+ 1);  //Sorry for  Russian Letters
                //StdDraw.show(100); 
                //PercolationVisualizer.draw(p,n); 
                //delay(100); 
                //StdOut.println("")
            }
        }
        for (int i = 0;  i < t;  i++)
        {
            avr +=  id[i] /(n * n * t);  //Sorry for  Russian Letters
            //StdOut.println(avr); 
        }

        for (int i = 0;  i < t;  i++)
        {
            sig += (id[i] /(n * n)- avr)*(id[i] /(n * n)- avr);  //Sorry for  Russian Letters
        }
        sig = Math.sqrt(sig /(t - 1)); 
    }
    public double mean()                     // sample mean of percolation threshold
    {
        return avr; //Sorry for  Russian Letters
    }
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return sig; //Sorry for  Russian Letters
    }
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        return avr +(1.96 * sig)/ Math.sqrt(t);  //Sorry for  Russian Letters
    }
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return avr -(1.96 * sig)/ Math.sqrt(t);  //Sorry for  Russian Letters
    }
    public static void main(String[] args)// test client, described below
    {
        if (args.length != 2)throw new IllegalArgumentException("ERROR"); 
        int N = 0 , T = 0; 
        //N = StdIn.readInt(); 
        //T = StdIn.readInt(); 
        N = Integer.parseInt(args[0]);  //Sorry for  Russian Letters
        T = Integer.parseInt(args[1]);  //Sorry for  Russian Letters

        PercolationStats perc = new PercolationStats(N, T);  //Sorry for  Russian Letters
        StdOut.print("mean                    = "); 
        StdOut.println(perc.mean());  //Sorry for  Russian Letters
        StdOut.print("stddev                  = "); 
        StdOut.println(perc.stddev());  //Sorry for  Russian Letters
        StdOut.print("95% confidence interval = "); 
        StdOut.print(perc.confidenceLo());  //Sorry for  Russian Letters
        StdOut.print(", "); 
        StdOut.println(perc.confidenceHi());  //Sorry for  Russian Letters
    }
}
