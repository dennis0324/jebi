package io.github.dennis0324.jebi.gui.controller;

import java.util.Comparator;

import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.scene.input.MouseEvent;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.scene.input.MouseEvent;


public class TableUserCompoController extends Controller {@FXML
    private MFXTableView<User> table;
    
    

=======
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class TableUserCompoController extends Controller {@FXML
    private MFXTableView<User> table;
    

    /**
     * User이라는 테이블을 초기화해주는 함수부분이다.
     * 
     * 
     */
    @SuppressWarnings("unchecked")
>>>>>>> e2cd5484f627acaf49677037bcd07e728d8c33e9
    private void setupTable(){
        MFXTableColumn<User> uidColumn = new MFXTableColumn<>("사용자 ID",false,Comparator.comparing(User::getUid));
        MFXTableColumn<User> nameColumn = new MFXTableColumn<>("이름",false,Comparator.comparing(User::getName));
        MFXTableColumn<User> emailColumn = new MFXTableColumn<>("이메일",false,Comparator.comparing(User::getEmail));
        MFXTableColumn<User> phoneNumberColumn = new MFXTableColumn<>("전화번호",false,Comparator.comparing(User::getPhoneNumber));

<<<<<<< HEAD
        uidColumn.setRowCellFactory(book -> new MFXTableRowCell<>(User::getUid));
        nameColumn.setRowCellFactory(book -> new MFXTableRowCell<>(User::getName));
        emailColumn.setRowCellFactory(book -> new MFXTableRowCell<>(User::getEmail));
        phoneNumberColumn.setRowCellFactory(book -> new MFXTableRowCell<>(User::getPhoneNumber));

        table.getTableColumns().addAll(uidColumn,nameColumn,emailColumn,phoneNumberColumn);
        // table.getFilters().addAll(
        //     new StringFilter<>("책 ID",Book::getUid),
        //     new StringFilter<>("책 이름",Book::getName),
        //     new StringFilter<>("작가",Book::getAuthor),
        //     new StringFilter<>("출판사",Book::getPublisher),
        //     new StringFilter<>("출판 날짜",Book::getPubDate),
        //     new IntegerFilter<>("카테고리",Book::getCategory)
        // );
    }
    

    public void initialize(){


        setupTable();
        table.autosizeColumnsOnInitialization();
        // table.setItems()
=======
        uidColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table, "to edit user"));
        nameColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table, "to edit user"));
        emailColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table, "to edit user"));
        phoneNumberColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table, "to edit user"));

        table.getTableColumns().addAll(uidColumn,nameColumn,emailColumn,phoneNumberColumn);
        table.getFilters().addAll(
            new StringFilter<>("책 ID",User::getUid),
            new StringFilter<>("책 이름",User::getName),
            new StringFilter<>("작가",User::getEmail),
            new StringFilter<>("출판사",User::getPhoneNumber)
        );
    }
    
    @Override
    public void onPageLoad() {}

    public void initialize(){
        setupTable();
        table.autosizeColumnsOnInitialization();
>>>>>>> e2cd5484f627acaf49677037bcd07e728d8c33e9
    }
}
