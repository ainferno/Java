import java.util.Arrays;

public class Brute 
{
   public static void main(String[] args)
   {
       String filename;
       filename = args[0];
       //filename = "input.txt";
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
       
       for(int i1 = 0;i1 < n;i1++)
       {
           for(int i2 = i1+1;i2 < n;i2++)
           {
               for(int i3 = i2+1;i3 < n;i3++)
               {
                   for(int i4 = i3+1;i4 < n;i4++)
                   {
                       //StdOut.println(i1 + " " + i2 + " " + i3 + " " + i4);
                       Point p2[] = new Point[4];
                       p2[0] = p[i1];
                       p2[1] = p[i2];
                       p2[2] = p[i3];
                       p2[3] = p[i4];
                       Arrays.sort(p2);
                       if(p2[0].slopeTo(p2[1]) == p2[1].slopeTo(p2[2]) && p2[0].slopeTo(p2[2]) == p2[0].slopeTo(p2[3]) && p2[0].slopeTo(p2[1]) == p2[0].slopeTo(p2[3]) && p2[1].slopeTo(p2[0]) == p2[1].slopeTo(p2[2]) && p2[1].slopeTo(p2[2]) == p2[1].slopeTo(p2[3]) && p2[1].slopeTo(p2[0]) == p2[1].slopeTo(p2[3]) /**/&& p2[2].slopeTo(p2[0]) == p2[2].slopeTo(p2[1]) && p2[2].slopeTo(p2[1]) == p2[2].slopeTo(p2[3]) && p2[2].slopeTo(p2[1]) == p2[2].slopeTo(p2[3]) && p2[3].slopeTo(p2[0]) == p2[3].slopeTo(p2[1]) && p2[3].slopeTo(p2[1]) == p2[3].slopeTo(p2[2]) && p2[3].slopeTo(p2[0]) == p2[3].slopeTo(p2[2]))
                       {
                           StdOut.println(p2[0].toString() + " -> " + p2[1].toString() + " -> " + p2[2].toString() + " -> " + p2[3].toString());
                           p2[0].drawTo(p2[3]);
                       }
                   }
               }
           }
       }
       StdDraw.show(0);
   }
}