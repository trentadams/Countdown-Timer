package countDownTimer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A JavaFX application that creates a countdown timer with buttons to start, stop, and reset the timer.
 */
public class Timer extends Application {

    private static final double PANE_WIDTH = 400;
    private static final double PANE_HEIGHT = 400;

    private CountdownTimer countdownTimer;  // Instance of CountdownTimer class

    /**
     * Starts the JavaFX application and displays the timer interface.
     * 
     * @param primaryStage the primary stage for the JavaFX application.
     */
    public void start(Stage primaryStage) {
        // Create a Text object for the message
        Text message = new Text("30");  // Starting from 30
        message.setY(PANE_HEIGHT / 2);
        message.setX(PANE_WIDTH / 2);

        // Initialize the countdown timer
        countdownTimer = new CountdownTimer(30, message);

        // Create the Start, Stop, and Reset buttons
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button resetButton = new Button("Reset");

        // Start button action
        startButton.setOnAction(event -> {
            if (!countdownTimer.isRunning()) {
                countdownTimer.start();
            }
        });

        // Stop button action
        stopButton.setOnAction(event -> {
            if (countdownTimer.isRunning()) {
                countdownTimer.stop();
            }
        });

        // Reset button action
        resetButton.setOnAction(event -> {
            countdownTimer.reset(30);  // Reset to initial value (30)
        });

        // Create an HBox for the buttons and set their alignment
        HBox buttonBox = new HBox(10, startButton, stopButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a Pane to hold the message and buttons
        Pane pane = new Pane();
        pane.getChildren().addAll(message, buttonBox);

        // Position the button box at the bottom of the pane
        buttonBox.setLayoutX(125);
        buttonBox.setLayoutY(50);

        // Create and set the scene
        Scene scene = new Scene(pane, PANE_WIDTH, PANE_HEIGHT);
        primaryStage.setTitle("Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}