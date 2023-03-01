package GUIGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class BoardUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            primaryStage.close();
            startGame(new Stage());
        });

        VBox vBox = new VBox();
        vBox.getChildren().add(startButton);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().add(vBox);
        hBox.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(hBox);
        layout.setStyle("-fx-background-color: black;");

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
