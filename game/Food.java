public class Food
{
    private int number, value;
    public Food(int num,int val)
    {
        number = num;
        value = val;
    }
    public int get_val()
    {
        return value;
    }
    public int eat()
    {
        return number;
    }
}