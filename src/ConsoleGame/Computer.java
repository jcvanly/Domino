package ConsoleGame;

/**
 * Jack Vanlyssel
 *
 * This class creates the computer player that you will play
 * against. The computer is passed a ConsoleGame.Boneyard and a board
 * just like the player is. Most of the work is done in
 * takeTurn which is the AI loop for the computer player.
 * Essentially, the computer just checks if a domino is
 * playable, then he plays it. If the computer can not
 * play a domino, he will draw a new one from the ConsoleGame.Boneyard.
 */

public class Computer {

    private final int STARTING_AMOUNT = 7;

    private Boneyard boneyard;
    private Board playArea;
    private Hand hand;
    private boolean canPlay;

    public Computer(Boneyard boneyard, Board board) {
        this.boneyard = boneyard;
        this.playArea = board;
        canPlay = true;

        hand = new Hand();
        initHand();
    }

    public void takeTurn() {
        System.out.println("Computer's turn");
        int rightPlayableVal = playArea.getRight().getRightValue();
        int leftPlayableVal = playArea.getLeft().getLeftValue();

        int i = 0;
        while (i < hand.getSize()) {
            Domino d = hand.seeDominoAt(i);
            if (d.getRightValue() == leftPlayableVal ||
                    leftPlayableVal == 0 || d.getRightValue() == 0) {
                //PLAY IT
                d = hand.removeDominoAt(i);
                playDomino(d, 'l');
                break;
            }
            else if (d.getLeftValue() == rightPlayableVal ||
                    rightPlayableVal == 0 || d.getLeftValue() == 0) {
                //PLAY IT
                d = hand.removeDominoAt(i);
                playDomino(d, 'r');
                break;
            }
            else if (d.getRightValue() == rightPlayableVal) {
                //ROTATE IT; PLAY IT
                d = hand.removeDominoAt(i);
                d.rotateDomino();
                playDomino(d, 'r');
                break;
            }
            else if (d.getLeftValue() == leftPlayableVal) {
                //ROTATE IT; PLAY IT
                d = hand.removeDominoAt(i);
                d.rotateDomino();
                playDomino(d, 'l');
                break;
            }
            else {
                i++;
            }
        }

        if (i == hand.getSize()) {
            drawFromBoneyard();
        }

        System.out.println(this);
    }

    public boolean getCanPlay() {
        return canPlay;
    }

    public int getHandLength() {
        return hand.getSize();
    }

    public int getScore() {
        return hand.getHandScore();
    }

    public String toString() {
        return "Computer has " + hand.getSize() + " dominoes";
    }

    private void initHand() {
        for (int i = 0; i < STARTING_AMOUNT; i++) {
            hand.addDomino(boneyard.fetchDomino());
        }
    }

    private void drawFromBoneyard() {
        if (boneyard.getSize() == 0) {
            System.out.println("Computer can't play.");
            canPlay = false;
        }
        else {
            Domino d = boneyard.fetchDomino();
            System.out.println("Computer drew from the boneyard.");
            hand.addDomino(d);
        }
    }

    private void playDomino(Domino dominoPlayed, char location) {
        //PLAY RIGHT SIDE
        if (location=='r') {
            System.out.println("Computer plays " + dominoPlayed + " at right");
            playArea.playRight(dominoPlayed);
        }
        //PLAY LEFT SIDE
        else {
            System.out.println("Computer plays " + dominoPlayed + " at left");
            playArea.playLeft(dominoPlayed);
        }
    }

}
