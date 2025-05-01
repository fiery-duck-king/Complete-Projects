import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements MouseListener, ActionListener, ChangeListener {

    int Startx = 1100 + (int) ((Math.random() * 161) - 80);
    int Starty = 350 + (int) ((Math.random() * 221) - 110);

    int C1 = 75;
    int C2 = 175;
    int C3 = 275;
    int C4 = 375;

    Area Env = new Area();

    boolean BackBoard = true;
    boolean Active = false;

    final int PANEL_WIDTH = 1350;
    final int PANEL_HEIGHT = 750;
    Image ball;
    boolean ShotFired = false;

    Timer timer;
    double Power = 8; // 5    // 1 Velocity per x tiles
    double xV = 0;            // x Velocity
    double yV = 0;            // y Velocity
    int x = 0;                // x - Position
    int y = 250;              // y - Position
    double a = 9.8; // 21     // Acceleration
    double u = .2; // .2      // Friction
    int t = 0;                // Time
    String StringPoints = "";
    int Points = 0;

    Panel() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.white);
        this.addMouseListener(this);
        ball = new ImageIcon("Image/Balls.png").getImage();
        timer = new Timer(25, this);
        timer.start();

//        System.out.println(ball.getWidth(null) / 2);
//        System.out.println(ball.getHeight(null) / 2);

    }

    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D d = (Graphics2D) g;

        d.setPaint(new Color(102, 255, 0));
        d.fillOval(Startx - C4/2, Starty - C4/2, C4,C4);
        d.setPaint(new Color(225, 255, 0));
        d.fillOval(Startx - C3/2, Starty - C3/2, C3,C3);
        d.setPaint(new Color(255, 102, 0));
        d.fillOval(Startx - C2/2, Starty - C2/2, C2,C2);
        d.setPaint(new Color(255, 0, 0));
        d.fillOval(Startx - C1/2, Starty - C1/2, C1,C1);

        //d.setPaint(new Color(252, 3, 219));  Own Color

        try {
            if (Env.RMOL()) {
                d.setPaint(new Color(0, Math.max(Math.min(145 - Env.ROC(), 255), 0), 15)); // Green
            } else {
                d.setPaint(new Color(0, Math.max(Math.min(234 + Env.ROC(), 255), 0), 255)); // Blue
            }
            d.fillOval(Env.ReX(), Env.ReY(), Env.ReW(), Env.ReH());
        } catch (NullPointerException ignored) {}

        d.setPaint(Color.black);
        d.drawString("" + Env.ReA(), 10, 50);
        d.drawString(StringPoints,10,100);

        //double thing = Math.sqrt(sqr(Math.abs(getMousePosition().x - Xcord())) + sqr(Math.abs(getMousePosition().y - Ycord())));

        try {
            int xMousePos = (int) (Xcord() + (getMousePosition().getX() - Xcord()) / 2);
            int yMousePos = (int) (Ycord() + (getMousePosition().getY() - Ycord()) / 2);
            d.drawLine(Xcord(), Ycord(), xMousePos, yMousePos);
        } catch (NullPointerException ignored) {}

        d.drawImage(ball, x, y,null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x >= PANEL_WIDTH - (ball.getWidth(null)) || x <= 0) {
            if (BackBoard) {
                xV = xV * -1;
                x = (x <= 0) ? 0 : PANEL_WIDTH - (ball.getWidth(null));
            } else {
                xV = 0;
                yV = 0;
            }
        }
        if (y >= PANEL_HEIGHT - (ball.getHeight(null)) || y < 0) {
            yV = yV * -1;
            y = (y <= 0) ? 0 : PANEL_HEIGHT - (ball.getHeight(null));
        }

        double placeu = u;
        if (Env.inArea(x, y)) {
            u = Env.ReA();
        }

        x = (int) (x + ((xV == Math.abs(xV)) ? 1 : -1) * Math.max((Math.abs(xV) - ((.5) * a)), 0));
        y = (int) (y + ((yV == Math.abs(yV)) ? 1 : -1) * Math.max((Math.abs(yV) - ((.5) * a)), 0));

        //System.out.println(xV + ": " + t);

        double ut = t * u;

        xV = ((xV == Math.abs(xV)) ? 1 : -1) * Math.max((Math.abs(xV) - ((a * u) * (sqr(ut))) / 2), 0);
        yV = ((yV == Math.abs(yV)) ? 1 : -1) * Math.max((Math.abs(yV) - ((a * u) * (sqr(ut))) / 2), 0);

        u = placeu;

        t++;
        if (xV == 0 && yV == 0) {
            t = 0;
            if (ShotFired) {
                CalculatePoints();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {}
                ShotFired = false;
                Startx = 1100 + (int) ((Math.random() * 81) - 40);
                Starty = 350 + (int) ((Math.random() * 81) - 40);
                x = 0;
                y = 250 + (int) (Math.random() * 201 - 100);
                Env.Reset(u);
            }
        }
        repaint();
    }

    public int Xcord() {
        return x + ball.getWidth(null) / 2;
    }
    public int Ycord() {
        return y + ball.getHeight(null) / 2;
    }

    public void Speed(int xA, int yA) {
        xV = xA / Power;
        yV = yA / Power;
        ShotFired = true;
    }

    int PastPoints = 0;
    int Repeat = 0;

    public void CalculatePoints() {
        int addPoints = 0;
        int NextHigher = 10;
        int AddingPoints;
        double Checker = Math.sqrt(sqr((Startx - Xcord())) + sqr((Starty - Ycord())));
        //System.out.println(Checker);
        if (Checker < C1 / 2.0) {
            addPoints = 425;
            NextHigher = -1;
        } else if (Checker < C2 / 2.0) {
            addPoints = 125;
            NextHigher = 425;
        } else if (Checker < C3 / 2.0) {
            addPoints = 35;
            NextHigher = 125;
        } else if (Checker < C4 / 2.0) {
            addPoints = 10;
            NextHigher = 35;
        }
        if (addPoints == PastPoints) {
            Repeat++;
            AddingPoints = (int) Math.min(addPoints + (Math.pow(1.31, Repeat) - 1) * addPoints, (NextHigher == -1) ? (addPoints + (Math.pow(1.31, Repeat) - 1) * addPoints) : NextHigher);
            Points += AddingPoints;
        } else {
            AddingPoints = addPoints;
            PastPoints = addPoints;
            Points += PastPoints;
            Repeat = 0;
        }
        StringPoints = "" + Points + " + " + AddingPoints;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Active) {
            Speed((int) getMousePosition().getX() - Xcord(), (int) getMousePosition().getY() - Ycord());
            Active = false;
        } else {
            if (Math.abs(getMousePosition().getX() - Xcord()) < 25 && Math.abs(getMousePosition().getY() - Ycord()) < 25) {
                Active = true;
            }
        }
    }

    boolean PlayingClip = false;

    @Override
    public void mouseReleased(MouseEvent e) {
        if (getMousePosition().getX() < 20 && getMousePosition().getY() < 30) {
            //System.out.println("Working");
            frame = new JFrame("Slider Demo");
            panel = new JPanel();

            Cu = new JLabel();
            uS = new JSlider(0,100,(int) (u * 100));
            uS.setPaintTicks(true);
            uS.setPaintTrack(true);
            uS.setMinorTickSpacing(1);
            uS.setMajorTickSpacing(10);
            uS.setPreferredSize(new Dimension(300, 100));
            uS.addChangeListener(this);
            Cu.setText("u : " + uS.getValue() / 100.0);

            Ca = new JLabel();
            aS = new JSlider(0,1000,(int) (a * 10));
            aS.setPaintTicks(true);
            aS.setPaintTrack(true);
            aS.setMinorTickSpacing(100);
            aS.setMajorTickSpacing(1000);
            aS.setPreferredSize(new Dimension(300, 100));
            aS.addChangeListener(this);
            aS.setLocation(300, 100);
            Ca.setText("a : " + aS.getValue() / 10.0);

            Cp = new JLabel();
            pS = new JSlider(0,200,(int) Power * 10);
            pS.setPaintTicks(true);
            pS.setPaintTrack(true);
            pS.setMinorTickSpacing(10);
            pS.setMajorTickSpacing(50);
            pS.setPreferredSize(new Dimension(300, 100));
            pS.setLocation(600, 200);
            pS.addChangeListener(this);
            Cp.setText("p : " + pS.getValue() / 10);

            panel.add(uS);
            panel.add(aS);
            panel.add(pS);
            panel.add(Cu);
            panel.add(Ca);
            panel.add(Cp);
            frame.add(panel);
            frame.setSize(420, 420);
            frame.setVisible(true);
        } else if (Math.sqrt(sqr((Startx - getMousePosition().x)) + sqr((Starty - getMousePosition().y))) < C1 / 2.0) {
            if (PlayingClip) {
                Stop();
                PlayingClip = false;
            } else {
                try {
                    SetRickRoll();
                    Start();
                    PlayingClip = true;
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public double sqr (double la) {
        return la * la;
    }


    JFrame frame;
    JPanel panel;
    JLabel Cu;
    JLabel Ca;
    JLabel Cp;
    JSlider uS;
    JSlider aS;
    JSlider pS;

    @Override
    public void stateChanged(ChangeEvent e) {
        Cu.setText("u : " + uS.getValue() / 100.0);
        u = uS.getValue() / 100.0;
        Ca.setText("a : " + aS.getValue() / 10.0);
        a = aS.getValue() / 10.0;
        Cp.setText("p : " + pS.getValue() / 10.0);
        Power = pS.getValue() / 10.0;
    }

    Clip clip;

    public void SetRickRoll() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Image/SuperUp.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public void Start() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }
    public void Stop() {
        clip.stop();
    }
}
