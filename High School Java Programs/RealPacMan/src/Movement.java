public class Movement {

    private boolean Up;
    private boolean Down;
    private boolean Left;
    private boolean Right;

    public Movement(int x, int y) {
        Up = true;
        Down = true;
        Left = true;
        Right = true;

        if (y == 0) {
            if (x == 1 || x == 2 || (x >= 5 && x <= 8) || (x >= 10 && x <= 13) || x == 16 || x == 17) {
                Down = false;
            }
            if (x == 3 || x == 14) {
                Right = false;
            }
            if (x == 4 || x == 15) {
                Left = false;
            }
        }
        if (y == 1) {
            if (x == 0 || x == 8 || x == 9 || x == 17) {
                Right = false;
            }
            if (x == 1 || x == 9 || x == 10 || x == 18) {
                Left = false;
            }
            if (x == 1 || x == 2 || (x >= 5 && x <= 8) || (x >= 10 && x <= 13) || x == 16 || x == 17) {
                Up = false;
            }
            if ((x >= 2 && x <= 7) || (x >= 11 && x <= 16)) {
                Down = false;
            }
        }
        if (y == 2) {
            if (x == 0 || x == 4 || (x >= 7 && x <= 10) || x == 13 || x == 17) {
                Right = false;
            }
            if (x == 1 || x == 5 || (x >= 8 && x <= 11) || x == 14 || x == 18) {
                Left = false;
            }
            if ((x >= 2 && x <= 7) || (x >= 11 && x <= 16)) {
                Up = false;
            }
            if (x == 3 || x == 6 || x == 12 || x == 15) {
                Down = false;
            }
        }
        if (y == 3) {
            if (x == 1 || x == 2 || x == 6 || x == 8 || x == 9 || x == 11  || x == 15 || x == 16) {
                Right = false;
            }
            if (x == 2 || x == 3 || x == 7 || x == 9 || x == 10 || x == 12  || x == 16 || x == 17) {
                Left = false;
            }
            if (x == 3 || x == 6 || x == 12 || x == 15) {
                Up = false;
            }
            if (x == 0 || x == 4 || x == 14 || x == 18) {
                Down = false;
            }
        }
        if (y == 4) {
            if (x != 4 && x != 8 && x != 9 && x != 13) {
                Right = false;
            }
            if (x != 5 && x != 9 && x != 10 && x != 14) {
                Left = false;
            }
            if (x == 4 || x == 14) {
                Up = false;
            }
            if (x == 5 || x == 9 || x == 13) {
                Down = false;
            }
        }
        if (y == 5) {
            if ((x >= 1 && x <= 4) || (x >= 7 && x <= 10) || (x >= 13 && x <= 16)) {
                Right = false;
            }
            if ((x >= 2 && x <= 5) || (x >= 8 && x <= 11) || (x >= 14 && x <= 17)) {
                Left = false;
            }
            if (x == 0 || x == 5 || x == 13 || x == 18) {
                Up = false;
            }
            if (x == 0 || x == 9 || x == 18) {
                Down = false;
            }
        }
        if (y == 6) {
            if (x != 2 && !(x >= 7 && x <= 10) && x != 15) {
                Right = false;
            }
            if (x != 3 && !(x >= 8 && x <= 11) && x != 16) {
                Left = false;
            }
            if (x == 9) {
                Up = false;
            }
            if (x == 3 || (x >= 8 && x <= 10) || x == 15) {
                Down = false;
            }
        }
        if (y == 7) {
            if (x == 1 || (x >= 4 && x <= 6) || x == 8 || (x >= 11 && x <= 13) || x == 16) {
                Right = false;
            }
            if (x == 2 || (x >= 5 && x <= 7) || x == 10 || (x >= 12 && x <= 14) || x == 17) {
                Left = false;
            }
            if (x == 0 || x == 3 || (x >= 8 && x <= 10) || x == 15 || x == 18) {
                Up = false;
            }
            if (x == 3 || x == 7 || x == 11 || x == 15) {
                Down = false;
            }
        }
        if (y == 8) {
            if (x == 0 || x == 1 || x == 3 || x == 4 || x == 13 || x == 14 || x == 16 || x == 17) {
                Right = false;
            }
            if (x == 1 || x == 2 || x == 4 || x == 5 || x == 14 || x == 15 || x == 17 || x == 18) {
                Left = false;
            }
            if (x == 3 || x == 7 || x == 9 || x == 11 || x == 15) {
                Up = false;
            }
            if (x == 2 || x == 5 || (x >= 7 && x <= 11 && x != 9) || x == 13 || x == 16) {
                Down = false;
            }
        }
        if (y == 9) {
            if (x == 0 || x == 8 || x == 9 || x == 17) {
                Right = false;
            }
            if (x == 1 || x == 9 || x == 10 || x == 18) {
                Left = false;
            }
            if (x == 2 || x == 5 || (x >= 7 && x <= 11 && x != 9) || x == 13 || x == 16) {
                Up = false;
            }
            if (x == 1 || (x >= 4 && x <= 6) || (x >= 12 && x <= 14) || x == 17) {
                Down = false;
            }
        }
        if (y == 10) {
            if (x == 2 || x == 7 || x == 10 || x == 15) {
                Right = false;
            }
            if (x == 3 || x == 8 || x == 11 || x == 16) {
                Left = false;
            }
            if (x == 1 || (x >= 4 && x <= 6) || (x >= 12 && x <= 14) || x == 17) {
                Up = false;
            }
        }


        if (y == 10) {
            Down = false;
        }
        if (y == 0) {
            Up = false;
        }
        if (x == 0) {
            Left = false;
        }
        if (x == 18) {
            Right = false;
        }

        /*
        System.out.print(x + " " + y + ", Up : " + Up);
        System.out.print(" || Down: " + Down);
        System.out.print(" || Left: " + Left);
        System.out.println(" || Rigth " + Right);
         */
    }

    public boolean moveUp() {
        return Up;
    }
    public boolean moveDown() {
        return Down;
    }
    public boolean moveLeft() {
        return Left;
    }
    public boolean moveRight() {
        return Right;
    }
}
