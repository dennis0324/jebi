package io.github.dennis0324.jebi.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import io.github.dennis0324.jebi.model.Book;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

public class TableBookCompoController extends Controller {

    @FXML
    private MFXTableView<Book> table;
    
    
    public void initialize() {
        setupTable();
        table.autosizeColumnsOnInitialization();

        // TODO Auto-generated method stub
        
    }
    
    @Override
	public void onPageLoad() {
		/* TODO: ... */
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
    
}
