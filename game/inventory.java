public class inventory
{
    private ResAr<Food> food;
    private ResAr<Weapon> weapons;
    private ResAr<Armor> armor;
    private int N, sF, sW, sA;
    public inventory(int str)
    {
        food = new ResAr<Food>();
        weapons = new ResAr<Weapon>();
        armor = new ResAr<Armor>();
        N = str;
        sF = 0;
        sW = 0;
        sA = 0;
    }
    
    public int add_food(Food fod)
    {
        if(sF >= N)return 1;
        food.enqueue(fod);
        sF++;
        return 0;
    }
    public int add_weap(Weapon fod)
    {
        if(sW >= N)return 1;
        weapons.enqueue(fod);
        sW++;
        return 0;
    }
    public int add_armor(Armor fod)
    {
        if(sA >= N)return 1;
        armor.enqueue(fod);
        sA++;
        return 0;
    }
    
    public int del_food(int n)
    {
        if(sF <= 0)return 1;
        food.get(n);
        sF--;
        return 0;
    }
    public int del_weap(Weapon fod)
    {
        if(sW >= N)return 1;
        weapons.enqueue(fod);
        sW++;
        return 0;
    }
    public int del_armor(Armor fod)
    {
        if(sA >= N)return 1;
        armor.enqueue(fod);
        sA++;
        return 0;
    }
    
    public Iterable<Food> list_food()
    {
        return food;
    }
    public Iterable<Weapon> list_weap()
    {
        return weapons;
    }
    public Iterable<Armor> list_arm()
    {
        return armor;
    }
}