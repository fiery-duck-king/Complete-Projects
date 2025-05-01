import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame {

    JButton TotalSticks = new JButton();
    JButton StartGame = new JButton();
    JButton AddPlayer = new JButton();
    ArrayList<JButton> Players = new ArrayList<>();
    JButton[] Sticks = new JButton[3];
    int Count;
    int RemainingSticks;
    boolean Nerd = false;

    Main() {
        AddPlayer.setBounds(50, 500, 100, 50);
        AddPlayer.setBackground(Color.white);
        AddPlayer.setText("New Player");
        AddPlayer.setVisible(!Nerd);
        AddPlayer.addActionListener(e -> {
            JButton NewPlayer = new JButton();

            NewPlayer.setBounds(75 + Players.size() * 75, 600, 50, 50);
            NewPlayer.setBackground(Color.white);
            NewPlayer.addActionListener(ee -> {
                if (NewPlayer.getBackground() == Color.WHITE) {
                    NewPlayer.setBackground(Color.YELLOW);
                } else {
                    NewPlayer.setBackground(Color.WHITE);
                }
            });

            Players.add(NewPlayer);
            this.add(NewPlayer);
        });

        for (int x = 0; x < 3; x++) {
            Sticks[x] = new JButton();
            Sticks[x].setBounds(200 + x * 100, 65, 100, 50);
            Sticks[x].setBackground(Color.white);
            Sticks[x].setVisible(Nerd);
            Sticks[x].setText(x + 1 + "");
            int xx = x;
            Sticks[x].addActionListener(e -> {
                RemoveSticks(xx + 1);
                if (Count >= Players.size()) {
                    Count = 0;
                }
                TotalSticks.setText(RemainingSticks + "");
                for (int y = 0; y < Players.size(); y++) {
                    if (Players.get(y).getBackground() == Color.white || Players.get(y).getBackground() == Color.YELLOW) {
                        Players.get(y).setBackground(Color.white);
                    }
                    if (y == Count) {
                        Players.get(y).setBackground(Players.get(y).getBackground() == Color.red ? Color.red : Color.YELLOW);
                    }
                }
            });
        }

        TotalSticks.setBounds(100, 65, 100, 50);
        TotalSticks.setBackground(Color.white);
        TotalSticks.setText("x");
        TotalSticks.setVisible(false);

        StartGame.setBounds(100, 65, 100, 50);
        StartGame.setBackground(Color.white);
        StartGame.setText("Start Game");
        StartGame.setVisible(!Nerd);
        StartGame.addActionListener(e -> {
            RemainingSticks = (int) (Math.round(Math.pow(2, Players.size()) / Players.size())) + 12 + Players.size();
            Nerd = !Nerd;
            AddPlayer.setVisible(false);
            TotalSticks.setVisible(true);
            StartGame.setVisible(false);
            Sticks[0].setVisible(true);
            Sticks[1].setVisible(true);
            Sticks[2].setVisible(true);
            TotalSticks.setText("" + RemainingSticks);
            Count = (int) (Math.random() * Players.size());

            for (int x = 0; x < Players.size(); x++) {
                if (Players.get(x).getBackground() == Color.white || Players.get(x).getBackground() == Color.YELLOW) {
                    Players.get(x).setBackground(Color.white);
                }
                if (x == Count) {
                    Players.get(x).setBackground(Players.get(x).getBackground() == Color.red ? Color.red : Color.YELLOW);
                }
            }
        });

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.add(TotalSticks);
        this.add(StartGame);
        this.add(AddPlayer);
        for (JButton x : Players) {
            this.add(x);
        }
        this.add(Sticks[0]);
        this.add(Sticks[1]);
        this.add(Sticks[2]);

    }

    void RemoveSticks(int Amount) {
        RemainingSticks -= Amount;
        if (RemainingSticks <= 0) {
            Players.get(Count).setVisible(false);
            Players.remove(Count);
            RemainingSticks = (int) (Math.round(Math.pow(2, Players.size()) / Players.size())) + 12 + Players.size();
            Count = (int) (Math.random() * Players.size());
        } else {
            Count++;
        }
    }

    public static void main(String[] args) {

        Main Start = new Main();
        /*
        Scanner kb = new Scanner(System.in);

        String[] Names;

        while (true) {
            try {
                System.out.println("How Many players are there?");
                int Size = Integer.parseInt(kb.nextLine());
                if (Size <= 0) {
                    Size /= 0; // Throw Error AKA restart
                }

                Names = new String[Size];

                for (int x = 0; x < Size; x++) {
                    System.out.println("Player " + (x + 1) + "'s name? (type \"(bot)\" for that player to be a bot)");
                    Names[x] = kb.nextLine();
                }

                System.out.println("Starting Game");
                break;
            } catch (Exception e) {
                System.out.println("Failure, restarting");
            }
        }

        while(Names.length > 1) {

            int Sticks = (int) (Math.round(Math.pow(2, Names.length) / Names.length)) + 12 + Names.length;
            int TurnOrder = (int) (Math.random() * Names.length);
            System.out.println(Names[TurnOrder] + " will start the game");
            while (true) {
                System.out.println("\n" + Names[TurnOrder] + ", how many sticks would you like take (from 1, 2, or 3) (" + Sticks + (Sticks == 1 ? " stick" : " sticks") + ")");
                int CountOfSticks;
                if (Names[TurnOrder].contains("(bot)")) {
                    CountOfSticks = BotMove(Sticks, Names.length);
                } else {
                    while (true) {
                        try {
                            CountOfSticks = Integer.parseInt(kb.nextLine());
                            if (CountOfSticks > 3 || CountOfSticks < 1) {
                                CountOfSticks /= 0; // Throw Error AKA restart
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }
                }
                System.out.print(Names[TurnOrder] + " removed " + CountOfSticks+ " sticks");
                Sticks -= CountOfSticks;
                if (Sticks <= 0) {
                    System.out.print(" and they are ELIMINATED.");

                    String[] NewNames = new String[Names.length - 1];

                    boolean Switch = false;
                    for (int x = 0; x < Names.length; x++) {
                        if (x != TurnOrder) {
                            NewNames[x - (Switch ? 1 : 0)] = Names[x];
                        } else {
                            Switch = true;
                        }
                    }

                    Names = NewNames;

                    if (Names.length > 1) {
                        System.out.println(" " + (Names.length) + " players remain");
                    }
                    break;
                } else {
                    System.out.println(", and " + Sticks + (Sticks == 1 ? " stick" : " sticks") + " remain!");
                    TurnOrder = (TurnOrder + 1 >= Names.length) ? 0 : TurnOrder + 1;
                }
            }
        }
        System.out.println(" " + Names[0] + " WON! Thanks for playing");

         */
    }
    public static int BotMove(int Amount, int PlayerCount) {
        int Send = (Amount - (PlayerCount - 1)) % 4;
        if (Send == 0) {
            Send = (int) (Math.random() * 3);
        }
        return Math.min(Amount - 1, Send);
    }
}
