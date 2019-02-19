import java.lang.UnsupportedOperationException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
    private int size;
    private Node first , last;
    private class Node
    {
        Item item;
        Node next , prev;
    }
    public Deque()                           // construct an empty deque
    {
        size = 0;
        first = null;
        last = null;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return size == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    public void addFirst(Item item)          // insert the item at the front
    {
        if(item == null)
        {
            throw new NullPointerException("NullPointerException");
        }
        else
        {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            if(!(first.next == null))first.next.prev = first;
            if(last == null)last = first;
            size++;
        }
    }
    public void addLast(Item item)           // insert the item at the end
    {
        if(item == null)
        {
            throw new NullPointerException("NullPointerException");
        }
        else
        {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldlast;
            if(!(last.prev == null))last.prev.next = last;
            if(first == null)first = last;
            size++;
        }
    }
    public Item removeFirst()                // delete and return the item at the front
    {
        if(size == 0)
        {
            throw new java.util.NoSuchElementException("java.util.NoSuchElementException");
        }
        else
        {
            if(size == 1)
            {
                Item item = first.item;
                first = null;
                last = null;
                size--;
                return item;
            }
            else
            {
                Item item = first.item;
                first = first.next;
                if(!(first == null))first.prev = null;
                size--;
                return item;
            }
        }
    }
    public Item removeLast()                 // delete and return the item at the end
    {
        if(size == 0)
        {
            throw new java.util.NoSuchElementException("java.util.NoSuchElementException");
        }
        else
        {
            if(size == 1)
            {
                //StdOut.println(first);
                Item item = last.item;
                first = null;
                last = null;
                size--;
                return item;
            }
            else
            {
                Item item = last.item;
                last = last.prev;
                if(!(last == null))last.next = null;
                size--;
                return item;
            }
        }
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException("UnsupportedOperationException");
        }
        public Item next()
        {
            if(hasNext())
            {
                Item item = current.item;
                current = current.next;
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
        Deque dek = new Deque();

        dek.addFirst(2);
        dek.addLast(3);
        dek.addLast(4);
        dek.addFirst(1);

        StdOut.println(dek.removeFirst());
        StdOut.println(dek.removeFirst());
        StdOut.println(dek.removeLast());
        StdOut.println(dek.removeLast());
    }
}
