import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mine extends JFrame{

    private static int HP = 1;
    private static int Tile = 0;
    private static int FlaggedBomb = 0;

    Mine(int dif) {
        //Randomizer random = new Randomizer();
        JButton[][] button = new JButton[dif][dif];
        int[][] bomb = new int[dif][dif];
        String[][] num = new String[dif][dif];
        JButton flag = new JButton();
        int[][] flagged = new int[dif][dif];
        AtomicBoolean flagger = new AtomicBoolean(false);
        JButton flagcount = new JButton();

        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                button[x][y] = new JButton();
            }
        }

        flag.setBounds(dif * 45 + 20, 20, 45, 45);
        flag.setBackground(Color.black);
        flagcount.setBounds(dif * 45 + 20, 65, 60, 45);
        flagcount.setBackground(Color.white);

        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                button[y][x].setBounds(y * 45 + 20, x * 45 + 20, 45, 45);
                flagged[x][y] = 0;
            }
        }

        int bombCount = 0;
        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                int random = (int) (Math.random() * 10);
                if (random < 2) {
                    bomb[x][y] = 1;
                    button[x][y].setText(" "); // Bomb
                    bombCount ++;
                } else {
                    button[x][y].setText(" ");
                    bomb[x][y] = 0;
                }
            }
        }
        System.out.println("There are " + bombCount + " Bombs");
        //button[x location] [y location].bla bla bla;

        int SafeCount = 3;
        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                int count = 0;
                if (bomb[x][y] == 0) {
                    for (int a = Math.max(x - 1, 0); a <= Math.min(x + 1, dif - 1); a++) {
                        for (int b = Math.max(y - 1, 0); b <= Math.min(y + 1, dif - 1); b++) {
                            if (bomb[a][b] == 1) {
                                count ++;
                            }
                        }
                    }
                    num[x][y] = String.valueOf(count);
                    if (count == 0 && SafeCount > 0) {
                        button[x][y].setText("0");
                        SafeCount--;
                    }
                    //button[x][y].setText(String.valueOf(count));
                    //button[x][y].setBackground(Color.GRAY);
                } else {
                    num[x][y] = "Bomb";
                    //button[x][y].setText("Bomb");
                    //button[x][y].setBackground(Color.RED);
                }
            }
        }

        int finalBombCount = bombCount;
        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                int finalX = x;
                int finalY = y;
                button[x][y].addActionListener(e -> Mine.close(button, num, bomb, finalX, finalY, dif, flagger.get(), flagged, flagcount, finalBombCount));
            }
        }

        flag.addActionListener(e -> flagger.set(Mine.switcher(flagger.get(), flag)));

        //Lives.setEnabled(false);

        //JOptionPane.showMessageDialog(null, "Their are " + bombCount + " Bombs");

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.add(flag);
        this.add(flagcount);
        for (int x = 0; x < dif; x++) {
            for (int y = 0; y < dif; y++) {
                //button[x][y].setFocusable(true);
                this.add(button[x][y]);
            }
        }
    }

    /*
    for (int x = 0; x < 9; x++) {
        for (int y = 0; y < 9; y++) {

        }
    }
    */
    public static void close(JButton[][] button, String[][] num, int[][] bomb, int a, int b, int dif, boolean flag, int[][] flagged, JButton flagcount, int Bombcount) {
        if (!flag) {
            if (flagged[a][b] == 0) {
                if (bomb[a][b] == 0) {
                    button[a][b].setEnabled(false);
                    button[a][b].setBackground(Color.white);
                    button[a][b].setText(num[a][b]);
                    Tile++;
                } else {
                    HP--;
                    JOptionPane.showMessageDialog(null, "You stuipid");
                }
            }

            if (HP <= 0) {
                JOptionPane.showMessageDialog(null, "You Lost");
                for (int x = 0; x < dif; x++) {
                    for (int y = 0; y < dif; y++) {
                        button[x][y].setEnabled(false);
                        button[x][y].setText(num[x][y]);
                        button[x][y].setBackground(Color.lightGray);
                        if (bomb[x][y] == 1) {
                            button[x][y].setBackground(Color.RED);
                        }
                    }
                }
            }
        } else {
            if (flagged[a][b] == 0) {
                button[a][b].setBackground(Color.red);
                flagged[a][b] = 1;
                FlaggedBomb++;
                flagcount.setText(String.valueOf(FlaggedBomb));
            } else {
                button[a][b].setBackground(Color.lightGray);
                flagged[a][b] = 0;
                FlaggedBomb--;
                flagcount.setText(String.valueOf(FlaggedBomb));
            }

            int correct = 0;
            if (Bombcount == FlaggedBomb && Tile == (dif * dif) - FlaggedBomb) {
                for (int x = 0; x < dif; x++) {
                    for (int y = 0; y < dif; y++) {
                        if (bomb[x][y] == flagged[x][y]){
                            correct ++;
                        }
                    }
                }
                if (correct == (dif * dif)) {
                    JOptionPane.showMessageDialog(null, "You found all the bombs");
                    for (int x = 0; x < dif; x++) {
                        for (int y = 0; y < dif; y++) {
                            button[x][y].setBackground(Color.yellow);
                            if (bomb[x][y] == 1) {
                                button[x][y].setBackground(Color.ORANGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have " + ((dif * dif) - correct) + " incorrect spaces");
                }
            }
        }
    }

        public static boolean switcher(boolean flag, JButton eh) {
            if (!flag) {
                eh.setBackground(Color.red);
                return true;
            } else {
                eh.setBackground(Color.black);
                return false;
            }
        }


    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Select a difficulty\nEasy\nMedium \\ Normal\nHard");
        int x = 0;
        String dif = kb.nextLine();
        if (dif.equalsIgnoreCase("easy")) {
            x = 10;
        } else if (dif.equalsIgnoreCase("medium") || dif.equalsIgnoreCase("normal")) {
            x = 15;
        } else if (dif.equalsIgnoreCase("hard")) {
            x = 20;
        } else if (dif.equalsIgnoreCase("custom")) {
            x = kb.nextInt();
        } else {
            System.out.println("ERROR... EXPERT MODE");
            x = 30;
        }
        Mine oof = new Mine(x);
    }
}
