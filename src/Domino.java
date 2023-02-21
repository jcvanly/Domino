public class Domino {
    private int left;
    private int right;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void flip() {
        int temp = left;
        left = right;
        right = temp;

    }
}
