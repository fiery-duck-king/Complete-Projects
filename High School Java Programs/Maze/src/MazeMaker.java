import java.util.ArrayList;

public class MazeMaker {
    static ArrayList<int[]> Path = new ArrayList<>();
    public static boolean[][] MazeMade(int xSize, int ySize) {
        int XLocation = 0;
        int YLocation = 0;
        int[] PassMove = new int[]{XLocation,YLocation};
        boolean SolveMaze = true;
        ArrayList<Integer> PastActions = new ArrayList<>();
        ArrayList<Integer> Patterns = new ArrayList<>();
        int SafeFail = 0;
        int Middle = 0;
        int Check = 5;
        while (SolveMaze) {
            int Direction = (Middle > (xSize + ySize) / 4) ? (int) (Math.random() * 7) : (int) (Math.random() * 11); // Up, Down, Left, Right
            Middle ++;
            int Left = 0;
            int Right = 0;
            int Up = 0;
            int Down = 0;
            for (int x = 0; x < Patterns.size(); x++) {
                if (Patterns.get(x) == 0) {
                    Up++;
                }
                if (Patterns.get(x) == 1) {
                    Down++;
                }
                if (Patterns.get(x) == 2) {
                    Right++;
                }
                if (Patterns.get(x) == 3) {
                    Left++;
                }
            }
            if (Direction == 4 || Direction == 5) {
                if (Up < Left && Up < Down && Up < Right) {
                    Direction = 0;
                } else if (Down < Left && Down < Up && Down < Right) {
                    Direction = 1;
                } else if (Left < Down && Left < Up && Left < Right) {
                    Direction = 3;
                } else if (Right < Left && Right < Up && Right < Down) {
                    Direction = 2;
                } else {
                    Direction = (int) (Math.random() * 4);
                }
            }

            if (Direction == 6) {
                if (Up > Left && Up > Down && Up > Right) {
                    Direction = 0;
                } else if (Down > Left && Down > Up && Down > Right) {
                    Direction = 1;
                } else if (Left > Down && Left > Up && Left > Right) {
                    Direction = 3;
                } else if (Right > Left && Right > Up && Right > Down) {
                    Direction = 2;
                } else {
                    Direction = (int) (Math.random() * 4);
                }
            }
            if (Direction == 7) {
                Direction = (int) (Math.random() * 3); // 0 1 2
            }
            if (Direction == 8) {
                Direction = (int) (Math.random() * 3) + 1; // 1 2 3
            }
            if (Direction == 9) {
                Direction = (int) (Math.random() * 3); // 0 1 3
                if (Direction == 2) {
                    Direction++;
                }
            }
            if (Direction == 10) {
                Direction = (int) (Math.random() * 3); // 0 3 2
                if (Direction == 1) {
                    Direction += 2;
                }
            }

            PastActions.add(0, Direction);
            Patterns.add(0, Direction);
            boolean MoveUp = true;
            boolean MoveDown = true;
            boolean MoveRight = true;
            boolean MoveLeft = true;
            int FailCount = 0;
            boolean TryAgain;
            int SAME = 0;

            for (int x = 1; x < PastActions.size(); x++) {
                if (PastActions.get(x) == Direction) {
                    SAME ++;
                }
            }
            if (PastActions.size() > 7) {
                PastActions.remove(PastActions.size()-1);
            }
            if (Patterns.size() > 14) {
                Patterns.remove(Patterns.size()-1);
            }

            if (SAME > 2 && Check == 0) {
                Check = 4;
                PastActions.remove(0);
                MoveUp = false;
                MoveDown = false;
                MoveRight = false;
                MoveLeft = false;
            }

            do {
                TryAgain = false;
                for (int x = 0; x < Path.size(); x++) {
                    if (Path.get(x)[0] - XLocation == 1 && Path.get(x)[1] - YLocation == 0) {
                        MoveRight = false;
                        FailCount++;
                    }
                    if (Path.get(x)[0] - XLocation == -1 && Path.get(x)[1] - YLocation == 0) {
                        MoveLeft = false;
                        FailCount++;
                    }
                    if (Path.get(x)[1] - YLocation == 1 && Path.get(x)[0] - XLocation == 0) {
                        MoveDown = false;
                        FailCount++;
                    }
                    if (Path.get(x)[1] - YLocation == -1 && Path.get(x)[0] - XLocation == 0) {
                        MoveUp = false;
                        FailCount++;
                    }
                }

                if (FailCount == 4) {
                    Path.remove(Path.size() - 1);
                    XLocation = PassMove[0];
                    YLocation = PassMove[1];
                    TryAgain = true;
                }
            } while (TryAgain);

            if (Direction == 0) {
                PassMove = new int[]{XLocation,YLocation};
                if (YLocation - 1 >= 0 && MoveUp) {
                    YLocation --;
                    Path.add(new int[] {XLocation , YLocation});
                }
            }

            if (Direction == 1) {
                if (YLocation + 1 < ySize && MoveDown) {
                    PassMove = new int[]{XLocation,YLocation};
                    YLocation ++;
                    Path.add(new int[] {XLocation , YLocation});
                }
            }

            if (Direction == 2) {
                if (XLocation + 1 < xSize && MoveRight) {
                    PassMove = new int[]{XLocation,YLocation};
                    XLocation ++;
                    Path.add(new int[] {XLocation , YLocation});
                }
            }

            if (Direction == 3) {
                if (XLocation - 1 >= 0 && MoveLeft) {
                    PassMove = new int[]{XLocation,YLocation};
                    XLocation --;
                    Path.add(new int[] {XLocation , YLocation});
                }
            }

            if (PassMove[0] == XLocation && PassMove[1] == YLocation) {
                SafeFail ++;
            }

            if (SafeFail > 20) {
                Path.clear();
                XLocation = 0;
                YLocation = 0;
                PassMove = new int[]{XLocation,YLocation};
                SafeFail = 0;
                //System.out.print("- ");
            }

            if ((XLocation > xSize - 3 && YLocation == ySize - 1) || XLocation == xSize - 1 && YLocation > ySize - 3) {
                SolveMaze = false;
                if (Path.size() < (int) (4 * (xSize + ySize) / 3.0)) {
                    SolveMaze = true;
                    Path.clear();
                    XLocation = 0;
                    YLocation = 0;
                    PassMove = new int[]{XLocation,YLocation};
                    SafeFail = 0;
                }
            }
            if (Check > 0) {
                Check--;
            }
        }
        Path.add(0, new int[]{0,0});
        boolean[][] Simple = new boolean[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                for (int z = 0; z < Path.size(); z++) {
                    if (Path.get(z)[0] == x && Path.get(z)[1] == y) {
                        Simple[x][y] = true;
                    }
                }
            }
        }

        return Simple; //FinalMaze;
    }

    public static ArrayList<int[]> Order() {
        return Path;
    }
}
