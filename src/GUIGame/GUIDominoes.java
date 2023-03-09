package GUIGame;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button drawButton; // instance variable
    private Button helpButton;

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 700;
    private Text playerScoreText;
    private Text computerScoreText;
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
        Image icon = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\taskbarIcon.png");
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
        playerScoreText.setText("Player Score: " + player.getScore());
        computerScoreText.setText("Computer Score: " + computer.getScore());
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

        // Create a rectangle to surround the player's hand
        Rectangle rect = new Rectangle(50, 405, 1400, 125);
        rect.setFill(Color.GRAY);
        rect.setOpacity(0.6);

        mainPane.getChildren().addAll(rect, player);
    }

    /***
     * adds the ext info about the boneyard and computer to the display
     */
    private void createGameInfoText() {
        boneyard.setLayoutX(600);
        boneyard.setLayoutY(75);

        computer.setLayoutX(625);
        computer.setLayoutY(50);

        playerScoreText = new Text("Player Score: 0");
        playerScoreText.setFont(new Font("Verdana", 20));
        playerScoreText.setFill(Color.WHITE);
        playerScoreText.setLayoutX(50);
        playerScoreText.setLayoutY(50);

        computerScoreText = new Text("Computer Score: 0");
        computerScoreText.setFont(new Font("Verdana", 20));
        computerScoreText.setFill(Color.WHITE);
        computerScoreText.setLayoutX(50);
        computerScoreText.setLayoutY(100);

        mainPane.getChildren().addAll(boneyard,computer,playerScoreText,computerScoreText);
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
        createLeftRightButton();
        createHelpButton();

    }
    private void createHelpButton() {
        helpButton = new Button();
        Image image = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\question_mark.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        helpButton.setGraphic(imageView);
        helpButton.setLayoutX(1450);
        helpButton.setLayoutY(10);
        mainPane.getChildren().add(helpButton);

        helpButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 20;");

        // Set on click action to show image view
        helpButton.setOnAction(e -> {
            Image helpImage = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\help.png");
            ImageView helpView = new ImageView(helpImage);
            helpView.setLayoutX(2);
            helpView.setLayoutY(2);
            mainPane.getChildren().add(helpView);

            // Create close button and add to mainPane
            Button closeButton = new Button("Close");
            closeButton.setLayoutX(1450);
            closeButton.setLayoutY(5);
            closeButton.setOnAction(a -> {
                mainPane.getChildren().remove(closeButton);
                mainPane.getChildren().remove(helpView);
            });
            mainPane.getChildren().add(closeButton);
        });
    }

    /***
     * creates button the player uses to draw
     */
    private void createDrawButton() {
        drawButton = new Button("Draw");
        drawButton.setLayoutX(625);
        drawButton.setLayoutY(600);

        // Button Styling
        drawButton.setPrefWidth(100);
        drawButton.setPrefHeight(40);
        drawButton.setFont(Font.font("Verdana", 20));
        drawButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 20;");

        mainPane.getChildren().add(drawButton);

        drawButton.setOnAction(e ->{
            player.takeTurn("Draw");
        });
    }

    /***
     * creates radio buttons the player uses to determine if they are going
     * to attempt to play on the left or right side of the play area
     */
    private void createLeftRightButton() {
        HBox buttonBox = new HBox();

        ToggleButton leftBtn = new ToggleButton("Left");
        leftBtn.setPrefWidth(75);
        leftBtn.setPrefHeight(25);
        leftBtn.setStyle("-fx-background-color: #A9A9A9; -fx-background-radius: 5em;");
        leftBtn.setTextFill(Color.WHITE);

        ToggleButton rightBtn = new ToggleButton("Right");
        rightBtn.setPrefWidth(75);
        rightBtn.setPrefHeight(25);
        rightBtn.setStyle("-fx-background-color: #A9A9A9; -fx-background-radius: 5em;");
        rightBtn.setTextFill(Color.WHITE);
        leftBtn.setSelected(false);
        rightBtn.setSelected(true);

        buttonBox.getChildren().addAll(leftBtn, rightBtn);
        buttonBox.setLayoutX(785);
        buttonBox.setLayoutY(607);
        mainPane.getChildren().add(buttonBox);

        ToggleGroup group = new ToggleGroup();
        leftBtn.setToggleGroup(group);
        rightBtn.setToggleGroup(group);

        group.selectToggle(rightBtn);
        rightBtn.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 5em;");


        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            } else {
                if (newValue == leftBtn) {
                    player.setPlayDirection('l');
                    leftBtn.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 5em;");
                    rightBtn.setStyle("-fx-background-color: #A9A9A9; -fx-background-radius: 5em;");
                } else {
                    player.setPlayDirection('r');
                    rightBtn.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 5em;");
                    leftBtn.setStyle("-fx-background-color: #A9A9A9; -fx-background-radius: 5em;");
                }
            }
        });
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

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(675);
        playAgainButton.setLayoutY(300);
        playAgainButton.setPrefWidth(150);
        playAgainButton.setPrefHeight(50);
        playAgainButton.setFont(new Font("Verdana", 20));
        playAgainButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 20;");
        playAgainButton.setOnAction(e -> {
            restartGame();
        });

        mainPane.getChildren().addAll(winnerText, playAgainButton);
    }

    private void restartGame() {
        // Reset game variables
        boneyard.reset();
        board.reset();
        player.reset();
        computer.reset();
        turnMade = false;
        gameIsOver = false;
        lastPlayerComp = false;

        // Update displays
        player.updateDisplay();
        boneyard.updateDisplay();
        computer.updateDisplay();
        board.updateDisplay();
        playerScoreText.setText("Player Score: " + player.getScore());
        computerScoreText.setText("Computer Score: " + computer.getScore());

        // Remove game over elements
        mainPane.getChildren().clear();

        // Recreate game elements
        createGameInfoText();
        createPlayerHand();
        createPlayArea();
        createButtons();
        createBackGround();
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
                    playerScoreText.setText("Player Score: " + player.getScore());
                    computerScoreText.setText("Computer Score: " + computer.getScore());

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
        if(player.getHandLength() == 0) {
            gameIsOver = true;
        }

        if(computer.getHandLength() == 0) {
            gameIsOver = true;
        }

        // Check if neither player can play
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

