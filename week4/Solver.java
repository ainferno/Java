public class Solver 
{
  private int move;
  private boolean sol;
  private Stack<Board> qu;
  
  private class Node  implements Comparable<Node> 
  {
    private Board bord;
    private Node prev;
    private int moves;
    
    private Node(Board bord, Node prev, int moves)
    {
      this.bord = bord;
      this.prev = prev;
      this.moves = moves;
    }
    
    public int compareTo(Node that)
    {
      if(this.bord.manhattan()+this.moves > that.bord.manhattan()+that.moves)return +1;
      else if(this.bord.manhattan()+this.moves < that.bord.manhattan()+that.moves)return -1;
      else return 0;
    }
    private int Moves()
    {
      return moves;
    }
  }
  public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
  {
    Node q = new Node(initial,null,0);
    Node q1 = new Node(initial.twin(),null,0); 
    
    
    sol = false;
    
    MinPQ<Node> pq = new MinPQ<Node>();
    MinPQ<Node> pq2 = new MinPQ<Node>();
    
    pq.insert(q);
    pq2.insert(q1);
    
    boolean sol2 = true;
    
    if(pq.min().bord.isGoal())
    {
      q = pq.min();
      sol = true;
      sol2 = false;
    }
    else if(pq2.min().bord.isGoal())
    {
      sol = false;
      sol2 = false;
    }
    else
    {
      Node q2 = pq.delMin();
      
      for(Board w : q2.bord.neighbors())
        pq.insert(new Node(w,q2,q2.Moves()+1));
      
      Node q21 = pq2.delMin();
      
      for(Board w : q21.bord.neighbors())
        pq2.insert(new Node(w,q21,q21.Moves() +1));
      
    }
    
    while(sol2)
    {
      //StdOut.println(pq.min().bord.toString());
      //StdDraw.show(1000);
      
      //StdOut.print(pq.min().bord);
      //StdOut.println("Manh - " + pq.min().bord.manhattan() + " Prio - " + (pq.min().bord.manhattan()+pq.min().Moves()) + " Moves - " + pq.min().Moves() + "\n");
      
      if(pq.min().bord.isGoal())
      {
        q = pq.min();
        sol = true;
        break;
      }
      else if(pq2.min().bord.isGoal())
      {
        sol = false;
        break;
      }
      else
      {
        Node q2 = pq.delMin();
        
        for(Board w : q2.bord.neighbors())
          if(!w.equals(q2.prev.bord))pq.insert(new Node(w,q2,q2.Moves()+1));
        
        Node q21 = pq2.delMin();
        
        for(Board w : q21.bord.neighbors())
          if(!w.equals(q21.prev.bord))pq2.insert(new Node(w,q21,q21.Moves() +1));
        
      }
    }
    move = -1;
    if(sol)
    {
      Queue<Board> qu2 = new Queue<Board>();
      qu = new Stack<Board>();
      
      Node th = q;
      
      while(th != null)
      {
        qu2.enqueue(th.bord);
        th = th.prev;
        move++;
      }
      
      while(!qu2.isEmpty())qu.push(qu2.dequeue());
    }
  }
  public boolean isSolvable()             // is the initial board solvable?
  {
    return sol;
  }
  public int moves()                      // min number of moves to solve initial board; -1 if no solution
  {
    return move;
  }
  public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
  {
    if(sol)return qu;
    else return null;
  }
  public static void main(String[] args)
  {
    // create initial board from file
    In in = new In("puzzle30.txt");//new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
      blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);
    
    // solve the puzzle
    Solver solver = new Solver(initial);
    
    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}