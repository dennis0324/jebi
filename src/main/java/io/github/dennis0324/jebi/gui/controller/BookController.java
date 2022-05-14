package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;



import io.github.dennis0324.jebi.model.Book;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;



class TextFieldListener implements EventHandler<ActionEvent> {
    private final TextField textField ;
    TextFieldListener(TextField textField) {
      this.textField = textField ;
    }

    @Override
    public void handle(ActionEvent arg0) {
        System.out.println("testing");
        // TODO Auto-generated method stub
        
    }
}


public class BookController extends Controller implements Initializable {

    @FXML
    private MFXTableView<Book> table;

    @FXML
    private StackPane contentArea;

    @FXML
    private TextField searchBar;

    @FXML
    private MFXButton searchButton;

    @FXML 
    private HBox searchContainer;


    @FXML
    void testingAction(ActionEvent event) {
        System.out.println(event);
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

        searchBar.focusedProperty().addListener(new InvalidationListener() {               
            @Override
            public void invalidated(Observable observable) {
                //here it is changing
                if(searchBar.isFocused()){
                    System.out.print("testing");
                }
                else{
                    System.out.print("testing2");
                }
            }
        });

        searchButton.setOnMouseClicked(event -> {
            System.out.println("mouse clicked");
        });


        setcontentArea();

        setupTable();
        table.autosizeColumnsOnInitialization();
    }

    private void setupTable(){
        MFXTableColumn<Book> uidColumn = new MFXTableColumn<>("책 ID",false,Comparator.comparing(Book::getUid));
        MFXTableColumn<Book> nameColumn = new MFXTableColumn<>("책 이름",false,Comparator.comparing(Book::getName));
        MFXTableColumn<Book> authorColumn = new MFXTableColumn<>("작가",false,Comparator.comparing(Book::getAuthor));
        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("출판사",false,Comparator.comparing(Book::getPublisher));
        MFXTableColumn<Book> pubDateColumn = new MFXTableColumn<>("출판 날짜",false,Comparator.comparing(Book::getPubDate));
        MFXTableColumn<Book> categoryColumn = new MFXTableColumn<>("카테고리",false,Comparator.comparing(Book::getCategory));


        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getUid));
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getName));
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getAuthor));
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getPublisher));
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getPubDate));
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getCategory));

        table.getTableColumns().addAll(uidColumn,nameColumn,authorColumn,publisherColumn,pubDateColumn,categoryColumn);
        // table.getFilters().addAll(
        //     new StringFilter<>("책 ID",Book::getUid),
        //     new StringFilter<>("책 이름",Book::getName),
        //     new StringFilter<>("작가",Book::getAuthor),
        //     new StringFilter<>("출판사",Book::getPublisher),
        //     new StringFilter<>("출판 날짜",Book::getPubDate),
        //     new IntegerFilter<>("카테고리",Book::getCategory)
        // );
    }

    private void setcontentArea(){
        Parent parent;
        try{
            parent = FXMLLoader.load(getClass().getResource("/pages/components/nonSelectedComponent.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
