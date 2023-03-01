package GUIGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BoardUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            primaryStage.close(); // close the start window
            startGame(new Stage()); // open the game window
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(startButton);
        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //@Override
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
