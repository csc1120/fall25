/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Controller for the Lab 3 Example
 */
public class Lab3Controller {
    @FXML
    private TextArea textArea;
    // chooser instance variable so it can be accessed in all handler methods
    private final FileChooser chooser = new FileChooser();

    @FXML
    private void open() {
        // creates a file object for your target file
        File file = chooser.showOpenDialog(null);
        // make sure the file isn't null
        if(file != null) {
            try (Scanner read = new Scanner(file)) {
                // use StringBuilder to gather all the text
                StringBuilder sb = new StringBuilder();
                while (read.hasNextLine()) {
                    sb.append(read.nextLine()).append("\n");
                }
                // set the text to the textArea
                this.textArea.setText(sb.toString());
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Could not open file").show();
            }
        }
    }

    @FXML
    private void save() {
        // create the save target file
        File file = chooser.showSaveDialog(null);
        // make sure it's not null
        if(file != null) {
            try(PrintWriter pw = new PrintWriter(file)) {
                // write the contents of the textArea to the file
                pw.println(this.textArea.getText());
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File Error");
                alert.setHeaderText("Oh no!!!!");
                alert.setContentText("You couldn't save it!");
                alert.show();
            }
        }
    }
}
