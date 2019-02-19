public class KdTree 
{
  private int size;
  private Node first;
  
  private class Node
  {
    private int depth;
    private Point2D point;
    private Node nBig;
    private Node nSmall;
    private RectHV rect;
    private Node(int dep,Point2D p,RectHV rect)
    {
      depth = dep;
      point = p;
      nBig = null;
      nSmall = null;
      this.rect = rect;
    }
  }
  
  public KdTree()                               // construct an empty set of points
  {
    first = null;
    size = 0;
  }
  public boolean isEmpty()                        // is the set empty?
  {
    return size == 0;
  }
  public int size()                               // number of points in the set
  {
    return size;
  }
  public void insert(Point2D p)                   // add the point p to the set (if it is not already in the set)
  {
    if(!contains(p))
    {
      if(size == 0)
      {
        first = new Node(1,p,new RectHV(0,0,1,1));
        size++;
      }
      else
      {
        Node x = first;
        while(true)
        {
          if(x.point.compareTo(p) ==0)
          {
            break;
          }
          else
          {
            if(x.depth % 2 == 1)
            {
              if(x.point.x() >= p.x())
              {
                if(x.nSmall == null)
                {
                  x.nSmall = new Node(x.depth+1,p,new RectHV(x.rect.xmin(),x.rect.ymin(),x.point.x(),x.rect.ymax()));
                  size++;
                  break;
                }
                else
                  x = x.nSmall;
              }
              else if(x.point.x() < p.x())
              {
                if(x.nBig == null)
                {
                  x.nBig = new Node(x.depth+1,p,new RectHV(x.point.x(),x.rect.ymin(),x.rect.xmax(),x.rect.ymax()));
                  size++;
                  break;
                }
                else
                  x = x.nBig;
              }
            }
            if(x.depth % 2 == 0)
            {
              if(x.point.y() >= p.y())
              {
                if(x.nSmall == null)
                {
                  x.nSmall = new Node(x.depth+1,p,new RectHV(x.rect.xmin(),x.rect.ymin(),x.rect.xmax(),x.point.y()));
                  size++;
                  break;
                }
                else
                  x = x.nSmall;
              }
              else if(x.point.y() < p.y())
              {
                if(x.nBig == null)
                {
                  x.nBig = new Node(x.depth+1,p,new RectHV(x.rect.xmin(),x.point.y(),x.rect.xmax(),x.rect.ymax()));
                  size++;
                  break;
                }
                else
                  x = x.nBig;
              }
            }
          }
        }
      }
    }
  }
  public boolean contains(Point2D p)              // does the set contain the point p?
  {
    //StdOut.println(size);
    if(size == 0)return false;
    else if(size == 1)return first.point.compareTo(p)==0;
    else
    {
      Node x = first;
      
      while(true)
      {
        //StdOut.println(x.point + " " + p);
        if(x.point.compareTo(p) == 0)return true;
        else
        {
          //StdOut.println(x.point);
          //StdOut.println(x.depth);
          if(x.depth % 2 == 1)
          {
            if(x.point.x() >= p.x())
            {
              //StdOut.println("POINT! x.x = " + x.point.x() + " x = " + p.x());
              //StdOut.println(x.nSmall.point);
              if(x.nSmall == null)return false;
              else x = x.nSmall; 
            }
            else if(x.point.x() < p.x())
            {
              if(x.nBig == null)return false;
              else x = x.nBig;
            }
          }
          else if(x.depth % 2 == 0)
          {
            //StdOut.println("WAT");
            if(x.point.y() >= p.y())
            {
              if(x.nSmall == null)return false;
              else x = x.nSmall;
            }
            else if(x.point.y() < p.y())
            {
              if(x.nBig == null)return false;
              else x = x.nBig;
            }
          }
        }
      }
    }
  }
  public void draw()                              // draw all of the points to standard draw
  {
    draw(first);
  }
  private void draw(Node x)
  {
    x.point.draw();
    if(x.depth % 2 == 0)new Point2D(x.rect.xmin(),x.point.y()).drawTo(new Point2D(x.rect.xmax(),x.point.y()));
    else new Point2D(x.point.x(),x.rect.ymin()).drawTo(new Point2D(x.point.x(),x.rect.ymax()));
    if(x.nSmall != null)draw(x.nSmall);
    if(x.nBig != null)draw(x.nBig);
  }
  public Iterable<Point2D> range(RectHV rect)     // all points in the set that are inside the rectangle
  {
    Queue<Point2D> qu = new Queue<Point2D>();
    if(size == 0)return null;
    add(qu,rect, first);
    
    return qu;
  }
  private void add(Queue<Point2D> qu,RectHV rect , Node x)
  {
    if(rect.contains(x.point))qu.enqueue(x.point);
    if(x.nSmall != null)if(rect.intersects(x.nSmall.rect))add(qu,rect,x.nSmall);
    if(x.nBig != null)if(rect.intersects(x.nBig.rect))add(qu,rect,x.nBig);
  }
  public Point2D nearest(Point2D p)               // a nearest neighbor in the set to p; null if set is empty
  {
    if(size == 0)return null;
    return nearest(first, p, first.point);
  }
  private Point2D nearest(Node x , Point2D p , Point2D best)
  {
    double m1 = p.distanceSquaredTo(best);
    double m2 = p.distanceSquaredTo(x.point);
    Point2D q = best;
    if(m1 > m2)q = x.point;
    
    Node left = x.nSmall , right = x.nBig;
    
    if(right == null || right == null)
    {
      if(left != null)if(left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(q))q = nearest(left,p,q);
      if(right != null)if(right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(q))q = nearest(right,p,q);
    }
    else
    {
      if(x.depth % 2 == 1)
      {
        if(p.x() > x.point.x())
        {
          left = x.nBig;
          right = x.nSmall;
        }
      }
      else
      {
        if(p.y() > x.point.y())
        {
          left = x.nBig;
          right = x.nSmall;
        }
      }
      if(left != null)if(left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(q))q = nearest(left,p,q);
      if(right != null)if(right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(q))q = nearest(right,p,q);
    }    
    return q;
  }
  public static void main(String[] args)
  {
    KdTree kt = new KdTree();
    kt.insert(new Point2D(0.5,0.5));
    kt.insert(new Point2D(0.4,0.9));
    kt.insert(new Point2D(0.7,0.3));
    kt.insert(new Point2D(0.9,0.2));
    kt.insert(new Point2D(0,0));
    kt.draw();
    kt.nearest(new Point2D(0.6,0.5)).draw();
    StdOut.println(kt.nearest(new Point2D(0.6,0.5)));
    //for(Point2D i : kt.range(new RectHV(0,0,1,1)))StdOut.println(i);
    StdOut.println(kt.contains(new Point2D(0.9,0.2)));
  }
}