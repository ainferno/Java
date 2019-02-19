import java.util.Arrays;

public class Fast 
{
   public static void main(String[] args)
   {
       String filename;
       //filename = args[0];
       filename = "input.txt";
       In in = new In(filename);
       
       int n = in.readInt();
       
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       StdDraw.show(0);
       
       Point[] p = new Point[n];
       
       for(int i = 0;i < n;i++)
       {
           p[i] = new Point(in.readInt(),in.readInt());
       }
       for(int i = 0;i < n;i++)
       {
           //StdOut.println(p[i]);
           p[i].draw();
       }
       for(int i = 0;i < n;i++)
       {
           Point p = p[0];
           p[0] = p[i];
           p[i] = p;
           Arrays.sort(p,1,n-1,p[0].SLOPE_ORDER);
       }
   }
}