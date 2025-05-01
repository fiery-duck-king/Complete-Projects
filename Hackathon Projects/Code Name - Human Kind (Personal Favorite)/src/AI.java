public class AI extends Entity {
    public AI(GamePanel Screen, Level Map, boolean isGood) {
        super(Screen, Map);
        Good = isGood;
        // Add Cards
        AllAtt.add(new P1());
        AllAtt.add(new P1());
        AllAtt.add(new P1());
        AllAtt.add(new P4());
        for (int x = 0; x < Screen.Stage; x++) {
            int R = (int) (Math.random() * 6);
            switch (R) {
                case 0 -> AllAtt.add(new P7());
                case 1 -> AllAtt.add(new P8());
                case 2 -> AllAtt.add(new P9());
                case 3 -> AllAtt.add(new P10());
                case 4 -> AllAtt.add(new P11());
                case 5 -> AllAtt.add(new P12());
            };
        }

        AddBuffs();
        SetRPos(Screen, Map);
        Name = isGood ? "Ally" : "Enemy";
    }

    public void SetPos(int xPos, int yPos) {
        System.out.println("Move!");
        x = xPos;
        y = yPos;
    }

    public void UseCard() {
        Active = AllAtt.get((int) (Math.random() * AllAtt.size()));
    }
}
