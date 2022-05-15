package io.github.dennis0324.jebi.gui.controller;

import java.util.Comparator;

import javafx.fxml.FXML;
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
    private void setupTable(){
        MFXTableColumn<User> uidColumn = new MFXTableColumn<>("사용자 ID",false,Comparator.comparing(User::getUid));
        MFXTableColumn<User> nameColumn = new MFXTableColumn<>("이름",false,Comparator.comparing(User::getName));
        MFXTableColumn<User> emailColumn = new MFXTableColumn<>("이메일",false,Comparator.comparing(User::getEmail));
        MFXTableColumn<User> phoneNumberColumn = new MFXTableColumn<>("전화번호",false,Comparator.comparing(User::getPhoneNumber));

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
    
    public void initialize(){
        setupTable();
        table.autosizeColumnsOnInitialization();
    }
    
    @Override
	public void onPageLoad() {
		/* TODO: ... */
	}
}
