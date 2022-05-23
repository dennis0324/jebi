package io.github.dennis0324.jebi.gui.event;

import io.github.dennis0324.jebi.gui.PageLoader;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CustomEventHandler implements EventHandler<MouseEvent> {
    private Runnable myMethod;
    private PageLoader pageloader;
    public CustomEventHandler(Runnable myMethod){
        this.myMethod = myMethod;
    }
    @Override
    public void handle(MouseEvent arg0) {
        // TODO Auto-generated method stub
        myMethod.run();
    }

}
