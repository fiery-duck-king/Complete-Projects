import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
//import java.util.ArrayList;

public class PacMan extends JFrame implements KeyListener {
/*
    public void paint(Graphics Grpah) {
        Graphics2D g = (Graphics2D) Grpah;
        g.setStroke(new BasicStroke(3));
        g.drawLine(100, 100 , 222 , 100);
    }

 */
    static int GameStatis = 0;
    int Level = 1;
    JLabel label;
    JLabel[] ghost = new JLabel[4];
    int change = 60;
    int PacX = 60 * 9 + 40;
    int PacY = 60 * 8 + 40;
    Movement[][] tiles = new Movement[19][11];
    Boolean[][] pellets = new Boolean[19][11];
    JLabel[][] pelletLook = new JLabel[19][11];
    int pelletCount = 0;
    int PacLives = 3;

    PacMan() {
        Enemy.reset();
        int maxwall = 80;
        JLabel[] walls = new JLabel[maxwall];
        for (int x = 0; x < maxwall; x++) {
            walls[x] = new JLabel();
        }

        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 11; y++) {
                pellets[x][y] = false;
                pelletLook[x][y] = new JLabel();
                pelletLook[x][y].setBounds(60 * x + 60, 60 * y + 60, 10, 10);

                pelletLook[x][y].setBackground(Color.white);
                pelletLook[x][y].setOpaque(true);
            }
        }

        pelletLook[0][4].setVisible(false);
        pellets[0][4] = true;
        pelletLook[0][6].setVisible(false);
        pellets[0][6] = true;
        pelletLook[18][4].setVisible(false);
        pellets[18][4] = true;
        pelletLook[18][6].setVisible(false);
        pellets[18][4] = true;
        pelletLook[9][5].setVisible(false);
        pellets[9][5] = true;
        pelletLook[9][7].setVisible(false);
        pellets[9][7] = true;

        PacMan.extraPaint(walls);

        JLabel[][] floor = new JLabel[19][11];

        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 11; y++) {
                tiles[x][y] = new Movement(x, y);
            }
        }
        this.setLayout(null);
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setSize(800, 800);

        ghost[0] = new JLabel();
        ghost[1] = new JLabel();
        ghost[2] = new JLabel();
        ghost[3] = new JLabel();

        ghost[0].setBounds(60 * 9 + 40,300 + 40,60,60);
        ghost[0].setBackground(Color.red);
        ghost[0].setOpaque(true);

        ghost[1].setBounds(60 * 9 + 40,300 + 40,60,60);
        ghost[1].setBackground(Color.blue);
        ghost[1].setOpaque(true);

        ghost[2].setBounds(60 * 9 + 40,300 + 40,60,60);
        ghost[2].setBackground(Color.orange);
        ghost[2].setOpaque(true);

        ghost[3].setBounds(60 * 9 + 40,300 + 40,60,60);
        ghost[3].setBackground(Color.pink);
        ghost[3].setOpaque(true);

        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 11; y++) {
                floor[x][y] = new JLabel();
                //floor[x][y].setIconTextGap(3);
                //int xx = x;
                //int yy = y;
                //floor[x][y].addActionListener(e -> floor[xx][yy].setBackground(Color.black));
            }
        }

        int megaextra = 0;
        for (int x = 0; x < 19; x++) {
            int extra = 0;
            for (int y = 0; y < 11; y++) {
                floor[x][y].setBounds(x * 60 + 40,y * 60 + 40,60,60);
                //floor[x][y].setText((x * 60 + 40 + megaextra) + " " + (y * 60 + 40 + extra));
                floor[x][y].setBackground(Color.gray);
                floor[x][y].setOpaque(true);
                extra++;
            }
            megaextra++;
        }

        label = new JLabel();
        label.setBounds(60 * 9 + 40,60 * 8 + 40,60,60);
        label.setBackground(Color.yellow);
        label.setOpaque(true);



        for (int x = 0; x < maxwall; x++) {
            this.add(walls[x]);
        }

        this.add(ghost[0]);
        this.add(ghost[1]);
        this.add(ghost[2]);
        this.add(ghost[3]);
        this.add(label);

        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 11; y++) {
                this.add(pelletLook[x][y]);
                this.add(floor[x][y]);
            }
        }
    }

    public static void extraPaint(JLabel[] walls) {
        walls[0].setBounds(100 ,100 ,122 ,2);

        walls[1].setBounds(280 ,40 ,2 ,60);

        walls[2].setBounds(60 * 15 + 40 ,40 ,2 ,60);

        walls[3].setBounds(340 ,100 ,240 ,2);

        walls[4].setBounds(640 ,100 ,240 ,2);

        walls[5].setBounds(60 * 16 + 40 ,100 ,120 ,2);

        walls[6].setBounds(100 ,100 ,2 ,120);

        walls[7].setBounds(60 * 10 + 40 ,100 ,2 ,180);

        walls[8].setBounds(60 * 9 + 40 ,100 ,2 ,180);

        walls[9].setBounds(60 * 18 + 40 ,100 ,2 ,120);

        walls[10].setBounds(160 ,160 ,360,2);

        walls[11].setBounds(60 * 11 + 40 ,160 ,360,2);

        walls[12].setBounds(60 * 5 + 40,160 ,2,60);

        walls[13].setBounds(60 * 8 + 40,160 ,2,60);

        walls[14].setBounds(60 * 11 + 40 ,160 ,2,60);

        walls[15].setBounds(60 * 14 + 40 ,160 ,2,60);

        walls[16].setBounds(60 * 2 + 40 ,60 * 3 + 40 ,2,60 * 6);

        walls[17].setBounds(40 ,60 * 4 + 40 ,60 ,60);

        walls[18].setBounds(40 ,60 * 6 + 40 ,60 ,60);

        walls[19].setBounds(60 * 3 + 40 ,60 * 3 + 40 ,2 ,180);

        walls[20].setBounds(60 * 3 + 40 ,60 * 3 + 40 ,60 ,2);

        walls[21].setBounds(100 ,60 * 8 + 40 ,2 ,120);

        walls[22].setBounds(60 * 3 + 40 ,60 * 3 + 40 ,60 ,2);

        walls[23].setBounds(100 ,60 * 10 + 40 ,60 ,2);

        walls[24].setBounds(60 * 2 + 40 ,60 * 9 + 40 ,60 ,2);

        walls[25].setBounds(60 * 4 + 40 ,60 * 4 + 40 ,2 ,180);

        walls[26].setBounds(60 * 4 + 40 ,60 * 4 + 40 ,60 ,2);

        walls[27].setBounds(60 * 4 + 40 ,60 * 4 + 40 ,2 ,180);

        walls[28].setBounds(60 * 3 + 40 ,60 * 7 + 40 ,60 ,2);

        walls[29].setBounds(60 * 3 + 40 ,60 * 8 + 40 ,60 ,2);

        walls[30].setBounds(60 * 4 + 40 ,60 * 8 + 40 ,2 ,60);

        walls[31].setBounds(60 * 5 + 40 ,60 * 5 + 40 ,2 ,60 * 4);

        walls[32].setBounds(60 * 3 + 40 ,60 * 10 + 40 ,2 ,60);

        walls[33].setBounds(60 * 5 + 40 ,60 * 9 + 40 ,60 ,2);

        walls[34].setBounds(60 * 6 + 40 ,60 * 4 + 40 ,2 ,60);

        walls[35].setBounds(60 * 5 + 40 ,60 * 5 + 40 ,60 ,2);

        walls[36].setBounds(60 * 6 + 40 ,60 * 6 + 40 ,2 ,120);

        walls[37].setBounds(60 * 6 + 40 ,60 * 3 + 40 ,60 ,2);

        walls[38].setBounds(60 * 7 + 40 ,60 * 3 + 40 ,2 ,120);

        walls[39].setBounds(60 * 8 + 40 ,60 * 4 + 40 ,2 ,120);

        walls[40].setBounds(60 * 7 + 40 ,60 * 6 + 40 ,2 ,120);

        walls[41].setBounds(60 * 7 + 40 ,60 * 8 + 40 ,60 ,2);

        walls[42].setBounds(60 * 7 + 40 ,60 * 9 + 40 ,120 ,2);

        walls[43].setBounds(60 * 8 + 40 ,60 * 10 + 40 ,2 ,60);

        walls[44].setBounds(60 * 9 + 40 ,60 * 9 + 40 ,2 ,60);

        walls[45].setBounds(60 * 8 + 40 ,60 * 7 + 40 ,180 ,2);

        walls[46].setBounds(60 * 9 + 40 ,60 * 7 + 40 ,60 ,60);

        walls[47].setBounds(60 * 9 + 40 ,60 * 5 + 40 ,60 ,60);

        walls[48].setBounds(60 * 4 + 40 ,60 * 10 + 40 ,180 ,2);

        walls[49].setBounds(60 * 10 + 40 ,60 * 9 + 40 ,120 ,2);

        walls[50].setBounds(60 * 11 + 40 ,60 * 10 + 40 ,2 ,60);

        walls[51].setBounds(60 * 11 + 40 ,60 * 4 + 40 ,2 ,120);

        walls[52].setBounds(60 * 12 + 40 ,60 * 6 + 40 ,2 ,120);

        walls[53].setBounds(60 * 11 + 40 ,60 * 8 + 40 ,60 ,2);

        walls[54].setBounds(60 * 13 + 40 ,60 * 6 + 40 ,2 ,120);

        walls[55].setBounds(60 * 12 + 40 ,60 * 10 + 40 ,180 ,2);

        walls[56].setBounds(60 * 13 + 40 ,60 * 6 + 40 ,2 ,120);

        walls[57].setBounds(60 * 10 + 40 ,60 * 9 + 40 ,2 ,60);

        walls[58].setBounds(60 * 12 + 40 ,60 * 3 + 40 ,2 ,120);

        walls[59].setBounds(60 * 12 + 40 ,60 * 3 + 40 ,60 ,2);

        walls[60].setBounds(60 * 13 + 40 ,60 * 4 + 40 ,2 ,60);

        walls[61].setBounds(60 * 13 + 40 ,60 * 5 + 40 ,60 ,2);

        walls[62].setBounds(60 * 14 + 40 ,60 * 5 + 40 ,2 ,240);

        walls[63].setBounds(60 * 13 + 40 ,60 * 9 + 40 ,60 ,2);

        walls[64].setBounds(60 * 14 + 40 ,60 * 4 + 40 ,60 ,2);

        walls[65].setBounds(60 * 15 + 40 ,60 * 4 + 40 ,2 ,180);

        walls[66].setBounds(60 * 15 + 40 ,60 * 7 + 40 ,60 ,2);

        walls[67].setBounds(60 * 15 + 40 ,60 * 8 + 40 ,2 ,60);

        walls[68].setBounds(60 * 15 + 40 ,60 * 8 + 40 ,60 ,2);

        walls[69].setBounds(60 * 15 + 40 ,60 * 8 + 40 ,2 ,60);

        walls[70].setBounds(60 * 15 + 40 ,60 * 3 + 40 ,60 ,2);

        walls[71].setBounds(60 * 16 + 40 ,60 * 3 + 40 ,2 ,180);

        walls[72].setBounds(60 * 17 + 40 ,60 * 3 + 40 ,2 ,60 * 6);

        walls[73].setBounds(60 * 16 + 40 ,60 * 9 + 40 ,60 ,2);

        walls[74].setBounds(60 * 16 + 40 ,60 * 9 + 40 ,60 ,2);

        walls[75].setBounds(60 * 16 + 40 ,60 * 10 + 40 ,2 ,60);

        walls[76].setBounds(60 * 17 + 40 ,60 * 10 + 40 ,60 ,2);

        walls[77].setBounds(60 * 18 + 40 ,60 * 8 + 40 ,2 ,120);

        walls[78].setBounds(60 * 18 + 40 ,60 * 4 + 40 ,60 ,60);

        walls[79].setBounds(60 * 18 + 40 ,60 * 6 + 40 ,60 ,60);

        for (int x = 0; x < 80; x++) {
            walls[x].setOpaque(true);
            walls[x].setBackground(Color.black);
        }
    }

    public static void main (String[] args) {
        new PacMan();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int PosX = (PacX - 40) / 60;
        int PosY = (PacY - 40) / 60;

        if (!pellets[PosX][PosY]) {
            pelletCount ++;
        }
        pellets[PosX][PosY] = true;

        if (GameStatis == 0) {
            switch (e.getKeyChar()) {
                case 'w': {
                    if (tiles[PosX][PosY].moveUp()) {
                        label.setLocation(label.getX(), label.getY() - change);
                        PacY -= change;
                    }
                    break;
                }
                case 'a':
                    if (PacX == 40 && PacY == 60 * 5 + 40) {
                        PacX = 60 * 18 + 40;
                        label.setBounds(PacX, 60 * 5 + 40, 60, 60);
                    }
                    if (tiles[PosX][PosY].moveLeft()) {
                        label.setLocation(label.getX() - change, label.getY());
                        PacX -= change;
                    }

                    break;
                case 's':
                    if (tiles[PosX][PosY].moveDown()) {
                        label.setLocation(label.getX(), label.getY() + change);
                        PacY += change;
                    }
                    break;
                case 'd':
                    if (tiles[PosX][PosY].moveRight()) {
                        label.setLocation(label.getX() + change, label.getY());
                        PacX += change;
                    }
                    break;
            }
            switch (e.getKeyCode()) {
                case 38:
                    if (tiles[PosX][PosY].moveUp()) {
                        label.setLocation(label.getX(), label.getY() - change);
                        PacY -= change;
                    }
                    break;
                case 37:
                    if (PacX == 40 && PacY == 60 * 5 + 40) {
                        PacX = 60 * 18 + 40;
                        label.setBounds(PacX, 60 * 5 + 40, 60, 60);
                    } else if (tiles[PosX][PosY].moveLeft()) {
                        label.setLocation(label.getX() - change, label.getY());
                        PacX -= change;
                    }
                    break;
                case 40:
                    if (tiles[PosX][PosY].moveDown()) {
                        label.setLocation(label.getX(), label.getY() + change);
                        PacY += change;
                    }
                    break;
                case 39:
                    if (PacX == 60 * 18 + 40 && PacY == 60 * 5 + 40) {
                        PacX = 40;
                        label.setBounds(PacX,60 * 5 + 40,60,60);
                    } else if (tiles[PosX][PosY].moveRight()) {
                        label.setLocation(label.getX() + change, label.getY());
                        PacX += change;
                    }
                    break;
            }
            if (!pellets[PosX][PosY]) {
                pelletCount++;
            }

            pelletLook[PosX][PosY].setVisible(false);
            pellets[PosX][PosY] = true;
            label.setText("" + pelletCount);
            Enemy.move(ghost, PacX, PacY, tiles, Level);

            if (pelletCount >= 203) {
                GameStatis = 1;
            }
        } else if (GameStatis == 1) { // Win
            JOptionPane.showMessageDialog(null, "You Won");
            Level ++;
            PacX = 60 * 9 + 40;
            PacY = 60 * 8 + 40;
            pelletCount = 0;
            label.setText("" + pelletCount);
            for (int x = 0; x < 19; x++) {
                for (int y = 0; y < 11; y++) {
                    pellets[x][y] = false;
                    pelletLook[x][y].setVisible(true);
                }
            }
            label.setBounds(60 * 9 + 40,60 * 8 + 40,60,60);

            for (int x = 0; x < 4; x++) {
                ghost[x].setBounds(60 * 9 + 40, 300 + 40, 60, 60);
            }
            Enemy.reset();
            GameStatis = 0;
            JOptionPane.showMessageDialog(null, "Level " + Level);
        } else if (GameStatis == 2) { // Lose
            PacLives--;
            if (PacLives <= 0) {
                JOptionPane.showMessageDialog(null, "You Lost");
            } else {
                JOptionPane.showMessageDialog(null, "You died, you have " + PacLives + " lives left");
                PacX = 60 * 9 + 40;
                PacY = 60 * 8 + 40;
                label.setBounds(60 * 9 + 40,60 * 8 + 40,60,60);

                for (int x = 0; x < 4; x++) {
                    ghost[x].setBounds(60 * 9 + 40, 300 + 40, 60, 60);
                }
                Enemy.reset();
                GameStatis = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("You got " + e.getKeyChar());
    }

    public static void Die() {
        GameStatis = 2;
    }


}
