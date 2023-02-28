package GUIGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BoardUI extends Application {

    @Override
    public void init() throws Exception {
        // Create a new button with the text "Start"
        Button startButton = new Button("Start");

        // Add an action event listener to the button that calls the start() method
        startButton.setOnAction(e -> start());

        // Create a new VBox layout and add the button to it
        VBox root = new VBox();
        root.getChildren().add(startButton);

        // Create a new scene with the VBox as the root and set its size
        Scene scene = new Scene(root, 400, 400);

        // Create a new stage for the scene
        stage = new Stage();
        stage.setScene(scene);

        // Set the title for the stage
        stage.setTitle("BoardUI");
    }


    @Override
    public void start(Stage primaryStage) {
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
