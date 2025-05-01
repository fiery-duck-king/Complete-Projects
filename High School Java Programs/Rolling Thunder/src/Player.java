import java.awt.*;

public class Player {
    int x;
    int y;
    int width;
    int height;
    int Speed;
    int HP = 25;

    int Count = 0;
    int MaxCount = 0;
    int DashSpeed = 250;
    double Angle;
    boolean Dash;
    int Burst;

    int DashTimer;

    Player() {
        x = 100;
        y = 100;
        width = 50;
        height = 50;
        Speed = 30;
        Dash = false;
        DashTimer = 0;
    }

    public boolean Alive() {
        return HP > 0;
    }

    public void Draw(Graphics2D d) {
        if (Alive()) {
            if (DashTimer <= 0) {
                d.setPaint(Color.black);
            } else {
                d.setPaint(Color.red);
            }
        }
        d.fillOval(x - (width / 2), y - (height / 2), width, height);
        d.setPaint(Color.black);
    }

    public void MoveUp() {
        y -= Speed;
        OOB(1);
    }
    public void MoveDown() {
        y += Speed;
        OOB(2);
    }
    public void MoveRight() {
        x += Speed;
        OOB(3);
    }
    public void MoveLeft() {
        x -= Speed;
        OOB(4);
    }

    public void Hit() {
        if (!Dashing()) {
            HP--;
        }
    }

    public void Lower() {
        if (DashTimer > 0) {
            DashTimer --;
        }
    }

    public void Dash(double Ang) {
        if (DashTimer <= 0) {
            Angle = Ang;
            Count = 4;
            MaxCount = Count;
            Dash = true;
            DashTimer = 30;
        }
    }
    public void ForcedDash(double Ang) {
        Angle = Ang;
        Count = 4;
        MaxCount = Count;
        Dash = true;
    }
    public void FinDash() {
        if (Count > 0) {
            x += DashSpeed * Math.cos((Angle * Math.PI) / 180) / MaxCount;
            y += DashSpeed * Math.sin((Angle * Math.PI) / 180) / MaxCount;
            Count--;
        } else {
            Dash = false;
        }
        while (x > 1350) {
            x -= 10;
        }
        while (x < 0) {
            x += 10;
        }
        while (y > 750) {
            y -= 10;
        }
        while (y < 0) {
            y += 10;
        }
    }
    public void Touch(Boss BS) {
        if (Math.sqrt(Math.pow(x - BS.X(), 2) + Math.pow(y - BS.Y(), 2)) < BS.Size()) {
            double c, d;
            c = (x - BS.X()); // x
            d = (x - BS.Y()); // y

            int Ang = (int) (Math.atan((d) / (c)) * 180 / Math.PI);
            if (c != Math.abs(c)) {
                Ang += 180;
            }
            ForcedDash(Ang);
        }
    }

    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }
    public int W() {
        return width;
    }

    public boolean Dashing() {
        return Dash;
    }

    public int DashSpeed() {
        return DashSpeed;
    }

    public double Angle() {
        return Angle;
    }

    public void OOB(int oof) {
        if (x > 1350 || x < 0) {
            switch (oof) {
                case 3 -> x -= Speed;
                case 4 -> x += Speed;
            }
        }
        if (y > 750 || y < 0) {
            switch (oof) {
                case 1 -> y += Speed;
                case 2 -> y -= Speed;
            }
        }
    }

    public void Bursting() {
        if (Burst > 0) {
            Burst--;
        }
    }

    public boolean Burst() {
        if (Burst <= 0) {
            Burst = (int) ((Math.random() * 10) * 10) + 120;
            return true;
        } else return false;
    }

    public int HPR() {
        return (int) (W() * HP / 25.0);
    }

}
