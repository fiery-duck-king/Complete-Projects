import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.*;

public class WallMaze extends JFrame implements KeyListener {
    boolean[] LooksCool = new boolean[4];

    int Sight = 3;
    int TorchSight = 4;
    int LimitedSight = 0;
    int TorchTime = 0;
    int InvisTime = 0;
    boolean MegaTorch = false;
    boolean ActiveTorch = false;
    static boolean Killed = false;
    boolean EasyMode = true;
    int EnemyInvis = 0;
    int TorchSabo = 0;
    int PlayerStun = 0;
    int TorchLimit = 4;
    ArrayList<int[]> SoundPast = new ArrayList<>();
    Boolean[] CanGo = new Boolean[4];

    static int Size = 20;
    static ArrayList<JLabel> Wall = new ArrayList<>();
    static int[][] Path = new int[Size][Size];
    static boolean[][] MoveRight = new boolean[Size][Size];
    static boolean[][] MoveUp = new boolean[Size][Size];
    static boolean[][] MoveLeft = new boolean[Size][Size];
    static boolean[][] MoveDown = new boolean[Size][Size];
    static ArrayList<int[]> Past = new ArrayList<>();
    JLabel[][] Floor = new JLabel[Size][Size];
    JLabel Player = new JLabel();
    JLabel MegaFloor = new JLabel();
    JLabel[] Enemy = new JLabel[4];
    int HeadStart = (Size / 2);
    ArrayList<int[]> Count = new ArrayList<>();
    int[] PastEnemyDirection = new int[Enemy.length];
    JLabel Torch = new JLabel();
    JLabel[] PowerUp = new JLabel[6]; // 4 - 4
    int[] PowerUpStats = new int[6]; // Powerup's Stats
    int[] PowerUpCount = new int[6]; // Types of Powerups on the Field
    JButton[] Tab = new JButton[7]; // Different Types of PowerUp's
    int[] EnemyStunned = new int[Enemy.length];


    WallMaze() {
        for (int x = 0; x < 4; x++) {
            CanGo[x] = true;
        }
        Arrays.fill(PowerUpCount, 0);
        for (int x = 0; x < PowerUp.length; x++) {
            PowerUp[x] = new JLabel();
        }
        for (int x = 0; x < Tab.length; x++) {
            Tab[x] = new JButton();
        }
        for (int x = 0; x < Enemy.length; x++) {
            Enemy[x] = new JLabel();
            EnemyStunned[x] = 0;
            PastEnemyDirection[x] = -1;
        }
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                int Rando = (int) (Math.random() * 2);
                int Rand0 = (int) (Math.random() * 5);
                if (Rando == 0 || Rand0 == 0) {
                    Path[x][y]++;
                    try {
                        Path[x + 1][y]++;
                    } catch (IndexOutOfBoundsException ignore) {}
                    if (Path[x][y] < 4) {
                        Wall.add(new JLabel());
                        MoveRight[x][y] = true;
                        try {
                            MoveLeft[x + 1][y] = true;
                        } catch (IndexOutOfBoundsException ignore) {}
                        Wall.get(Wall.size() - 1).setBackground(Color.black);
                        Wall.get(Wall.size() - 1).setBounds((35 * (x + 1)), (35 * y), 2, 35);
                    } else {
                        Path[x][y]--;
                        try {
                            Path[x + 1][y]--;
                        } catch (IndexOutOfBoundsException ignore) {}
                    }
                }
                if (Rando == 1 || Rand0 == 1) {
                    Path[x][y]++;
                    try {
                        Path[x][y + 1]++;
                    } catch (IndexOutOfBoundsException ignore) {}
                    if (Path[x][y] < 4) {
                        MoveDown[x][y] = true;
                        try {
                            MoveUp[x][y + 1] = true;
                        } catch (IndexOutOfBoundsException ignore) {}
                        Wall.add(new JLabel());
                        Wall.get(Wall.size() - 1).setBackground(Color.black);
                        Wall.get(Wall.size() - 1).setBounds((35 * x), (35 * (y + 1)), 35, 2);
                    } else {
                        Path[x][y]--;
                        try {
                            Path[x][y + 1]--;
                        } catch (IndexOutOfBoundsException ignore) {}
                    }
                }
            }
        }

        ArrayList<int[]> NoTouch = new ArrayList<>();
        while (WallMaze.MoveTest(0,0) < Size * Size) {
        //for (int a = 0; a < 10; a ++) {
            Past.clear();
            int Space = WallMaze.MoveTest(0, 0);
            //System.out.println(Space);
            //System.out.println(toString(Past));
            int Rando;
            int[] Cords = new int[]{0,0};

            for (int z = 0; z < Size * 2; z++) {
                int x = Math.min(z, Size - 1);
                int y = (Math.max(0, z - Size));
                int Limit = x;
                while (y <= Limit) {
                    boolean Try = true;
                    for (int A = 0; A < Past.size(); A++) {
                        if (Arrays.equals(Past.get(A), new int[]{x, y})) {
                            Try = false;
                            break;
                        }
                    }
                    //System.out.println("[" + x + "," + y + "] was a " + Try);
                    if (Try) {
                        boolean Touchable = true;
                        for (int b = 0; b < NoTouch.size(); b++) {
                            if (Arrays.equals(NoTouch.get(b), new int[]{x, y})) {
                                Touchable = false;
                                break;
                            }
                        }
                        if (Touchable) {
                            Cords = new int[]{x, y};
                            //System.out.println(Arrays.toString(Cords));
                            y = Size * 10;
                            z = Size * 10;
                        }
                    }
                    y++;
                    x--;
                }
            }
            if (Arrays.equals(Cords, new int[]{0,0}) && Space != 1) {
                NoTouch.clear();
            }
            boolean TryAgain = true;
            int FailCount = 0;
            boolean Rand0 = true;
            boolean Rand1 = true;
            boolean Rand2 = true;
            boolean Rand3 = true;

            while (TryAgain) {
                Past.clear();
                TryAgain = false;
                Rando = (int) (Math.random() * 2);
                if (Rando == 0 && Rand0) {
                    if (MoveUp[Cords[0]][Cords[1]]) {
                        MoveUp[Cords[0]][Cords[1]] = false;
                        try {
                            MoveDown[Cords[0]][Cords[1] - 1] = false;
                        } catch (IndexOutOfBoundsException ignore) {}
                        if (WallMaze.MoveTest(0, 0) > Space) {
                            for (int x = 0; x < Wall.size(); x++) {
                                if (Wall.get(x).getX() / 35 == Cords[0] && Wall.get(x).getY() / 35 == Cords[1] && Wall.get(x).getHeight() == 2) {
                                    Wall.get(x).setBackground(Color.red);
                                    Wall.get(x).setVisible(false);
                                    Wall.remove(x);
                                    break;
                                }
                            }
                        } else {
                            MoveUp[Cords[0]][Cords[1]] = true;
                            try {
                                MoveDown[Cords[0]][Cords[1] - 1] = true;
                            } catch (IndexOutOfBoundsException ignore) {}
                            TryAgain = true;
                            Rand0 = false;
                            FailCount++;
                        }
                    } else {
                        TryAgain = true;
                        Rand0 = false;
                        FailCount++;
                    }
                }
                if (Rando == 1 && Rand1) {
                    if (MoveLeft[Cords[0]][Cords[1]]) {
                        MoveLeft[Cords[0]][Cords[1]] = false;
                        try {
                            MoveRight[Cords[0] - 1][Cords[1]] = false;
                        } catch (IndexOutOfBoundsException ignore) {}
                        if (WallMaze.MoveTest(0, 0) > Space) {
                            for (int x = 0; x < Wall.size(); x++) {
                                if (Wall.get(x).getX() / 35 == Cords[0] && Wall.get(x).getY() / 35 == Cords[1] && Wall.get(x).getHeight() == 35) {
                                    Wall.get(x).setBackground(Color.red);
                                    Wall.get(x).setVisible(false);
                                    Wall.remove(x);
                                    break;
                                }
                            }
                        } else {
                            MoveLeft[Cords[0]][Cords[1]] = true;
                            try {
                                MoveRight[Cords[0] - 1][Cords[1]] = true;
                            } catch (IndexOutOfBoundsException ignore) {}
                            TryAgain = true;
                            FailCount++;
                            Rand1 = false;
                        }
                    } else {
                        TryAgain = true;
                        FailCount++;
                        Rand1 = false;
                    }
                }
                /*
                if (Rando == 2 && Rand2) {
                    if (MoveDown[Cords[0]][Cords[1]]) {
                        MoveDown[Cords[0]][Cords[1]] = false;
                        try {
                            MoveUp[Cords[0]][Cords[1] + 1] = false;
                        } catch (IndexOutOfBoundsException ignore) {}
                        if (WallMaze.MoveTest(0, 0) > Space) {
                            for (int x = 0; x < Wall.size(); x++) {
                                if (Wall.get(x).getX() / 35 == Cords[0] && (Wall.get(x).getY() / 35) + 1 == Cords[1] && Wall.get(x).getHeight() == 2) {
                                    Wall.get(x).setBackground(Color.red);
                                }
                            }
                        } else {
                            MoveDown[Cords[0]][Cords[1]] = true;
                            try {
                                MoveUp[Cords[0]][Cords[1] + 1] = true;
                            } catch (IndexOutOfBoundsException ignore) {}
                            TryAgain = true;
                            FailCount++;
                            Rand2 = false;
                        }
                    } else {
                        TryAgain = true;
                        FailCount++;
                        Rand2 = false;
                    }
                }

                if (Rando == 3 && Rand3) {
                    if (MoveRight[Cords[0]][Cords[1]]) {
                        MoveRight[Cords[0]][Cords[1]] = false;
                        try {
                            MoveLeft[Cords[0] + 1][Cords[1]] = false;
                        } catch (IndexOutOfBoundsException ignore) {}
                        if (WallMaze.MoveTest(0, 0) > Space) {
                            for (int x = 0; x < Wall.size(); x++) {
                                if ((Wall.get(x).getX() / 35) + 1 == Cords[0] && Wall.get(x).getY() / 35 == Cords[1] && Wall.get(x).getHeight() == 35) {
                                    Wall.get(x).setBackground(Color.red);
                                }
                            }
                        } else {
                            MoveRight[Cords[0]][Cords[1]] = true;
                            try {
                                MoveLeft[Cords[0] + 1][Cords[1]] = true;
                            } catch (IndexOutOfBoundsException ignore) {}
                            TryAgain = true;
                            FailCount++;
                            Rand3 = false;
                        }
                    } else {
                        TryAgain = true;
                        FailCount++;
                        Rand3 = false;
                    }
                }
                */
                if (FailCount >= 2) {
                    TryAgain = false;
                }
            }
            //System.out.println(Arrays.toString(Cords) + " failed " + FailCount + " times");
            NoTouch.add(Cords);
            Past.clear();
        }
        //System.out.println(WallMaze.MoveTest(0, 0));

        Wall.add(new JLabel());
        Wall.get(Wall.size() - 1).setBounds(0,0,35*Size,2);
        Wall.get(Wall.size() - 1).setBackground(Color.BLACK);
        Wall.add(new JLabel());
        Wall.get(Wall.size() - 1).setBounds(0,0,2,35*Size);
        Wall.get(Wall.size() - 1).setBackground(Color.BLACK);
        Wall.add(new JLabel());
        Wall.get(Wall.size() - 1).setBounds(0,35*Size,35*Size,2);
        Wall.get(Wall.size() - 1).setBackground(Color.BLACK);
        Wall.add(new JLabel());
        Wall.get(Wall.size() - 1).setBounds(35*Size,0,2,35*Size);
        Wall.get(Wall.size() - 1).setBackground(Color.BLACK);

        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                Floor[x][y] = new JLabel();
                Floor[x][y].setBounds(35 * x,35 * y,35,35);
                Floor[x][y].setBackground(Color.black);
                Floor[x][y].setOpaque(true);
            }
        }

        Player.setBounds(0,0,35,35);
        Player.setBackground(Color.blue);
        Player.setOpaque(true);

        Enemy[0].setBounds(0,0,35,35);
        Enemy[0].setBackground(Color.red);
        Enemy[0].setOpaque(true);

        Enemy[1].setBounds((Size - 1) * 35, (Size - 1) * 35,35,35);
        Enemy[1].setBackground(Color.red);
        Enemy[1].setOpaque(true);

        Enemy[2].setBounds((Size - 1) * 35,0,35,35);
        Enemy[2].setBackground(Color.red);
        Enemy[2].setOpaque(true);

        Enemy[3].setBounds(0, (Size - 1) * 35,35,35);
        Enemy[3].setBackground(Color.red);
        Enemy[3].setOpaque(true);

        MegaFloor.setBounds(0,0,Size * 35,Size * 35);
        MegaFloor.setBackground(Color.WHITE);
        MegaFloor.setOpaque(true);

        Torch.setBounds(10,10,15,15);
        Torch.setBackground(Color.yellow);
        Torch.setOpaque(true);

        for (int x = 0; x < Size; x++) {
            MoveUp[x][0] = true;
            MoveDown[x][Size - 1] = true;
            MoveLeft[0][x] = true;
            MoveRight[Size - 1][x] = true;
        }

        for (int x = 0; x < Floor.length; x++) {
            for (int y = 0; y < Floor[x].length; y++) {
                int xChange = Math.abs((Player.getX() / 35) - x);
                int yChange = Math.abs((Player.getY() / 35) - y);
                //Floor[x][y].setVisible(false);
                if (xChange + yChange < Sight) {
                    Floor[x][y].setBackground(Color.white);
                    Floor[x][y].setLocation(35 * x , 35 * y);
                } else if (xChange + yChange == Sight) {
                    Floor[x][y].setBackground(Color.black);
                    Floor[x][y].setLocation((35 * x) - 5 * ((Player.getX() / 35) - x), (35 * y) - 5 * ((Player.getY() / 35) - y));
                } else {
                    Floor[x][y].setBackground(Color.black);
                    Floor[x][y].setLocation(35 * x, 35 * y);
                }
            }
        }

        //(int) (Math.random() * PowerUpCount.length)
        PowerUpCount[((int) (Math.random() * 2) == 0) ? 2 : 4]++; // Start with a random PowerUp

        for (int x = 0; x < PowerUp.length; x++) {
            PowerUp[x].setBounds((35 * (int) (Math.random() * Size)) + 10, (35 * (int) (Math.random() * Size)) + 10, 15, 15);
            if (PowerUp[x].getX() == (35 * (Size - 1)) + 10 && PowerUp[x].getY() == 10) {
                PowerUp[x].setLocation(35 * (Size - 2), 10);
            }
            if (PowerUp[x].getY() == (35 * (Size - 1)) + 10 && PowerUp[x].getX() == 10) {
                PowerUp[x].setLocation(10, 35 * (Size - 2));
            }
            if (PowerUp[x].getX() == (35 * (Size - 1)) + 10 && PowerUp[x].getY() == (35 * (Size - 1)) + 10) {
                PowerUp[x].setLocation((35 * (Size - 1) + 10) - 35 * (1 + (int) (Math.random() * Size / 3)), (35 * (Size - 1) + 10) - 35 * (1 + (int) (Math.random() * Size / 3)));
            }
            if (PowerUp[x].getY() == 10 && PowerUp[x].getX() == 10) {
                PowerUp[x].setLocation(10 + 35 * (1 + (int) (Math.random() * Size / 3)), 10 + 35 * (1 + (int) (Math.random() * Size / 3)));
            }
            PowerUpStats[x] = (int) (Math.random() * PowerUpStats.length);//Tab.length);
            PowerUp[x].setOpaque(true);
            switch (PowerUpStats[x]) {
                case 0 -> PowerUp[x].setBackground(Color.MAGENTA);
                case 1 -> PowerUp[x].setBackground(Color.PINK);
                case 2 -> PowerUp[x].setBackground(Color.GREEN);
                case 3 -> PowerUp[x].setBackground(Color.CYAN);
                case 4 -> PowerUp[x].setBackground(Color.BLUE);
                case 5 -> PowerUp[x].setBackground(Color.red);
                //case 6 -> PowerUp[x].setBackground(Color.yellow);
            }

            if (x < Tab.length) {
                Tab[x].setBounds((Size * 35) + 30, 50 * x + 70, 200, 50);
                Tab[0].setText("Torch Fuel: " + PowerUpCount[0]);
                Tab[1].setText("Googles: " + PowerUpCount[1]);
                Tab[2].setText("Invis: " + PowerUpCount[2]);
                Tab[3].setText("Mega Torch: " + PowerUpCount[3]);
                Tab[4].setText("Stun: " + PowerUpCount[4]);
                Tab[5].setText("Enemy TP: " + PowerUpCount[5]);

                Tab[0].setBackground(Color.MAGENTA);
                Tab[1].setBackground(Color.PINK);
                Tab[2].setBackground(Color.GREEN);
                Tab[3].setBackground(Color.cyan);
                Tab[4].setBackground(Color.BLUE);
                Tab[5].setBackground(Color.red);

                Tab[0].setVisible(true);
                Tab[1].setVisible(true);
                Tab[2].setVisible(true);
                Tab[3].setVisible(true);
                Tab[4].setVisible(true);
                Tab[5].setVisible(true);

                Tab[Tab.length - 1].setBackground(Color.yellow);
                Tab[Tab.length - 1].setBounds((Size * 35) + 30, 20, 200, 50);
                Tab[Tab.length - 1].setText("Torch Count: " + TorchLimit);
                Tab[Tab.length - 1].setOpaque(true);
            }
            // Torch Refresh // Magenta
            // Sight Increase // Pink
            // Invis // Green
            // MegaTorch // Cyan
            // Stun // Blue
        }

        this.setLayout(null);
        this.setSize(975, 800);
        this.setVisible(true);
        this.addKeyListener(this);
        for (JLabel jLabel : Wall) {
            jLabel.setOpaque(true);
            this.add(jLabel);
        }
        this.add(Player);
        this.add(Torch);
        for (JLabel[] x : Floor) {
            for (JLabel y : x) {
                this.add(y);
            }
        }
        for (JLabel x : Enemy) {
            this.add(x);
        }
        for (JLabel x : PowerUp) {
            this.add(x);
        }
        for (JButton x : Tab) {
            this.add(x);
        }
        this.add(MegaFloor);
    }

    private String toString(ArrayList<int[]> past) {
        String la = "";
        int count = 0;
        for (int[] The: past) {
            if (count == past.size() - 1) {
                la += "[" + The[0] + "],["+ The[1] + "]";
            } else {
                la += "[" + The[0] + "],[" + The[1] + "] - ";
            }
            count ++;
        }
        return la;
    }

    public static void main(String[] args) {
        WallMaze run = new WallMaze();
    }

    public static int MoveTest(int xPos, int yPos) {
        int Count = 1;
        Past.add(new int[]{xPos,yPos});
        boolean fail = true;
        if (!(MoveUp[xPos][yPos]) && yPos - 1 > -1) {
            for (int[] ints : Past) {
                if (Arrays.equals(ints, new int[]{xPos, yPos - 1})) {
                    fail = false;
                    break;
                }
            }
            if (fail) {
                Count += MoveTest(xPos, yPos - 1);
            }
        }
        fail = true;
        if (!(MoveDown[xPos][yPos]) && yPos + 1 < Size) {
            for (int[] ints : Past) {
                if (Arrays.equals(ints, new int[]{xPos, yPos + 1})) {
                    fail = false;
                    break;
                }
            }
            if (fail) {
                Count += MoveTest(xPos, yPos + 1);
            }
        }
        fail = true;
        if (!(MoveRight[xPos][yPos]) && xPos + 1 < Size) {
            for (int[] ints : Past) {
                if (Arrays.equals(ints, new int[]{xPos + 1, yPos})) {
                    fail = false;
                    break;
                }
            }
            if (fail) {
                Count += MoveTest(xPos + 1, yPos);
            }
        }
        fail = true;
        if (!(MoveLeft[xPos][yPos]) && xPos - 1 > -1) {
            for (int[] ints : Past) {
                if (Arrays.equals(ints, new int[]{xPos - 1, yPos})) {
                    fail = false;
                    break;
                }
            }
            if (fail) {
                Count += MoveTest(xPos - 1, yPos);
            }
        }
        return Count;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { // The Best One
        if (!(Killed) || EasyMode) {
            int MrRandom = (PlayerStun > 0) ? (int) (Math.random() * 100) : 100;
            if (!(PlayerStun > 0) && MrRandom > 60) {
                switch (e.getKeyChar()) {
                    case 'w': {
                        if (!(MoveUp[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX(), Player.getY() - 35);
                        }
                        break;
                    }
                    case 'a': {
                        if (!(MoveLeft[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX() - 35, Player.getY());
                        }
                        break;
                    }
                    case 's': {
                        if (!(MoveDown[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX(), Player.getY() + 35);
                        }
                        break;
                    }
                    case 'd': {
                        if (!(MoveRight[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX() + 35, Player.getY());
                        }
                        break;
                    }
                    case 'q': {
                        if (TorchLimit > 0) {
                            Arrays.fill(EnemyStunned, +1);
                            TorchTime = 75;
                            TorchSight = 4;
                            Torch.setVisible(true);
                            Torch.setLocation(Player.getX() + 10, Player.getY() + 10);
                            if (MegaTorch) {
                                MegaTorch = false;
                                ActiveTorch = true;
                                TorchTime = 100;
                            } else {
                                ActiveTorch = false;
                            }
                            TorchLimit--;
                        } else {
                            System.out.println(TorchLimit);
                            //TorchLimit++;
                        }
                        break;
                    }
                }
                switch (e.getKeyCode()) {
                    case 38: {
                        if (!(MoveUp[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX(), Player.getY() - 35);
                        }
                        break;
                    }
                    case 37: {
                        if (!(MoveLeft[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX() - 35, Player.getY());
                        }
                        break;
                    }
                    case 40: {
                        if (!(MoveDown[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX(), Player.getY() + 35);
                        }
                        break;
                    }
                    case 39: {
                        if (!(MoveRight[Player.getX() / 35][Player.getY() / 35])) {
                            Player.setLocation(Player.getX() + 35, Player.getY());
                        }
                        break;
                    }
                }
            } else {
                boolean La = switch (e.getKeyCode()) {
                    case 38, 37, 40, 39 -> true;
                    default -> false;
                } || switch (e.getKeyChar()) {
                    case 'w', 'a', 's', 'd' -> true;
                    default -> false;
                };

                if (La) {
                    boolean Moved = true;
                    while (Moved) {
                        int Rando = (int) (Math.random() * 4);
                        switch (Rando) {
                            case 0: {
                                if (!(MoveUp[Player.getX() / 35][Player.getY() / 35])) {
                                    Player.setLocation(Player.getX(), Player.getY() - 35);
                                    Moved = false;
                                }
                                break;
                            }
                            case 1: {
                                if (!(MoveLeft[Player.getX() / 35][Player.getY() / 35])) {
                                    Player.setLocation(Player.getX() - 35, Player.getY());
                                    Moved = false;
                                }
                                break;
                            }
                            case 2: {
                                if (!(MoveDown[Player.getX() / 35][Player.getY() / 35])) {
                                    Player.setLocation(Player.getX(), Player.getY() + 35);
                                    Moved = false;
                                }
                                break;
                            }
                            case 3: {
                                if (!(MoveRight[Player.getX() / 35][Player.getY() / 35])) {
                                    Player.setLocation(Player.getX() + 35, Player.getY());
                                    Moved = false;
                                }
                                break;
                            }
                        }
                    }
                    PlayerStun--;
                }
            }

            switch (e.getKeyCode()) {
                case 49: {
                    if (PowerUpCount[0] > 0) { // Torch Refresh // Magenta
                        if (TorchTime > 50) {
                            TorchTime = (int) (85 * 1.15);
                            TorchSight = 4;
                        } else {
                            TorchSight = 4;
                            TorchTime = 75;
                        }
                        PowerUpCount[0]--;
                    }
                    break;
                }
                case 50: {
                    if (PowerUpCount[1] > 0) { // Sight Increase // Pink
                        Sight += 2;
                        LimitedSight = (int) (20 * 1.25);
                        PowerUpCount[1]--;
                    }
                    break;
                }
                case 51: {
                    if (PowerUpCount[2] > 0) { // Invis // Green
                        InvisTime = (int) (12 * 1.25);
                        PowerUpCount[2]--;
                    }
                    break;
                }
                case 52: {
                    if (PowerUpCount[3] > 0) { // MegaTorch // Cyan
                        MegaTorch = true;
                        TorchLimit++;
                        PowerUpCount[3]--;
                    }
                    break;
                }
                case 53: {
                    if (PowerUpCount[4] > 0) { // Stun // Blue
                        HeadStart = (int) (10 * 1.25 * 1.2); // 15
                        PowerUpCount[4]--;
                    }
                    break;
                }
                case 54: {
                    //PowerUpCount[5]++;
                    if (PowerUpCount[5] > 0) { // TP // red
                        for (int x = 0; x < Enemy.length; x++) {
                            boolean y = true;
                            while (y) {
                                if (Math.abs(Enemy[x].getX() - Player.getX()) / 35 < Math.max(1,Sight) && Math.abs(Enemy[x].getY() - Player.getY()) / 35 < Math.max(1, Sight)) {
                                    Enemy[x].setLocation((int) (Math.random() * Size) * 35, (int) (Math.random() * Size) * 35);
                                } else {
                                    y = false;
                                }
                            }
                        }

                        PowerUpCount[5]--;
                    }
                    break;
                }
            }

            for (int x = 0; x < Floor.length; x++) {
                for (int y = 0; y < Floor[x].length; y++) {
                    int xChange = Math.abs((Player.getX() / 35) - x);
                    int yChange = Math.abs((Player.getY() / 35) - y);
                    int xTorch = Math.abs(((Torch.getX() - 10) / 35) - x);
                    int yTorch = Math.abs(((Torch.getY() - 10) / 35) - y);

                    if (xChange + yChange < Sight || (xTorch + yTorch < TorchSight && Torch.isVisible())) {
                        Floor[x][y].setVisible(false);
                        Floor[x][y].setLocation(35 * x, 35 * y);
                    } else if (xChange + yChange == Sight || (xTorch + yTorch == TorchSight && Torch.isVisible())) {
                        Floor[x][y].setVisible(true);
                        Floor[x][y].setBackground(Color.black);
                        if (xChange + yChange == Sight) {
                            Floor[x][y].setLocation((35 * x) - (int) Math.max(((15.0 + Sight) / Sight), 4) * ((Player.getX() / 35) - x), (35 * y) - (int) Math.max(((15.0 + Sight) / Sight), 4) * ((Player.getY() / 35) - y));
                        } else {
                            Floor[x][y].setLocation((35 * x) - (int) Math.max(((15.0 + TorchSight) / TorchSight), 4) * (((Torch.getX() - 10) / 35) - x), (35 * y) - (int) Math.max(((15.0 + TorchSight) / TorchSight), 4) * (((Torch.getY() - 10) / 35) - y));
                        }
                    } else {
                        Floor[x][y].setVisible(true);
                        Floor[x][y].setBackground(Color.black);
                        Floor[x][y].setLocation(35 * x, 35 * y);
                    }
                    if (Player.getX() / 35 == Size - 1 && Player.getY() / 35 == Size - 1) {
                        Floor[x][y].setVisible(false);
                    }
                }
            }
            if (e.getKeyChar() == 'r') {
                for (int x = 0; x < Floor.length; x++) {
                    for (int y = 0; y < Floor[x].length; y++) {
                        Floor[x][y].setVisible(false);
                    }
                }
            }
            if (e.getKeyChar() == 'i') {
                EnemyInvis = 15;
            }
            if (HeadStart < 1) {
                for (int d = 0; d < Enemy.length; d++) {
                    if (EnemyStunned[d] <= 0) {
                        Enemy[d].setVisible(true);
                        Count.clear();
                        boolean Wonder = true;
                        int[] Direction = new int[]{-1, -1};
                        Arrays.fill(LooksCool,true);
                        if (d % 2 == 0) {
                            SightTiles(Enemy[d].getX() / 35, Enemy[d].getY() / 35, 0, false);
                        } else {
                            Enemy[d].setBackground(Color.pink);
//                        System.out.println("\nEnemy " + d);
                            Direction = SoundDirection(Enemy[d].getX() / 35, Enemy[d].getY() / 35, (PlayerStun > 0) ? 6 : 4, true);
                            Count.add(new int[]{1, 1});
                        }
                        for (int x = 0; x < Count.size(); x++) {
                            if ((Arrays.equals(Count.get(x), new int[]{Player.getX() / 35, Player.getY() / 35}) || Direction[1] == 0) && Wonder && InvisTime <= 0) {
                                PastEnemyDirection[d] = -1;
                                if ((Direction[1] == 0) ? Direction[0] == 3 : (Enemy[d].getX() - Player.getX() < 0)) {
                                    Enemy[d].setLocation(Enemy[d].getX() + 35, Enemy[d].getY());
                                    Wonder = false;
                                }
                                if ((Direction[1] == 0) ? Direction[0] == 2 : (Enemy[d].getX() - Player.getX() > 0)) {
                                    Enemy[d].setLocation(Enemy[d].getX() - 35, Enemy[d].getY());
                                    Wonder = false;
                                }
                                if ((Direction[1] == 0) ? Direction[0] == 1 : (Enemy[d].getY() - Player.getY() < 0)) {
                                    Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() + 35);
                                    Wonder = false;
                                }
                                if ((Direction[1] == 0) ? Direction[0] == 0 : (Enemy[d].getY() - Player.getY() > 0)) {
                                    Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() - 35);
                                    Wonder = false;
                                }
                            }
                        }
                        for (int x = 0; x < Count.size(); x++) {
                            if ((Arrays.equals(Count.get(x), new int[]{(Torch.getX() - 10) / 35, (Torch.getY() - 10) / 35}) || Direction[1] == 1) && Wonder && Torch.isVisible()) {
                                PastEnemyDirection[d] = -1;
                                if ((Direction[1] != 1) ? (Enemy[d].getX() - Torch.getX() < 0) : Direction[0] == 3) {
                                    Enemy[d].setLocation(Enemy[d].getX() + 35, Enemy[d].getY());
                                    Wonder = false;
                                }
                                if ((Direction[1] != 1) ? (Enemy[d].getX() - Torch.getX() > 0) : Direction[0] == 2) {
                                    Enemy[d].setLocation(Enemy[d].getX() - 35, Enemy[d].getY());
                                    Wonder = false;
                                }
                                if ((Direction[1] != 1) ? (Enemy[d].getY() - Torch.getY() < 0) : Direction[0] == 1) {
                                    Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() + 35);
                                    Wonder = false;
                                }
                                if ((Direction[1] != 1) ? (Enemy[d].getY() - Torch.getY() > 0) : Direction[0] == 0) {
                                    Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() - 35);
                                    Wonder = false;
                                }
                            }
                        }
                        for (int x = 0; x < Count.size(); x++) {
                            for (int y = 0; y < PowerUp.length; y++) {
                                if ((Arrays.equals(Count.get(x), new int[]{(PowerUp[y].getX() - 10) / 35, (PowerUp[y].getY() - 10) / 35}) || Direction[1] == 2) && Wonder && PowerUp[y].isVisible()) {
                                    PastEnemyDirection[d] = -1;
                                    if ((Direction[1] != 2) ? (Enemy[d].getX() - PowerUp[y].getX() < 0) : Direction[0] == 3) {
                                        Enemy[d].setLocation(Enemy[d].getX() + 35, Enemy[d].getY());
                                        Wonder = false;
                                    }
                                    if ((Direction[1] != 2) ? (Enemy[d].getX() - PowerUp[y].getX() > 0) : Direction[0] == 2) {
                                        Enemy[d].setLocation(Enemy[d].getX() - 35, Enemy[d].getY());
                                        Wonder = false;
                                    }
                                    if ((Direction[1] != 2) ? (Enemy[d].getY() - PowerUp[y].getY() < 0) : Direction[0] == 1) {
                                        Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() + 35);
                                        Wonder = false;
                                    }
                                    if ((Direction[1] != 2) ? (Enemy[d].getY() - PowerUp[y].getY() > 0) : Direction[0] == 0) {
                                        Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() - 35);
                                        Wonder = false;
                                    }
                                }
                            }
                        }
                        int xChange = Math.abs(Player.getX() - Enemy[d].getX()) / 35;
                        int yChange = Math.abs(Player.getY() - Enemy[d].getY()) / 35;
                        int xTorch = xChange;
                        int yTorch = yChange;
                        if (Torch.isVisible()) {
                            xTorch = Math.abs(Torch.getX() - 10 - Enemy[d].getX()) / 35;
                            yTorch = Math.abs(Torch.getY() - 10 - Enemy[d].getY()) / 35;
                        }
                        boolean Repeat = false;
                        boolean ExtraRepeat = false;
                        //boolean MegaRepeat = false;
                        if (d % 2 == 0) {
                            Repeat = (xChange + yChange > Sight + 3 && xTorch + yTorch > TorchSight + 2);
                            ExtraRepeat = (xChange + yChange > Sight + 5 && xTorch + yTorch > TorchSight + 4);
                            //MegaRepeat = (xChange + yChange > Sight + 9 && xTorch + yTorch > TorchSight + 7);
                        }
                        for (int r = 0; r < 2; r++) {
                            if (Wonder) {
                                Wonder = false;
                                boolean Retry = true;

                                if (d % 2 == 0) {
                                    SightTiles(Enemy[d].getX() / 35, Enemy[d].getY() / 35, 0, false);
                                }

                                boolean Up = !(MoveUp[Enemy[d].getX() / 35][Enemy[d].getY() / 35]) && CanGo[0] && LooksCool[0];
                                boolean Down = !(MoveDown[Enemy[d].getX() / 35][Enemy[d].getY() / 35]) && CanGo[1] && LooksCool[1];
                                boolean Left = !(MoveLeft[Enemy[d].getX() / 35][Enemy[d].getY() / 35]) && CanGo[2] && LooksCool[2];
                                boolean Right = !(MoveRight[Enemy[d].getX() / 35][Enemy[d].getY() / 35]) && CanGo[3] && LooksCool[3];
                                int Count = 0;
                                if (Up) {
                                    Count++;
                                }
                                if (Down) {
                                    Count++;
                                }
                                if (Left) {
                                    Count++;
                                }
                                if (Right) {
                                    Count++;
                                }
                                if (Count == 1) {
                                    PastEnemyDirection[d] = -1;
                                }

                                int Paster = -1;
                                while (Retry) {
                                    Retry = false;
                                    int Rando = (int) (Math.random() * 4);
                                    if (Rando == 0) {
                                        if (Up && PastEnemyDirection[d] != 2) {
                                            Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() - 35);
                                        } else {
                                            Retry = true;
                                        }
                                    }
                                    if (Rando == 1) {
                                        if (Left && PastEnemyDirection[d] != 3) {
                                            Enemy[d].setLocation(Enemy[d].getX() - 35, Enemy[d].getY());
                                        } else {
                                            Retry = true;
                                        }
                                    }
                                    if (Rando == 2) {
                                        if (Down && PastEnemyDirection[d] != 0) {
                                            Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() + 35);
                                        } else {
                                            Retry = true;
                                        }
                                    }
                                    if (Rando == 3) {
                                        if (Right && PastEnemyDirection[d] != 1) {
                                            Enemy[d].setLocation(Enemy[d].getX() + 35, Enemy[d].getY());
                                        } else {
                                            Retry = true;
                                        }
                                    }
                                    Paster = Rando;

                                    if (!(Up || Down || Left || Right)) {
                                        Retry = false;
                                    }
                                }
                                PastEnemyDirection[d] = Paster;
                                if (Repeat) {
                                    Repeat = false;
                                    Wonder = true;
                                } else if (ExtraRepeat) {
                                    ExtraRepeat = false;
                                    Wonder = true;
                                } // else if (MegaRepeat) {
//                                    MegaRepeat = false;
//                                    Wonder = true;
//                                }
//                                    if (Player.getY() - Enemy[d].getY() < 0 && Math.abs(Player.getX() - Enemy[d].getX()) < Math.abs(Player.getY() - Enemy[d].getY())) {
//                                        if (Enemy[d].getY() / 35 != 0) {
//                                            Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() - 35);
//                                        }
//                                    }
//                                    if (Player.getX() - Enemy[d].getX() < 0 && Math.abs(Player.getX() - Enemy[d].getX()) > Math.abs(Player.getY() - Enemy[d].getY())) {
//                                        if (Enemy[d].getX() / 35 != 0) {
//                                            Enemy[d].setLocation(Enemy[d].getX() - 35, Enemy[d].getY());
//                                        }
//                                    }
//                                    if (Player.getY() - Enemy[d].getY() > 0 && Math.abs(Player.getX() - Enemy[d].getX()) < Math.abs(Player.getY() - Enemy[d].getY())) {
//                                        if (Enemy[d].getY() / 35 != Size - 1) {
//                                            Enemy[d].setLocation(Enemy[d].getX(), Enemy[d].getY() + 35);
//                                        }
//                                    }
//                                    if (Player.getX() - Enemy[d].getX() > 0 && Math.abs(Player.getX() - Enemy[d].getX()) > Math.abs(Player.getY() - Enemy[d].getY())) {
//                                        if (Enemy[d].getX() / 35 != Size - 1) {
//                                            Enemy[d].setLocation(Enemy[d].getX() + 35, Enemy[d].getY());
//                                        }
//                                    }
//                                }
                            }
                        }
                        Arrays.fill(CanGo, true);
                        if (Torch.getX() - 10 == Enemy[d].getX() && Torch.getY() - 10 == Enemy[d].getY()) {
                            Torch.setVisible(false);
                            EnemyStunned[d] += 5;
                        }
                        if (Player.getX() == Enemy[d].getX() && Player.getY() == Enemy[d].getY()) {
                            Killed = true;
                        }
                    } else {
                        EnemyStunned[d] --;
                    }
                }
            } else {
                HeadStart--;
            }
            if (TorchTime < 1) { // Torch Burning
                Torch.setVisible(false);
            } else {
                if (ActiveTorch) {
                    TorchTime--;
                    TorchSight = 0;
                    if (TorchTime > 75) {
                        TorchSight++;
                    }
                    if (TorchTime > 45) {
                        TorchSight++;
                    }
                    if (TorchTime > 15) {
                        TorchSight++;
                    }
                    if (TorchTime > 0) {
                        TorchSight++;
                    }
                } else {
                    TorchTime--;
                    TorchSight = 0;
                    if (TorchTime > 50) { // Maybe 55
                        TorchSight++;
                    }
                    if (TorchTime > 30) {
                        TorchSight++;
                    }
                    if (TorchTime > 10) {
                        TorchSight++;
                    }
                    if (TorchTime > 0) {
                        TorchSight++;
                    }
                    if (TorchSabo > 0) {
                        TorchSabo --;
                        TorchSight = Math.max(1,TorchSight - 1);
                    }
                }
            }

            if (LimitedSight < 0) {
                Sight = 3;
                LimitedSight = 0;
            }
            if (LimitedSight > 0) {
                LimitedSight--;
                if (LimitedSight == 0) {
                    LimitedSight = -1;
                }
            }
            if (InvisTime > 0) {
                InvisTime--;
                Player.setBackground(Color.CYAN);
            } else {
                Player.setBackground(Color.BLUE);
            }
            if (EnemyInvis == -1) {
                EnemyInvis = 0;
                for (int x = 0; x < Enemy.length; x++) {
                    Enemy[x].setVisible(true);
                }
                Player.setBackground(Color.BLUE);
            }
            if (EnemyInvis > 0) {
                EnemyInvis--;
                Player.setBackground(Color.GREEN);
                for (int x = 0; x < Enemy.length; x++) {
                    int xTorch = Math.abs(Torch.getX() - 10 - Enemy[x].getX()) / 35;
                    int yTorch = Math.abs(Torch.getY() - 10 - Enemy[x].getY()) / 35;
                    if (xTorch + yTorch <= TorchSight && Torch.isVisible()) {
                        Enemy[x].setVisible(true);
                    } else {
                        Enemy[x].setVisible(false);
                    }
                }
                if (EnemyInvis == 0) {
                    EnemyInvis = -1;
                }
            }

            Tab[0].setText("Torch Fuel: " + PowerUpCount[0]);
            Tab[1].setText("Googles: " + PowerUpCount[1]);
            Tab[2].setText("Invis: " + PowerUpCount[2]);
            Tab[3].setText("Mega Torch: " + PowerUpCount[3]);
            Tab[4].setText("Stun: " + PowerUpCount[4]);
            Tab[5].setText("Enemy TP: " + PowerUpCount[5]);
            Tab[Tab.length - 1].setText("Torch Count: " + TorchLimit);

            for (int x = 0; x < PowerUp.length; x++) {
                if (Player.getX() == PowerUp[x].getX() - 10 && Player.getY() == PowerUp[x].getY() - 10 && PowerUp[x].isVisible()) {
                    PowerUpCount[PowerUpStats[x]]++;
                    NewPowerUp(x);
                }
                for (int y = 0; y < Enemy.length; y++) {
                    if (Enemy[y].getX() == PowerUp[x].getX() - 10 && Enemy[y].getY() == PowerUp[x].getY() - 10 && PowerUp[x].isVisible()) {
                        switch (PowerUpStats[x]) {
                            case 0 -> {
                                TorchTime = (int) (TorchTime * .3) - 5;
                            }
                            case 1 -> {
                                Sight --;
                                LimitedSight = 25;
                            }
                            case 2 -> {
                                EnemyInvis = (int) (130 * .63); // 81
                            }
                            case 3 -> {
                                TorchSabo = 40;
                            }
                            case 4 -> {
                                PlayerStun = (int) (15 * .7); // 10
                            }
                            case 5 -> {
                                Player.setLocation((int) (Math.random() * Size) * 35, (int) (Math.random() * Size) * 35);
                            }
//                            Torch Flush // Magenta
//                            Sight Decrease // Pink
//                            Enemy Invis // Green
//                            Wimpy Torch // Cyan
//                            Player Stun // Blue
                        }
                        NewPowerUp(x);
                    }
                }
            }
            if (Killed) {
                if (EasyMode) {
                    HeadStart = 10;
                    Player.setLocation(0, 0);
                    Enemy[0].setLocation(0, 0);
                    Enemy[1].setLocation((Size - 1) * 35, (Size - 1) * 35);
                    Enemy[2].setLocation((Size - 1) * 35, 0);
                    Enemy[3].setLocation(0, (Size - 1) * 35);
                    Killed = false;
                } else {
                    for (int x = 0; x < Floor.length; x++) {
                        for (int y = 0; y < Floor[x].length; y++) {
                            Floor[x][y].setVisible(false);
                        }
                    }
                    for (JLabel enemy : Enemy) {
                        enemy.setVisible(true);
                    }
                }
            }
        }
    }

    /*
// Torch Refresh // Magenta
// Sight Increase // Pink
// Invis // Green
// MegaTorch // Cyan
// Stun // Blue
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    public int[] SoundDirection(int xPos, int yPos, int Turns, boolean start) {
        if (start) {
            SoundPast.clear();
            SoundPast.add(new int[]{xPos, yPos});
            SoundPast.add(new int[]{xPos, yPos - 1});
            int[] Maybe = new int[]{-1, -1};
            if (!(MoveUp[xPos][yPos])) {
                SoundDirection(xPos, yPos - 1, Turns - 1, false);
                for (int[] x : SoundPast) {
                    if (Arrays.equals(new int[]{Player.getX() / 35, Player.getY() / 35}, x)) {
                        return new int[]{0, 0};
                    }
                    if (Arrays.equals(new int[]{(Torch.getX() - 10) / 35, (Torch.getY() - 10) / 35}, x) && Torch.isVisible()) {
                        Maybe = new int[]{0, 1};
                    }
                    if (Maybe[1] == -1) {
                        for (int y = 0; y < PowerUp.length; y++) {
                            if (Arrays.equals(new int[]{(PowerUp[y].getX() - 10) / 35, (PowerUp[y].getY() - 10) / 35}, x) && PowerUp[y].isVisible()) {
//                                System.out.println("Power Up");
                                Maybe = new int[]{0, 2};
                            }
                        }
                    }
                }
                if (SoundPast.size() < Turns && Maybe[0] == -1 && Maybe[1] == -1) {
//                    System.out.println("Up: " + toString(SoundPast) + " " + Turns);
                    CanGo[0] = false;
                }
            }
            SoundPast.clear();
            SoundPast.add(new int[]{xPos, yPos});
            SoundPast.add(new int[]{xPos, yPos + 1});
            if (!(MoveDown[xPos][yPos])) {
                SoundDirection(xPos, yPos + 1, Turns - 1, false);
                for (int[] x : SoundPast) {
                    if (Arrays.equals(new int[]{Player.getX() / 35, Player.getY() / 35}, x)) {
                        return new int[]{1, 0};
                    }
                    if (Arrays.equals(new int[]{(Torch.getX() - 10) / 35, (Torch.getY() - 10) / 35}, x) && Torch.isVisible()) {
                        Maybe = new int[]{1, 1};
                    }
                    if (Maybe[1] == -1) {
                        for (int y = 0; y < PowerUp.length; y++) {
                            if (Arrays.equals(new int[]{(PowerUp[y].getX() - 10) / 35, (PowerUp[y].getY() - 10) / 35}, x) && PowerUp[y].isVisible()) {
//                                System.out.println("Power Down");
                                Maybe = new int[]{1, 2};
                            }
                        }
                    }
                }
                if (SoundPast.size() < Turns && Maybe[0] == -1 && Maybe[1] == -1) {
//                    System.out.println("Down: " + toString(SoundPast) + " " + Turns);
                    CanGo[1] = false;
                }
            }
            SoundPast.clear();
            SoundPast.add(new int[]{xPos, yPos});
            SoundPast.add(new int[]{xPos - 1, yPos});
            if (!(MoveLeft[xPos][yPos])) {
                SoundDirection(xPos - 1, yPos, Turns - 1, false);
                for (int[] x : SoundPast) {
                    if (Arrays.equals(new int[]{Player.getX() / 35, Player.getY() / 35}, x)) {
                        return new int[]{2, 0};
                    }
                    if (Arrays.equals(new int[]{(Torch.getX() - 10) / 35, (Torch.getY() - 10) / 35}, x) && Torch.isVisible()) {
                        Maybe = new int[]{2, 1};
                    }
                    if (Maybe[1] == -1) {
                        for (int y = 0; y < PowerUp.length; y++) {
                            if (Arrays.equals(new int[]{(PowerUp[y].getX() - 10) / 35, (PowerUp[y].getY() - 10) / 35}, x) && PowerUp[y].isVisible()) {
//                                System.out.println("Power Left");
                                Maybe = new int[]{2, 2};
                            }
                        }
                    }
                }
                if (SoundPast.size() < Turns && Maybe[0] == -1 && Maybe[1] == -1) {
//                    System.out.println("Left: " + toString(SoundPast) + " " + Turns);
                    CanGo[2] = false;
                }
            }
            SoundPast.clear();
            SoundPast.add(new int[]{xPos, yPos});
            SoundPast.add(new int[]{xPos + 1, yPos});
            if (!(MoveRight[xPos][yPos])) {
                SoundDirection(xPos + 1, yPos, Turns - 1, false);
                for (int[] x : SoundPast) {
                    if (Arrays.equals(new int[]{Player.getX() / 35, Player.getY() / 35}, x)) {
                        return new int[]{3, 0};
                    }
                    if (Arrays.equals(new int[]{(Torch.getX() - 10) / 35, (Torch.getY() - 10) / 35}, x) && Torch.isVisible()) {
                        Maybe = new int[]{3, 1};
                    }
                    if (Maybe[1] == -1) {
                        for (int y = 0; y < PowerUp.length; y++) {
                            if (Arrays.equals(new int[]{(PowerUp[y].getX() - 10) / 35, (PowerUp[y].getY() - 10) / 35}, x) && PowerUp[y].isVisible()) {
//                                System.out.println("Power Right");
                                Maybe = new int[]{3, 2};
                            }
                        }
                    }
                }
                if (SoundPast.size() < Turns && Maybe[0] == -1 && Maybe[1] == -1) {
//                    System.out.println("Right: " + toString(SoundPast) + " " + Turns);
                    CanGo[3] = false;
                }
            }
            SoundPast.clear();
            return Maybe;
        } else {
            if (Turns > 0) {
                boolean Good = true;
                if (!(MoveUp[xPos][yPos])) {
                    for (int[] x : SoundPast) {
                        if (Arrays.equals(x, new int[]{xPos, yPos-1})) {
                            Good = false;
                            break;
                        }
                    }
                    if (Good) {
                        SoundPast.add(new int[]{xPos, yPos - 1});
                        SoundDirection(xPos, yPos - 1, Turns - 1, false);
                    }
                }

                Good = true;
                if (!(MoveDown[xPos][yPos])) {
                    for (int[] x : SoundPast) {
                        if (Arrays.equals(x, new int[]{xPos, yPos + 1})) {
                            Good = false;
                            break;
                        }
                    }
                    if (Good) {
                        SoundPast.add(new int[]{xPos, yPos + 1});
                        SoundDirection(xPos, yPos + 1, Turns - 1, false);
                    }
                }

                Good = true;
                if (!(MoveLeft[xPos][yPos])) {
                    for (int[] x : SoundPast) {
                        if (Arrays.equals(x, new int[]{xPos - 1, yPos})) {
                            Good = false;
                            break;
                        }
                    }
                    if (Good) {
                        SoundPast.add(new int[]{xPos - 1, yPos});
                        SoundDirection(xPos - 1, yPos, Turns - 1, false);
                    }
                }

                Good = true;
                if (!(MoveRight[xPos][yPos])) {
                    for (int[] x : SoundPast) {
                        if (Arrays.equals(x, new int[]{xPos + 1, yPos})) {
                            Good = false;
                            break;
                        }
                    }
                    if (Good) {
                        SoundPast.add(new int[]{xPos + 1, yPos});
                        SoundDirection(xPos + 1, yPos, Turns - 1, false);
                    }
                }
            }
            return new int[]{-1, -1};
        }
    }

    public boolean SightTiles(int xPos, int yPos, int Direction, boolean First) {
        boolean Intresting = false;
        if (Direction == 0) {
            LooksCool[0] = SightTiles(xPos, yPos, 1, true);
            LooksCool[1] = SightTiles(xPos, yPos, 2, true);
            LooksCool[2] = SightTiles(xPos, yPos, 3, true);
            LooksCool[3] = SightTiles(xPos, yPos, 4, true);
            //System.out.println(Arrays.toString(LooksCool));
            return false;
        } else {
            if (!(MoveUp[xPos][yPos]) && (!First || Direction == 1)) {
                if (Direction == 1) {
                    Count.add(new int[]{xPos, yPos - 1});
                    Intresting = Intresting || SightTiles(xPos, yPos - 1, Direction, false);
                } else if (Direction != 2){
                    Intresting = true;
                }
            }
            if (!(MoveDown[xPos][yPos]) && (!First || Direction == 2)) {
                if (Direction == 2) {
                    Count.add(new int[]{xPos, yPos + 1});
                    Intresting = Intresting || SightTiles(xPos, yPos + 1, Direction, false);
                } else if (Direction != 1) {
                    Intresting = true;
                }
            }
            if (!(MoveLeft[xPos][yPos]) && (!First || Direction == 3)) {
                if (Direction == 3) {
                    Count.add(new int[]{xPos - 1, yPos});
                    Intresting = Intresting || SightTiles(xPos - 1, yPos, Direction, false);
                } else if (Direction != 4) {
                    Intresting = true;
                }
            }
            if (!(MoveRight[xPos][yPos]) && (!First || Direction == 4)) {
                if (Direction == 4) {
                    Count.add(new int[]{xPos + 1, yPos});
                    Intresting = Intresting || SightTiles(xPos + 1, yPos, Direction, false);
                } else if (Direction != 3) {
                    Intresting = true;
                }
            }
            return Intresting;
        }
    }

    public void NewPowerUp(int Pos) {
        PowerUp[Pos].setLocation((int) (Math.random() * Size) * 35 + 10, (int) (Math.random() * Size) * 35 + 10);
        PowerUp[Pos].setVisible(true);
        PowerUpStats[Pos] = (int) (Math.random() * PowerUpStats.length);//Tab.length);
        switch (PowerUpStats[Pos]) {
            case 0 -> PowerUp[Pos].setBackground(Color.MAGENTA);
            case 1 -> PowerUp[Pos].setBackground(Color.PINK);
            case 2 -> PowerUp[Pos].setBackground(Color.GREEN);
            case 3 -> PowerUp[Pos].setBackground(Color.CYAN);
            case 4 -> PowerUp[Pos].setBackground(Color.BLUE);
            case 5 -> PowerUp[Pos].setBackground(Color.red);
        }
    }
}
