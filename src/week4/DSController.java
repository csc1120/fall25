/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Controller for Window 1
 */
public class DSController {
    // Instance variable for the second stage so we can access it
    private Stage stage2;

    // Setter so that we can set the second stage instance once it is
    // created in the main class
    public void setStage2(Stage stage) {
        this.stage2 = stage;
    }

    @FXML
    private void open() {
        this.stage2.show();
    }

    @FXML
    private void close() {
        this.stage2.close();
    }
}
