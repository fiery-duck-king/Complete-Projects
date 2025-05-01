import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle extends JFrame{
    
    private static int HP;
    private static int Tile = 0;
    static boolean yes = false;
    static boolean Input = true;
    static int Count = -1;

    Puzzle(int dif) {
        //Randomizer random = new Randomizer();
        JButton Toggle = new JButton();
        JButton[] Number = new JButton[9];
        JButton[][] button = new JButton[9][9];
        int[][] num = new int[9][9];
        JButton quit = new JButton();
        JButton Lives = new JButton();
        ArrayList<Integer> nums = new ArrayList<Integer>();

        for (int x = 0; x < 9; x++) {
            Number[x] = new JButton();
            for (int y = 0; y < 9; y++) {
                button[x][y] = new JButton();
            }
        }

        Toggle.setBounds(660, 650, 60, 60);
        Toggle.setBackground(Color.lightGray);
        Toggle.addActionListener(e -> {
            Input = !Input;
            Count = -1;
            Toggle.setText("");
            if (Input) {
                Toggle.setBackground(Color.lightGray);
                for (int x = 0; x < 9; x++) {
                    Number[x].setBackground(Color.gray);
                }
            } else {
                Toggle.setBackground(Color.pink);
                for (int x = 0; x < 9; x++) {
                    Number[x].setBackground(Color.pink);
                }
            }
        });

        for (int x = 0; x < 9; x++) {
            int TX = x + 1;
            Number[x].setText("" + TX);
            Number[x].setBackground(Color.gray);
            Number[x].setBounds(x * 60 + 60, 650, 60, 60);
            Number[x].addActionListener(e -> {
                if (!Input) {
                    Count = TX;
                    for (int y = 0; y < 9; y++) {
                        Number[y].setBackground(Color.pink);
                    }
                    Toggle.setText("" + TX);
                    Number[TX - 1].setBackground(Color.GRAY);
                }
            });
        }

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int add = 0;

                if (x <= 2) {
                    add = 0;
                } else if (x > 2 && x <= 5) {
                    add = 5;
                } else if (x > 5) {
                    add = 10;
                }

                if (y <= 2) {
                    button[y][x].setBounds(y * 60 + 60, x * 60 + 60 + add, 60, 60);
                } else if (y > 2 && y <= 5) {
                    button[y][x].setBounds(y * 60 + 65, x * 60 + 60 + add, 60, 60);
                } else if (y > 5) {
                    button[y][x].setBounds(y * 60 + 70, x * 60 + 60 + add, 60, 60);
                }

            }
        }

        quit.setBounds(150, 0, 150, 50);
        quit.setBackground(Color.MAGENTA);

        for (int x = 0; x < 9; x++) {
            int redo = 0;
            int size = 0;
            for (int a = 1; a <= 9; a++) {
                nums.add(a);
                size++;
            }
            int recount = 0;
            //System.out.println(nums);
            for (int y = 0; y < 9;) {
                int give = (int) (Math.random() * size);
                int la = nums.get(give);
                //System.out.println("Random number was " + give);
                //System.out.println("The number at " + give + " is at " + la + "\n");
                int oofed = 0;
                for (int b = 0; b < x; b++) {
                    if (la == num[b][y]) {
                        oofed = 1;
                        recount ++;
                        break;
                    }
                }
                int pos = 0;
                if (oofed == 0) { // 0 1 2 // 3 4 5 // 6 7 8
                    if (x >=0 && x <= 2) {
                        if (y >=0 && y <= 2) {
                            pos = 1;
                        }
                        if (y >= 3 && y <= 5) {
                            pos = 4;
                        }
                        if (y >=6 && y < 9) {
                            pos = 7;
                        }
                    }
                    if (x >=3 && x <= 5) {
                        if (y >=0 && y <= 2) {
                            pos = 2;
                        }
                        if (y >= 3 && y <= 5) {
                            pos = 5;
                        }
                        if (y >=6 && y < 9) {
                            pos = 8;
                        }
                    }
                    if (x >= 6 && x < 9) {
                        if (y >=0 && y <= 2) {
                            pos = 3;
                        }
                        if (y >= 3 && y <= 5) {
                            pos = 6;
                        }
                        if (y >=6 && y < 9) {
                            pos = 9;
                        }
                    }

                    if (pos == 1) {
                        for (int a = 0; a <= 2; a++) {
                            for (int b = 0; b <= 2; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 2) {
                        for (int a = 3; a <= 5; a++) {
                            for (int b = 0; b <= 2; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 3) {
                        for (int a = 6; a <= 8; a++) {
                            for (int b = 0; b <= 2; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 4) {
                        for (int a = 0; a <= 2; a++) {
                            for (int b = 3; b <= 5; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 5) {
                        for (int a = 3; a <= 5; a++) {
                            for (int b = 3; b <= 5; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 6) {
                        for (int a = 6; a <= 8; a++) {
                            for (int b = 3; b <= 5; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 7) {
                        for (int a = 0; a <= 2; a++) {
                            for (int b = 6; b <= 8; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 8) {
                        for (int a = 3; a <= 5; a++) {
                            for (int b = 6; b <= 8; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    } if (pos == 9) {
                        for (int a = 6; a <= 8; a++) {
                            for (int b = 6; b <= 8; b++) {
                                if (num[a][b] == la) {
                                    oofed = 1;
                                    recount ++;
                                    break;
                                }
                            }
                        }
                    }

                }

                if (oofed == 0) {
                    num[x][y] = la;
                    button[x][y].setText(String.valueOf(num[x][y]));
                    nums.remove(give);
                    size--;
                    y++;
                    //System.out.println("worked");
                }
                if (recount > 25) {
                    y = 0;
                    nums.clear();
                    size = 0;
                    for (int a = 1; a <= 9; a++) {
                        nums.add(a);
                        size++;
                    }
                    //System.out.println("recounting");
                    recount = 0;
                    redo ++;
                }
                if (redo > 15) {
                    x = 0;
                    nums.clear();
                    size = 0;
                    for (int a = 1; a <= 9; a++) {
                        nums.add(a);
                        size++;
                    }
                    for (int a = 0; a < 9; a++) {
                        for (int b = 0; b < 9; b++) {
                            num[a][b] = 0;
                        }
                    }
                    System.out.println("Resetting");
                    redo = 0;
                }
            }
            //System.out.println("Row set");
        }

        //button[x location] [y location].bla bla bla;

        int risk = 0;
        int riskmax = 0;
        int riskmin = 0;
        if (dif == 1) {
            risk = (int) Math.round(81 * (Math.random() * .1 + .7));
            riskmax = (int) Math.round(81 * (1 * .1 + .7));
            riskmin = (int) Math.round(81 * (0 * .1 + .7));
        } else if (dif == 2) {
            risk = (int) Math.round(81 * (Math.random() * .11 + .51));
            riskmax = (int) Math.round(81 * (1 * .11 + .51));
            riskmin = (int) Math.round(81 * (0 * .11 + .51));
        } else if (dif == 3) {
            risk = (int) Math.round(81 * (Math.random() * .15 + .30));
            riskmax = (int) Math.round(81 * (1 * .15 + .30));
            riskmin = (int) Math.round(81 * (0 * .15 + .30));
        } else if (dif == 5) {
            risk = (int) Math.round(81 * (Math.random() * .07 + .18));
            riskmax = (int) Math.round(81 * (1 * .07 + .18));
            riskmin = (int) Math.round(81 * (0 * .07 + .18));
        }

        int yes = 0;
        int cent = 0;
        while (yes != -1) {
            cent = 0;
            yes++;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    int rando = (int) (Math.random() * 100) + 1;
                    if (rando > risk) { // 0 or 1
                        button[x][y].setText(" ");
                        button[x][y].setBackground(Color.lightGray);
                        button[x][y].setEnabled(true); // Hidden
                    } else {
                        button[x][y].setText(String.valueOf(num[x][y]));
                        button[x][y].setBackground(Color.white);
                        button[x][y].setEnabled(false); // Showen
                        cent ++;
                    }

                }
            }
            if (cent < riskmax && cent > riskmin) {
                //System.out.println(yes);
                System.out.println(riskmin + " " + cent + " " + riskmax+ ". Risk is " + risk);
                yes = -1;
            }
        }

        Tile = cent;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int finalX = x;
                int finalY = y;
                button[x][y].addActionListener(e -> Puzzle.close(button, finalX, finalY, num, Lives));
            }
        }

        Lives.setBounds(300, 0, 200, 50);
        Lives.setBackground(Color.RED);
        Lives.setText((Puzzle.yes) ? "You have " + HP + " HP" : "You have " + HP + " fails");
        //Lives.setEnabled(false);

        quit.setText("END PROGRAM");
        quit.addActionListener(e -> this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE));
        quit.setFocusable(false);
        Lives.setFocusable(false);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.add(quit);
        this.add(Lives);
        this.add(Toggle);
        for (int x = 0; x < 9; x++) {
            this.add(Number[x]);
            for (int y = 0; y < 9; y++) {
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
    public static void close(JButton[][] button, int a, int b, int[][] check, JButton live) {
        String answer;
        if (Input) {
            answer = JOptionPane.showInputDialog("What number (1 - 9)");
        } else {
            answer = "" + Count;
        }
        int numAns = Integer.parseInt(answer);
        if (check[a][b] == numAns) {
            button[a][b].setEnabled(false);
            button[a][b].setText(answer);
            button[a][b].setBackground(Color.white);
            //JOptionPane.showMessageDialog(null, "You did it. You have " + HP + " HP remaining");
            Tile ++;
        } else {
            if (!yes) {
                HP++;
                JOptionPane.showMessageDialog(null, "You have " + HP + " fails");
                live.setText("You have " + HP + " fails");
            } else {
                HP--;
                JOptionPane.showMessageDialog(null, "You have " + HP + " HP");
                live.setText("You have " + HP + " HP");
            }
        }
        if (Tile == 81) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    button[x][y].setBackground(Color.YELLOW);
                    if (HP > 3) {
                        button[x][y].setBackground(Color.ORANGE);
                    }
                }
            }
            if (yes) {
                JOptionPane.showMessageDialog(null, "YOU WON. You have " + HP + " HP remaining");
            } else {
                if (HP <= 3) {
                    JOptionPane.showMessageDialog(null, "YOU WON. You have " + HP + " fails");
                } else {
                    JOptionPane.showMessageDialog(null, "You have completed the Puzzle with " + HP + " fails. You can do better");
                }
            }
        }
        if (HP <= 0 && yes) {
            JOptionPane.showMessageDialog(null, "You Lost");
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    String ouch = button[x][y].getText();
                    if (ouch.equalsIgnoreCase(" ")) {
                        button[x][y].setEnabled(false);
                        button[x][y].setBackground(Color.MAGENTA);
                        button[x][y].setText(String.valueOf(check[x][y]));
                    } else {
                        button[x][y].setBackground(Color.GRAY);
                    }
                }
            }
        }
}


    public static void main(String[] args) {
        // x = (boolean) ? 1 : 3; == if (boolean) {x = 1} else {x = 3}
        Scanner kb = new Scanner(System.in);
        System.out.println("(If you want to play HardCore Mode, Type \"+\")\nExample: Easy+\nSelect a difficulty\nEasy\nMedium \\ Normal\nHard");
        int x;
        String dif = kb.nextLine();
        if (dif.contains("+")) {
            yes = true;
        }
        if (dif.equalsIgnoreCase("easy") || dif.equalsIgnoreCase("easy+")) {
            x = 1;
        } else if (dif.equalsIgnoreCase("medium") || dif.equalsIgnoreCase("normal") || dif.equalsIgnoreCase("medium+") || dif.equalsIgnoreCase("normal+")) {
            x = 2;
        } else if (dif.equalsIgnoreCase("hard") || dif.equalsIgnoreCase("hard+")) {
            x = 3;
        } else {
            System.out.println("ERROR... EXPERT MODE");
            x = 5;
        }
        HP = (yes) ? 3 : 0;
        Puzzle oof = new Puzzle(x);
    }
}
