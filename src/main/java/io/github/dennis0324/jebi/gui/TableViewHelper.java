package io.github.dennis0324.jebi.gui;

import java.util.function.Function;

import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TableViewHelper {
    /**
     * 로그인 페이지 컨트롤러를 나타내는 클래스.
     * 
     * @param extractor 테이블 버튼 이벤트를 사용할 필드버튼
     * @param pathString 버튼을 누를경우에 txml 패스
     */
    public static <T,E> MFXTableRowCell<T,E> toItemClickEventHandler(Function<T,E> extractor,MFXTableView<T> table,String pathString){
        EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
            public void handle(final MouseEvent mouseEvent) {
                System.out.print(pathString);
                table.getSelectionModel().clearSelection();
                mouseEvent.consume();  
                // TableController tableController = (TableController)getPageLoader().getArgument();
                // openEditComponent(tableController.getContentArea(),pathString);
            }
        };
        MFXTableRowCell<T,E> uidColumnCallback = new MFXTableRowCell<>(extractor);
        uidColumnCallback.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
        return uidColumnCallback;
    }
}
