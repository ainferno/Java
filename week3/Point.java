import java.util.Comparator;

public class Point implements Comparable<Point> 
{

    // compare points by slope
    public  final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) 
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() 
    {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) 
    {
        if(that == null)
        {
            throw new NullPointerException("NullPointerException");
        }
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) 
    {
        if(that == null)throw new NullPointerException("NullPointerException");
        double dobX, dobY;
        dobX = that.x-this.x;
        dobY = that.y-this.y;
        if(that.x != this.x && that.y == this.y)return 0;
        else if(that.x != this.x)return dobY/dobX;
        else if(that.y != this.y)return Double.POSITIVE_INFINITY;
        else return Double.NEGATIVE_INFINITY;
        /* YOUR CODE HERE */
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) 
    {
        if(that == null)
        {
            throw new NullPointerException("NullPointerException");
        }
        /* YOUR CODE HERE */
        if(this.y > that.y)return +1;
        else if(this.y < that.y)return -1;
        else
        {
            if(this.x > that.x)return +1;
            else if(this.x < that.x)return -1;
            else return 0;
        }
    }

    // return string representation of this point
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    private class SlopeOrder implements Comparator<Point>
    {
        public int compare(Point a, Point b)
        {
            if(slopeTo(a) > slopeTo(b))return +1;
            else if(slopeTo(a) < slopeTo(b))return -1;
            else return 0;
        }
    }
    // unit test
    public static void main(String[] args) 
    {
        Point p1 = new Point(10,10);
        StdOut.println(p1.toString());
        
        Point p2 = new Point(10,20);
        StdOut.println(p2.toString());
        
        StdOut.println(p2.slopeTo(p1));
    }
}