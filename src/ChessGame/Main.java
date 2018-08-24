package ChessGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Board root = Board.getInstance();


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


}
