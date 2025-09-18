/*
 * Course: CSC-1120
 * Lab Quiz 3
 * Quiz3
 * Name: Sean Jones
 * Last Updated: 09-18-25
 */
package week3.quiz3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A happy, joyful quiz full of unicorns and rainbows
 */
public class Quiz3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Path buttons = Paths.get("data", "buttons.txt");
        HBox hBox = new HBox();
        try(Scanner read = new Scanner(buttons)) {
            while(read.hasNextLine()) {
                Button b = new Button(read.nextLine());
                b.setOnAction(this::changeText);
                hBox.getChildren().add(b);
            }
        } catch(IOException e) {
            new Alert(Alert.AlertType.ERROR, "Could not load buttons").show();
        }
        stage.setScene(new Scene(hBox));
        stage.setTitle("Quiz 3");
        stage.show();
    }

    private void changeText(ActionEvent e) {
        ((Button) e.getSource()).setText("clicked");
    }
}
