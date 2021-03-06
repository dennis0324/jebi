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
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.WriteResult;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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
    
    // 테이블에서 선택한 사용자의 "관찰 가능한" 정보.
    private SimpleObjectProperty<User> userProperty = new SimpleObjectProperty<>(null);
    
	@FXML
	private MFXTableView<User> userTable;
	
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
	public void initialize() {
		users.addListener(
    		(ListChangeListener.Change<? extends User> c) -> {
    			userTable.setItems(users);
    		}
    	);
		
		onPageLoad();
	}
	
	@Override
	public void onPageLoad() {
		setupUserTable();
	}
	
	/**
	 * 테이블의 셀을 클릭했을 때 호출되는 메소드이다.
	 * 
	 * @param event 마우스 이벤트의 종류.
	 */
	private void onTableRowCellClicked(MouseEvent event) {
		ObservableMap<Integer, User> obMap = userTable.getSelectionModel()
			.getSelection();
		
		Iterator<Entry<Integer, User>> iterator = obMap.entrySet().iterator();
		
		clearSelection();
		
		if (iterator.hasNext()) {
			Entry<Integer, User> entry = iterator.next();
			
			LOG.info("테이블에서 키 값이 " + entry.getKey() + "인 사용자를 선택했습니다.");
			
			userProperty.set(entry.getValue());
		}
	}
	
	/**
	 * 테이블에서 선택한 사용자의 "관찰 가능한" 정보를 반환한다.
	 * 
	 * @return 테이블에서 선택한 사용자의 "관찰 가능한" 정보.
	 */
	public SimpleObjectProperty<User> getUserProperty() {
		return userProperty;
	}
	
	/**
	 * 주어진 이름을 가진 사용자를 생성하고, 테이블에 추가한다.
	 * 
	 * @param name 테이블에 추가할 사용자의 이름.
	 */
	public void addToTable(String name) {
		User user = new User(name, "", "", "");
		
		ApiFutures.addCallback(
            provider.createUser(user),
            new ApiFutureCallback<WriteResult>() {
                @Override
                public void onSuccess(WriteResult result) {
                	reloadUsers();
                	
                	Platform.runLater(() -> userProperty.set(user));
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
	}
	
	/**
	 * 테이블의 셀 선택을 해제한다. 
	 */
	public void clearSelection() {
		userTable.getSelectionModel().clearSelection();
		
		userProperty.set(null);
	}
	
	/**
	 * 모든 사용자의 정보를 다시 불러온다.
	 */
	public void reloadUsers() {
		ApiFutures.addCallback(
            provider.getUsers(),
            new ApiFutureCallback<ArrayList<User>>() {
                @Override
                public void onSuccess(ArrayList<User> result) {
                	LOG.info("총 " + result.size() + "개의 사용자 계정을 찾았습니다.");
                	
                	// 비동기 연산이 끝난 다음에 테이블을 업데이트한다.
                	Platform.runLater(() -> users.setAll(result));
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
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
        
        uidColumn.setRowCellFactory(user -> TableViewHelper.getRowCellFactory(User::getUid, this::onTableRowCellClicked));
        nameColumn.setRowCellFactory(user -> TableViewHelper.getRowCellFactory(User::getName, this::onTableRowCellClicked));
        emailColumn.setRowCellFactory(user -> TableViewHelper.getRowCellFactory(User::getEmail, this::onTableRowCellClicked));
        phoneNumberColumn.setRowCellFactory(user -> TableViewHelper.getRowCellFactory(User::getPhoneNumber, this::onTableRowCellClicked));
        isAdminColumn.setRowCellFactory(user -> TableViewHelper.getRowCellFactory(User::isAdmin, this::onTableRowCellClicked));
        
        userTable.getTableColumns().addAll(uidColumn, nameColumn, emailColumn, phoneNumberColumn, isAdminColumn);
        
        userTable.getFilters().addAll(
            new StringFilter<>("고유 ID", User::getUid),
            new StringFilter<>("이름", User::getName),
            new StringFilter<>("이메일", User::getEmail),
            new StringFilter<>("전화번호", User::getPhoneNumber),
            new StringFilter<>("관리자 여부", User::getPhoneNumber)
        );
        
        reloadUsers();
        
        // userTable.autosizeColumnsOnInitialization();
	}
}
