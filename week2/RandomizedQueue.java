import java.lang.UnsupportedOperationException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] s;
    private int N = 0;
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        s = (Item[]) new Object[1];
    }
    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }
    public int size()                        // return the number of items on the queue
    {
        return N;
    }
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }
    public void enqueue(Item item)           // add the item
    {
        if(item == null)
        {
            throw new NullPointerException("NullPointerException");
        }
        else
        {
            if(N == s.length)resize(2 * s.length);
            s[N++] = item;
        }
    }
    public Item dequeue()                    // delete and return a random item
    {
        if(N == 0)
        {
            throw new java.util.NoSuchElementException("java.util.NoSuchElementException");
        }
        else
        {
            /*
            s[StdRandom.uniform(N)] = s[N - 1];//Разобраться
            
            Item item = s[--N];
            s[N] = null;
            if(N > 0 && N == s.length/4)resize(s.length/2);
            return item;*/
        }
    }
    public Item sample()                     // return (but do not delete) a random item
    {
        if(N == 0)
        {
            throw new java.util.NoSuchElementException("java.util.NoSuchElementException");
        }
        else
            return s[StdRandom.uniform(N)];
    }

    private class ListIterator implements Iterator<Item>
    {
        private int Nun = N-1;
        private Item[] copy = (Item[]) new Object[N];
        private ListIterator()
        {
            //copy = (Item[]) new Object[N];
            for(int i = 0; i < N; i++)
                copy[i] = s[i];
            
            StdRandom.shuffle(copy);
            //for(int i = 0;i < N;i++)
            //    StdOut.println(copy[i]);
            //StdRandom.shuffle(copy);
        }
        public boolean hasNext()
        {
            //StdOut.println(Nun < N);
            return Nun >= 0; // error for N = 1, Nan = 0
        }
        public void remove()
        {
            throw new UnsupportedOperationException("UnsupportedOperationException");
        }
        public Item next()
        {
            //StdOut.println(copy[Nun]);
            if(hasNext())
            {
                Item item = copy[Nun];
                //StdOut.print(Nun + " ");
                //StdOut.println(copy[Nun]);
                Nun--;
                return item;
            }
            else
            {
                throw new java.util.NoSuchElementException("java.util.NoSuchElementException");
            }
        }
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator();
    }

    public static void main(String[] args)   // unit testing
    {
        /*RandomizedQueue rQ = new RandomizedQueue();
        for(int i = 0;i < 10;i++)
            rQ.enqueue(i);
//        rQ.enqueue(1);
        
        for(Object i : rQ)
            StdOut.println(i);*/
    }
}
