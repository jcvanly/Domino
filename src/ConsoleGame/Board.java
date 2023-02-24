package ConsoleGame; /**
 * Jack Vanlyssel
 *
 * The ConsoleGame.Board class will generate the board that the dominoes
 * are played on. In this case, the board is shown in text.
 * The board is a Deque and you can use playRight and playLeft
 * in order play dominoes on either side of the board. toString
 * is used to generate the string representation of the board.
 */

import java.util.Deque;
import java.util.LinkedList;

public class Board {

    private Deque<Domino> playedDominoes;
    private boolean topOffset = false;

    public Board() {
        playedDominoes = new LinkedList<>();
    }

    public void playRight(Domino d) {
        playedDominoes.addLast(d);
    }

    public void playLeft(Domino d) {
        playedDominoes.addFirst(d);
        topOffset = !topOffset;
    }

    public Domino getRight() {
        if (playedDominoes.size() == 0) return null;
        else return playedDominoes.getLast();
    }

    public Domino getLeft() {
        if (playedDominoes.size() == 0) return null;
        else return playedDominoes.getFirst();
    }

    public int getSize() {
        return playedDominoes.size();
    }

    public String toString() {
        StringBuilder top = new StringBuilder();
        StringBuilder bottom = new StringBuilder();
        int counter = 0;

        if (topOffset) {
            top.append("  ");
            for (Domino d : playedDominoes) {
                if ((counter%2) == 1) top.append(d.toString());
                else bottom.append(d.toString());
                counter++;
            }
        }
        else {
            bottom.append("  ");
            for (Domino d : playedDominoes) {
                if ((counter%2) == 0) top.append(d.toString());
                else bottom.append(d.toString());
                counter++;
            }
        }

        return top + "\n" + bottom;
    }

}

