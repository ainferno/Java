import java.util.Random;
public class HelloWorld
{
    public static void main(String args[])
    {
        double[] a = new double[100];
        Random rand = new Random();
        double max;
        for(int i = 0;i < 100;i++)
            a[i] = rand.nextDouble();
        max = a[0];
        for(int i = 1;i < a.length;i++)
            if(max < a[i])
                max = a[i];
        System.out.println("Max = " + max);
    }
}