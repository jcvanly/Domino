package GUIGame;
/**
 * Jack Vanlyssel
 *
 * The ConsoleGame.Board class will generate the board that the dominoes
 * are played on. In this case, the board is shown in text.
 * The board is a Deque and you can use playRight and playLeft
 * in order play dominoes on either side of the board. toString
 * is used to generate the string representation of the board.
 */

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Deque;
import java.util.LinkedList;
import javafx.scene.shape.Rectangle;


public class Board extends VBox {

    private Deque<Domino> playedDominoes;
    private boolean topOffset = false;

    public Board() {
        playedDominoes = new LinkedList<>();

        HBox top = new HBox();
        HBox bottom = new HBox();

        Rectangle spacer = new Rectangle(50, 50);
        spacer.setOpacity(0);
        int counter = 0;

        if (topOffset) {
            top.getChildren().add(spacer);
            for (Domino d : playedDominoes) {
                if ((counter%2) == 1) top.getChildren().add(d);
                else bottom.getChildren().add(d);
                counter++;
            }
        }
        else {
           bottom.getChildren().add(spacer);
            for (Domino d : playedDominoes) {
                if ((counter%2) == 0) top.getChildren().add(d);
                else bottom.getChildren().add(d);
                counter++;
            }
        }
        setAlignment(Pos.CENTER);
        getChildren().addAll(top, bottom);
    }


    public void playRight(Domino d) {
        playedDominoes.addLast(d);
    }

    public void playLeft(Domino d) {
        playedDominoes.addFirst(d);
        topOffset = !topOffset;
    }


    public void updateDisplay() {
        getChildren().clear();

        HBox top = new HBox();
        HBox bottom = new HBox();

        Rectangle spacer = new Rectangle(50, 50);
        spacer.setOpacity(0);
        int counter = 0;

        if (topOffset) {
            top.getChildren().add(spacer);
            for (Domino d : playedDominoes) {
                if ((counter%2) == 1) top.getChildren().add(d);
                else bottom.getChildren().add(d);
                counter++;
            }
        }
        else {
            bottom.getChildren().add(spacer);
            for (Domino d : playedDominoes) {
                if ((counter%2) == 0) top.getChildren().add(d);
                else bottom.getChildren().add(d);
                counter++;
            }
        }

        getChildren().addAll(top, bottom);
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

