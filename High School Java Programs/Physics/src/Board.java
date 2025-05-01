import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Board extends JFrame implements MouseListener {
    public static void main(String[] args) {
        new Board();
    }

    boolean Active = false;

    Panel panel;

    Board() {

        panel = new Panel();
        //panel.addMouseListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        //this.setLayout(null);
        //this.setSize(975, 800);
        //this.addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Active) {
            panel.Speed((int) getMousePosition().getX() - panel.Xcord(), (int) getMousePosition().getY() - panel.Ycord());
            Active = false;
        } else {
            if (Math.abs(getMousePosition().getX() - (panel.Xcord() + 25)) < 25 && Math.abs(getMousePosition().getY() - (panel.Ycord() + 50)) < 25) {
                Active = true;
            }
        }
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
    public int getTheX() {
        return (int) getMousePosition().getX();
    }
    public int getTheY() {
        return (int) getMousePosition().getY();
    }

}
