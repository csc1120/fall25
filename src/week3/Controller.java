/*
 * Course: CSC-1120
 * FXML Example
 * Controller
 * Name: Sean Jones
 * Last Updated: 09-17-25
 */
package week3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class that interfaces with the GUI
 */
public class Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText("You logged in!");
        alert.setContentText("Secure, aren't we?");
        alert.show();
    }

    @FXML
    private void cancel() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
