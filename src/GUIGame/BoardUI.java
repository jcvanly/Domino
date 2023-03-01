package GUIGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        Label titleLabel = new Label("Dominoes");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        VBox vboxTitle = new VBox();
        vboxTitle.getChildren().add(titleLabel);
        vboxTitle.setAlignment(Pos.CENTER);

        VBox vboxAll = new VBox();
        vboxAll.getChildren().addAll(vboxTitle, vBox);
        vboxAll.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(vboxAll);
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
