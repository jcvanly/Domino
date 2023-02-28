package GUIGame;

import javafx.application.Application;
import javafx.stage.Stage;


public class BoardUI extends Application {
    /***
     * Creates GameManagerGui object and sets the
     * primaryStage to the GameManagerGui stage
     * @param primaryStage staged launched
     */
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
