package GUIGame; /**
 * Jack Vanlyssel
 *
 * The ConsoleGame.Boneyard is a pile of dominoes that players must draw
 * their initial hands from. They also draw a domino from the
 * ConsoleGame.Boneyard if they cannot play any of their dominoes. fetchDomino
 * is used to get dominoes from the ConsoleGame.Boneyard, getSize will return
 * how many dominoes are in the BoneYard, toString will tell
 * the user how many dominoes are left in the ConsoleGame.Boneyard. Finally,
 * innitBoneyard will fill the ConsoleGame.Boneyard with 28 dominoes.
 */

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.LinkedList;

public class Boneyard extends Text {

    private static final int LARGEST_DOMINO_VALUE = 6;

    private LinkedList<Domino> dominoList;

    public Boneyard() {
        dominoList = new LinkedList<>();
        initBoneyard();

        setText(this.toString());
        setFont(new Font("Verdana", 20));
        setFill(Color.WHITE);

    }

    public void updateDisplay() {
        setText(this.toString());
    }

    public int getSize() {
        return dominoList.size();
    }

    public String toString() {
        return "The ConsoleGame.Boneyard has " + dominoList.size() + " dominoes";
    }

    public Domino fetchDomino() {
        return dominoList.pop();
    }

    private void initBoneyard() {
        for (int i = 0; i <= LARGEST_DOMINO_VALUE; i++) {
            for (int k = i; k <= LARGEST_DOMINO_VALUE; k++) {
                dominoList.add(new Domino(i,k));
            }
        }
        Collections.shuffle(dominoList);
    }

}

