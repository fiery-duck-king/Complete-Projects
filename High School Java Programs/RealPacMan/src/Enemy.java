import javax.swing.*;

public class Enemy {

    private static int change = 60;
    private static int PastMove0 = 0; // 1 = Right // 2 = Left // 3 = Up // 4 = Down //
    private static int PastMove1 = 0; // 1 = Right // 2 = Left // 3 = Up // 4 = Down //
    private static int PastMove2 = 0; // 1 = Right // 2 = Left // 3 = Up // 4 = Down //
    private static int PastMove3 = 0; // 1 = Right // 2 = Left // 3 = Up // 4 = Down //
    private static int turns = 0;
    private static int[] wonder = new int[4];
    private static boolean[] Chase = new boolean[4];
    private static int[] RandoX = new int[4];
    private static int[] RandoY = new int[4];

    public static void reset() {
        turns = 0;
        Chase[0] = false;
        Chase[1] = false;
        Chase[2] = false;
        Chase[3] = false;

        wonder[0] = 0;
        wonder[1] = 0;
        wonder[2] = 0;
        wonder[3] = 0;
        //System.out.println("Working");
    }

    private static void check(int x, int Level) {
        Chase[x] = !Chase[x];
        if (Chase[x]) {
            wonder[x] = (int) Math.round(Math.random() * 15 + 26);
        } else {
            wonder[x] = (int) Math.round(Math.random() * 10 + Math.min(Level, 15) + 21 - Math.min(Level, 15));
        }
        RandoX[x] = (int) (Math.random() * 19);
        RandoY[x] = (int) (Math.random() * 11);
        //System.out.println("Ghost " + x + " is targeting [" + RandoX[x] + ", " + RandoY[x] + "]");
    }

    public static void move(JLabel[] ghost, int PacX, int PacY, Movement[][] move, int Level) {
        for (int all = 0; all < Math.min(4,Math.max(1, (turns / 10))); all++) {
            if ((PacX - ghost[all].getX() < 10 && PacX - ghost[all].getX() > -10) && (PacY - ghost[all].getY() < 10 && PacY - ghost[all].getY() > -10)) {
                PacMan.Die();
            }
            if (wonder[all] <= 0) {
                Enemy.check(all, Level);
            } else {
                wonder[all]--;
            }
            boolean moved = false;
            int changeX, changeY;
            int PosX = (ghost[all].getX() - 40) / 60;
            int PosY = (ghost[all].getY() - 40) / 60;
            if (Chase[all]) {
                changeX = PacX - ghost[all].getX();
                changeY = PacY - ghost[all].getY();
            } else {
                changeX = RandoX[all] - ghost[all].getX();
                changeY = RandoY[all] - ghost[all].getX();
            }
            boolean[] fail = {true, true, true, true};
            int[] addfail = {0, 0, 0, 0};

            if (all == 0) {
                while (!moved && (fail[0] || fail[1] || fail[2] || fail[3])) {
                    boolean instaPass = false;
                    boolean instaFail = false;
                    if (!fail[0]) {
                        addfail[0] = 1;
                    }
                    if (!fail[1]) {
                        addfail[1] = 1;
                    }
                    if (!fail[2]) {
                        addfail[2] = 1;
                    }
                    if (!fail[3]) {
                        addfail[3] = 1;
                    }
                    if ((addfail[0] + addfail[1] + addfail[2] + addfail[3]) % 2 != 0) {
                        if (addfail[0] + addfail[1] < addfail[2] + addfail[3]) {
                            instaPass = true;
                        } else if (addfail[0] + addfail[1] > addfail[2] + addfail[3]) {
                            instaFail = true;
                        }
                    }
                    if (!(instaFail) && ((((Math.abs(changeX) < Math.abs(changeY)) && changeX != 0) || changeY == 0) && (fail[1] || fail[0]) || !(fail[2] || fail[3])) || instaPass) {
                        if (Math.abs(changeX) != changeX && fail[1] || !fail[0]) {
                            if (move[PosX][PosY].moveLeft() && PastMove0 != 2) {
                                ghost[0].setLocation(ghost[0].getX() - change, ghost[0].getY());
                                moved = true;
                                PastMove0 = 1;
                            } else {
                                fail[1] = false;

                            }
                        } else if (move[PosX][PosY].moveRight() && PastMove0 != 1) {
                            ghost[0].setLocation(ghost[0].getX() + change, ghost[0].getY());
                            moved = true;
                            PastMove0 = 2;
                        } else {
                            fail[0] = false;
                        }
                    } else {
                        if (Math.abs(changeY) != changeY && fail[3] || !fail[2]) {
                            if (move[PosX][PosY].moveUp() && PastMove0 != 4) {
                                ghost[0].setLocation(ghost[0].getX(), ghost[0].getY() - change);
                                moved = true;
                                PastMove0 = 3;
                            } else {
                                fail[3] = false;
                            }
                        } else if (move[PosX][PosY].moveDown() && PastMove0 != 3) {
                            ghost[0].setLocation(ghost[0].getX(), ghost[0].getY() + change);
                            moved = true;
                            PastMove0 = 4;
                        } else {
                            fail[2] = false;
                        }
                    }
                    //System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
                }
                if (!(fail[0] || fail[1] || fail[2] || fail[3])) {
                    PastMove0 = 0;
                }
            }
            if (all == 1) {
                while (!moved && (fail[0] || fail[1] || fail[2] || fail[3])) {
                    boolean instaPass = false;
                    boolean instaFail = false;
                    if (!fail[0]) {
                        addfail[0] = 1;
                    }
                    if (!fail[1]) {
                        addfail[1] = 1;
                    }
                    if (!fail[2]) {
                        addfail[2] = 1;
                    }
                    if (!fail[3]) {
                        addfail[3] = 1;
                    }
                    if ((addfail[0] + addfail[1] + addfail[2] + addfail[3]) % 2 != 0) {
                        if (addfail[0] + addfail[1] < addfail[2] + addfail[3]) {
                            instaPass = true;
                        } else if (addfail[0] + addfail[1] > addfail[2] + addfail[3]) {
                            instaFail = true;
                        }
                    }
                    if (!(instaFail) && ((((Math.abs(changeX) > Math.abs(changeY)) && changeX != 0) || changeY == 0) && (fail[1] || fail[0]) || !(fail[2] || fail[3])) || instaPass) {
                        if (Math.abs(changeX) != changeX && fail[1] || !fail[0]) {
                            if (move[PosX][PosY].moveLeft() && PastMove1 != 2) {
                                ghost[all].setLocation(ghost[all].getX() - change, ghost[all].getY());
                                moved = true;
                                PastMove1 = 1;
                            } else {
                                fail[1] = false;

                            }
                        } else if (move[PosX][PosY].moveRight() && PastMove1 != 1) {
                            ghost[all].setLocation(ghost[all].getX() + change, ghost[all].getY());
                            moved = true;
                            PastMove1 = 2;
                        } else {
                            fail[0] = false;
                        }
                    } else {
                        if (Math.abs(changeY) != changeY && fail[3] || !fail[2]) {
                            if (move[PosX][PosY].moveUp() && PastMove1 != 4) {
                                ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() - change);
                                moved = true;
                                PastMove1 = 3;
                            } else {
                                fail[3] = false;
                            }
                        } else if (move[PosX][PosY].moveDown() && PastMove1 != 3) {
                            ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() + change);
                            moved = true;
                            PastMove1 = 4;
                        } else {
                            fail[2] = false;
                        }
                    }
                    //System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
                }
                if (!(fail[0] || fail[1] || fail[2] || fail[3])) {
                    PastMove1 = 0;
                }
            }
//
            if (all == 2) {
                while (!moved && (fail[0] || fail[1] || fail[2] || fail[3])) {
                    boolean instaPass = false;
                    boolean instaFail = false;
                    if (!fail[0]) {
                        addfail[0] = 1;
                    }
                    if (!fail[1]) {
                        addfail[1] = 1;
                    }
                    if (!fail[2]) {
                        addfail[2] = 1;
                    }
                    if (!fail[3]) {
                        addfail[3] = 1;
                    }
                    if ((addfail[0] + addfail[1] + addfail[2] + addfail[3]) % 2 != 0) {
                        if (addfail[0] + addfail[1] < addfail[2] + addfail[3]) {
                            instaPass = true;
                        } else if (addfail[0] + addfail[1] > addfail[2] + addfail[3]) {
                            instaFail = true;
                        }
                    }
                    if (!(instaFail) && ((((Math.abs(changeX) > Math.abs(changeY)) && changeX != 0) || changeY == 0) && (fail[1] || fail[0]) || !(fail[2] || fail[3])) || instaPass) {
                        if (Math.abs(changeX) != changeX && fail[1] || !fail[0]) {
                            if (move[PosX][PosY].moveLeft() && PastMove2 != 2) {
                                ghost[all].setLocation(ghost[all].getX() - change, ghost[all].getY());
                                moved = true;
                                PastMove2 = 1;
                            } else {
                                fail[1] = false;

                            }
                        } else if (move[PosX][PosY].moveRight() && PastMove2 != 1) {
                            ghost[all].setLocation(ghost[all].getX() + change, ghost[all].getY());
                            moved = true;
                            PastMove2 = 2;
                        } else {
                            fail[0] = false;
                        }
                    } else {
                        if (Math.abs(changeY) != changeY && fail[3] || !fail[2]) {
                            if (move[PosX][PosY].moveUp() && PastMove2 != 4) {
                                ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() - change);
                                moved = true;
                                PastMove2 = 3;
                            } else {
                                fail[3] = false;
                            }
                        } else if (move[PosX][PosY].moveDown() && PastMove2 != 3) {
                            ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() + change);
                            moved = true;
                            PastMove2 = 4;
                        } else {
                            fail[2] = false;
                        }
                    }
                    //System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
                }
                if (!(fail[0] || fail[1] || fail[2] || fail[3])) {
                    PastMove2 = 0;
                }
            }
//
            if (all == 3){
                while (!moved && (fail[0] || fail[1] || fail[2] || fail[3])) {
                    boolean instaPass = false;
                    boolean instaFail = false;
                    if (!fail[0]) {
                        addfail[0] = 1;
                    }
                    if (!fail[1]) {
                        addfail[1] = 1;
                    }
                    if (!fail[2]) {
                        addfail[2] = 1;
                    }
                    if (!fail[3]) {
                        addfail[3] = 1;
                    }
                    if ((addfail[0] + addfail[1] + addfail[2] + addfail[3]) % 2 != 0) {
                        if (addfail[0] + addfail[1] < addfail[2] + addfail[3]) {
                            instaPass = true;
                        } else if (addfail[0] + addfail[1] > addfail[2] + addfail[3]) {
                            instaFail = true;
                        }
                    }
                    if (!(instaFail) && ((((Math.abs(changeX) < Math.abs(changeY)) && changeX != 0) || changeY == 0) && (fail[1] || fail[0]) || !(fail[2] || fail[3])) || instaPass) {
                        if (Math.abs(changeX) != changeX && fail[1] || !fail[0]) {
                            if (move[PosX][PosY].moveLeft() && PastMove3 != 2) {
                                ghost[all].setLocation(ghost[all].getX() - change, ghost[all].getY());
                                moved = true;
                                PastMove3 = 1;
                            } else {
                                fail[1] = false;

                            }
                        } else if (move[PosX][PosY].moveRight() && PastMove3 != 1) {
                            ghost[all].setLocation(ghost[all].getX() + change, ghost[all].getY());
                            moved = true;
                            PastMove3 = 2;
                        } else {
                            fail[0] = false;
                        }
                    } else {
                        if (Math.abs(changeY) != changeY && fail[3] || !fail[2]) {
                            if (move[PosX][PosY].moveUp() && PastMove3 != 4) {
                                ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() - change);
                                moved = true;
                                PastMove3 = 3;
                            } else {
                                fail[3] = false;
                            }
                        } else if (move[PosX][PosY].moveDown() && PastMove3 != 3) {
                            ghost[all].setLocation(ghost[all].getX(), ghost[all].getY() + change);
                            moved = true;
                            PastMove3 = 4;
                        } else {
                            fail[2] = false;
                        }
                    }
                    //System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
                }
                if (!(fail[0] || fail[1] || fail[2] || fail[3])) {
                    PastMove3 = 0;
                }
            }
            if ((PacX - ghost[all].getX() < 10 && PacX - ghost[all].getX() > -10)&& (PacY - ghost[all].getY() < 10 && PacY - ghost[all].getY() > -10)) {
                PacMan.Die();
            }
        }
        turns ++;
    }
}
