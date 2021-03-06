public class PointSET 
{
  private int size;
  private SET<Point2D> points;
  public PointSET()                               // construct an empty set of points
  {
    points = new SET<Point2D>();
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
      points.add(p);
      size++;
    }
  }
  public boolean contains(Point2D p)              // does the set contain the point p?
  {
    return points.contains(p);
  }
  public void draw()                              // draw all of the points to standard draw
  {
    for(Point2D i : points)
    {
      i.draw();
    }
  }
  public Iterable<Point2D> range(RectHV rect)     // all points in the set that are inside the rectangle
  {
    Queue<Point2D> qu = new Queue<Point2D>();
    for(Point2D i : points)
    {
      if(rect.contains(i))qu.enqueue(i);
    }
    return qu;
  }
  public Point2D nearest(Point2D p)               // a nearest neighbor in the set to p; null if set is empty
  {
    Point2D p2 = points.min();
    for(Point2D i : points)
    {
      if(i.distanceTo(p) < p2.distanceTo(p))p2 = i;
    }
    return p2;
  }
}