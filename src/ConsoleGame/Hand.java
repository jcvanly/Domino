package ConsoleGame; /**
 * Jack Vanlyssel
 *
 * ConsoleGame.Hand keeps track of the dominoes that a player currently
 * has. getHandSize returns the size of a player's hand. addDomino
 * adds a domino to the players hand. removeDominoAt is used to
 * remove a domino at a specific index. seeDomino lets you peak
 * at a domino in a players hand. getTrayScore adds the value
 * of all the dominoes in a players hand. toString will display
 * your hand of dominoes.
 */

import ConsoleGame.Domino;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Domino> dominoList;

    public Hand() {
        this.dominoList = new ArrayList<>();
    }

    public int getSize() {
        return dominoList.size();
    }

    public void addDomino(Domino domino) {
        dominoList.add(0, domino);
    }
    
    public Domino removeDominoAt(int index) {
        return dominoList.remove(index);
    }

    public Domino seeDominoAt(int index) {
        return dominoList.get(index);
    }

    public int getTrayScore() {
        int total = 0;
        for (Domino d : dominoList) {
            total += d.getLeftValue();
            total += d.getRightValue();
        }
        return total;
    }

    public String toString() {
        return "Tray: " + dominoList.toString();
    }

}

