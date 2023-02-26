package GUIGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a new button with the text "Start Game"
        Button startButton = new Button("Start Game");

        // Add an event handler for when the button is clicked
        startButton.setOnAction(event -> {
            // Start the game
            startGame();
        });

        // Add the button to a vertical box layout
        VBox layout = new VBox();
        layout.getChildren().add(startButton);

        // Create a new scene with the layout
        Scene scene = new Scene(layout, 400, 300);

        // Set the title of the stage and show the scene
        primaryStage.setTitle("Game Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to start the game
    private void startGame() {
        // TODO: Add code to start the game
        System.out.println("Starting game...");
    }
}