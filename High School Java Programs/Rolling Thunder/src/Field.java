import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Field extends JPanel implements MouseListener, ActionListener, ChangeListener, KeyListener {
    final int PANEL_WIDTH = 1350;
    final int PANEL_HEIGHT = 750;
    Timer timer;
    int up = 0;
    int down = 0;
    int left = 0;
    int right = 0;

    Player Play = new Player();
    Boss BS = new Boss();
    ArrayList<Dice> Die = new ArrayList<>();
    ArrayList<Number> Num = new ArrayList<>();
    ArrayList<Projectile> Pro = new ArrayList<>();

    Field() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.green);
        this.addMouseListener(this);
        this.addKeyListener(this);

        timer = new Timer(25, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D d = (Graphics2D) g;

        if (!Play.Alive()) {
            d.setPaint(Color.blue);
        }
        Play.Draw(d);
        d.setPaint(Color.black);

        for (Dice A : Die) {
            A.Draw(d);
        }
        for (Number N : Num) {
            N.Draw(d);
        }
        for (Projectile P : Pro) {
            P.Draw(d);
        }
        if (!BS.Alive()) {
            d.setPaint(Color.blue);
        } else {
            d.setPaint(Color.DARK_GRAY);
        }
        BS.Draw(d);
        d.fillRect(Play.X() - (Play.W() / 2), Play.Y() - Play.W(), Play.W(), 10);
        d.fillRect(BS.X() - (BS.Size() / 2), BS.Y() - BS.Size(), BS.Size(), 10);
        d.setPaint(Color.red);
        d.fillRect(Play.X() - (Play.W() / 2), Play.Y() - Play.W(), Play.HPR(), 10);
        d.fillRect(BS.X() - (BS.Size() / 2), BS.Y() - BS.Size(), BS.HPR(), 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (up > 0) {
            Play.MoveUp();
            up--;
        }
        if (down > 0) {
            Play.MoveDown();
            down--;
        }
        if (left > 0) {
            Play.MoveLeft();
            left--;
        }
        if (right > 0) {
            Play.MoveRight();
            right--;
        }

        Play.FinDash();


        for (Dice D : Die) {
            D.Touching(Play, BS);
            D.Move();
        }
        for (int a = 0; a < Die.size(); a++) {
            if (Die.get(a).Bursted()) {
                for (int x = 0; x < Die.get(a).Numb(); x++) {
                    Die.add(new Dice(Die.get(a).X(), Die.get(a).Y()));
                }
                Num.add(new Number(Die.get(a)));
                Die.remove(a);
                a--;
            }
            try {
                if (Die.get(a).Smoke()) {
                    Num.add(new Number(Die.get(a)));
                    Die.remove(a);
                    a--;
                }
                if (Die.get(a).Die()) {
                    Die.remove(a);
                    a--;
                }
            } catch (IndexOutOfBoundsException ignored) {}
        }
        for (int a = 0; a < Num.size(); a++) {
            if (Num.get(a).Delete()) {
                for (int r = 0; r < Num.get(a).Num(); r++) {
                    Pro.add(new Projectile(Num.get(a)));
                }
                Num.remove(a);
                a--;
            }
        }
        for (int a = 0; a < Pro.size(); a++) {
            Pro.get(a).Move(BS, Play);
            if (Pro.get(a).HitBoss(BS)) {
                BS.Hit();
                Pro.remove(a);
                a--;
            }
            try {
                for (Dice D : Die) {
                    if (Pro.get(a).HitDice(D)) {
                        Pro.remove(a);
                        a--;
                    }
                }
                if (Pro.get(a).HitPlayer(Play)) {
                    Play.Hit();
                    Pro.remove(a);
                    a--;
                }
            } catch (IndexOutOfBoundsException ignored) {}
        }

        if (BS.Alive()) {
            BS.Move(Play);
            if (BS.Shoot()) {
                Die.add(new Dice(BS.X(), BS.Y(), Play));
            }
        }
        Play.Lower();
        Play.Touch(BS);
        Play.Bursting();
        repaint();
    }

    public void Type(KeyEvent e) {
        if (Play.Alive()) {
            switch (e.getKeyChar()) {
                case 'w': {
                    up++;
                    break;
                }
                case 'a': {
                    left++;
                    break;
                }
                case 's': {
                    down++;
                    break;
                }
                case 'd': {
                    right++;
                    break;
                }
                case 'q', 'e', ' ': {
                    double c, d;
                    try {
                        c = (Play.X() - getMousePosition().getX()); // x
                        d = (Play.Y() - getMousePosition().getY()); // y
                    } catch (NullPointerException set) {
                        c = Math.random() * 50 - 25;
                        d = Math.random() * 50 - 25;
                    }
                    int Ang = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
                    if (c != Math.abs(c)) {
                        Ang += 180;
                    }
                    Ang += 180;
                    Play.Dash(Ang);
                    break;
                }
            }

            switch (e.getKeyCode()) {
                case 38: { // Up
                    up++;
                    break;
                }
                case 37: { // Left
                    left++;
                    break;
                }
                case 40: { // Down
                    down++;
                    break;
                }
                case 39: { // Right
                    right++;
                    break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double c, d;
        try {
            c = (Play.X() - getMousePosition().getX()); // x
            d = (Play.Y() - getMousePosition().getY()); // y
        } catch (NullPointerException set) {
            c = Math.random() * 50 - 25;
            d = Math.random() * 50 - 25;
        }
        int Ang = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
        if (c != Math.abs(c)) {
            Ang += 180;
        }
        Ang += 180;
        Play.Dash(Ang);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}