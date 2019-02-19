public class Subset
{
   public static void main(String[] args)
   {
       if (args.length != 1)throw new IllegalArgumentException("ERROR");
       
       int k = Integer.parseInt(args[0]);
       
       RandomizedQueue<String> rQ = new RandomizedQueue<String>();
       
       while(!StdIn.isEmpty())
       {
           String str = StdIn.readString();
           rQ.enqueue(str);
       }
       
       for(int i = 0;i < k;i++)
           StdOut.println(rQ.dequeue());
   }
}
