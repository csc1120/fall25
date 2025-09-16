/*
 * Course: CSC-1120
 * Event Handling in JavaFX
 * week3.EventHandling
 * Name: Sean Jones
 * Last Updated: 09-15-25
 */
package week3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * An example of Event Handling in JavaFX that starts
 * with a separate handler class and eentually shrinks
 * the code down to a single method reference and a
 * helper method
 */
public class EventHandling extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final int width = 400;
        final int height = 300;
        Pane root = new FlowPane();
        Scene scene = new Scene(root, width, height);
        Button button = new Button("JavaFX Day 2");
        /*
        button.setOnAction(new ButtonHandler());
        // Anonymous inner class
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button btn = (Button)actionEvent.getSource();
                if(btn.getEffect() == null) {
                    btn.setEffect(new BoxBlur());
                } else {
                    btn.setEffect(null);
                }
            }
        });
        */
        // lambda expression
        // button.setOnAction(e -> blurButton(e));
        // Method Reference
        button.setOnAction(this::blurButton);
        root.getChildren().add(button);
        stage.setScene(scene);
        stage.setTitle("Exception Handling");
        stage.show();
    }

    private void blurButton(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (button.getEffect() == null) {
            button.setEffect(new BoxBlur());
        } else {
            button.setEffect(null);
        }
    }

    /*
     * Private Inner class
     *
     * In class, I did not make this static. Inner class may often
     * be static classes. We will talk about this when we cover
     * LinkedLists
     */
    private static class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            System.out.println("I was Clicked");
        }
    }
}