package GUIGame;

/**
 * Jack Vanlyssel
 *
 * This class contains the starting screen that launches
 * the rest of the game.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BoardUI extends Application {

    public void start(Stage primaryStage) {
        // Create the start button
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Verdana", 20));
        // Apply styling
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-pref-width: 100px; -fx-pref-height: 25px;");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                startGame(primaryStage);
                ((Stage) startButton.getScene().getWindow()).close();

            }
        });

        // Create the VBoxes for the start button
        VBox vBoxStart = new VBox(startButton);
        vBoxStart.setAlignment(Pos.CENTER);

        // Add a spacer Region to the VBox to push the button to the bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        vBoxStart.getChildren().add(spacer);

        // Set the size of the spacer Region to take up two thirds of the available space in the VBox
        spacer.setPrefHeight(vBoxStart.getHeight() * 2.0 / 3.0);

        // Combine the VBoxes for the start button
        VBox vBoxAll = new VBox(vBoxStart);
        vBoxAll.setAlignment(Pos.BOTTOM_CENTER);
        vBoxAll.setPadding(new Insets(0, 0, 170, 0));

        // Create a new BorderPane and set the VBoxes as the center
        BorderPane layout = new BorderPane();
        layout.setCenter(vBoxAll);

        // Load the image to use as the background
        Image backgroundImage = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\domino.png");

        // Create a new BackgroundImage object using the image
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Set the background of the BorderPane to the BackgroundImage
        layout.setBackground(new Background(background));

        // Create the scene with the BorderPane as the root
        Scene scene = new Scene(layout, 1500, 700);

        Image icon = new Image("C:\\Users\\jackv\\IdeaProjects\\Domino\\src\\Resources\\taskbarIcon.png");
        primaryStage.getIcons().add(icon);
        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void startGame(Stage primaryStage) {
        GUIDominoes manager = new GUIDominoes();
        primaryStage = manager.getMainStage();
        primaryStage.show();
    }

    /***
     * launches the primary stage
     * @param args primaryStage
     */
    public static void main(String[] args) {
        launch(args);
    }

}
