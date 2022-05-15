package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.Book;

import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class TableBookCompoController extends Controller {

    private StackPane stackPane;

    @FXML
    private MFXTableView<Book> table;
    

    @Override
    public void onPageLoad() {}
    
    public void initialize() {
        setupTable();
        table.autosizeColumnsOnInitialization();

        // TODO Auto-generated method stub
        
    }
    
    @Override
	public void onPageLoad() {
		/* TODO: ... */
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

        uidColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getUid,table,"/pages/Component/editbookComponent.fxml"));
        nameColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getName,table,"/pages/Component/editbookComponent.fxml"));
        authorColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getAuthor,table,"/pages/Component/editbookComponent.fxml"));
        publisherColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getPublisher,table,"/pages/Component/editbookComponent.fxml"));
        pubDateColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getPubDate,table,"/pages/Component/editbookComponent.fxml"));
        categoryColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(Book::getCategory,table,"/pages/Component/editbookComponent.fxml"));

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

    
}