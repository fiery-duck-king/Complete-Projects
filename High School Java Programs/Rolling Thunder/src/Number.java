import java.awt.*;

public class Number {
    int x;
    int y;
    int Num;
    int Size;
    int Timer;
    boolean Evil;

    Number(Dice D) {
        x = D.X();
        y = D.Y();
        Num = D.Numb();
        Size = 5;
        Timer = 650;
        Evil = D.BossHit();
    }

    public boolean Evil() {
        return Evil;
    }
    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }
    public int Num() {
        return Num;
    }

    public boolean Delete() {
        return Timer < 0;
    }

    public void Draw(Graphics2D d) {
        if (Size < 15) {
            Size += 1;
        } else {
            Timer -= 25;
        }
        switch (Num) {
            case 1 -> drawOne(d);
            case 2 -> drawTwo(d);
            case 3 -> drawThree(d);
            case 4 -> drawFour(d);
            case 5 -> drawFive(d);
            case 6 -> drawSix(d);

        }
    }

    public void drawOne(Graphics2D d) {
        d.fillRect(x + (Size * 5), y, (Size / 5), (Size * 5));
        d.fillRect(x + (Size * 5), y + (Size * 5), (Size / 5), (Size * 5));
    }
    public void drawTwo(Graphics2D d) {
        d.fillRect(x, y, (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y, (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 5), (Size * 5), (Size / 5));
        d.fillRect(x, y + (Size * 5), (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 10), (Size * 5), (Size / 5));
    }

    public void drawThree(Graphics2D d) {
        d.fillRect(x, y, (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y, (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 5), (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y + (Size * 5), (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 10), (Size * 5), (Size / 5));
    }

    public void drawFour(Graphics2D d) {
        d.fillRect(x, y, (Size / 5), (Size * 5));
        d.fillRect(x + (Size * 5), y, (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 5), (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y + (Size * 5), (Size / 5), (Size * 5));
    }

    public void drawFive(Graphics2D d) {
        d.fillRect(x, y, (Size * 5), 2);
        d.fillRect(x, y, (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 5), (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y + (Size * 5), (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 10), (Size * 5), (Size / 5));
    }

    public void drawSix(Graphics2D d) {
        d.fillRect(x, y, (Size * 5), (Size / 5));
        d.fillRect(x, y, (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 5), (Size * 5), (Size / 5));
        d.fillRect(x + (Size * 5), y + (Size * 5), (Size / 5), (Size * 5));
        d.fillRect(x, y + (Size * 10), (Size * 5), (Size / 5));
        d.fillRect(x, y + (Size * 5), (Size / 5), (Size * 5));
    }

}
