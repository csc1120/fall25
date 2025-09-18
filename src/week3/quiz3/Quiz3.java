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
public class Quiz3 extends Application { // extends Application
    public static void main(String[] args) {
        launch(args); //main method calls launch
    }

    @Override // Overrides the abstract start method
    public void start(Stage stage) {
        // get the file containing the button text
        Path buttons = Paths.get("data", "buttons.txt");
        // make a parent container
        HBox hBox = new HBox();
        // try with resources to read the file
        try(Scanner read = new Scanner(buttons)) {
            while(read.hasNextLine()) {
                // for each line of text make a button using the text
                Button b = new Button(read.nextLine());
                // set a listener for the changeText
                b.setOnAction(this::changeText);
                // add the button to the children list of the container
                hBox.getChildren().add(b);
            }
        } catch(IOException e) {
            // show an Alert if there is an exception
            new Alert(Alert.AlertType.ERROR, "Could not load buttons").show();
        }
        // add the container to the Scene and the Scene to the Stage
        stage.setScene(new Scene(hBox));
        // Always give  your Stage a title
        stage.setTitle("Quiz 3");
        // show the Stage!
        stage.show();
    }

    // handler method should be private
    private void changeText(ActionEvent e) {
        // Get a reference to the Button from the event object it created when clicked
        ((Button) e.getSource()).setText("clicked");
    }
}
