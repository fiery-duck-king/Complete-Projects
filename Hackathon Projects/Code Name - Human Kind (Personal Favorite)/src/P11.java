import java.awt.*;

public class P11 extends Upgrade {
    // public int ExtraHP;
    // public int Robo;
    // public int ExtraDamage;
    // public int Range;
    // public int Speed;

    P11() {
        Robo = 25;
        ExtraHP = 35;
        ExtraDamage = 5;
        Range = .2f;
        Speed = 75;

        Att.Name = "CPU Overdrive";
        Att.Action = false;
    }
}