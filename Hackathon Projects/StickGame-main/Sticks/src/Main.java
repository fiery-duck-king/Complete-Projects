import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        String[] Names;

        while (true) {
            try {
                System.out.println("How Many players are there?");
                int Size = Integer.parseInt(kb.nextLine());
                if (Size <= 0) {
                    Size /= 0; // Throw Error AKA restart
                }

                Names = new String[Size];

                for (int x = 0; x < Size; x++) {
                    System.out.println("Player " + (x + 1) + "'s name? (type \"(bot)\" for that player to be a bot)");
                    Names[x] = kb.nextLine();
                }

                System.out.println("Starting Game");
                break;
            } catch (Exception e) {
                System.out.println("Failure, restarting");
            }
        }

        while(Names.length > 1) {

            int Sticks = (int) (Math.round(Math.pow(2, Names.length) / Names.length)) + 12 + Names.length;
            int TurnOrder = (int) (Math.random() * Names.length);
            System.out.println(Names[TurnOrder] + " will start the game");
            while (true) {
                System.out.println("\n" + Names[TurnOrder] + ", how many sticks would you like take (from 1, 2, or 3) (" + Sticks + (Sticks == 1 ? " stick" : " sticks") + ")");
                int CountOfSticks;
                if (Names[TurnOrder].contains("(bot)")) {
                    CountOfSticks = BotMove(Sticks, Names.length);
                } else {
                    while (true) {
                        try {
                            CountOfSticks = Integer.parseInt(kb.nextLine());
                            if (CountOfSticks > 3 || CountOfSticks < 1) {
                                CountOfSticks /= 0; // Throw Error AKA restart
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }
                }
                System.out.print(Names[TurnOrder] + " removed " + CountOfSticks+ " sticks");
                Sticks -= CountOfSticks;
                if (Sticks <= 0) {
                    System.out.print(" and they are ELIMINATED.");

                    String[] NewNames = new String[Names.length - 1];

                    boolean Switch = false;
                    for (int x = 0; x < Names.length; x++) {
                        if (x != TurnOrder) {
                            NewNames[x - (Switch ? 1 : 0)] = Names[x];
                        } else {
                            Switch = true;
                        }
                    }

                    Names = NewNames;

                    if (Names.length > 1) {
                        System.out.println(" " + (Names.length) + " players remain");
                    }
                    break;
                } else {
                    System.out.println(", and " + Sticks + (Sticks == 1 ? " stick" : " sticks") + " remain!");
                    TurnOrder = (TurnOrder + 1 >= Names.length) ? 0 : TurnOrder + 1;
                }
            }
        }
        System.out.println(" " + Names[0] + " WON! Thanks for playing");
    }

    public static int BotMove(int Amount, int PlayerCount) {
        int Send = (Amount - (PlayerCount - 1)) % 4;
        if (Send == 0) {
            Send = (int) (Math.random() * 3);
        }
        return Math.min(Amount - 1, Send);
    }
}
