package countDownTimer;

import javafx.scene.text.Text;
import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CountdownTimer.
 */
public class CountdownTest {

    private CountdownTimer countdownTimer;
    private Text mockMessage;
    private MediaPlayer mockMediaPlayer;

    @Test
    public void testStartTimer() {
        // Ensure the timer starts when the start() method is called
        countdownTimer.start();
        assertTrue(countdownTimer.isRunning(), "Timer should be running after start()");
    }

    @Test
    public void testStopTimer() {
        countdownTimer.start();  // Start the timer first
        countdownTimer.stop();   // Now stop the timer

        assertFalse(countdownTimer.isRunning(), "Timer should not be running after stop()");
    }

    @Test
    public void testResetTimer() {
        countdownTimer.start();  // Start the timer
        countdownTimer.reset(10);  // Reset to 10 seconds

        // Verify the state is reset
        assertEquals(10, countdownTimer.getCounterValue(), "Timer should be reset to the given value");
        assertFalse(countdownTimer.isRunning(), "Timer should be stopped after reset");
    }

    @Test
    public void testCountdownUpdate() {
        // Start the timer with an initial value of 3 seconds
        countdownTimer.reset(3);
        countdownTimer.start();

        // Simulate the passage of time (1 second per update)
        countdownTimer.update();  // After 1 second
        assertEquals("2", mockMessage.getText(), "Message should be updated to 2");

        countdownTimer.update();  // After 2 seconds
        assertEquals("1", mockMessage.getText(), "Message should be updated to 1");

        countdownTimer.update();  // After 3 seconds (timer reaches 0)
        assertEquals("0", mockMessage.getText(), "Message should be updated to 0");
    }

    @Test
    public void testNoSoundBeforeZero() {
        // Start the timer with 2 seconds
        countdownTimer.reset(2);
        countdownTimer.start();

        // Simulate countdown without reaching zero
        countdownTimer.update();  // Countdown from 2 -> 1
        countdownTimer.update();  // Countdown from 1 -> 0

    }

    @Test
    public void testTimerDoesNotStartIfAlreadyRunning() {
        countdownTimer.start();
        countdownTimer.start(); // Try starting again when it's already running

        // Assert that the timer is running only once
        assertEquals(1, countdownTimer.timeline.getCurrentTime().toSeconds(), "Timer should not start again");
    }

    @Test
    public void testTimerStopsWhenStopped() {
        countdownTimer.start();
        countdownTimer.stop(); // Stop the timer

        assertFalse(countdownTimer.isRunning(), "Timer should stop after calling stop()");
    }
}