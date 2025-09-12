/*
 * Course: CSC-1120
 * Java FX Example
 * JavaFXExample
 * Name: Sean Jones
 * Last Updated: 09-12-25
 */
package week2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Simple JavaFX Example Program
 */
public class JavaFXExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final int width = 300;
        final int height = 300;
        // Make the panes (containers)
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        vBox.setAlignment(Pos.CENTER);
        // Make the nodes
        Button button1 = new Button("Click Me");
        Button button2 = new Button("No, Click ME!");
        TextField field = new TextField();
        // add the nodes to their containers
        hBox.getChildren().add(button1);
        hBox.getChildren().add(button2);
        vBox.getChildren().addAll(field, hBox);
        // add the top-level "parent" container to the scene
        Scene scene = new Scene(vBox, width, height);
        // add the scene to the stage
        stage.setScene(scene);
        stage.setTitle("JavaFX Example");
        // show the stage
        stage.show();
    }
}
