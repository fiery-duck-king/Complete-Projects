import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class WarCraft extends JFrame{

    static Cards[] card = new Cards[16];
    JButton[] Allies = new JButton[5];
    JButton[] Enemies = new JButton[5];
    int AllyNumber = (int) (Math.random() * 5) + 1;
    int EnemyNumber = (int) (Math.random() * 5) + 1;
    int ButtonAlly = AllyNumber;
    int ButtonEnemy = EnemyNumber;
    //int[] RandoAlly = new int[3];
    //int[] RandoEnemy = new int[3];
    ArrayList<Integer> RandoPerson = new ArrayList<>();
    JLabel BackRound = new JLabel();
    JLabel Battle = new JLabel();
    JLabel[] Stats = new JLabel[6];
    ArrayList<Integer> useHP = new ArrayList<>();
    //ArrayList<Integer> ExtraHP = new ArrayList<>(); //Allows Health to go over Max
    ArrayList<Integer> NormalDamage = new ArrayList<>(); //Because HP worked
    ArrayList<Integer> ExtraDamage = new ArrayList<>(); //Base Damage Never Changes
    JButton[] Style = new JButton[3];
    ArrayList<Integer> AttackLocater = new ArrayList<>();
    int inUse = -1;
    HashMap<String, Integer> ButtonLink = new HashMap<>();
    JButton Fight = new JButton();
    JLabel MaxHP = new JLabel();
    JLabel AllSee = new JLabel();
    boolean Finish = true;
    JButton[] Map = new JButton[8];
    int Level = 0;
    int Money = 0;
    JButton[] AllAllies = new JButton[15];
    JButton[] WhichAllies = new JButton[5];
    JButton Shop = new JButton();
    boolean ready = false;
    int AllyChoosen = -1;
    boolean UseHealth = false;
    boolean UseDamage = false;
    boolean UseShield = false;
    boolean YouWon = false;
    JButton[] Items = new JButton[4];
    boolean Location = true; // true = Map // false = Shop
    int ItemHealth = 0;
    int ItemDamage = 0;
    int ItemShield = 0;
    int ItemRevive = 0;
    int[] Rigged = new int[5];
    int UsingHealth = 0;
    int UsingDamage = 0;
    int UsingShield = 0;
    ArrayList<Integer> HPMap = new ArrayList<>();

    WarCraft() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                Rigged[y] = -1;
                int use = (x * 5) + y;
                AllAllies[use] = new JButton();
                AllAllies[use].setBounds(50 * y + 500, 50 * x + 20, 50, 50);
                AllAllies[use].setText("" + (use));
                AllAllies[use].setVisible(false);
                AllAllies[use].addActionListener(e -> AllyChoosen = use);
                if (Cards.Color(use).equals("Red")) {
                    AllAllies[use].setBackground(Color.red);
                }
                if (Cards.Color(use).equals("Green")) {
                    AllAllies[use].setBackground(Color.GREEN);
                }
                if (Cards.Color(use).equals("Blue")) {
                    AllAllies[use].setBackground(Color.BLUE);
                }
            }
        }

        for (int x = 0; x < 8; x++) {
            Map[x] = new JButton();
            if (x < 5) {
                Enemies[x] = new JButton();
                Allies[x] = new JButton();
                WhichAllies[x] = new JButton();
                WhichAllies[x].setEnabled(false);
                WhichAllies[0].setEnabled(true);
            }
        }

        for (int x = 0; x < Stats.length; x++) {
            Stats[x] = new JLabel();
            if (x < 3) {
                Style[x] = new JButton();
            }
        }

        for (int x = 0; x < 4; x++) {
            if (x < 3) {
                Style[x].setBounds((100 * x) + (x * 15) + 50, 575, 80, 80);
                Style[x].setBackground(Color.orange);
                Style[x].setText("Ability " + (x + 1));
            }
            Items[x] = new JButton();
            Items[x].setBounds((x * 150) + 200, 150, 150, 150);
            Items[x].setVisible(false);
        }

        for (int x = 0; x < 5; x++) {
            WhichAllies[x].setBounds(50 * x + 500,200, 50, 50);
            int Tx = x;
            WhichAllies[x].addActionListener(e -> {
                if (AllyChoosen > -1) {
                    int SettingUp = AllyChoosen;
                    if (!(UseHealth) && !(UseShield)) {
                        useHP.add(Cards.MaxHP(SettingUp));
                    }
                    if ((UseHealth) && !(UseShield)) {
                        useHP.add((int) (Cards.MaxHP(SettingUp) * 1.3));
                        ItemHealth --;
                        UsingHealth ++;
                    }
                    if (!(UseHealth) && (UseShield)) {
                        useHP.add(Cards.MaxHP(SettingUp) + 15);
                        ItemShield --;
                        UsingShield ++;
                    }
                    if ((UseHealth) && (UseShield)) {
                        useHP.add((int) (Cards.MaxHP(SettingUp) * 1.35) + 20);
                        ItemHealth --;
                        ItemShield --;
                        UsingHealth ++;
                        UsingShield ++;
                    }

                    if (!(UseDamage)) {
                        ExtraDamage.add(0);
                    } else {
                        ExtraDamage.add(5);
                        ItemDamage --;
                        UsingDamage ++;
                    }
                    ButtonLink.put(("" + SettingUp + 0), Tx);
                    NormalDamage.add(Cards.baseDamage(SettingUp));
                    Allies[Tx].setText(card[SettingUp].Numbername());
                    RandoPerson.add(SettingUp);
                    if (AllyNumber == 1) {
                        Allies[Tx].setBounds(100, 300, 200, 200);
                    }
                    if (AllyNumber == 2) {
                        Allies[Tx].setBounds(150 * Tx + 50, 350, 150, 150);
                    }
                    if (AllyNumber == 3) {
                        Allies[Tx].setBounds(100 * Tx + 50, 350, 100, 100);
                    }
                    if (AllyNumber == 4) {
                        Allies[Tx].setBounds(80 * Tx + 50, 350, 80, 80);
                    }
                    if (AllyNumber == 5) {
                        Allies[Tx].setBounds(60 * Tx + 60, 360, 60, 60);
                    }
                    if (Cards.Color(SettingUp).equals("Red")) {
                        Allies[Tx].setBackground(Color.red);
                    }
                    if (Cards.Color(SettingUp).equals("Green")) {
                        Allies[Tx].setBackground(Color.GREEN);
                    }
                    if (Cards.Color(SettingUp).equals("Blue")) {
                        Allies[Tx].setBackground(Color.BLUE);
                    }
                    if (Cards.Color(SettingUp).equals("Gray")) {
                        Allies[Tx].setBackground(Color.GRAY);
                    }
                    WhichAllies[Tx].setText("" + AllyChoosen);
                    for (int z = 0; z < 5; z++) {
                        WhichAllies[z].setEnabled(false);
                    }
                    try {
                        WhichAllies[Tx + 1].setEnabled(true);
                    } catch (IndexOutOfBoundsException ignored) {}
                    AllAllies[RandoPerson.get(Tx)].setEnabled(false);
                    AllyChoosen = -1;
                    UseShield = false;
                    UseHealth = false;
                    UseDamage = false;
                } else {
                    System.out.println("You failed at clicking");
                }
            });
        }

        Shop.setBounds(25,25,50,50);
        Shop.addActionListener(e -> {
            Location = !Location;
            if (Location) {
                for (int x = 0; x < 8; x++) {
                    if (x < 4) {
                        Items[x].setVisible(false);
                    }
                    Map[x].setVisible(true);
                    Shop.setText("");
                }
            } else {
                for (int x = 0; x < 8; x++) {
                    if (x < 4) {
                        Items[x].setVisible(true);
                        Items[x].setBounds((x * 150) + 100, 150, 150, 150);
                        Items[0].setText("Extra HP: " + ItemHealth);
                        Items[1].setText("Damage: " + ItemDamage);
                        Items[2].setText("Shield: " + ItemShield);
                        Items[3].setText("Revive: " + ItemRevive);
                    }
                    Map[x].setVisible(false);
                    Shop.setText("" + Money);
                }
            }
        });

        for (int x = 0; x < 4; x++) {
            int oofx = x;
            Items[x].setBackground(Color.lightGray);
            Items[0].setText("Extra HP: " + ItemHealth);
            Items[1].setText("Damage: " + ItemDamage);
            Items[2].setText("Shield: " + ItemShield);
            Items[3].setText("Revive: " + ItemRevive);
            Items[x].addActionListener(e -> {
                if (Location) {
                    if (oofx == 0 && ItemHealth > 0) {
                        UseHealth = !UseHealth;
                        if (UseHealth) {
                            Items[oofx].setBackground(Color.GRAY);
                        } else {
                            Items[oofx].setBackground(Color.lightGray);
                        }
                    }
                    if (oofx == 1 && ItemDamage > 0) {
                        UseDamage = !UseDamage;
                        if (UseDamage) {
                            Items[oofx].setBackground(Color.GRAY);
                        } else {
                            Items[oofx].setBackground(Color.lightGray);
                        }
                    }
                    if (oofx == 2 && ItemShield > 0) {
                        UseShield = !UseShield;
                        if (UseShield) {
                            Items[oofx].setBackground(Color.GRAY);
                        } else {
                            Items[oofx].setBackground(Color.lightGray);
                        }
                    }
                    if (oofx == 3 && ItemRevive > 0) {
                        String Count = "";
                        ArrayList<Integer> TheCount = new ArrayList<>();
                        for (int z = 0; z < 15; z++) {
                            if (!AllAllies[z].isEnabled()) {
                                Count += z;
                                TheCount.add(z);
                                AllAllies[z].setBackground(Color.lightGray);
                            }
                            AllAllies[z].setEnabled(!AllAllies[z].isEnabled());
                        }
                        if (TheCount.size() != 0) {
                            boolean DoItRight = true;
                            int What = -1;
                            while (DoItRight) {
                                What = Integer.parseInt(JOptionPane.showInputDialog("What Ally do you revive? : \n" + Count));
                                for (int z = 0; z < TheCount.size(); z++) {
                                    if (TheCount.get(z) == What) {
                                        DoItRight = false;
                                        break;
                                    }
                                }
                            }
                            for (int z = 0; z < 15; z++) {
                                AllAllies[z].setEnabled(!AllAllies[z].isEnabled());
                                if (Cards.Color(z).equals("Red")) {
                                    AllAllies[z].setBackground(Color.red);
                                }
                                if (Cards.Color(z).equals("Green")) {
                                    AllAllies[z].setBackground(Color.GREEN);
                                }
                                if (Cards.Color(z).equals("Blue")) {
                                    AllAllies[z].setBackground(Color.BLUE);
                                }
                            }
                            AllAllies[What].setEnabled(true);
                            ItemRevive --;
                        } else {
                            System.out.println("You Silly");
                            for (int z = 0; z < 15; z++) {
                                AllAllies[z].setEnabled(!AllAllies[z].isEnabled());
                                if (Cards.Color(z).equals("Red")) {
                                    AllAllies[z].setBackground(Color.red);
                                }
                                if (Cards.Color(z).equals("Green")) {
                                    AllAllies[z].setBackground(Color.GREEN);
                                }
                                if (Cards.Color(z).equals("Blue")) {
                                    AllAllies[z].setBackground(Color.BLUE);
                                }
                            }
                        }
                    }
                } else {
                    if (oofx == 0) {
                        if (Money >= 10) {
                            ItemHealth++;
                            Money -= 10;
                        }
                    }
                    if (oofx == 1) {
                        if (Money >= 10) {
                            ItemDamage++;
                            Money -= 10;
                        }
                    }
                    if (oofx == 2) {
                        if (Money >= 10) {
                            ItemShield++;
                            Money -= 10;
                        }
                    }
                    if (oofx == 3) {
                        if (Money >= 10) {
                            ItemRevive++;
                            Money -= 10;
                        }
                    }
                    Items[0].setText("Extra HP: " + ItemHealth);
                    Items[1].setText("Damage: " + ItemDamage);
                    Items[2].setText("Shield: " + ItemShield);
                    Items[3].setText("Revive: " + ItemRevive);
                }
                Shop.setText("" + Money);
            });
        }

        /*
                                if (y < 4) {
                            if (y < 3) {
                                Style[y].setVisible(true);
                            }
                            Items[y].setVisible(false);
                        }
         */

        for (int a = 0; a < 8; a++) {
            Map[0].setBounds(300, 625, 100, 100);
            Map[1].setBounds(200 + (int) Math.round((Math.random() * 50) - 25), 475, 100, 100);
            Map[2].setBounds(400 + (int) Math.round((Math.random() * 50) - 25), 475, 100, 100);
            Map[3].setBounds(200 + (int) Math.round((Math.random() * 50) - 25), 325, 100, 100);
            Map[4].setBounds(400 + (int) Math.round((Math.random() * 50) - 25), 325, 100, 100);
            Map[5].setBounds(200 + (int) Math.round((Math.random() * 50) - 25), 175, 100, 100);
            Map[6].setBounds(400 + (int) Math.round((Math.random() * 50) - 25), 175, 100, 100);
            Map[7].setBounds(300 + (int) Math.round((Math.random() * 50) - 25), 25, 100, 100);
            Map[a].setEnabled(false);
            Map[0].setText("X");
            Map[0].setEnabled(true);
            Map[a].setBackground(Color.BLACK);
            int aa = a;
            Map[a].addActionListener(ee -> {
                if (RandoPerson.size() == AllyNumber && AllyNumber > 0 && EnemyNumber > 0) {
                    ready = true;
                }
                if (ready) {
                    ready = false;
                    Level++;
                    for (int y = 0; y < 8; y++) {
                        Map[y].setVisible(false);
                        Shop.setVisible(false);
                        if (y < 5) {
                            WhichAllies[y].setVisible(false);
                        }
                        if (y < AllyNumber) {
                            Allies[y].setVisible(true);
                        }
                        if (y < EnemyNumber) {
                            Enemies[y].setVisible(true);
                        }
                        if (y < 4) {
                            if (y < 3) {
                                Style[y].setVisible(true);
                            }
                            Items[y].setVisible(false);
                        }
                        if (y < 6) {
                            Stats[y].setVisible(true);
                        }
                        Fight.setVisible(true);
                        AllSee.setVisible(true);
                        MaxHP.setVisible(true);
                        Battle.setVisible(true);
                        BackRound.setVisible(true);
                    }
                    for (int x = 0; x < 15; x++) {
                        AllAllies[x].setVisible(false);
                    }

                    AllSee.setBounds(1000, 100, 350, 550);
                    AllSee.setBackground(ColorUIResource.lightGray);
                    AllSee.setOpaque(true);

                    MaxHP.setBounds(470, 160, 80, 40);
                    MaxHP.setBackground(Color.red);
                    MaxHP.setOpaque(true);

                    Fight.setBounds(570, 10, 250, 80);
                    Fight.setBackground(Color.RED);
                    Fight.setText("FIGHT");

                    Stats[0].setBounds(470, 120, 80, 40);
                    Stats[0].setBackground(Color.red);
                    Stats[0].setOpaque(true);

                    Stats[1].setBounds(840, 120, 80, 80);
                    Stats[1].setBackground(Color.GREEN);
                    Stats[1].setOpaque(true);

                    Stats[2].setBounds(470, 220, 460, 100);
                    Stats[2].setBackground(Color.lightGray);
                    Stats[2].setOpaque(true);

                    Stats[3].setBounds(470, 330, 460, 100);
                    Stats[3].setBackground(Color.lightGray);
                    Stats[3].setOpaque(true);

                    Stats[4].setBounds(470, 440, 460, 100);
                    Stats[4].setBackground(Color.lightGray);
                    Stats[4].setOpaque(true);

                    Stats[5].setBounds(600, 120, 190, 80);
                    Stats[5].setBackground(Color.lightGray);
                    Stats[5].setOpaque(true);

                    Battle.setBounds(15, 50, 400, 500);
                    Battle.setVisible(true);
                    Battle.setBackground(Color.BLACK);
                    Battle.setOpaque(true);

                    BackRound.setBounds(450, 100, 500, 800);
                    BackRound.setVisible(true);
                    BackRound.setBackground(Color.white);
                    BackRound.setOpaque(true);

                    for (int x = 0; x < (AllyNumber + EnemyNumber); x++) {
                        if (x < AllyNumber) {
                            AttackLocater.add(-1);
                        } else {
                            AttackLocater.add((int) (Math.random() * 3) + 2);
                        }
                    }

                    ArrayList<Integer> NoRepeat = new ArrayList<>();
                    boolean fail = false;
                    int SettingUp = 0;
                    System.out.println(Arrays.toString(Rigged));
                    for (int x = AllyNumber; x < (AllyNumber + EnemyNumber); x++) {
                        if (Rigged[x - AllyNumber] == -1) {
                            SettingUp = (int) (Math.random() * 15);
                        } else {
                            SettingUp = Rigged[x - AllyNumber];
                            System.out.println("Get " + (x - AllyNumber));
                            System.out.println(SettingUp);
                        }
                        //System.out.println(RandoEnemy[x]);
                        for (Integer laInt : NoRepeat) {
                            if (laInt == SettingUp) {
                                fail = true;
                                x--;
                                break;
                            }
                        }

                        if (!fail) {
                            NoRepeat.add(SettingUp);
                            useHP.add(Cards.MaxHP(SettingUp));
                            ButtonLink.put(("" + SettingUp + 1), (x - AllyNumber));
                            ExtraDamage.add(0);
                            RandoPerson.add(SettingUp);
                            NormalDamage.add(Cards.baseDamage(SettingUp));
                            Enemies[x - AllyNumber].setText(card[SettingUp].Numbername());
                            if (EnemyNumber == 1) {
                                Enemies[x - AllyNumber].setBounds(100, 100, 200, 200);
                            }
                            if (EnemyNumber == 2) {
                                Enemies[x - AllyNumber].setBounds(150 * (x - AllyNumber) + 50, 150, 150, 150);
                            }
                            if (EnemyNumber == 3) {
                                Enemies[x - AllyNumber].setBounds(100 * (x - AllyNumber) + 60, 150, 100, 100);
                            }
                            if (EnemyNumber == 4) {
                                Enemies[x - AllyNumber].setBounds(80 * (x - AllyNumber) + 50, 150, 80, 80);
                            }
                            if (EnemyNumber == 5) {
                                Enemies[x - AllyNumber].setBounds(60 * (x - AllyNumber) + 60, 140, 60, 60);
                            }
                            if (Cards.Color(SettingUp).equals("Red")) {
                                Enemies[x - AllyNumber].setBackground(Color.red);
                            }
                            if (Cards.Color(SettingUp).equals("Green")) {
                                Enemies[x - AllyNumber].setBackground(Color.GREEN);
                            }
                            if (Cards.Color(SettingUp).equals("Blue")) {
                                Enemies[x - AllyNumber].setBackground(Color.BLUE);
                            }
                        }
                        fail = false;
                    }
                    NoRepeat.clear();

                    System.out.println(ButtonLink);

                    for (int x = 0; x < AllyNumber; x++) {
                        int oofx = x;
                        int CardNum = RandoPerson.get(x);
                        Allies[ButtonLink.get("" + CardNum + 0)].addActionListener(e -> {
                            //System.out.println(HPMap + ": " + oofx);
                            inUse = HPMap.get(oofx);
                            Stats[0].setText("" + (useHP.get(inUse)));
                            Stats[1].setText("" + (NormalDamage.get(inUse) + ExtraDamage.get(inUse)));
                            for (int hide = 2; hide < 5; hide++) {
                                Stats[hide].setVisible(true);
                            }
                            if (useHP.get(inUse) <= 0) {
                                Style[0].setEnabled(false);
                                Style[1].setEnabled(false);
                                Style[2].setEnabled(false);
                            } else {
                                Style[0].setEnabled(true);
                                Style[1].setEnabled(true);
                                Style[2].setEnabled(true);
                            }
                            Stats[2].setText(Cards.returnAbility(CardNum, 0, 0));
                            Stats[3].setText(Cards.returnAbility(CardNum, 1, 0));
                            Stats[4].setText(Cards.returnAbility(CardNum, 2, 0));
                            Stats[2].setBackground(Color.lightGray);
                            Stats[3].setBackground(Color.lightGray);
                            Stats[4].setBackground(Color.lightGray);
                            Stats[5].setText(Cards.Name(CardNum));
                            if (AttackLocater.get(inUse) == 2) {
                                Stats[2].setBackground(Color.gray);
                            }
                            if (AttackLocater.get(inUse) == 3) {
                                Stats[3].setBackground(Color.gray);
                            }
                            if (AttackLocater.get(inUse) == 4) {
                                Stats[4].setBackground(Color.gray);
                            }
                            MaxHP.setText("" + Cards.MaxHP(CardNum));
                        });
                    }
                    for (int x = 0; x < ButtonEnemy; x++) {
                        int oofx = x;
                        int EnNum = RandoPerson.get(x + AllyNumber);
                        Enemies[ButtonLink.get("" + EnNum + 1)].addActionListener(e -> {
                            //System.out.println(HPMap + ": " + (oofx + ButtonAlly));
                            inUse = HPMap.get(oofx + ButtonAlly);
                            Stats[0].setText("" + (useHP.get(inUse)));
                            Stats[1].setText("" + (NormalDamage.get(inUse) + ExtraDamage.get(inUse)));
                            Stats[2].setText("" + Abilities.getAbility(EnNum, (AttackLocater.get(inUse) - 2), 1));
                            for (int hide = 3; hide < 5; hide++) {
                                Stats[hide].setVisible(false);
                            }
                            Stats[5].setText(Cards.Name(RandoPerson.get(inUse)));
                            MaxHP.setText("" + Cards.MaxHP(EnNum));
                        });
                    }

                    Fight.addActionListener(e -> {//                                                                              ||||

                        int add = 0;
                        int t = 0;
                        for (Integer x : AttackLocater) {
                            if (x > 1 || x == 10) {
                                System.out.println(t + " " + RandoPerson.get(t));
                                add++;
                            }
                            t++;
                        }

                        if (add >= AllyNumber + EnemyNumber) {
                            ArrayList<int[]> Sorter = new ArrayList<int[]>();
                            ArrayList<Integer> PureSpeed = new ArrayList<Integer>();
                            ArrayList<int[]> Sorted = new ArrayList<int[]>();
                            for (int x = 0; x < AllyNumber; x++) {
                                int[] AddOn = {Abilities.Speed(RandoPerson.get(x), (AttackLocater.get(x) - 2)), RandoPerson.get(x), (AttackLocater.get(x) - 2), 0, x};
                                Sorter.add(AddOn); // Speed, Ally #, Attack #, Team, HP placement // 0 - 1 - 2 - 3 - 4
                                PureSpeed.add(Abilities.Speed(RandoPerson.get(x), (AttackLocater.get(x) - 2)));
                                //System.out.println(RandoPerson[x]);
                            }
                            for (int x = AllyNumber; x < (AllyNumber + EnemyNumber); x++) {
                                int[] AddOn = {Abilities.Speed(RandoPerson.get(x), (AttackLocater.get(x) - 2)), RandoPerson.get(x), (AttackLocater.get(x) - 2), 1, x};
                                Sorter.add(AddOn);
                                PureSpeed.add(Abilities.Speed(RandoPerson.get(x), (AttackLocater.get(x) - 2)));
                                //System.out.println(RandoPerson[x]);
                            }
                            //System.out.println("\n\n");
                            Collections.sort(PureSpeed);

                            //System.out.println(PureSpeed);
                            //System.out.println(Sorter.size() + "\n");

                            //System.out.println("\n\n");
                            for (int x = 0; x < PureSpeed.size(); ) {
                                x++;
                                if (Sorter.get(x - 1)[0] == PureSpeed.get(0)) {
                                    Sorted.add(Sorter.get(x - 1));
                                    Sorter.remove(x - 1);
                                    PureSpeed.remove(0);
                                    x = 0;
                                }
                            }

                            ArrayList<Integer> Reset = new ArrayList<>();
                            ArrayList<int[]> AntiHeal = new ArrayList<>();
                            ArrayList<int[]> MegaTarget = new ArrayList<>();
                            int[] BeforeHP = new int[EnemyNumber + AllyNumber];
                            for (int x = 0; x < (EnemyNumber + AllyNumber); x++) {
                                BeforeHP[x] = useHP.get(Sorted.get(x)[4]);
                            }

                            for (int x = 0; x < Sorted.size() && Finish; x++) {
                                //System.out.println(Arrays.toString(Sorted.get(x)));
                                //int z = 0;
                                int Target = 0;
                                try {
                                    Target = Abilities.setTarget(Sorted, useHP, Cards.AllDamage(), ExtraDamage, x, AllyNumber, EnemyNumber);
                                } catch (IndexOutOfBoundsException la) {
                                    System.out.println("Target Error: " + la);
                                    if (Sorted.get(x)[3] == 0) {
                                        System.out.println("Allies have killed all enemies");
                                        YouWon = true;
                                        UsingDamage = 0;
                                        UsingShield = 0;
                                        UsingHealth = 0;
                                    } else {
                                        System.out.println("Enemies have killed all Alles");
                                        YouWon = false;
                                    }
                                    Finish = false;
                                }
                                //System.out.println(Sorted.get(x)[1] + ": " + Arrays.toString(useHP));
                                //if (Target > 0) {System.out.println(Sorted.get(Target)[1] + " is the SHOULD TARGET");}
                                if (Finish) {
                                    int THP = -1;
                                    int DummyCard = -1;
                                    for (int y = 0; y < (AllyNumber + EnemyNumber); y++) {
                                        if (Target >= 0) {
                                            //System.out.println("HP of target: " + useHP[Sorted.get(Target)[4]] + " VS " + useHP[y] + "\nAllies: " + NormalDamage[Sorted.get(Target)[4]] + " VS " + NormalDamage[y] + "\n");
                                            if (useHP.get(Sorted.get(Target)[4]) == useHP.get(y) && NormalDamage.get(Sorted.get(Target)[4]) == NormalDamage.get(y)) {
                                                if (Target >= 3) {
                                                    System.out.println("Enemy is attacking " + Sorted.get(Target)[1]);
                                                } else {
                                                    System.out.println("Ally is attacking " + Sorted.get(Target)[1]);
                                                }
                                                //System.out.println(useHP[x] + " vs " + useHP[y] + "\n" + useDamage[x] + " vs " + useDamage[y] + "\n");
                                                THP = y;
                                                DummyCard = Target;
                                                //break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    for (int z = 0; z < MegaTarget.size(); z++) {
                                        if (MegaTarget.get(z)[1] != Sorted.get(x)[3]) {
                                            Target = MegaTarget.get(z)[0];
                                            System.out.println("THE TARGET IS " + Sorted.get(Target)[1]);
                                        }
                                    }
                                    //System.out.println( + ": " + useHP[Sorted.get(x)[1]]);
                                    try {
                                        useHP = Abilities.Attack(Sorted, useHP, Cards.AllDamage(), x, THP, ExtraDamage, DummyCard, BeforeHP, AllyNumber, EnemyNumber, NormalDamage);
                                    } catch (IndexOutOfBoundsException eee) {
                                        System.out.println("\n* " + eee + " *\n");
                                    }
                        /*
                        try {
                            useHP = Abilities.Attack(Sorted, useHP, Cards.AllDamage(), x, THP, ExtraDamage, DummyCard, BeforeHP, AllyNumber, EnemyNumber);
                        } catch (IndexOutOfBoundsException la) {
                            if (Sorted.get(x)[3] == 0) {
                                System.out.println("Allies have killed all enemies");
                            } else {
                                System.out.println("Enemies have killed all Alles");
                            }
                            Finish = false;
                        }
                         */
                                    Sorted.set(x, new int[]{-1, Sorted.get(x)[1], Sorted.get(x)[2], Sorted.get(x)[3], Sorted.get(x)[4]});
                                    int TheExtra = Abilities.getExtra();
                                    if (TheExtra != 0) {
                                        if (TheExtra == 1) {
                                            ExtraDamage = Abilities.DamageBuff(Sorted, useHP, Cards.AllDamage(), ExtraDamage, x, AllyNumber, EnemyNumber);
                                        }
                                        if (TheExtra == 2) {
                                            ExtraDamage.set(Sorted.get(THP)[4], -1 * Cards.baseDamage(Sorted.get(THP)[1]));
                                            //System.out.println(Sorted.get(THP)[1] + "'s attack is now " + (Cards.baseDamage(Sorted.get(THP)[1]) + ExtraDamage[Sorted.get(THP)[4]]));
                                            Reset.add(THP);
                                        }
                                        if (TheExtra == 3) {
                                            AntiHeal.add(new int[]{Target, useHP.get(Sorted.get(Target)[4])});
                                            //System.out.println(Sorted.get(Target)[1] + " might not heal ***");
                                        }
                                        if (TheExtra == 4) {
                                            MegaTarget.add(new int[]{Target, Sorted.get(Target)[3]});
                                        }
                                        if (TheExtra == 5) {
                                            Sorted.set(Target, new int[]{Sorted.get(Target)[0] + 3, Sorted.get(Target)[1], Sorted.get(Target)[2], Sorted.get(Target)[3], Sorted.get(Target)[4]});
                                            for (int z = 0; z < Sorted.size(); z++) {
                                                if (Sorted.get(z)[0] > Sorted.get(Target)[0]) {
                                                    Sorted.add(z, Sorted.get(Target));
                                                    Sorted.remove(Target);
                                                    break;
                                                }
                                            }
                                        }
                                        if (TheExtra == 6) {
                                            MegaTarget.add(new int[]{x, Sorted.get(x)[3]});
                                        }
                                        if (TheExtra == 7) {
                                            Sorted.set(Target, new int[]{2, Sorted.get(Target)[1], Sorted.get(Target)[2], Sorted.get(Target)[3], Sorted.get(Target)[4]});
                                            for (int z = 0; z < Sorted.size(); z++) {
                                                if (Sorted.get(z)[0] > Sorted.get(Target)[0]) {
                                                    Sorted.add(z, Sorted.get(Target));
                                                    Sorted.remove(Target);
                                                    break;
                                                }
                                            }
                                        }

                                    }
                                }
                                //System.out.println(Sorted.get(x)[1] + ": " + Arrays.toString(useHP) + "\n");
                            }

                            for (int x = 0; x < AntiHeal.size(); x++) { // Extra 3
                                System.out.println(AntiHeal.get(x)[1] + " * VS * " + useHP.get(Sorted.get(AntiHeal.get(x)[0])[4]));
                                if (AntiHeal.get(x)[1] < useHP.get(Sorted.get(AntiHeal.get(x)[0])[4])) {
                                    System.out.println(Sorted.get(x)[1] + " needs to take " + (useHP.get(Sorted.get(AntiHeal.get(x)[0])[4]) - AntiHeal.get(x)[1]) + " damage");
                                    System.out.println(useHP.get(Sorted.get(AntiHeal.get(x)[0])[4]) + " VS " + AntiHeal.get(x)[1]);
                                    useHP.set(Sorted.get(AntiHeal.get(x)[0])[4], useHP.get(Sorted.get(AntiHeal.get(x)[0])[4]) - (useHP.get(Sorted.get(AntiHeal.get(x)[0])[4]) - AntiHeal.get(x)[1]));
                                }
                            }

                            for (int x = 0; x < Reset.size(); x++) { // Extra 2
                                ExtraDamage.set(Sorted.get(Reset.get(x))[4], ExtraDamage.get(Sorted.get(Reset.get(x))[4]) + Cards.baseDamage(Sorted.get(Reset.get(x))[1]));
                                //System.out.println(Sorted.get(Reset.get(x))[1] + "'s attack is reset to " + (Cards.baseDamage(Sorted.get(Reset.get(x))[1]) + ExtraDamage[Sorted.get(Reset.get(x))[4]]));
                            }
                            Reset.clear();
                            ExtraDamage = Abilities.LowerDamage(ExtraDamage);
                            //System.out.println("\n");
                            for (int x = 0; x < AllyNumber; x++) {
                                int TheCatch;
                                int FailCase;
                                boolean Pass = true;
                                try {
                                    TheCatch = useHP.get(HPMap.get(x));
                                    FailCase = Sorted.get(HPMap.get(x))[1];
                                } catch (IndexOutOfBoundsException ignore) {
                                    TheCatch = 1;
                                    FailCase = 1;
                                    Pass = false;
                                }
                                int Easy = HPMap.get(x);
                                if (TheCatch <= 0) {
                                    //System.out.println("HPMap: " + HPMap + "\nPlayers: " + RandoPerson + "\nHP: " + useHP);
                                    AttackLocater.set(Easy, 10);
                                    Allies[x].setBackground(Color.gray);
                                    if (inUse == RandoPerson.get(Easy)) {
                                        inUse = -1;
                                    }
                                    Allies[ButtonLink.get("" + RandoPerson.get(Easy) + 0)].setVisible(false);
                                    AttackLocater.remove(Easy);
                                    useHP.remove(Easy);
                                    ExtraDamage.remove(Easy);
                                    RandoPerson.remove(Easy);
                                    NormalDamage.remove(Easy);
                                    AllyNumber--;
                                    for (int z = x; z < HPMap.size(); z++) {
                                        if (z == x || HPMap.get(z) == -1) {
                                            HPMap.set(z, -1);
                                        } else {
                                            HPMap.set(z, HPMap.get(z) - 1);
                                        }
                                    }
                                    //System.out.println("VS\nHPMap: " + HPMap + "\nPlayers: " + RandoPerson + "\nHP: " + useHP);
                                    //System.out.println(AttackLocater.size() + "\n" + useHP.size() + "\n" + ExtraDamage.size() + "\n" + RandoPerson.size() + "\n" + NormalDamage.size());
                                } else if (TheCatch > Math.ceil(Cards.MaxHP(FailCase) * 1.65) && Pass) {
                                    //System.out.println(useHP.get(Easy) + " has gone over " + Math.ceil(Cards.MaxHP(Sorted.get(Easy)[1]) * 1.65));
                                    useHP.set(Easy, Math.max((int) Math.round(useHP.get(Easy) / 1.31), (int) Math.ceil(Cards.MaxHP(Sorted.get(Easy)[1]) * 1.65)));
                                    Allies[ButtonLink.get("" + RandoPerson.get(Easy) + 0)].setVisible(true);
                                }
                            }
                            for (int x = ButtonAlly; x < (ButtonAlly + ButtonEnemy); x++) {
                                int TheCatch;
                                boolean Pass = true;
                                int Easy = HPMap.get(x);
                                try {
                                    TheCatch = useHP.get(Easy);
                                } catch (IndexOutOfBoundsException ignore) {
                                    TheCatch = 1;
                                    Pass = false;
                                }

                                if (TheCatch <= 0) {
                                    if (inUse == RandoPerson.get(Easy)) {
                                        inUse = -1;
                                    }
                                    //System.out.println("HPMap: " + HPMap + "\nPlayers: " + RandoPerson + "\nHP: " + useHP);
                                    AttackLocater.set(Easy, 10);
                                    Enemies[ButtonLink.get("" + RandoPerson.get(Easy) + 1)].setVisible(false);
                                    //Enemies[x - AllyNumber].setVisible(false);
                                    AttackLocater.remove(Easy);
                                    useHP.remove(Easy);
                                    ExtraDamage.remove(Easy);
                                    RandoPerson.remove(Easy);
                                    NormalDamage.remove(Easy);
                                    EnemyNumber--;
                                    for (int z = x; z < HPMap.size(); z++) {
                                        if (z == x || HPMap.get(z) == -1) {
                                            HPMap.set(z, -1);
                                        } else {
                                            HPMap.set(z, HPMap.get(z) - 1);
                                        }
                                    }
                                    //System.out.println("VS\nHPMap: " + HPMap + "\nPlayers: " + RandoPerson + "\nHP: " + useHP);
                                    //System.out.println(AttackLocater.size() + "\n" + useHP.size() + "\n" + ExtraDamage.size() + "\n" + RandoPerson.size() + "\n" + NormalDamage.size());
                                } else if (Pass){
                                    AttackLocater.set(Easy, (int) (Math.random() * 3) + 2);
                                    if (useHP.get(Easy) > Math.ceil(Cards.MaxHP(Sorted.get(Easy)[1]) * 1.65)) {
                                        useHP.set(Easy, Math.max((int) Math.round(useHP.get(Easy) / 1.31), (int) Math.ceil(Cards.MaxHP(Sorted.get(Easy)[1]) * 1.65)));
                                    }
                                    Enemies[ButtonLink.get("" + RandoPerson.get(Easy) + 1)].setVisible(true);
                                }
                            }

                            AllSee.setText("");
                            AllSee.setText(Abilities.returnWhat());

                            try {
                                String Useless = "" + (useHP.get(inUse));
                            } catch (IndexOutOfBoundsException c) {
                                inUse = -1;
                            }
                            if (inUse != -1) {
                                Stats[0].setText("" + (useHP.get(inUse)));
                                Stats[1].setText("" + (Cards.baseDamage(RandoPerson.get(inUse)) + ExtraDamage.get(inUse)));
                                for (int hide = 2; hide < 5; hide++) {
                                    Stats[hide].setVisible(true);
                                }
                                if (useHP.get(inUse) <= 0) {
                                    Style[0].setEnabled(false);
                                    Style[1].setEnabled(false);
                                    Style[2].setEnabled(false);
                                } else {
                                    Style[0].setEnabled(true);
                                    Style[1].setEnabled(true);
                                    Style[2].setEnabled(true);
                                }
                                if (inUse < AllyNumber) {
                                    Stats[2].setText(Cards.returnAbility(RandoPerson.get(inUse), 0, 0));
                                    Stats[3].setText(Cards.returnAbility(RandoPerson.get(inUse), 1, 0));
                                    Stats[4].setText(Cards.returnAbility(RandoPerson.get(inUse), 2, 0));
                                    Stats[2].setBackground(Color.lightGray);
                                    Stats[3].setBackground(Color.lightGray);
                                    Stats[4].setBackground(Color.lightGray);
                                    Stats[5].setText(Cards.Name(RandoPerson.get(inUse)));
                                } else {
                                    Stats[2].setText("" + Abilities.getAbility(RandoPerson.get(inUse), (AttackLocater.get(inUse) - 2), 1));
                                    for (int hide = 3; hide < 5; hide++) {
                                        Stats[hide].setVisible(false);
                                    }
                                    Stats[5].setText(Cards.Name(RandoPerson.get(inUse)));
                                    MaxHP.setText("" + Cards.MaxHP(RandoPerson.get(inUse)));
                                }
                            }
                        } else {
                            System.out.println("NO : " + AttackLocater + "\nAdd is : " + add);
                        }
                        int z = 0;
                        int q = 0;
                        for (int x = 0; x < (AllyNumber + EnemyNumber); x++) {
                            if (x < AllyNumber) {
                                if (AllyNumber == 1) {
                                    Allies[ButtonLink.get("" + RandoPerson.get(x) + 0)].setBounds(100, 300, 200, 200);
                                }
                                if (AllyNumber == 2) {
                                    Allies[ButtonLink.get("" + RandoPerson.get(x) + 0)].setBounds(150 * z + 50, 350, 150, 150);
                                }
                                if (AllyNumber == 3) {
                                    Allies[ButtonLink.get("" + RandoPerson.get(x) + 0)].setBounds(100 * z + 50, 350, 100, 100);
                                }
                                if (AllyNumber == 4) {
                                    Allies[ButtonLink.get("" + RandoPerson.get(x) + 0)].setBounds(80 * z + 50, 350, 80, 80);
                                }
                                if (AllyNumber == 5) {
                                    Allies[ButtonLink.get("" + RandoPerson.get(x) + 0)].setBounds(60 * z + 60, 360, 60, 60);
                                }
                                z++;
                            } else {
                                if (EnemyNumber == 1) {
                                    Enemies[ButtonLink.get("" + RandoPerson.get(x) + 1)].setBounds(100, 100, 200, 200);
                                }
                                if (EnemyNumber == 2) {
                                    Enemies[ButtonLink.get("" + RandoPerson.get(x) + 1)].setBounds(150 * q + 50, 150, 150, 150);
                                }
                                if (EnemyNumber == 3) {
                                    Enemies[ButtonLink.get("" + RandoPerson.get(x) + 1)].setBounds(100 * q + 60, 150, 100, 100);
                                }
                                if (EnemyNumber == 4) {
                                    Enemies[ButtonLink.get("" + RandoPerson.get(x) + 1)].setBounds(80 * q + 50, 150, 80, 80);
                                }
                                if (EnemyNumber == 5) {
                                    Enemies[ButtonLink.get("" + RandoPerson.get(x) + 1)].setBounds(60 * q + 60, 140, 60, 60);
                                }
                                q++;
                            }
                        }
                        if (EnemyNumber < 1 || AllyNumber < 1) {
                            if (EnemyNumber < 1) {
                                System.out.println("Allies have killed all enemies");
                                YouWon = true;
                                for (int b = 0; b < useHP.size(); b++) {
                                    AllAllies[RandoPerson.get(b)].setEnabled(true);
                                }
                            } else {
                                System.out.println("Enemies have killed all Alles");
                                YouWon = false;
                                Level --;
                            }
                            System.out.println(Level);
                            Finish = false;

                        }
                        if (!Finish) {
                            Finish = true;
                            for (int y = 0; y < 8; y++) {
                                Map[y].setVisible(true);
                                Shop.setVisible(true);
                                if (y < 5) {
                                    Allies[y].setVisible(false);
                                    Enemies[y].setVisible(false);
                                    WhichAllies[y].setEnabled(false);
                                    WhichAllies[0].setEnabled(true);
                                    WhichAllies[y].setText("");
                                }
                                if (y < 3) {
                                    Style[y].setVisible(false);
                                }
                                if (y < 6) {
                                    Stats[y].setVisible(false);
                                }
                                Fight.setVisible(false);
                                AllSee.setVisible(false);
                                MaxHP.setVisible(false);
                                Battle.setVisible(false);
                                BackRound.setVisible(false);
                                for (int x = 0; x < 8; x++) {
                                    Map[x].setEnabled(false);
                                    Map[x].setText("");
                                }

                                if (Level % 5 == 0) {
                                    Map[0].setEnabled(true);
                                    Map[0].setText("X");
                                }
                                if (Level % 5 == 1) {
                                    Map[1].setEnabled(true);
                                    Map[2].setEnabled(true);
                                    Map[1].setText("X");
                                    Map[2].setText("X");
                                }
                                if (Level % 5 == 2) {
                                    Map[3].setEnabled(true);
                                    Map[4].setEnabled(true);
                                    Map[3].setText("X");
                                    Map[4].setText("X");
                                }
                                if (Level % 5 == 3) {
                                    Map[5].setEnabled(true);
                                    Map[6].setEnabled(true);
                                    Map[5].setText("X");
                                    Map[6].setText("X");
                                }
                                if (Level % 5 == 4) {
                                    Map[7].setEnabled(true);
                                    Map[7].setText("X");
                                }
                            }

                            AttackLocater.clear();
                            useHP.clear();
                            ExtraDamage.clear();
                            RandoPerson.clear();
                            NormalDamage.clear();
                            AllyNumber = -1;
                            EnemyNumber = -1;
                            ButtonLink.clear();
                            HPMap.clear();
                            for (JButton currentButton : Allies) {
                                for (ActionListener al : currentButton.getActionListeners()) {
                                    currentButton.removeActionListener(al);
                                }
                                currentButton.setVisible(false);
                            }
                            for (JButton currentButton : Enemies) {
                                for (ActionListener al : currentButton.getActionListeners()) {
                                    currentButton.removeActionListener(al);
                                }
                                currentButton.setVisible(false);
                            }
                            if (YouWon) {
                                Money += (int) (Math.random() * 13 + 11); // 11 - 23
                            }
                            System.out.println("You Finished");
                            ItemHealth += UsingHealth;
                            ItemDamage += UsingDamage;
                            ItemShield += UsingShield;
                            UsingHealth = 0;
                            UsingDamage = 0;
                            UsingShield = 0;
                            for (int x = 0; x < 5; x ++) {
                                Rigged[x] = -1;
                            }
                        }
                        System.out.println(AllyNumber + " with " + EnemyNumber);
                    });

                    for (int x = 0; x < 3; x++) {
                        int oofx = x + 2;
                        Style[x].addActionListener(e -> {
                            if (inUse >= 0 && inUse < AllyNumber) {
                                AttackLocater.set(inUse, oofx);
                                if (oofx == 2) {
                                    Stats[2].setBackground(Color.gray);
                                    Stats[3].setBackground(Color.lightGray);
                                    Stats[4].setBackground(Color.lightGray);
                                }
                                if (oofx == 3) {
                                    Stats[2].setBackground(Color.lightGray);
                                    Stats[3].setBackground(Color.gray);
                                    Stats[4].setBackground(Color.lightGray);
                                }
                                if (oofx == 4) {
                                    Stats[2].setBackground(Color.lightGray);
                                    Stats[3].setBackground(Color.lightGray);
                                    Stats[4].setBackground(Color.gray);
                                }
                            }
                        });
                    }
                } else {
                    HPMap.clear();
                    if (aa == 0) {
                        if (Level == 0) {
                            AllyNumber = 3;
                            EnemyNumber = 3;
                        } else {
                            AllyNumber = 5;
                            EnemyNumber = 5;
                        }
                    }
                    if (aa == 1) {
                        if (Level == 1) {
                            AllyNumber = 2;
                            EnemyNumber = 1;
                        } else {
                            AllyNumber = 3;
                            EnemyNumber = 2;
                        }
                    }
                    if (aa == 2) {
                        if (Level == 1) {
                            AllyNumber = 4;
                            EnemyNumber = 4;
                        } else {
                            AllyNumber = 1;
                            EnemyNumber = 3;
                        }
                    }
                    if (aa == 3) {
                        if (Level == 1) {
                            AllyNumber = 1;
                            EnemyNumber = 1;
                        } else {
                            AllyNumber = 3;
                            EnemyNumber = 5;
                        }
                    }
                    if (aa == 4) {
                        if (Level == 1) {
                            AllyNumber = 2;
                            EnemyNumber = 5;
                            //Enemy should be weak swarms
                        } else {
                            
                        }
                    }
                    if (aa == 5) {
                        AllyNumber = 4;
                        EnemyNumber = 2;
                        //Allies should have an automatic 0 Attack. Enemies should have 50% less attack
                    }
                    if (aa == 6) {
                        AllyNumber = 1;
                        EnemyNumber = 2;
                        //Enemies start with 25% less damage
                    }
                    if (aa == 7) {
                        AllyNumber = 5;
                        EnemyNumber = 1;
                        Rigged[0] = 15;
                        //Boss
                    }
                    for (int x = 0; x < (AllyNumber + EnemyNumber); x++) {
                        HPMap.add(x);
                    }
                    ButtonAlly = AllyNumber;
                    ButtonEnemy = EnemyNumber;
                    for (int x = 0; x < AllyNumber; x++) {
                        WhichAllies[x].setVisible(true);
                    }
                    for (int x = 0; x < 15; x++) {
                        AllAllies[x].setVisible(true);
                    }
                    for (int x = 0; x < 4; x++) {
                        Items[x].setVisible(true);
                        Items[x].setBounds((x * 100) + 400, 300, 100, 100);
                    }
                    for (int x = 0; x < 8; x++) {
                        Map[x].setVisible(false);
                    }
                    Shop.setVisible(false);
                    Map[aa].setVisible(true);
                    //System.out.println("Here you go");
                }
                System.out.println(RandoPerson);
            });
        }
        for (int y = 0; y < 8; y++) {
            Map[y].setVisible(true);
            Shop.setVisible(true);
            if (y < 5) {
                Allies[y].setVisible(false);
                Enemies[y].setVisible(false);
                WhichAllies[y].setVisible(false);
            }
            if (y < 4) {
                Items[y].setVisible(false);
                if (y < 3) {
                    Style[y].setVisible(false);
                }
            }
            if (y < 6) {
                Stats[y].setVisible(false);
            }
            Fight.setVisible(false);
            AllSee.setVisible(false);
            MaxHP.setVisible(false);
            Battle.setVisible(false);
            BackRound.setVisible(false);
        }


        for (int x = 0; x < 8; x++) {
            this.add(Map[x]);
            if (x < 5) {
                this.add(Allies[x]);
                this.add(Enemies[x]);
                this.add(WhichAllies[x]);
                if (x < 4) {
                    this.add(Items[x]);
                    if (x < 3) {
                        this.add(Style[x]);
                    }
                }
            }
        }
        this.add(Fight);
        for (JLabel stat : Stats) {
            this.add(stat);
        }
        for (int x = 0; x < 15; x++) {
            this.add(AllAllies[x]);
        }
        this.add(Shop);
        this.add(AllSee);
        this.add(MaxHP);
        this.add(Battle);
        this.add(BackRound);
        this.setLayout(null);
        this.setSize(1000, 800);
        this.setVisible(true);
        /*
        try {
        } catch (NullPointerException ignored) {
            System.out.println("OOF");
        }
        */
    }

    public static void main(String[] args) {
        for (int x = 0; x < 16; x++) {
            card[x] = new Cards(x);
        }
        try {
            WarCraft fight = new WarCraft();
        }
        catch (IndexOutOfBoundsException why) {
            System.out.println("*" + why+ "*");
        }
    }
}
