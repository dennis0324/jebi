package io.github.dennis0324.jebi.gui.controller;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import io.github.dennis0324.jebi.gui.PageLoader;
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
        PageLoader pageLoader = (PageLoader)getPageLoader();
        MFXTableColumn<User> uidColumn = new MFXTableColumn<>("사용자 ID",false,Comparator.comparing(User::getUid));
        MFXTableColumn<User> nameColumn = new MFXTableColumn<>("이름",false,Comparator.comparing(User::getName));
        MFXTableColumn<User> emailColumn = new MFXTableColumn<>("이메일",false,Comparator.comparing(User::getEmail));
        MFXTableColumn<User> phoneNumberColumn = new MFXTableColumn<>("전화번호",false,Comparator.comparing(User::getPhoneNumber));

        uidColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table,pageLoader, "/pages/Component/userEditAddComponent.fxml"));
        nameColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table,pageLoader, "/pages/Component/userEditAddComponent.fxml"));
        emailColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table,pageLoader, "/pages/Component/userEditAddComponent.fxml"));
        phoneNumberColumn.setRowCellFactory(book -> TableViewHelper.toItemClickEventHandler(User::getUid, table, pageLoader,"/pages/Component/userEditAddComponent.fxml"));

        table.getTableColumns().addAll(uidColumn,nameColumn,emailColumn,phoneNumberColumn);
        table.getFilters().addAll(
            new StringFilter<>("책 ID",User::getUid),
            new StringFilter<>("책 이름",User::getName),
            new StringFilter<>("작가",User::getEmail),
            new StringFilter<>("출판사",User::getPhoneNumber)
        );
        table.setItems(FXCollections.observableArrayList(new User("testing","","",""),new User("testing","","","")));
    }
    
    public void initialize(){

    }
    
    @Override
	public void onPageLoad() {
        setupTable();
        table.autosizeColumnsOnInitialization();
	}
}
