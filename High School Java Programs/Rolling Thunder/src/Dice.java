import java.awt.*;

public class Dice {
    int x;
    int y;
    int width;
    int heigth;

    int XV;
    int YV;
    double MoveAngle;
    boolean DrawNum;

    boolean Smacked = false;
    boolean InstaKill = false;
    boolean Evil = false;
    int Born = 20;
    boolean Burst = false;

    int MaxDice;
    int DiceFace;
    int Angle;

    Dice(int X, int Y) {
        x = X;
        y = Y;
        int oof = (int) ((Math.random() * 3) + 2) * 10;
        width = oof;
        heigth = oof;
        MaxDice = (Math.random() * 20 < 1) ? 20 : 6;
        DrawNum = false;
        DiceFace = (int) (Math.random() * MaxDice) + 1;
        Angle = 0;
        MoveAngle = Math.random() * 359;
        XV = (int) Math.round(50 * Math.cos((MoveAngle * Math.PI) / 180));
        YV = (int) Math.round(50 * Math.sin((MoveAngle * Math.PI) / 180));
    }

    Dice(int X, int Y, Player P) {
        x = X;
        y = Y;
        int oof = (int) ((Math.random() * 3) + 2) * 10;
        width = oof;
        heigth = oof;
        MaxDice = (Math.random() * 20 < 1) ? 20 : 6;
        DrawNum = false;
        DiceFace = (int) (Math.random() * MaxDice) + 1;
        Angle = 0;

        double c, d;
        try {
            c = (X - P.X()); // x
            d = (Y - P.Y()); // y
        } catch (NullPointerException set) {
            c = Math.random() * 50 - 25;
            d = Math.random() * 50 - 25;
        }
        int Ang = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
        if (c != Math.abs(c)) {
            Ang += 180;
        }
        Ang += 180;
        MoveAngle = Ang;
        XV = (int) Math.round(50 * Math.cos((MoveAngle * Math.PI) / 180));
        YV = (int) Math.round(50 * Math.sin((MoveAngle * Math.PI) / 180));
    }

    public void Move() {
        x += XV / 4;
        y += YV / 4;
        if (XV == 0 && YV == 0 && Smacked) {
            DrawNum = true;
        }
        if (Smacked) {
            if (XV != 0 || YV != 0) {
                XV = (int) (XV * .9);
                YV = (int) (YV * .9);
                Angle += 10;
            }
        } else {
            Angle += 10;
        }
        if (x > 1350 || x < 0) {
            XV *= -1;
        }
        if (y > 750 || y < 0) {
            YV *= -1;
        }
    }

    public void Draw(Graphics2D d) {
        if (MaxDice == 20) {
            d.setPaint(Color.yellow);
        } else {
            d.setPaint(Color.black);
        }
        double Ang = (Angle * Math.PI) / 180;
        int[] XP = new int[]{x - (int) (width * Math.cos(Ang)), x - (int) (width * Math.cos(Ang + (Math.PI / 2))), x + (int) (width * Math.cos(Ang)), x + (int) (width * Math.cos(Ang + (Math.PI / 2)))};
        int[] YP = new int[]{y - (int) (heigth * Math.sin(Ang)), y - (int) (heigth * Math.sin(Ang + (Math.PI / 2))), y + (int) (heigth * Math.sin(Ang)), y + (int) (heigth * Math.sin(Ang + (Math.PI / 2)))};
        d.fillPolygon(XP, YP, 4);
        d.setPaint(Color.black);
    }

    public boolean Smoke() {
        return DrawNum;
    }

    public void Touching(Player Play, Boss B) {
        if (Math.sqrt(Math.pow(Play.X() - x, 2) + Math.pow(Play.Y() - y, 2)) < width + Play.W()) {
            if (Play.Dashing()) {
                if (!Smacked && Born <= 0) {
                    MoveAngle = Play.Angle();
                    XV = (int) Math.round(Play.DashSpeed() * Math.cos((MoveAngle * Math.PI) / 180));
                    YV = (int) Math.round(Play.DashSpeed() * Math.sin((MoveAngle * Math.PI) / 180));
                    Smacked = true;
                    Evil = false;
                    if (Play.Burst()) {
                        Burst = true;
                    }
                }
            }
        }
        if (Math.sqrt(Math.pow(B.X() - x, 2) + Math.pow(B.Y() - y, 2)) < width + B.Size()) {
            if (Born <= 0) {
                MoveAngle = Play.Angle();
                XV = (int) Math.round(Play.DashSpeed() * Math.cos((MoveAngle * Math.PI) / 180));
                YV = (int) Math.round(Play.DashSpeed() * Math.sin((MoveAngle * Math.PI) / 180));
                if (!Smacked) {
                    Smacked = true;
                    Evil = true;
                }
            }
        }
        if (Born > 0) {
            Born--;
        }
    }

    public boolean BossHit() {
        return Evil;
    }

    public boolean Die() {
        return InstaKill;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public int Size() {
        return width;
    }

    public int Numb() {
        return DiceFace;
    }

    public boolean Bursted() {
        return Burst;
    }

}
