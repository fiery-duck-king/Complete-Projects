import java.util.ArrayList;

public class P14 extends Upgrade {
    // public int ExtraHP;
    // public int Robo;
    // public int ExtraDamage;
    // public int Range;
    // public int Speed;

    P14() {
        Robo -= 5;
        ExtraHP = 5;
        Speed = 25;
        Att = new Style() {
            @Override
            public boolean TakeOver(Entity Self) {
                boolean Scare = false;
                for (int x = 0; x < GamePanel.Units.size(); x++) {
                    if (Self == GamePanel.Units.get(x) || (Self != GamePanel.player && Self.Good == GamePanel.Units.get(x).Good)) {
                        continue;
                    }

                    float TDis = (float) Math.sqrt(Math.pow(GamePanel.Units.get(x).x - Self.x, 2) + Math.pow(GamePanel.Units.get(x).y - Self.y, 2));
                    if (TDis <= 75 * Self.Range) {
                        Scare = true;
                        Self.Heal(Self, 5);
                    }
                }
                return Scare;
            }

            @Override
            public void Pain(Entity Self, float Amount) {}

            @Override
            public void WhenAttacked(Entity Self, Entity Enemy, int Range) {
                Enemy.Dam(Self, Self.Damage * .5f);
            }

            @Override
            public void Attack(Entity Self, int[] TargetPos, float Range) {}
        };

        Att.Name = "Shower";
        Att.Action = true;
    }
}