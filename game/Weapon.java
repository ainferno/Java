public class Weapon
{
    private int damage, value;
    public Weapon(int dam, int val)
    {
        damage = dam;
        value = val;
    }
    public int get_val()
    {
        return value;
    }
    public int attack()
    {
        return StdRandom.uniform(damage)+1;
    }
}