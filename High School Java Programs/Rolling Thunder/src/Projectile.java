import java.awt.*;

public class Projectile {
    int x;
    int y;
    int Size = 20;
    int Speed = 5;
    double Direction;
    int TimeA;
    boolean Evil;

    Projectile(Number Num) {
        x = Num.X();
        y = Num.Y();
        Direction = Math.random() * 359;
        Evil = Num.Evil();
        for (int r = 0; r < 5; r++) {
            x = x + (int) (Speed * Math.cos((Direction * Math.PI) / 180));
            y = y + (int) (Speed * Math.sin((Direction * Math.PI) / 180));
        }
    }

    public void Draw(Graphics2D d) {
        d.drawOval(x - (Size / 2), y - (Size / 2), Size, Size);
    }

    public void Move(Boss BS, Player P) {
        x = x + (int) (Speed * Math.cos((Direction * Math.PI) / 180));
        y = y + (int) (Speed * Math.sin((Direction * Math.PI) / 180));
        TimeA++;

        double c, d;
        if (Evil) {
            c = (x - P.X()); // x
            d = (y - P.Y()); // y
        } else {
            c = (x - BS.X()); // x
            d = (y - BS.Y()); // y
        }
        int Ang = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
        if (c != Math.abs(c)) {
            Ang += 180;
        }
        Ang += 180;

        int M = 3;
        if (TimeA < 200) {
            M = 10;
            Speed = 10;
        }

        double Sub = Ang - Direction;
        Direction += Math.max(Math.min((Sub * .25), M), -M);

    }

    public boolean HitBoss(Boss BS) {
        return Math.sqrt(Math.pow(x - BS.X(), 2) + Math.pow(y - BS.Y(), 2)) < BS.Size();
    }
    public boolean HitDice(Dice D) {
        return Math.sqrt(Math.pow(x - D.X(), 2) + Math.pow(y - D.Y(), 2)) < D.Size();
    }
    public boolean HitPlayer(Player P) {
        return Math.sqrt(Math.pow(x - P.X(), 2) + Math.pow(y - P.Y(), 2)) < P.W();
    }

}