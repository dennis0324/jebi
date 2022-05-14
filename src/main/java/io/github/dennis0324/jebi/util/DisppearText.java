package io.github.dennis0324.jebi.util;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Labeled;

public class DisppearText {
    static public void setText(Labeled label, String errorMessage, int milliSec){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                label.setText(errorMessage);
                label.setVisible(true);
                delay(milliSec,() -> label.setVisible(false));
            }
            
        });
    }

    private static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
      }
      
}
