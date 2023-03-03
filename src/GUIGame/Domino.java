package GUIGame;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Jack Vanlyssel
 *
 * Creates the dominoes that are used during play. Features
 * a constructor, rotateDomino which swaps the values of the
 * left and right side, getLeftValue which will return the left
 * side of a domino, getRightValue which returns the right side
 * value, and toString which returns the string representation
 * of a domino, [1 4] for example.
 */
public class Domino extends StackPane {

    private int leftValue;
    private int rightValue;
    private boolean selected;

    private Text leftText;
    private Text rightText;

    private Rectangle dominoBody;

    public Domino(int l, int r) {
        leftValue = l;
        rightValue = r;
        selected = false;

        dominoBody = new Rectangle(100,50);
        dominoBody.setFill(Color.WHITE);
        dominoBody.setArcHeight(15);
        dominoBody.setArcWidth(15);
        dominoBody.setStroke(Color.BLACK);
        dominoBody.setStrokeWidth(3);

        Line divider = new Line(0,0,0,40);

        // Unicode characters for domino dots
        String leftDots = getDots(l);
        String rightDots = getDots(r);

        leftText = new Text(leftDots);
        leftText.setFont(new Font("Arial Unicode MS", 20));
        leftText.setFill(Color.BLACK);
        leftText.setTranslateX(-25);
        if (l == 0) leftText.setText("");

        rightText = new Text(rightDots);
        rightText.setFont(new Font("Arial Unicode MS", 20));
        rightText.setFill(Color.BLACK);
        rightText.setTranslateX(25);
        if (r == 0) rightText.setText("");

        getChildren().addAll(dominoBody, divider, leftText, rightText);
        setOnMouseClicked(this::handleMouseClick);

    }

    public void rotateDomino() {
        int temp = leftValue;
        leftValue = rightValue;
        rightValue = temp;
        String leftDots = getDots(leftValue);
        String rightDots = getDots(rightValue);
        leftText.setText(leftDots);
        rightText.setText(rightDots);
        if (leftValue == 0) leftText.setText("");
        if (rightValue == 0) rightText.setText("");
    }
    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    public int getLeftValue() {return leftValue;}

    public int getRightValue() {return rightValue;}

    public String toString() {
        return "[" + leftValue + " " + rightValue + "]";
    }
    private String getDots(int value) {
        // Unicode characters for domino dots
        String[] dotPatterns = {
                "\u2800", // Blank
                "\u2849", // 1 dot
                "\u2855", // 2 dots
                "\u285B", // 3 dots
                "\u28E7", // 4 dots
                "\u28EF", // 5 dots
                "\u28F7", // 6 dots
        };
        if (value == 0) {
            return dotPatterns[0];
        } else if (value < 7) {
            return dotPatterns[value];
        } else {
            return "";
        }
    }
    private void handleMouseClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            this.selected = !selected;
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            rotateDomino();
        }

    }

}

