import java.awt.*;

public class Boss {

    int HP;
    double XV;
    double YV;
    double Ang;
    int Wait;
    int Pew;

    int x = 1200;
    int y = 300;
    int Size = 150;

    Boss() {
        HP = 100;
        XV = 0;
        YV = 0;
        Ang = 0;
        Wait = 0;
        Pew = 85;
    }

    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }
    public int Size() {
        return (int) (Size * .85);
    }

    public void Hit() {
        HP--;
    }

    public boolean Alive() {
        return HP > 0;
    }

    public boolean Shoot() {
        if (Pew <= 0) {
            Pew = (int) (Math.random() * 5.5) * 10 + 50;
            if (HP < 70) {
                Pew -= 25;
            }
            return true;
        } else {
            Pew--;
            return false;
        }
    }

    public void Move(Player Play) {
        if (Wait <= 0) {
            if (XV == 0 && YV == 0) {
                XV = 30;
                YV = XV;
                Wait = (int) ((Math.random() * 5) + 2) * 10;
                double c, d;
                try {
                    c = (Play.X() - x); // x
                    d = (Play.Y() - y); // y
                } catch (NullPointerException set) {
                    c = Math.random() * 50 - 25;
                    d = Math.random() * 50 - 25;
                }
                int Angle = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
                if (c != Math.abs(c)) {
                    Angle += 180;
                }
                Ang = Angle;
            } else {
                x += (int) (XV * Math.cos(Ang));
                y += (int) (YV * Math.sin(Ang));
                XV *= .95;
                YV *= .95;

                if (Math.abs(XV) <= .1) {
                    XV = 0;
                }
                if (Math.abs(YV) <= .1) {
                    YV = 0;
                }

                if (x > 1350 || x < 0) {
                    XV *= -1;
                }
                if (y > 750 || y < 0) {
                    YV *= -1;
                }
            }
        } else {
            Wait--;
        }
    }

    public int HPR() {
        return (int) (Size() * HP / 100.0);
    }

    public void Draw(Graphics2D d) {
        d.fillOval(x - (Size / 2), y - (Size / 2), Size, Size);
    }
}
