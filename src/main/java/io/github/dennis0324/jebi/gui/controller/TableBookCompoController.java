package io.github.dennis0324.jebi.gui.controller;

import java.util.function.Function;
import java.io.IOException;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import io.github.dennis0324.jebi.model.Book;

import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class TableBookCompoController extends Controller {

    private StackPane stackPane;

    @FXML
    private MFXTableView<Book> table;
    
    
    public void initialize() {


        setupTable();
        table.autosizeColumnsOnInitialization();

        // TODO Auto-generated method stub
        
    }

    /**
     * Book이라는 테이블을 초기화해주는 함수부분이다.
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    private void setupTable(){
        MFXTableColumn<Book> uidColumn = new MFXTableColumn<>("책 ID",false,Comparator.comparing(Book::getUid));
        MFXTableColumn<Book> nameColumn = new MFXTableColumn<>("책 이름",false,Comparator.comparing(Book::getName));
        MFXTableColumn<Book> authorColumn = new MFXTableColumn<>("작가",false,Comparator.comparing(Book::getAuthor));
        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("출판사",false,Comparator.comparing(Book::getPublisher));
        MFXTableColumn<Book> pubDateColumn = new MFXTableColumn<>("출판 날짜",false,Comparator.comparing(Book::getPubDate));
        MFXTableColumn<Book> categoryColumn = new MFXTableColumn<>("카테고리",false,Comparator.comparing(Book::getCategory));

        uidColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getUid,"/pages/Component/editbookComponent.fxml"));
        nameColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getName,"/pages/Component/editbookComponent.fxml"));
        authorColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getAuthor,"/pages/Component/editbookComponent.fxml"));
        publisherColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getPublisher,"/pages/Component/editbookComponent.fxml"));
        pubDateColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getPubDate,"/pages/Component/editbookComponent.fxml"));
        categoryColumn.setRowCellFactory(book -> toItemClickEventHandler(Book::getCategory,"/pages/Component/editbookComponent.fxml"));

        table.getTableColumns().addAll(uidColumn,nameColumn,authorColumn,publisherColumn,pubDateColumn,categoryColumn);

        
        table.getFilters().addAll(
            new StringFilter<Book>("책 ID",Book::getUid),
            new StringFilter<>("책 이름",Book::getName),
            new StringFilter<>("작가",Book::getAuthor),
            new StringFilter<>("출판사",Book::getPublisher),
            new StringFilter<>("출판 날짜",Book::getPubDate),
            new IntegerFilter<>("카테고리",Book::getCategory)
            );
            table.setItems(FXCollections.observableArrayList(new Book("testing"),new Book("testing")));
    }


    /**
     * 로그인 페이지 컨트롤러를 나타내는 클래스.
     * 
     * @param extractor 테이블 버튼 이벤트를 사용할 필드버튼
     * @param pathString 버튼을 누를경우에 txml 패스
     */
    public <T,E> MFXTableRowCell<T,E> toItemClickEventHandler(Function<T,E> extractor,String pathString){
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

    // /**
    //  * 로그인 페이지 컨트롤러를 나타내는 클래스.
    //  * 
    //  * @param extractor 테이블 버튼 이벤트를 사용할 필드버튼
    //  * @param textString 버튼을 누를경l우에 실행되는 텍스트
    //  */
    // public <T,E,T2,E2> MFXTableRowCell<T,E> toItemClickEventHandler(Function<T,E> extractor,Function<T2,E2> function){
    //     EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
    //         public void handle(final MouseEvent mouseEvent) {
    //             // TODO add buttonAction function
    //         }
    //     };
    //     MFXTableRowCell<T,E> uidColumnCallback = new MFXTableRowCell<>(extractor);
    //     uidColumnCallback.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
    //     return uidColumnCallback;
    // }


    private void openEditComponent(StackPane stackPane, String pathString){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(pathString));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(parent);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    
}
