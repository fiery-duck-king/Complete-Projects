import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze extends JFrame {
    int PlayerXLocation = 0;
    int PlayerYLocation = 0;
    static int Size = 20;
    static boolean[][] MazeInfo;
    static ArrayList<int[]> Order;
    JButton[][] MazeTiles = new JButton[Size][Size];
    ArrayList<JLabel> Walls = new ArrayList<>();

    Maze() {
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                MazeTiles[x][y] = new JButton();
                MazeTiles[x][y].setBounds((35 * x),(35 * y), 35, 35);
                MazeTiles[x][y].setVisible(true);
                //MazeTiles[x][y].setFocusable(true);
            }
        }

        for (int z = 0; z < Order.size(); z++) {
            MazeTiles[Order.get(z)[0]][Order.get(z)[1]].setText("" + z);
        }

        int Count = 0;
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                if (MazeInfo[x][y]) {
                    MazeTiles[x][y].setBackground(Color.BLACK);
                    //MazeTiles[x][y].setVisible(true);
                    Count ++;
                    //MazeTiles[x][y].setText("" + Count);
                } else {
                    MazeTiles[x][y].setBackground(Color.white);
                }
            }
        }
        System.out.println(Count);
        Walls = Maze.MakeWalls(Walls);

        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        for (int x = 0; x < Walls.size(); x++) {
            Walls.get(x).setOpaque(true);
            this.add(Walls.get(x));
        }
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                //MazeTiles[x][y].setVisible(false);
                this.add(MazeTiles[x][y]);
            }
        }

    }

    public static void main (String[] args) {
        System.out.println("Stared");
        MazeInfo = MazeMaker.MazeMade(Size, Size);
        Order = MazeMaker.Order();
        System.out.println(Arrays.deepToString(MazeInfo));
        Maze LoadMaze = new Maze();
    }

    public static ArrayList<JLabel> MakeWalls(ArrayList<JLabel> Wall) {
        for (int x = 0; x < Size; x++) {
            for (int y = 0; y < Size; y++) {
                Wall.add(new JLabel());
                int Max = Wall.size() - 1;
                Wall.get(Max).setBackground(Color.blue);
                int Rando = (int) (Math.random() * 2);
                if (Rando == 0) {
                    Wall.get(Max).setBounds((35 * x),(35 * y), 2, 35);
                }
                if (Rando == 1) {
                    Wall.get(Max).setBounds((35 * x),(35 * y), 35, 2);
                }
            }
        }





        return Wall;
    }
}
