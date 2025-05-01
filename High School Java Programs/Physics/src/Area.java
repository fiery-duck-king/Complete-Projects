public class Area {
    int Width;
    int Heigth;
    int X;
    int Y;
    double Applier;
    double Change;
    boolean MOL;

    public void Reset (double u) {
        int Total = (int) (Math.random() * 200) + 425;
        Width = (int) (Math.random() * 300) + 100;
        Total -= Width;
        Width += (Width % 2 != 0) ? 1 : 0;
        Heigth = Total;
        Heigth += (Heigth % 2 != 0) ? 1 : 0;
        X = (int) (Math.random() * 600) + 200;
        Y = (int) (Math.random() * 400) + 100;
        int Rando = (int) (Math.random() * 3);
        int UD = ((int) (Math.random() * 2) == 0) ? -1 : 1;
        switch (Rando) {
            case 0 -> Applier = Math.max(u + UD * (double) ((int) (Math.random() * 10) + 10) / 100, 0);
            case 1 -> Applier = u * (1 + (((int) (Math.random() * 125) + 20) / 100.0));
            case 2 -> Applier = u - (u * ((int) (Math.random() * 85) + 15) / 100.0);
        }
        Change = (Applier - u) * 500;
        MOL = u < Applier;
    }

    public int ReW() {
        return Width;
    }
    public int ReH() {
        return Heigth;
    }
    public int ReX() {
        return X;
    }
    public int ReY() {
        return Y;
    }
    public double ReA() {
        return Applier;
    }

    public boolean inArea(int x, int y) {
        return (Sqr(X - x) / Sqr(Width / 2.0)) + (Sqr(Y - y) / Sqr(Heigth / 2.0)) < 1;
    }

    public double Sqr(double x) {
        return x * x;
    }

    public boolean RMOL() {
        return MOL;
    }

    public int ROC() {
        return (int) Math.round(Change);
    }
}
