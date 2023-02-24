package ConsoleGame;

/**
 * Jack Vanlyssel
 *
 * Creates the dominoes that are used during play. Features
 * a constructor, rotateDomino which swaps the values of the
 * left and right side, getLeftValue which will return the left
 * side of a domino, getRightValue which returns the right side
 * value, and toString which returns the string representation
 * of a domino, [1 4] for example.
 */
public class Domino {

    private int leftValue;
    private int rightValue;

    public Domino(int l, int r) {
        leftValue = l;
        rightValue = r;
    }

    public void rotateDomino() {
        int temp = leftValue;
        leftValue = rightValue;
        rightValue = temp;
    }

    public int getLeftValue() {return leftValue;}

    public int getRightValue() {return rightValue;}

    public String toString() {
        return "[" + leftValue + " " + rightValue + "]";
    }
}

