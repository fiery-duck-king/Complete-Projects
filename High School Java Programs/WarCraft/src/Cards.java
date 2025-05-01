public class Cards {
    static int[] HP = new int[16];
    static int[] Damage = new int[16];
    static Abilities[] Ability = new Abilities[16];
    static String[] Name = new String[16];
    static String[] Class = new String[16];
    int c;

    public Cards(int x) {
        c = x;
        Ability[x] = new Abilities(x);
        switch(x) {
            case 0 : {
                HP[x] = 20;
                Damage[x] = 7;
                Name[x] = "Snipper (0)";
                break;
            }
            case 1: {
                HP[x] = 45;
                Damage[x] = 3;
                Name[x] = "Cook (1)";
                break;
            }
            case 2 : {
                HP[x] = 50;
                Damage[x] = 3;
                Name[x] = "Tank (2)";
                break;
            }
            case 3: {
                HP[x] = 25;
                Damage[x] = 2;
                Name[x] = "Healer (3)";
                break;
            }
            case 4 : {
                HP[x] = 31;
                Damage[x] = 6;
                Name[x] = "Warrior (4)";
                break;
            }
            case 5: {
                HP[x] = 65;
                Damage[x] = 9;
                Name[x] = "Murderer (5)";
                break;
            }
            case 6 : {
                HP[x] = 60;
                Damage[x] = 2;
                Name[x] = "6";
                break;
            }
            case 7: {
                HP[x] = 11;
                Damage[x] = 10;
                Name[x] = "7";
                break;
            }
            case 8 : {
                HP[x] = 21;
                Damage[x] = 4;
                Name[x] = "8";
                break;
            }
            case 9: {
                HP[x] = 100;
                Damage[x] = 5;
                Name[x] = "9";
                break;
            }
            case 10 : {
                HP[x] = 50;
                Damage[x] = 4;
                Name[x] = "10";
                break;
            }
            case 11: {
                HP[x] = 30;
                Damage[x] = 2;
                Name[x] = "11";
                break;
            }
            case 12 : {
                HP[x] = 56;
                Damage[x] = 4;
                Name[x] = "12";
                break;
            }
            case 13: {
                HP[x] = 31;
                Damage[x] = 5;
                Name[x] = "13";
                break;
            }
            case 14 : {
                HP[x] = 45;
                Damage[x] = 5;
                Name[x] = "Skippy";
                break;
            }
            case 15 : {
                HP[x] = 345;
                Damage[x] = 12;
                Name[x] = "Boss";
                break;
            }
        }
        if (x % 3 == 0) {
            Class[x] = "Red";
        } else if (x % 3 == 1) {
            Class[x] = "Green";
        } else {
            Class[x] = "Blue";
        }
        if (x == 15) {
            Class[15] = "Gray";
        }
    }

    public static int MaxHP(int x) {return HP[x];}
    public static int baseDamage(int x) {return Damage[x];}
    public static int[] AllDamage() {return Damage;}
    public static String Name(int x) {/*if (x == 15) {return "Boss";}*/ return Name[x];}
    public static String Color(int x) {
        if (x == 15) {
            return "Gray";
        }
        return Class[x];
    }

    public static String returnAbility(int x, int y, int z) {
        return (Abilities.getAbility(x, y, z));
    }
    public String Numbername() {return "" + c;}

}
