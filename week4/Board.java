import java.lang.Math;
public class Board 
{
    private int[][] bord;
    private int N;
    private int moves;
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {                                      // (where blocks[i][j] = block in row i, column j)
        N = blocks[0].length;
        bord = new int[N][N];
        for(int i = 0;i < N;i++)
        {
            for(int q = 0;q < N;q++)
            {
                bord[i][q] = blocks[i][q];
            }
        }
        moves = 0;        
    }
    public int dimension()                 // board dimension N
    {
        return N;
    }
    private int xytoi(int Y, int X)
    {
        return Y * N + X+1;
    }
    public int hamming()                   // number of blocks out of place
    {
        int qt = 0;
        for(int i = 0;i < N;i++)
        {
            for(int q = 0;q < N;q++)
            {
                if(bord[i][q] != xytoi(i,q) && bord[i][q] != 0)qt++;
            }
        }
        return qt;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int qt = 0;
        for(int i = 0;i < N;i++)
        {
            for(int q = 0;q < N;q++)
            {
                if(bord[i][q]!=0)
                {
                    int I = bord[i][q]-1;
                    int Q = I / N;
                    I = (bord[i][q]-1) - Q * N;
                    qt = qt + Math.abs(Q - i) + Math.abs(I - q);
                    //StdOut.println("i = " + i + " q = " + q + " Q = " + Q + " I = " + I + " qt = "  +qt);
                }
            }
        }
        return qt;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        if(hamming() == 0)return true;
        else return false;
    }
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    {
        int[][] bord2 = new int[N][N];
        for(int i = 0;i < N;i++)
        {
            for(int w = 0;w < N;w++)
            {
                bord2[i][w] = bord[i][w];
            }
        }
        if(bord[0][0] != 0 && bord[0][1] != 0)
        {
            int a = bord2[0][1];
            bord2[0][1] = bord2[0][0];
            bord2[0][0] = a;
        }
        else
        {
            int a = bord2[1][1];
            bord2[1][1] = bord2[1][0];
            bord2[1][0] = a;
        }
        return new Board(bord2);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if(y == this)return true;
        if(y == null)return false;
        if(y.getClass() != this.getClass())return false;
        
        Board that = (Board) y;
        if(this.N != that.N)return false;
        
        for(int i = 0;i < N;i++)
        {
            for(int q = 0;q < N;q++)
            {
                if(this.bord[i][q] != that.bord[i][q])return false;
            }
        }
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Stack<Board> q = new Stack<Board>();
        int x = 0 , y = 0;
        int[][] bord2 = new int[N][N];
        
        for(int i = 0;i < N;i++)
        {
            for(int w = 0;w < N;w++)
            {
                bord2[i][w] = bord[i][w];
            }
        }
        
        for(int i = 0;i < N;i++)
        {
            for(int w = 0;w < N;w++)
            {
                if(bord[i][w] == 0)
                {
                    x = i;
                    y = w;
                    break;
                }
            }
        }
        //StdOut.println("x = " + x + " y = " + y);
        
        if(x == 0)
        {
            int a = bord2[x+1][y];
            bord2[x+1][y] = bord2[x][y];
            bord2[x][y] = a;
            Board bor = new Board(bord2);
            bor.moves = moves + 1;
            q.push(new Board(bord2));
        }
        else if(x == N-1)
        {
            int a = bord2[x-1][y];
            bord2[x-1][y] = bord2[x][y];
            bord2[x][y] = a;
            Board bor = new Board(bord2);
            bor.moves = moves + 1;
            q.push(new Board(bord2));
        }
        else
        {
            int a = bord2[x+1][y];
            bord2[x+1][y] = bord2[x][y];
            bord2[x][y] = a;
            
            Board bor1 = new Board(bord2);
            bor1.moves = moves + 1;
            
            q.push(bor1);          
            
            a = bord2[x+1][y];
            bord2[x+1][y] = bord2[x][y];
            bord2[x][y] = a;
            
            int b = bord2[x-1][y];
            bord2[x-1][y] = bord2[x][y];
            bord2[x][y] = b;
            
            Board bor2 = new Board(bord2);
            bor2.moves = moves + 1;
            
            q.push(bor2);
        }
        for(int i = 0;i < N;i++)
        {
            for(int w = 0;w < N;w++)
            {
                bord2[i][w] = bord[i][w];
            }
        }
        if(y == 0)
        {
            int a = bord2[x][y+1];
            bord2[x][y+1] = bord2[x][y];
            bord2[x][y] = a;
            Board bor = new Board(bord2);
            bor.moves = moves + 1;
            q.push(new Board(bord2));
        }
        else if(y == N-1)
        {
            int a = bord2[x][y-1];
            bord2[x][y-1] = bord2[x][y];
            bord2[x][y] = a;      
            Board bor = new Board(bord2);
            bor.moves = moves + 1;
            q.push(new Board(bord2));
        }
        else
        {
            int a = bord2[x][y+1];
            bord2[x][y+1] = bord2[x][y];
            bord2[x][y] = a;

            Board bor1 = new Board(bord2);
            bor1.moves = moves + 1;
            
            q.push(bor1);    
            
            a = bord2[x][y+1];
            bord2[x][y+1] = bord2[x][y];
            bord2[x][y] = a;
            
            a = bord2[x][y-1];
            bord2[x][y-1] = bord2[x][y];
            bord2[x][y] = a;
            
            Board bor2 = new Board(bord2);
            bor2.moves = moves + 1;
            
            q.push(bor2);
            
            a = bord2[x][y-1];
            bord2[x][y-1] = bord2[x][y];
            bord2[x][y] = a;
        }
        
        //for(Board fat : q)StdOut.println(fat.toString());
        //StdOut.println("POINT");
        return q;
    }
    public String toString()               // string representation of the board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", bord[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    public static void main(String[] args)
    {
        int[][]h = new int[3][3];
        h[0][0]=8;
        h[0][1]=1;
        h[0][2]=3;
        h[1][0]=4;
        h[1][1]=0;
        h[1][2]=2;
        h[2][0]=7;
        h[2][1]=6; 
        h[2][2]=5;
        Board bor = new Board(h);
        StdOut.print(bor.toString());
        StdOut.println(bor.hamming());
        StdOut.println(bor.manhattan());
        StdOut.println(bor.twin());
        //for(Board i : bor.neighbors())StdOut.print(i.toString());
    }
}