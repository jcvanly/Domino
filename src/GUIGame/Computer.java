package GUIGame;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


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

public class Computer extends Text{

    private final int STARTING_AMOUNT = 7;

    private Boneyard boneyard;
    private Board board;
    private Hand hand;
    private boolean canPlay;

    public Computer(Boneyard boneyard, Board board) {
        this.boneyard = boneyard;
        this.board = board;
        canPlay = true;

        hand = new Hand();
        initTray();

        setText(this.toString());
        setFont(new Font("Verdana", 20));
        setFill(Color.WHITE);

    }

    public void takeTurn() {
        System.out.println("ConsoleGame.Computer's turn");
        int rightPlayableVal = board.getRight().getRightValue();
        int leftPlayableVal = board.getLeft().getLeftValue();

        int i = 0;
        while (i < hand.getHandSize()) {
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

        if (i == hand.getHandSize()) {
            drawFromBoneyard();
        }

        System.out.println(this);
    }

    public void updateDisplay() {
        setText(this.toString());
    }

    public boolean getCanPlay() {
        return canPlay;
    }

    public int getTrayLength() {
        return hand.getHandSize();
    }

    public int getScore() {
        return hand.getHandScore();
    }

    public String toString() {
        return "ConsoleGame.Computer has " + hand.getHandSize() + " dominoes";
    }

    private void initTray() {
        for (int i = 0; i < STARTING_AMOUNT; i++) {
            hand.addDomino(boneyard.fetchDomino());
        }
    }

    private void drawFromBoneyard() {
        if (boneyard.getSize() == 0) {
            System.out.println("ConsoleGame.Computer can't play.");
            canPlay = false;
        }
        else {
            Domino d = boneyard.fetchDomino();
            System.out.println("ConsoleGame.Computer drew from the boneyard.");
            hand.addDomino(d);
        }
    }

    private void playDomino(Domino dominoPlayed, char location) {
        //PLAY RIGHT SIDE
        if (location=='r') {
            System.out.println("ConsoleGame.Computer plays " + dominoPlayed + " at right");
            board.playRight(dominoPlayed);
        }
        //PLAY LEFT SIDE
        else {
            System.out.println("ConsoleGame.Computer plays " + dominoPlayed + " at left");
            board.playLeft(dominoPlayed);
        }
    }

}
