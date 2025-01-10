package countDownTimer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * A class that represents a countdown timer with visual and audio feedback.
 * The timer counts down from an initial value and displays the remaining time.
 * When the countdown reaches zero, it plays a sound.
 */
public class CountdownTimer {

    private int counterValue;
    private Text message;
    Timeline timeline;
    private MediaPlayer mediaPlayer;
    private boolean isRunning;

    /**
     * Constructs a CountdownTimer instance with an initial time value and a message display.
     * 
     * @param initialTime the starting time for the countdown in seconds.
     * @param message the Text object to display the countdown message.
     */
    public CountdownTimer(int initialTime, Text message) {
        this.setCounterValue(initialTime);
        this.message = message;
        this.isRunning = false;

        // Set up the media player for the sound
        Media sound = new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3");
        this.mediaPlayer = new MediaPlayer(sound);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Create the countdown logic using KeyFrame
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> update());
        this.timeline = new Timeline(keyFrame);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the countdown timer if it's not already running.
     */
    public void start() {
        if (!isRunning) {
            timeline.play();
            isRunning = true;
        }
    }

    /**
     * Stops the countdown timer if it's currently running.
     */
    public void stop() {
        if (isRunning) {
            timeline.stop();
            isRunning = false;
        }
    }

    /**
     * Resets the countdown timer to the specified initial time and updates the message display.
     * 
     * @param initialTime the starting time for the reset countdown in seconds.
     */
    public void reset(int initialTime) {
        stop();
        this.setCounterValue(initialTime);
        updateMessage();
    }

    /**
     * Updates the countdown by decrementing the time and updating the message display.
     * When the countdown reaches zero, the sound is played.
     */
    void update() {
        if (getCounterValue() > 0) {
            setCounterValue(getCounterValue() - 1);
            updateMessage();
        } else {
            updateMessage();
            mediaPlayer.play();  // Play sound when the countdown finishes
        }
    }
    
    /**
     * Updates the displayed message with the current countdown value.
     */
    private void updateMessage() {
        message.setText(String.valueOf(getCounterValue()));
    }

    /**
     * Returns the current running state of the timer.
     * 
     * @return true if the timer is currently running, false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

	public int getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(int counterValue) {
		this.counterValue = counterValue;
	}
}