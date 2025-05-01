import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements KeyListener {
    public static void main(String[] args) {
        new Main();
    }

    Field panel;

    Main() {

        panel = new Field();

        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        panel.Type(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}