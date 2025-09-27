/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Simple example of multiple windows in JavaFX
 */
public class DifferentStages extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("differentstages.fxml"));
        Parent root = loader1.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Stage 1");

        // make the second stage
        Stage stage2 = new Stage();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("differentstages2.fxml"));
        Parent root2 = loader2.load();
        stage2.setScene(new Scene(root2));
        stage2.setTitle("Stage 2");

        // set starting location of stage2 to make it not
        // overlap the first stage
        stage2.setX(0);
        stage2.setY(0);

        // get an instance of the DSController from the loader
        DSController controller = loader1.getController();
        // set the second stage to an instance variable in the first stage
        controller.setStage2(stage2);

        stage.show();
    }
}
