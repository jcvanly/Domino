package GUIGame; /**
 * Defines the player class which will handle input for the human
 * player. The player class is passed a ConsoleGame.Boneyard and ConsoleGame.Board on creation.
 * The player is then prompted to enter input by takeTurn. They can
 * choose to either draw from the ConsoleGame.Boneyard, play a domino, or quit.
 * If they pick play, the user will be prompted with picking a
 * domino, choosing whether to play it on the left or right, and
 * choosing whether to rotate it or not. After specifying how they
 * want to play, playDomino is called with the commands and the
 * domino is played.
 *
 */

 import javafx.scene.layout.VBox;
public class Player extends VBox {

    private final int STARTING_AMOUNT = 7;

    private Boneyard boneyard;
    private Board board;
    private Hand hand;
    private boolean canPlay;

    private int selectedDominoIndex;
    private char playDirection;

    public Player(Boneyard boneyard, Board board) {
        this.boneyard = boneyard;
        this.board = board;
        playDirection = 'r';
        canPlay = true;

        hand = new Hand();
        initTray();

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
        if(turnType.equals("Draw")) {
            drawFromBoneyard();
        }
        else if (turnType.equals("Play")) {
            playDomino();
        }
        else {
            canPlay = false;
        }
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

    private void initTray() {
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
            int rightPlayableVal = board.getRight().getRightValue();
            int leftPlayableVal = board.getLeft().getLeftValue();

            if (playDirection == 'r') {
                if (d.getLeftValue() == rightPlayableVal ||
                        rightPlayableVal == 0 || d.getLeftValue() == 0) {
                    hand.removeDominoAt(selectedDominoIndex);
                    placeDomino(d, 'r');
                }
            }
            else {
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
            newX = currentX - 50;
        }

        else
        {
            newX = currentX - 50;
        }

        board.setLayoutX(newX);
    }

    private void placeDomino(Domino dominoPlayed, char location) {
        //PLAY RIGHT SIDE
        if (location=='r') {
            board.playRight(dominoPlayed);
            changePlayAreaX('r');

        }
        //PLAY LEFT SIDE
        else {
            board.playLeft(dominoPlayed);
            changePlayAreaX('l');

        }
    }

    private void drawFromBoneyard() {
        if (boneyard.getSize() == 0) {
            canPlay = false;
        }
        else {
            Domino d = boneyard.fetchDomino();
            hand.addDomino(d);
        }
    }

}
