import java.lang.Math;
public class Pers
{
    private int level, strength, constitution, dexterity, intelligence, wisdom, charisma, AC, FORTITUDE, REACTION, WILL, HEALTH, Speed, XP;
    private int mod_strength, mod_constitution, mod_dexterity, mod_intelligence, mod_wisdom, mod_charisma;
    private String Name;
    public Pers(int str, int cons, int dex, int intel, int wis, int chari, int speed)
    {
        level = 1;
        XP = 0;
        strength = str;
        constitution = cons;
        dexterity = dex;
        intelligence = intel;
        wisdom = wis;
        charisma = chari;
        
        check_stats();
        check_mod();
        check_def(); 
    }
    public void gain_xp(int xp)
    {
        XP+=xp;
        if(XP >= 1000 * (level+1))level_up();
    }
    private void level_up()
    {
        level++;
        if(level % 2 == 0)
        {
            strength++;
            constitution++;
            dexterity++;
            intelligence++;
            wisdom++;
            charisma++;
            
            check_mod();
            check_stats();
            check_def();
        }
    }
    private void check_def()
    {
        FORTITUDE = 10 + level/2+Math.max(mod_strength,mod_constitution);
        REACTION = 10 + level/2+Math.max(mod_dexterity,mod_wisdom);
        WILL = 10 + level/2+Math.max(mod_intelligence,mod_charisma);
    }
    private void check_stats()
    {
        Speed = 6 + dexterity/2 - 6;
        HEALTH = 20 + constitution/2 - 6;
    }
    private void check_mod()
    {
        mod_strength = strength/2-5;
        mod_constitution = constitution/2-5;
        mod_dexterity = dexterity/2-5;
        mod_intelligence = intelligence/2-5;
        mod_wisdom = wisdom/2-5;
        mod_charisma = charisma/2-5;
    }
}