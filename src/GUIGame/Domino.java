package GUIGame;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Domino extends StackPane {

    private int leftValue;
    private int rightValue;
    private boolean selected;

    private ImageView leftImageView;
    private ImageView rightImageView;

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

        leftImageView = new ImageView(getImage(l));
        leftImageView.setFitWidth(50);
        leftImageView.setFitHeight(50);
        leftImageView.setTranslateX(-25);
        if (l == 0) leftImageView.setImage(null);

        rightImageView = new ImageView(getImage(r));
        rightImageView.setFitWidth(50);
        rightImageView.setFitHeight(50);
        rightImageView.setTranslateX(25);
        if (r == 0) rightImageView.setImage(null);

        getChildren().addAll(dominoBody, divider, leftImageView, rightImageView);
        setOnMouseClicked(this::handleMouseClick);
    }

    private Image getImage(int value) {
        // You would need to change the image paths to match your own image files
        String imagePath = "";
        switch (value) {
            case 0:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\1.png";
                break;
            case 1:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\1.png";
                break;
            case 2:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\2.png";
                break;
            case 3:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\3.png";
                break;
            case 4:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\4.png";
                break;
            case 5:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\5.png";
                break;
            case 6:
                imagePath = "C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\6.png";
                break;
        }
        return new Image(imagePath);
    }

    public void rotateDomino() {
        int temp = leftValue;
        leftValue = rightValue;
        rightValue = temp;
        leftImageView.setImage(getImage(leftValue));
        rightImageView.setImage(getImage(rightValue));
        if (leftValue == 0) leftImageView.setImage(null);
        if (rightValue == 0) rightImageView.setImage(null);
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public int getRightValue() {
        return rightValue;
    }

    public String toString() {
        return "[" + leftValue + " " + rightValue + "]";
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
