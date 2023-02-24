package ConsoleGame;

/**
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

import java.util.Scanner;
import static java.lang.System.exit;

public class Player {

    private final int STARTING_AMOUNT = 7;

    private Boneyard boneyard;
    private Board playArea;
    private Hand hand;
    private boolean canPlay;

    private Scanner scanner;

    public Player(Boneyard boneyard, Board board) {
        this.boneyard = boneyard;
        this.playArea = board;
        canPlay = true;
        hand = new Hand();
        initTray();
        scanner = new Scanner(System.in);
    }

    public void takeTurn() {
        System.out.println(boneyard);
        System.out.println(playArea);
        System.out.println(hand);

        char input;
        do {
            input = getUserInput();
            switch (input) {
                case 'p' -> play();
                case 'd' -> drawFromBoneyard();
                case 'q' -> exit(0);
                default -> System.out.println("Invalid input!");
            }
        } while (input != 'p' && input != 'd' && input != 'q');

        System.out.println(boneyard);
        System.out.println(playArea);
        System.out.println(hand);
    }

    public boolean getCanPlay() {
        return canPlay;
    }

    public int getTrayLength() {
        return hand.getSize();
    }

    public int getScore() {
        return hand.getTrayScore();
    }

    public String toString() {
        return hand.toString();
    }

    private void initTray() {
        for (int i = 0; i < STARTING_AMOUNT; i++) {
            hand.addDomino(boneyard.fetchDomino());
        }
    }

    private char getUserInput() {
        System.out.println("""
                Human's turn
                [p] Play Domino
                [d] Draw from ConsoleGame.Boneyard
                [q] Quit""");
        return scanner.next().charAt(0);
    }

    private void play() {
        int dominoIndex = -1;
        while (dominoIndex < 0 || dominoIndex >= hand.getSize()) {
            System.out.println("Which domino? (index)");
            dominoIndex = scanner.nextInt();
            if (dominoIndex < 0 || dominoIndex >= hand.getSize()) {
                System.out.println("Invalid input!");
            }
        }

        char location = ' ';
        while (location != 'l' && location != 'r') {
            System.out.println("Left or Right? (l/r)");
            location = scanner.next().charAt(0);
            if (location != 'l' && location != 'r') {
                System.out.println("Invalid input!");
            }
        }

        char rotation = ' ';
        while (rotation != 'y' && rotation != 'n') {
            System.out.println("Rotate first? (y/n)");
            rotation = scanner.next().charAt(0);
            if (rotation != 'y' && rotation != 'n') {
                System.out.println("Invalid input!");
            }
        }

        playDomino(dominoIndex, location, rotation);
    }

    private void playDomino(int index, char location, char rotation) {
        Domino dominoPlayed = hand.removeDominoAt(index);
        if (rotation == 'y') {
            dominoPlayed.rotateDomino();
        }
        int dominoValOnBoard = (location == 'r') ?
                (playArea.getRight() == null ? 0 : playArea.getRight().getRightValue()) :
                (playArea.getLeft() == null ? 0 : playArea.getLeft().getLeftValue());

        int dominoValInPlay = (location == 'r') ? dominoPlayed.getLeftValue() : dominoPlayed.getRightValue();

        if (dominoValInPlay == dominoValOnBoard || dominoValOnBoard == 0 || dominoValInPlay == 0) {
            if (location == 'r') {
                System.out.println("Playing " + dominoPlayed + " at right");
                playArea.playRight(dominoPlayed);
            } else {
                System.out.println("Playing " + dominoPlayed + " at left");
                playArea.playLeft(dominoPlayed);
            }
        } else {
            System.out.println("Invalid play");
            hand.addDomino(dominoPlayed);
        }
    }

    private void drawFromBoneyard() {
        if (boneyard.getSize() == 0) {
            System.out.println("ConsoleGame.Boneyard is empty! Nothing played.");
            canPlay = false;
        } else {
            Domino d = boneyard.fetchDomino();
            System.out.println("You drew " + d + " from the boneyard.");
            hand.addDomino(d);
        }
    }
}
