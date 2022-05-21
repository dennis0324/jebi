package io.github.dennis0324.jebi.gui.component;

import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.gui.controller.TableController;
import io.github.dennis0324.jebi.gui.TableViewHelper.Type;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CustomButtonEvent {
    static public class sendToDB implements EventHandler<MouseEvent> {
        private PageLoader pageLoader;
        public sendToDB(PageLoader pageLoader){
            this.pageLoader = pageLoader;
        }

        @Override
        public void handle(MouseEvent event) {
            // TODO 파이어베이스
            TableController tableController = (TableController)pageLoader.getArgument();
            if(tableController.getType() == Type.User){
                
            }
            else if(tableController.getType() == Type.Book){

            }

            
        }
    
    }
}
