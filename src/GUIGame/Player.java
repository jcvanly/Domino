package GUIGame;

/**
 * Jack Vanlyssel
 *
 * Defines the player class which will handle input for the human
 * player. The player class is passed a Boneyard and Board on creation.
 * The player is then prompted to enter input by takeTurn. They can
 * choose to either draw from the Boneyard, play a domino, or quit.
 * If they pick play, the user will be prompted with picking a
 * domino, choosing whether to play it on the left or right, and
 * choosing whether to rotate it or not. After specifying how they
 * want to play, playDomino is called with the commands and the
 * domino is played.
 */

import javafx.scene.layout.VBox;
public class Player extends VBox {

    private final int STARTING_AMOUNT = 7;

    private Boneyard boneyard;
    private Board board;
    private Hand hand;
    private boolean canPlay;

    private int selectedDominoIndex;
    public char playDirection;

    public Player(Boneyard boneyard, Board board) {
        this.boneyard = boneyard;
        this.board = board;
        playDirection = 'r';
        canPlay = true;

        hand = new Hand();
        initHand();

        hand.updateDisplay();

        getChildren().add(hand);
    }

    public void updateDisplay() {
        getChildren().clear();
        hand.updateDisplay();
        getChildren().addAll(hand);
    }

    public boolean hasDominoSelected() {
        selectedDominoIndex = hand.checkForSelected();
        return selectedDominoIndex != -1;
    }

    public void takeTurn(String turnType) {
        if (turnType.equals("Draw")) {
            if (hasPlayableDomino()) {
                // Don't draw a new domino if the player has a playable one
                return;
            }
            drawFromBoneyard();
        }

        else if (turnType.equals("Play")) {
            playDomino();
        }

        else {
            canPlay = false;
        }
    }

    public boolean hasPlayableDomino() {
        int rightPlayableVal = board.getRight().getRightValue();
        int leftPlayableVal = board.getLeft().getLeftValue();

        for (int i = 0; i < hand.getHandSize(); i++) {
            Domino d = hand.getDominoAt(i);
            if (d.getLeftValue() == leftPlayableVal || d.getRightValue() == rightPlayableVal ||
                    leftPlayableVal == 0 || rightPlayableVal == 0 || d.getLeftValue() == rightPlayableVal
            || d.getRightValue() == leftPlayableVal) {
                // The player has a playable domino
                return true;
            }
        }

        // The player does not have a playable domino
        return false;
    }

    public boolean getCanPlay() {
        return canPlay;
    }

    public void setPlayDirection(char dir) {
        playDirection = dir;
    }

    public int getHandLength() {
        return hand.getHandSize();
    }

    public int getScore() {
        return hand.getHandScore();
    }

    public String toString() {
        return hand.toString();
    }

    private void initHand() {
        for (int i = 0; i < STARTING_AMOUNT; i++) {
            hand.addDomino(boneyard.fetchDomino());
        }
    }

    private void playDomino() {
        //Attempt to play selected domino at left then right
        Domino d = hand.getDominoAt(selectedDominoIndex);
        if (board.getSize() == 0) {
            hand.removeDominoAt(selectedDominoIndex);
            placeDomino(d,playDirection);
        }
        else {
            int rightPlayableVal = 0;
            int leftPlayableVal = 0;

            Domino right = board.getRight();
            Domino left = board.getLeft();

            if (right != null) {
                rightPlayableVal = right.getRightValue();
            }

            if (left != null) {
                leftPlayableVal = left.getLeftValue();
            }

            if (playDirection == 'r') {
                if (d.getLeftValue() == rightPlayableVal ||
                        rightPlayableVal == 0 || d.getLeftValue() == 0 ) {
                    hand.removeDominoAt(selectedDominoIndex);
                    placeDomino(d, 'r');
                }
            }
            else if (playDirection == 'l'){
                if (d.getRightValue() == leftPlayableVal ||
                        leftPlayableVal == 0 || d.getRightValue() == 0) {
                    hand.removeDominoAt(selectedDominoIndex);
                    placeDomino(d, 'l');
                }
            }
        }
        selectedDominoIndex = -1;
    }

    public void changePlayAreaX(char side) {
        double currentX = board.getLayoutX();
        double newX;

        if(side == 'r')
        {
            newX = currentX-25;
        }

        else if(side == 'l')
        {
            newX = currentX-25;
        }

        else {newX = currentX;}

        board.setLayoutX(newX);
    }

    private void placeDomino(Domino dominoPlayed, char location) {
        // PLAY RIGHT SIDE
        if (location == 'r') {
            board.playRight(dominoPlayed);
            changePlayAreaX('r');
            playDirection = 'r'; // Update player's playDirection
        }
        // PLAY LEFT SIDE
        else if (location == 'l') {
            board.playLeft(dominoPlayed);
            changePlayAreaX('l');
            playDirection = 'l'; // Update player's playDirection
        }
    }

    private void drawFromBoneyard() {
        if (boneyard.getSize() == 0) {
            canPlay = false;
        } else {
            Domino d = boneyard.fetchDomino();
            hand.addDomino(d);
            hand.updateDisplay();
            boneyard.updateDisplay();
        }
    }

    public void reset() {
        hand.clear();
        initHand();
        hand.updateDisplay();
        canPlay = true;
    }
}
