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

import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * 메인 테이블 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableController extends Controller {
    // 사용자가 선택한 메뉴의 인덱스.
    private SimpleIntegerProperty menuIndexProperty = new SimpleIntegerProperty(-1);
    
	// 사용자 계정 정보.
    private User user;
    
    /* ::: 메뉴 버튼... ::: */
    
    @FXML
    private MFXButton userMenuBtn;
    
    @FXML
    private MFXButton bookMenuBtn;
    
    /* ::: 좌측 하단 사용자 정보... ::: */
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label emailLabel;
	
	/* ::: 테이블 영역... ::: */
	
	@FXML
	private StackPane tablePane;
	
	@FXML
	private Parent tableUserCompo;
	
	@FXML
	private TableUserCompoController tableUserCompoController;
	
	@FXML
	private Parent tableBookCompo;
	
	@FXML
	private TableBookCompoController tableBookCompoController;
	
	/* ::: 검색 영역... ::: */
	
	@FXML
	private StackPane searchPane;
	
	@FXML
	private Parent searchCompo;
	
	@FXML
	private SearchCompoController searchCompoController;
	
	@Override
	public void initialize() {
		menuIndexProperty.addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue != newValue) {
					Platform.runLater(
						() -> searchCompoController.updateFilters(newValue.intValue())
					);
				}
			}
		);
		
		tableUserCompoController.getSelectedUser().addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue != newValue) {
					/* TODO: ... */
				}
			}
		);
		
		tableBookCompoController.getSelectedBook().addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue != newValue) {
					/* TODO: ... */
				}
			}
		);
	}

	@Override
	public void onPageLoad() {
		user = (User) getPageLoader().getArgument();
		
		// 사용자가 관리자 권한을 가지고 있지 않다면, 
		// 사용자가 볼 수 있는 메뉴를 제한한다.
		if (!user.isAdmin()) {
			// userMenuBtn.setVisible(false);
			// bookMenuBtn.setVisible(false);
			
			/* TODO: ... */
		}
    	
    	nameLabel.setText(user.getName());
    	emailLabel.setText(user.getEmail());
    	
    	updateTablePane(0);
	}
	
	@FXML
	public void onUserMenuBtnAction() {
		updateTablePane(0);
	}
	
	@FXML
	public void onBookMenuBtnAction() {
		updateTablePane(1);
	}
	
	/**
	 * 주어진 인덱스에 따라 테이블 영역을 업데이트한다.
	 * 
	 * @param menuIndex 사용자가 선택한 메뉴의 인덱스.
	 */
	private void updateTablePane(int menuIndex) {
		if (menuIndexProperty.get() == menuIndex) return;
		
		tableUserCompo.setManaged((menuIndex == 0));
		tableUserCompo.setVisible((menuIndex == 0));
		 
		tableBookCompo.setManaged((menuIndex == 1));
		tableBookCompo.setVisible((menuIndex == 1));
		
		menuIndexProperty.set(menuIndex);
	}
}