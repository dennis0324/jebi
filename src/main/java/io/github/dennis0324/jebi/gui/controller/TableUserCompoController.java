/*
 * jebi: A book management software made with JavaFX.
 * 
 * Copyright (c) 2022 Dennis Ko (https://github.com/dennis0324)
 * Copyright (c) 2022 Jaedeok Kim (https://github.com/jdeokkim)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.dennis0324.jebi.gui.controller;

import java.util.ArrayList;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * 사용자 관리 메뉴 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableUserCompoController extends Controller {
	// `TableUserCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(TableUserCompoController.class);
    
    // `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 모든 사용자의 정보가 저장된 배열.
    private ObservableList<User> users = FXCollections.observableArrayList();
    
	@FXML
	private MFXTableView<User> userTable;
	
	@Override
	public void initialize() {
		onPageLoad();
	}
	
	@Override
	public void onPageLoad() {
		setupUserTable();
	}
	
	/**
	 * 사용자 정보 테이블을 초기화한다.
	 */
	@SuppressWarnings("unchecked")
	private void setupUserTable() {
		LOG.info("사용자 정보 테이블을 초기화합니다.");
		
		MFXTableColumn<User> uidColumn = new MFXTableColumn<>("고유 ID", false, Comparator.comparing(User::getUid));
        MFXTableColumn<User> nameColumn = new MFXTableColumn<>("이름", false, Comparator.comparing(User::getName));
        MFXTableColumn<User> emailColumn = new MFXTableColumn<>("이메일", false, Comparator.comparing(User::getEmail));
        MFXTableColumn<User> phoneNumberColumn = new MFXTableColumn<>("전화번호", false, Comparator.comparing(User::getPhoneNumber));
        MFXTableColumn<User> isAdminColumn = new MFXTableColumn<>("관리자 여부", false, Comparator.comparing(User::isAdmin));
        
        /* TODO: `TableViewHelper`를 이용하여 테이블 셀 생성하기 */
        
        uidColumn.setRowCellFactory(user -> new MFXTableRowCell<>(User::getUid));
        nameColumn.setRowCellFactory(user -> new MFXTableRowCell<>(User::getName));
        emailColumn.setRowCellFactory(user -> new MFXTableRowCell<>(User::getEmail));
        phoneNumberColumn.setRowCellFactory(user -> new MFXTableRowCell<>(User::getPhoneNumber));
        isAdminColumn.setRowCellFactory(user -> new MFXTableRowCell<>(User::isAdmin));
        
        userTable.getTableColumns().addAll(uidColumn, nameColumn, emailColumn, phoneNumberColumn, isAdminColumn);
        
        userTable.getFilters().addAll(
            new StringFilter<>("고유 ID", User::getUid),
            new StringFilter<>("이름", User::getName),
            new StringFilter<>("이메일", User::getEmail),
            new StringFilter<>("전화번호", User::getPhoneNumber),
            new StringFilter<>("관리자 여부", User::getPhoneNumber)
        );
        
        ApiFutures.addCallback(
            provider.getUsers(),
            new ApiFutureCallback<ArrayList<User>>() {
                @Override
                public void onSuccess(ArrayList<User> result) {
                	LOG.info("총 " + result.size() + "개의 사용자 계정을 찾았습니다.");
                	
                	Platform.runLater(
                		() -> {
                			users.addAll(result);
                			
                			userTable.setItems(users);
                		}
                	);
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
        
        // userTable.autosizeColumnsOnInitialization();
	}
}
