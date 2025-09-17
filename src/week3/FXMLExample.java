/*
 * Course: CSC-1120
 * FXML Example
 * FXMLExample
 * Name: Sean Jones
 * Last Updated: 09-17-25
 */
package week3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXML Example that loads the fxml file into memory
 * and launches the program
 */
public class FXMLExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlexample.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("FXML Example");
        stage.show();
    }
}
