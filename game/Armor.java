public class Armor
{
    private int AC, value;
    public Armor(int ac, int val)
    {
        AC = ac;
        value = val;
    }
    public int get_val()
    {
        return value;
    }
    public int count_def()
    {
        return AC;
    }
}