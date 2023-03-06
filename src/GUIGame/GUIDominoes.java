package GUIGame;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Jack Vanlyssel
 *
 * This class laMunches the game using startGame which in turn
 * creates a player, an AI, a board, and a boneyard. The
 * player and AI are both passed the board and boneyard.
 * isGameOver will check for game over conditions
 * and if they are met, gameOver will then display the winner
 * and scores.
 */

public class GUIDominoes {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 700;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private Player player;
    private AnimationTimer gameTimer;

    private Computer computer;
    private Boneyard boneyard;
    private Board board;


    private boolean turnMade;
    private boolean gameIsOver;
    private boolean lastPlayerComp;

    /***
     * initialises the mainPane, mainScene, mainStage, boneyard, play area,
     * player, and computer.
     * calls helper methods to generate objects to display
     */
    public GUIDominoes() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        mainStage.setTitle("Dominoes!");
        mainStage.setResizable(false);
        Image icon = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\icon.png");
        mainStage.getIcons().add(icon);

        boneyard = new Boneyard();
        board = new Board();
        player = new Player(boneyard, board);
        computer = new Computer(boneyard, board);
        turnMade = false;
        gameIsOver = false;
        lastPlayerComp = false;

        createGameInfoText();
        createPlayerHand();
        createPlayArea();
        createButtons();
        createBackGround();
        createGameLoop();

        player.updateDisplay();
        boneyard.updateDisplay();
        computer.updateDisplay();
        board.updateDisplay();
    }

    /***
     * gets mainStage of the display
     * @return mainStage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /***
     * creates background of the display
     */
    private void createBackGround() {
        Image backgroundImage = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\game_bg.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    /***
     * adds player tray to the display
     */
    private void createPlayerHand() {
        player.setLayoutX(400);
        player.setLayoutY(440);
        mainPane.getChildren().add(player);
    }

    /***
     * adds the ext info about the boneyard and computer to the display
     */
    private void createGameInfoText() {
        boneyard.setLayoutX(600);
        boneyard.setLayoutY(75);

        computer.setLayoutX(625);
        computer.setLayoutY(50);

        mainPane.getChildren().addAll(boneyard,computer);
    }

    /***
     * adds the play area to the display
     */
    private void createPlayArea() {
        board.setLayoutX(700);
        board.setLayoutY(200);
        mainPane.getChildren().addAll(board);
    }





    /***
     * adds the usable buttons to the display
     */
    private void createButtons() {
        createDrawButton();
        createPassButton();
        createRadioButton();
    }

    /***
     * creates button the player uses to draw
     */
    private void createDrawButton() {
        Button drawButton = new Button("Draw");
        drawButton.setLayoutX(450);
        drawButton.setLayoutY(600);
        //Button Styling
        drawButton.setPrefWidth(100);
        drawButton.setPrefHeight(25);
        drawButton.setFont(Font.font("Verdana", 20));
        mainPane.getChildren().add(drawButton);

        drawButton.setOnAction(e ->{
            player.takeTurn("Draw");
            turnMade = true;
        });
    }

    /***
     * creates button uses to pass
     */
    private void createPassButton() {
        Button passButton = new Button("Pass");
        passButton.setLayoutX(950);
        passButton.setLayoutY(600);
        //Button Styling
        passButton.setPrefWidth(100);
        passButton.setPrefHeight(25);
        passButton.setFont(Font.font("Verdana", 20));
        mainPane.getChildren().add(passButton);

        passButton.setOnAction(e -> {
            player.takeTurn("Pass");
            turnMade = true;
        });
    }

    /***
     * creates radio buttons the player uses to determine if they are going
     * to attempt to play on the left or right side of the play area
     */
    private void createRadioButton() {
        HBox buttonBox = new HBox();
        ToggleGroup group = new ToggleGroup();

        RadioButton leftBtn = new RadioButton("Left");
        leftBtn.setToggleGroup(group);
        leftBtn.setPrefWidth(100);
        leftBtn.setPrefHeight(25);
        leftBtn.setFont(Font.font("Verdana", 15));
        leftBtn.setTextFill(Color.WHITE);

        RadioButton rightBtn = new RadioButton("Right");
        rightBtn.setToggleGroup(group);
        rightBtn.setSelected(true);
        buttonBox.getChildren().addAll(leftBtn, rightBtn);
        rightBtn.setPrefWidth(100);
        rightBtn.setPrefHeight(25);
        rightBtn.setFont(Font.font("Verdana", 15));
        rightBtn.setTextFill(Color.WHITE);

        buttonBox.setLayoutX(675);
        buttonBox.setLayoutY(605);
        mainPane.getChildren().add(buttonBox);

        rightBtn.setOnAction(e -> player.setPlayDirection('r'));

        leftBtn.setOnAction(e -> player.setPlayDirection('l'));
    }

    /***
     * creates and displays the end game text depending on who won
     */
    private void createEndGameText() {
        int playerScore = player.getScore();
        int compScore = computer.getScore();

        Text winnerText = new Text();
        winnerText.setFont(new Font("Verdana", 55));
        winnerText.setFill(Color.WHITE);

        if (playerScore > compScore) {
            winnerText.setText("Computer Wins!");
        } else if (compScore > playerScore) {
            winnerText.setText("Player Wins!");
        } else {
            System.out.println("Scores are equal! The last player wins:");
            if (lastPlayerComp) {
                winnerText.setText("Computer Wins!");
            } else {
                winnerText.setText("Player Wins!");
            }
        }
        winnerText.setLayoutX(600);
        winnerText.setLayoutY(220);

        mainPane.getChildren().add(winnerText);
    }

    /***
     * creates and starts the game loop
     */
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkIfDominoPlayed();
                if (turnMade) {
                    lastPlayerComp = false;
                    checkForGameOver();
                    if(!gameIsOver) {
                        computer.takeTurn();
                        lastPlayerComp = true;
                    }

                    player.updateDisplay();
                    boneyard.updateDisplay();
                    computer.updateDisplay();
                    board.updateDisplay();

                    checkForGameOver();
                    turnMade = false;
                }
                if (gameIsOver) {
                    gameOver();
                    gameIsOver = false;
                }
            }
        };
        gameTimer.start();
    }

    /***
     * Checks if the player has clicked on a domino in their hand
     */
    private void checkIfDominoPlayed() {
        if(player.hasDominoSelected()) {
            int playedDominoes = board.getSize();
            player.takeTurn("Play");
            if(playedDominoes != board.getSize()) turnMade = true;
        }
    }

    /***
     * Checks if either the player or computer has won
     */
    private void checkForGameOver() {
        if (boneyard.getSize() == 0) {
            gameIsOver = true;
        }

        if(player.getHandLength() == 0) {
            gameIsOver = true;
        }

        if(computer.getTrayLength() == 0) {
            gameIsOver = true;
        }
        //If neither of them have played
        if (!player.getCanPlay() && !computer.getCanPlay()) {
            gameIsOver = true;
        }
    }

    /***
     * Removes some game elements and displays the winner Text
     */
    private void gameOver() {
        mainPane.getChildren().clear();
        createBackGround();
        createEndGameText();
    }

}

