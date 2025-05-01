import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.*;

public class Abilities {

    static String[] DoesOne = new String[16];
    static String[] DoesTwo = new String[16];
    static String[] DoesThree = new String[16];
    static int[][] Speed = new int[16][3];
    static int Extra = 0;
    static String Everything = "";
    static boolean[] Hold = new boolean[16];

    Abilities(int x) {

        switch(x) {
            case 0 : {
                DoesOne[x] = "Attack an Enemy, and stop them from attacking";
                Speed[x][0] = 1;
                DoesTwo[x] = "Deal 10 Damage to an Enemy. If they die, attack again, dealing 3 less damage";
                Speed[x][1] = 6;
                DoesThree[x] = "Deal 3 Damage, if they heal, they instead take damage";
                Speed[x][2] = 2;
                break;
            }
            case 1: {
                DoesOne[x] = "Deal 4 Damage, and all Allies target that enemy";
                Speed[x][0] = 1;
                DoesTwo[x] = "All allies gain + 3 attack, and all enemies gain +1 attack";
                Speed[x][1] = 5;
                DoesThree[x] = "All allies heal for the amount of damamge that they would do x1.5";
                Speed[x][2] = 5;
                break;
            }
            case 2 : {
                DoesOne[x] = "All enemies deal 20% less damage, and heal 3HP";
                Speed[x][0] = 3;
                DoesTwo[x] = "Attack an enemy, and they attack 3 turns slower";
                Speed[x][1] = 1;
                DoesThree[x] = "Deal 5 damage to an enemy, all enemies target you";
                Speed[x][2] = 4;
                break;
            }
            case 3: {
                DoesOne[x] = "Attack and Enemy. All allies heal 60% more of the damage you delt divided by the # of allies alive";
                Speed[x][0] = 6;
                DoesTwo[x] = "Heal Everyone by 3, and for each enemy you heal, heal allies +1 HP";
                Speed[x][1] = 7;
                DoesThree[x] = "Deal 4 damage, and that enemy has a speed of 2";
                Speed[x][2] = 1;
                break;
            }
            case 4 : {
                DoesOne[x] = "Attack an enemy, if they die, heal 6 HP. Otherwise, gain +2 damage";
                Speed[x][0] = 5;
                DoesTwo[x] = "Attack an Enemy. If hurt, attack all enemies";
                Speed[x][1] = 8;
                DoesThree[x] = "Attack all enemies that have a damage buff. Gain +3 damage and take 4 damage for each non buffed enemy";
                Speed[x][2] = 9;
                break;
            }
            case 5: {
                DoesOne[x] = "If hurt, attack an ally";
                Speed[x][0] = 5;
                DoesTwo[x] = "If this is the fastest card, deal x2 damage. Else, deal 35% less damage";
                Speed[x][1] = 4;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = 1;
                break;
            }
            case 6 : {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 7: {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 8 : {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 9: {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 10 : {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 11: {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 12 : {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 13: {
                DoesOne[x] = "DoesOne " + x;
                Speed[x][0] = x;
                DoesTwo[x] = "DoesTwo " + x;
                Speed[x][1] = x;
                DoesThree[x] = "DoesThree " + x;
                Speed[x][2] = x;
                break;
            }
            case 14 : {
                DoesOne[x] = "Attacks, and heals base on the damage delt";
                Speed[x][0] = 5;
                DoesTwo[x] = "Attacks, hitting all enemies";
                Speed[x][1] = 8;
                DoesThree[x] = "Gain +5 HP and +1 Attack, gain an additional +2 HP, +1 Attack for each Blue Troop on the field";
                Speed[x][2] = 10;
                break;
            }
            case 15 : {
                DoesOne[x] = "Attack 3 different Enemies. If their is only 1 Enemy to attack, attack that Enemy twice";
                Speed[x][0] = 6;
                DoesTwo[x] = "All Enemies lose 3 attack, heals 3 HP for each Enemy that loses attack";
                Speed[x][1] = 5;
                DoesThree[x] = "All Enemies with less then 4 attack are attacked, and he gains +5 Attack";
                Speed[x][2] = 9;
            }
        }
    }

    public static String getAbility(int x, int y, int z) {
        if (y == 0) {
            return DoesOne[x];
        }
        if (y == 1) {
            return DoesTwo[x];
        }
        if (y == 2) {
            return DoesThree[x];
        }
        return "DEAD";
    }

    public static int Speed(int x, int y) {
        if (y == 8) {
            return -2;
        } else if (y > 3) {
            return -1;
        } else {
            return Speed[x][y];
        }
    }

    public static ArrayList<Integer> Attack(ArrayList<int[]> Player, ArrayList<Integer> HP, int[] Damage, int Place, int TargetHP, ArrayList<Integer> ExtraDamage, int EnNum, int[] UnhurtHP, int AllyCount, int EnemyCount, ArrayList<Integer> UseDamage) {// Speed, Ally #, Attack #
        int SelfHP = Player.get(Place)[4];
        //System.out.println("Attacking");
        switch (Player.get(Place)[1]) {
            case 0: {
                if (Player.get(Place)[2] == 0) {
                    HP.set(TargetHP, HP.get(TargetHP) - (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]));
                    Extra = 2;
                    Everything += Player.get(EnNum)[1] + " cannot attack, but they can still do damage\n";
                }
                if (Player.get(Place)[2] == 1) {
                    int y = 0;
                    boolean Repeat = true;
                    while (Repeat) {
                        Repeat = false;
                        //System.out.println();
                        HP.set(TargetHP, HP.get(TargetHP) - ((y - 3) * -3) + 1);
                        if (HP.get(TargetHP) <= 0) {
                            Repeat = true;
                            y++;
                            Everything += Player.get(EnNum)[1] + " is killed\n";
                            int Tar = 0;

                            for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                                if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                                    Tar = x;
                                    break;
                                }
                            }
                            for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                                if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                                    if (HP.get(Player.get(x)[4]) < HP.get(Player.get(Tar)[4])) {
                                        Tar = x;
                                    }
                                }
                            }
                            for (int z = 0; z < (AllyCount + EnemyCount); z++) {
                                if (HP.get(Player.get(Tar)[4]) == HP.get(z) && Damage[Player.get(Tar)[4]] == Damage[z]) {
                                    TargetHP = z;
                                    EnNum = Tar;
                                    //break;
                                }
                            }

                        } else {
                            Everything += Player.get(EnNum)[1] + " has taken " + (((y - 3) * -3) + 1) + " damage\n";
                        }
                    }
                }
                if (Player.get(Place)[2] == 2) {
                    HP.set(TargetHP, HP.get(TargetHP) - 3);
                    Extra = 3;
                }
                break;
            }

            case 1: {
                if (Player.get(Place)[2] == 0) {
                    HP.set(TargetHP, HP.get(TargetHP) - 4);
                    Extra = 4;
                    Everything += "Unit 2 has delt 4 Damage to " + Player.get(EnNum)[1] + "\n";
                }
                if (Player.get(Place)[2] == 1) {
                    Extra = 1;
                }
                if (Player.get(Place)[2] == 2) {
                    int Tar = 0;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] == Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            Tar = x;
                            for (int z = 0; z < (AllyCount + EnemyCount); z++) {
                                if (HP.get(Player.get(Tar)[4]) == HP.get(z) && Damage[Player.get(Tar)[4]] == Damage[z]) {
                                    TargetHP = z;
                                    EnNum = Tar;
                                    //break;
                                }
                            }
                            //System.out.print(HP[TargetHP]);
                            HP.set(TargetHP, HP.get(TargetHP) + (int) Math.round((ExtraDamage.get(Player.get(EnNum)[4]) + Damage[Player.get(EnNum)[1]]) * 1.5));
                            Everything += Player.get(EnNum)[1] + " has healed " + ((int) Math.round((ExtraDamage.get(Player.get(EnNum)[4]) + Damage[Player.get(EnNum)[1]]) * 1.5)) + " HP" + "\n";
                        }
                    }

                }
                break;
            }

            case 2: {
                if (Player.get(Place)[2] == 0) {
                    Extra = 1;
                    HP.set(SelfHP, HP.get(SelfHP) - 3);
                    Everything += "All enemies deal 20% less damage, and heal 3 HP\n";
                }
                if (Player.get(Place)[2] == 1) {
                    HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                    Extra = 5;
                    Everything += Player.get(EnNum)[1] + " has taken " + (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)) + " damage, and has a speed of " + (Player.get(EnNum)[0] + 2) + "\n";
                }
                if (Player.get(Place)[2] == 2) {
                    HP.set(TargetHP, HP.get(TargetHP) - 5);
                    Extra = 6;
                    Everything += "You delt 5 damage to " + Player.get(EnNum)[1] + ", and all enemies target you\n";
                }
                break;
            }

            case 3: {
                if (Player.get(Place)[2] == 0) {
                    HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                    int Divide = 0;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] == Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            Divide++;
                        }
                    }
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] == Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) + (int) Math.round(((Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)) * 1.6) / Divide));
                        }
                    }
                    Everything += "You delt " + (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)) + " damage, and all allies heal " + (int) Math.round(((Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)) * 1.6) / Divide) + " HP\n";
                }
                if (Player.get(Place)[2] == 1) {
                    int EnHealed = 0;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) + 3);
                            EnHealed++;
                        }
                    }
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] == Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) + (3 + EnHealed));
                        }
                    }
                    Everything += "All enemies healed " + 3 + " HP, and all allies healed " + (3 + EnHealed) + " HP\n";
                }
                if (Player.get(Place)[2] == 2) {
                    HP.set(TargetHP, HP.get(TargetHP) - 4);
                    Extra = 7;
                }
                break;
            }

            case 4: {
                if (Player.get(Place)[2] == 0) {
                    HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                    if (HP.get(TargetHP) < 1) {
                        HP.set(SelfHP, HP.get(SelfHP) + 6);
                    } else {
                        Extra = 1;
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    if (UnhurtHP[Place] < HP.get(Player.get(Place)[4])) {
                        for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                            if (Player.get(x)[3] != Player.get(Place)[3]) {
                                HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) - (int) Math.round(ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]));
                            }
                        }
                    } else {
                        HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                    }
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            if (ExtraDamage.get(Player.get(x)[4]) != 0) {
                                HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) - (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]));
                            } else {
                                HP.set(SelfHP, HP.get(SelfHP) - 4);
                            }
                        }
                    }
                    Extra = 1;
                }
                break;
            }

            case 5: {
                if (Player.get(Place)[2] == 0) {
                    if (UnhurtHP[Place] < HP.get(Player.get(Place)[4])) {
                        HP.set(TargetHP, HP.get(TargetHP) - (int) Math.round(ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]));
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    boolean Fastest = true;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[0] > Player.get(Place)[0] && Player.get(x)[0] != -2) {
                            Fastest = false;
                            break;
                        }
                    }
                    if (Fastest) {
                        HP.set(TargetHP, HP.get(TargetHP) - (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) * 2);
                    } else {
                        HP.set(TargetHP, HP.get(TargetHP) - (int) Math.round((ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) * .65));
                    }
                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }

            case 14: {
                if (Player.get(Place)[2] == 0) {
                    //System.out.println(ExtraDamage[SelfHP]);
                    HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP))); //Cards.baseDamage(Player.get(Place)[1]);
                    HP.set(SelfHP, HP.get(SelfHP) + (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]])); //Cards.baseDamage(Player.get(Place)[1]);
                    Everything += "Unit 14 healed " + (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) + " HP after dealing " + (ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) + " damage to " + Player.get(EnNum)[1] + "\n";
                }
                if (Player.get(Place)[2] == 1) {
                    StringBuilder nameList = new StringBuilder();
                    int count = 0;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        //System.out.println(Player.get(x)[1] + " is on team " + Player.get(x)[3] + ". They have " + HP[Player.get(x)[4]] + " HP");
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            //System.out.println(ExtraDamage[SelfHP]);
                            HP.set(Player.get(x)[4], HP.get(Player.get(x)[4]) - (int) (Math.round((ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) / 2.0)));
                            if (count <= 1) {
                                nameList.append(Player.get(x)[1]).append(", ");
                            } else {
                                nameList.append("and ").append(Player.get(x)[1]);
                            }
                            count++;
                        }
                    }
                    Everything += "Unit 14 has delt " + (int) Math.round((ExtraDamage.get(SelfHP) + Damage[Player.get(Place)[1]]) / 2.0) + " damage to " + nameList + "\n";
                }
                if (Player.get(Place)[2] == 2) {
                    int PreHP = 3; //(ExtraDamage[SelfHP] + Damage[Player.get(Place)[1]])
                    int FakeAttack = 1;
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Cards.Color(Player.get(x)[1]).equals("Blue")) {
                            PreHP += 2;
                            FakeAttack += 1;
                        }
                    }
                    Everything += "Unit 14 has healed " + PreHP + " and gained +" + FakeAttack + " attack\n";
                    HP.set(SelfHP, HP.get(SelfHP) + PreHP);
                    Extra = 1;
                }
                break;
            }
            case 15: {
                if (Player.get(Place)[2] == 0) {
                    int Max = Math.max(1, Math.min(3, AllyCount));
                    for (int x = 0; x < Max; x++) {
                        if (Max == 1) {
                            HP.set(TargetHP, HP.get(TargetHP) - (2 * (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)))); //Cards.baseDamage(Player.get(Place)[1]);
                        } else {
                            HP.set(TargetHP, HP.get(TargetHP) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                            int Target = -1;
                            for (int y = 0; y < (AllyCount + EnemyCount); y++) {
                                if (Player.get(y)[3] != Player.get(Place)[3] && HP.get(Player.get(y)[4]) > 0) {
                                    Target = y;
                                    break;
                                }
                            }
                            for (int y = 0; y < (AllyCount + EnemyCount); y++) {
                                if (Player.get(y)[3] != Player.get(Place)[3]) {
                                    if (HP.get(Player.get(y)[4]) > HP.get(Player.get(Target)[4]) && HP.get(Player.get(y)[4]) > 0) {
                                        Target = y;
                                    }
                                }
                            }
                            for (int y = 0; y < (AllyCount + EnemyCount); y++) {
                                if (Target >= 0) {
                                    //System.out.println("HP of target: " + useHP[Sorted.get(Target)[4]] + " VS " + useHP[y] + "\nAllies: " + NormalDamage[Sorted.get(Target)[4]] + " VS " + NormalDamage[y] + "\n");
                                    if (HP.get(Player.get(Target)[4]) == HP.get(y) && Objects.equals(UseDamage.get(Player.get(Target)[4]), UseDamage.get(y))) {
                                        TargetHP = y;
                                        EnNum = Target;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            HP.set(SelfHP, HP.get(SelfHP) + 3);
                        }
                    }
                    Extra = 1;
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && Damage[x] < 4) {
                            HP.set(x, HP.get(x) - (Damage[Player.get(Place)[1]] + ExtraDamage.get(SelfHP)));
                        }
                    }
                    Extra = 1;
                }
                break;
            }
        }

        System.out.println("Attacker: " + Player.get(Place)[1]);
        System.out.println("Damage of Attacker: " + Damage[Player.get(Place)[1]]);
        System.out.println("HP of Attacker: " + HP.get(SelfHP));

        return HP;
    }

    public static int setTarget(ArrayList<int[]> Player, ArrayList<Integer> HP, int[] Damage, ArrayList<Integer> ExtraDamage, int Place, int AllyCount, int EnemyCount) {// Speed, Ally #, Attack #
        //System.out.println(Player.get(Place)[1]+ "'s Attack is " + Player.get(Place)[2]);
        /*
        && HP.get(Player.get(x)[4]) > 0   //Must
        Damage[Player.get(x)[4]] > Damage[Player.get(target)[4]]   //   Damage Higher
        Damage[Player.get(x)[4]] < Damage[Player.get(target)[4]]   //   Damage Lower
        HP.get(Player.get(x)[4]) > HP.get(Player.get(target)[4])   //   HP Higher
        HP.get(Player.get(x)[4]) < HP.get(Player.get(target)[4])   //   HP Lower
        Player.get(x)[0] > Player.get(target)[0]   //   Slower
        Player.get(x)[0] < Player.get(target)[0]   //   Fastest
        */
        int target = -2;

        for (int x = 0; x < (AllyCount + EnemyCount); x++) {
            if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                target = x;
                break;
            }
        }

        switch (Player.get(Place)[1]) {
            case 0 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            if (Damage[Player.get(x)[4]] > Damage[Player.get(target)[4]] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                    //System.out.println("The Target should be " + (Player.get(target)[1]));
                    //return target;
                }
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(Player.get(x)[4]) > 0) {
                            if (HP.get(Player.get(x)[4]) < HP.get(Player.get(target)[4]) && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                    //System.out.println("The Target should be " + (Player.get(target)[1]));
                    return target;
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (HP.get(Player.get(x)[4]) > HP.get(Player.get(target)[4]) && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                    //System.out.println("The Target should be " + (Player.get(target)[1]));
                    //return target;
                }
                break;
            }
            case 1 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Damage[Player.get(x)[4]] > Damage[Player.get(target)[4]] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                    //System.out.println("The Target should be " + (Player.get(target)[1]));
                    //return target;
                }
                if (Player.get(Place)[2] == 1) {
                    target = 0;
                }
                if (Player.get(Place)[2] == 2) {
                    target = 0;
                }
                break;
            }
            case 2 : {
                if (Player.get(Place)[2] == 0) {
                    target = 0;
                }
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Player.get(x)[0] < Player.get(target)[0] && HP.get(Player.get(x)[4]) > 0 && Player.get(x)[0] > 1) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (HP.get(Player.get(x)[4]) > HP.get(Player.get(target)[4])) {
                                target = x;
                            }
                        }
                    }
                }
                break;
            }
            case 3 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Player.get(x)[0] < Player.get(target)[0] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    target = -1;
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Player.get(x)[0] > Player.get(target)[0] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                break;
            }
            case 4 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Player.get(x)[0] > Player.get(target)[0] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Damage[Player.get(x)[4]] < Damage[Player.get(target)[4]] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 2) {
                    target = -1;
                }
                break;
            }
            case 5 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (HP.get(Player.get(x)[4]) > HP.get(Player.get(target)[4]) && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3] && HP.get(x) > 0) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (Damage[Player.get(x)[4]] > Damage[Player.get(target)[4]] && HP.get(Player.get(x)[4]) > 0) {
                                target = x;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 6 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 7 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 8 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 9 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 10 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 11 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 12 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 13 : {
                if (Player.get(Place)[2] == 0) {

                }
                if (Player.get(Place)[2] == 1) {

                }
                if (Player.get(Place)[2] == 2) {

                }
                break;
            }
            case 14 : {
                if (Player.get(Place)[2] == 0) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            //System.out.println(Player.get(x)[3] + " VS " + Player.get(Place)[3]);
                            if (HP.get(Player.get(x)[4]) > HP.get(Player.get(target)[4])) {
                                target = x;
                            }
                        }
                    }
                    //System.out.println("The Target should be " + (Player.get(target)[1]));
                    //return target;
                }
                if (Player.get(Place)[2] == 1) {
                    target = 0;
                }
                if (Player.get(Place)[2] == 2) {
                    target = -1;
                }
                break;
            }
            case 15 : {
                if (Player.get(Place)[2] == 0) {
                    for (int y = 0; y < (AllyCount + EnemyCount); y++) {
                        if (Player.get(y)[3] != Player.get(Place)[3]) {
                            if (HP.get(Player.get(y)[4]) > HP.get(Player.get(target)[4]) && HP.get(Player.get(y)[4]) > 0) {
                                target = y;
                            }
                        }
                    }
                }
                if (Player.get(Place)[2] == 1) {
                    target = -1;
                }
                if (Player.get(Place)[2] == 2) {
                    target = -1;
                }
                break;
            }
        }

        //System.out.println(Player.get(Place)[1] + " = " + target);
        if (target != -2) {
            return target;
        } else {
            return Player.get(Player.size())[1];
        }
    }

    public static int getExtra() {
        int rExtra = Extra;
        Extra = 0;
        return rExtra;
    }

    public static ArrayList<Integer> DamageBuff(ArrayList<int[]> Player, ArrayList<Integer> HP, int[] BaseDamage, ArrayList<Integer> ExtraDamage, int Place, int AllyCount, int EnemyCount) {// Speed, Ally #, Attack #
        //System.out.println(Arrays.toString(ExtraDamage));
        switch (Player.get(Place)[1]) {

            case 1 : {
                for (int y = 0; y < Player.size(); y++) {
                    System.out.println(Player.get(Place)[3] + " VS " +  Player.get(y)[3]);
                    if (ExtraDamage.get(Player.get(y)[4]) == 0) {
                        Hold[Player.get(y)[4]] = true;
                    }

                    if (Player.get(Place)[3] == Player.get(y)[3]) {
                        ExtraDamage.set(Player.get(y)[4], ExtraDamage.get(Player.get(y)[4]) + 3);
                    } else {
                        ExtraDamage.set(Player.get(y)[4], ExtraDamage.get(Player.get(y)[4]) + 1);
                    }
                    //Hold[y] = true;
                }
                Everything += "All Allies gain +3 Damage, while all Enemies gain +1 Damage\n";
                break;
            }

            case 2 : {
                for (int y = 0; y < (AllyCount + EnemyCount); y++) {
                    if (Player.get(y)[3] != Player.get(Place)[3]) {
                        if (ExtraDamage.get(Player.get(y)[4]) == 0) {
                            Hold[Player.get(y)[4]] = true;
                        }
                        ExtraDamage.set(Player.get(y)[4], ExtraDamage.get(Player.get(y)[4]) - (int) Math.round(Math.max((BaseDamage[Player.get(y)[1]] + ExtraDamage.get(Player.get(y)[4])) * .2, BaseDamage[Player.get(y)[1]] * .15)));
                        System.out.println(Player.get(y)[1] + "'s attack went from " + BaseDamage[Player.get(y)[1]] + " to " + (BaseDamage[Player.get(y)[1]] + ExtraDamage.get(Player.get(y)[4])));
                    }
                }
            }

            case 4: {
                if (ExtraDamage.get(Player.get(Place)[4]) == 0) {
                    Hold[Player.get(Place)[4]] = true;
                }

                if (Player.get(Place)[2] == 0) {
                    ExtraDamage.set(Player.get(Place)[4], ExtraDamage.get(Player.get(Place)[4]) + 2);
                }
                if (Player.get(Place)[2] == 2) {
                    for (int x = 0; x < (EnemyCount + AllyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            if (ExtraDamage.get(Player.get(x)[4]) == 0) {
                                ExtraDamage.set(Player.get(Place)[4], ExtraDamage.get(Player.get(Place)[4]) + 3);
                            }
                        }
                    }
                }

                break;
            }

            case 14: {
                if (ExtraDamage.get(Player.get(Place)[4]) == 0) {
                    Hold[Player.get(Place)[4]] = true;
                }
                ExtraDamage.set(Player.get(Place)[4], ExtraDamage.get(Player.get(Place)[4]) + 2);
                break;
            }
            case 15 : {
                if (Player.get(Place)[2] == 1) {
                    for (int x = 0; x < (AllyCount + EnemyCount); x++) {
                        if (Player.get(x)[3] != Player.get(Place)[3]) {
                            if (ExtraDamage.get(Player.get(x)[4]) == 0) {
                                Hold[Player.get(x)[4]] = true;
                            }
                            ExtraDamage.set(x, ExtraDamage.get(x) - 3);
                        }
                    }
                }
                if (Player.get(Place)[2] == 2) {
                    if (ExtraDamage.get(Player.get(Place)[4]) == 0) {
                        Hold[Player.get(Place)[4]] = true;
                    }
                    ExtraDamage.set(Player.get(Place)[4], ExtraDamage.get(Player.get(Place)[4]) + 5);
                }
                break;
            }
        }


        return ExtraDamage;
    }

    public static ArrayList<Integer> LowerDamage(ArrayList<Integer> Damage) {
        for (int x = 0; x < Damage.size(); x++) {
            if (!Hold[x]) {
                if ((int) Math.round(Math.round(Damage.get(x) * .7) * .7) == (int) Math.round(Damage.get(x) * .7)) {
                    if (Damage.get(x) > 0) {
                        Damage.set(x, Damage.get(x) - 1);
                    } else if (Damage.get(x) < 0) {
                        Damage.set(x, Damage.get(x) + 1);
                    }
                } else {
                    Damage.set(x, (int) Math.round(Damage.get(x) * .7));
                }
            }
        }
        //System.out.println("\n~~~ " + Arrays.toString(Damage) + " ~~~");
        for (int x = 0; x < 6; x++) {
            Hold[x] = false;
        }
        return Damage;

    }

    public static String returnWhat() {
        String All = Everything;
        Everything = "";
        System.out.println("\n" + All);
        return All;
    }
}
