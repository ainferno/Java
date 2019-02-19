public class Percolation
{
    private boolean[] id; //Sorry for  Russian Letters
    private WeightedQuickUnionUF uf; //Sorry for  Russian Letters
    private int count; //Sorry for  Russian Letters
    private int top; //Sorry for  Russian Letters
    private int bottom; //Sorry for  Russian Letters

    public Percolation(int N)          // create N-by-N grid, with all sites blocked
    {
        count = N; //Sorry for  Russian Letters
        id = new boolean[N*N]; //Sorry for  Russian Letters
        for (int i = 0;  i < N * N;  i++)id[i] = false;  //Sorry for  Russian Letters
        uf = new WeightedQuickUnionUF(N * N);  //Sorry for  Russian Letters
        top = -1; //Sorry for  Russian Letters
        bottom = -1; //Sorry for  Russian Letters
    }
    private int xytoi(int Y, int X)
    {
        X--; //Sorry for  Russian Letters
        Y--; //Sorry for  Russian Letters
         return Y * count + X; //Sorry for  Russian Letters
    }
    public void open(int i, int j)     // open site(row i, column j)if  it is not already
    {
        if (!(i > 0 && j > 0 && i <= count && j <= count))
        {
            throw new IndexOutOfBoundsException("OUHOOO");
        }
        else
        {
            if (id[xytoi(i, j)])//Sorry for  Russian Letters
            {
                 return; //Sorry for  Russian Letters
            }
            else
            {
                id[xytoi(i, j)] = true;  //Sorry for  Russian Letters
                if(count == 1)
                {
                    bottom = 0;
                    top = 0;
                }
                else
                {
                    if (i == 1)
                    {
                        if (top == -1)
                        {
                            top = xytoi(i, j);  //Sorry for  Russian Letters
                            if (id[xytoi(i+1, j)])uf.union(top, xytoi(i + 1, j));  //Sorry for  Russian Letters
                        }
                        else
                        {
                            uf.union(xytoi(i, j), top);  //Sorry for  Russian Letters
                            if (id[xytoi(i+1, j)])uf.union(xytoi(i, j), xytoi(i + 1, j));  //Sorry for  Russian Letters
                        }
                    }
                    if (i == count && count != 1)
                    {
                        if (bottom == -1)
                        {
                            bottom = xytoi(i, j);  //Sorry for  Russian Letters
                            if (id[xytoi(i-1, j)])uf.union(xytoi(i, j), xytoi(i - 1, j));  //Sorry for  Russian Letters
                        }
                        else
                        {
                            uf.union(xytoi(i, j), bottom);  //Sorry for  Russian Letters
                            if (id[xytoi(i-1, j)])uf.union(xytoi(i, j), xytoi(i - 1, j));  //Sorry for  Russian Letters
                        }
                        if (id[xytoi(i-1, j)])uf.union(xytoi(i, j), xytoi(i - 1, j));  //Sorry for  Russian Letters
                    }
                    if (i < count && i > 1)
                    {
                        if (id[xytoi(i-1, j)])uf.union(xytoi(i, j), xytoi(i - 1, j));  //Sorry for  Russian Letters
                        if (id[xytoi(i+1, j)])uf.union(xytoi(i, j), xytoi(i + 1, j));  //Sorry for  Russian Letters
                    }
                    
                    if (j == 1 && count != 1)
                    {
                        if (id[xytoi(i, j+1)])uf.union(xytoi(i, j), xytoi(i, j + 1));  //Sorry for  Russian Letters
                    }
                    if (j == count && count != 1)
                    {
                        if (id[xytoi(i, j-1)])uf.union(xytoi(i, j), xytoi(i, j - 1));  //Sorry for  Russian Letters
                    }
                    if (j < count && j > 1)
                    {
                        if (id[xytoi(i, j+1)])uf.union(xytoi(i, j), xytoi(i, j + 1));  //Sorry for  Russian Letters
                        if (id[xytoi(i, j-1)])uf.union(xytoi(i, j), xytoi(i, j - 1));  //Sorry for  Russian Letters
                    }
                }
            }
        }
    }
    public boolean isOpen(int i, int j)// is site (row i, column j) open?
    {
        if (!(i > 0 && j > 0 && i <= count && j <= count))
        {
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        else
        {
             return id[xytoi(i, j)];  //Sorry for  Russian Letters
        }
    }
    public boolean isFull(int i, int j)// is site (row i, column j) full?
    {
        if (!(i > 0 && j > 0 && i <= count && j <= count))
        {
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
        else
        {
            if (top == -1) return false;
            else
            {
                 return uf.connected(xytoi(i, j), top);  //Sorry for  Russian Letters
            }
        }
    }
    public boolean percolates()            // does the system percolate?
    {
        if (top == -1 || bottom == -1) return false;
        else
        {
             return uf.connected(bottom, top);
        }
    }
    public static void main(String[] args)
    {
        Percolation pe = new Percolation(1);
        pe.open(1,1);
        //pe.open(1,2);
        //pe.open(2,1);
        //pe.open(2,2);
    }
}
