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

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.DatabaseMode;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * 메뉴의 종류를 나타내는 열거형.
 * 
 * @author dennis0324
 */
enum MenuType {
	UNKNOWN(-1),
	USER(0),
	BOOK(1);
	
	// 메뉴의 종류를 나타내는 값.
	private int value;
	
	/**
	 * `MenuType`의 생성자.
	 * 
	 * @param value 메뉴의 종류를 나타내는 인덱스.
	 */
	MenuType(int value) {
		this.value = value;
	}
	
	/**
	 * 메뉴의 종류에 해당하는 정수를 반환한다.
	 * 
	 * @return 메뉴의 종류에 해당하는 정수.
	 */
	int get() {
		return value;
	}
}

/**
 * 메인 테이블 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableController extends Controller {
    // 사용자가 선택한 메뉴의 "관찰 가능한" 인덱스.
    private static SimpleObjectProperty<MenuType> menuTypeProperty;
    
    // `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 접속한 사용자 계정의 정보.
    private static User user;
    
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
	
	/* ::: 데이터 추가 영역... ::: */
	
	@FXML
	private TextField addToTableField;
	
	@FXML
	private MFXButton addToTableBtn;
	
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


	@FXML
	private Parent IdleCompo;
	
	@FXML
	private TableBookCompoController idleStateCompoController;
	
	/* ::: 사용자 추가 및 편집 영역... ::: */
	
	@FXML
	private Parent userEditAddCompo;
	
	@FXML
	private UserEditAddCompoController userEditAddCompoController;
	
    /* ::: 책 추가 및 편집 영역... ::: */
	
	@FXML
	private Parent bookEditAddCompo;
	
	@FXML
	private BookEditAddCompoController bookEditAddCompoController;
	
	/*
	 * 클래스 생성 직전에 호출된다.
	 */
	static {
		menuTypeProperty = new SimpleObjectProperty<>(MenuType.UNKNOWN);
	}
	
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
	public void initialize() {
		// 선택한 메뉴에 따라 보여줄 내용을 변경한다.
		menuTypeProperty.addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue != newValue) {
					Platform.runLater(
						() -> {
							tableUserCompoController.clearSelection();
							tableBookCompoController.clearSelection();
							
							if (newValue == MenuType.USER) {
								tableUserCompo.setManaged(true);
								tableUserCompo.setVisible(true);
								
								IdleCompo.setManaged(true);
								IdleCompo.setVisible(true);
								
								tableBookCompo.setManaged(false);
								tableBookCompo.setVisible(false);

								userEditAddCompo.setManaged(false);
								userEditAddCompo.setVisible(false);
								
								userEditAddCompoController.setCurrentUser(null);
								bookEditAddCompoController.setCurrentUser(null);
							} else if (newValue == MenuType.BOOK) {
								tableUserCompo.setManaged(false);
								tableUserCompo.setVisible(false);
								
								tableBookCompo.setManaged(true);
								tableBookCompo.setVisible(true);
								
								tableUserCompoController.getUserProperty().set(user);
								
								userEditAddCompo.setManaged(true);
								userEditAddCompo.setVisible(true);
								
								userEditAddCompoController.setCurrentUser(user);
								bookEditAddCompoController.setCurrentUser(user);
							}
							
							bookEditAddCompo.setManaged(false);
							bookEditAddCompo.setVisible(false);
						}
					);
				}
			}
		);
		 
		// 책 관리 메뉴를 먼저 보여준다.
		menuTypeProperty.set(MenuType.BOOK);
		
		// 테이블에서 선택한 사용자에 따라 보여줄 내용을 변경한다.
		tableUserCompoController.getUserProperty().addListener(
			(observable, oldValue, newValue) -> {				
				userEditAddCompo.setManaged((newValue != null));
				userEditAddCompo.setVisible((newValue != null));
				
				userEditAddCompoController.setBackBtnVisible((newValue != user));
				
				userEditAddCompoController.updateData(newValue);
			}
		);
		
		// 테이블에서 선택한 책에 따라 보여줄 내용을 변경한다.
		tableBookCompoController.getBookProperty().addListener(
			(observable, oldValue, newValue) -> {
				userEditAddCompo.setManaged((newValue == null));
				userEditAddCompo.setVisible((newValue == null));
				
				bookEditAddCompo.setManaged((newValue != null));
				bookEditAddCompo.setVisible((newValue != null));
				
				bookEditAddCompoController.updateData(newValue);
			}
		);
		
		// 사용자가 '이전' 버튼을 클릭했을 때의 동작을 지정한다.
		userEditAddCompoController.getBackProperty().addListener(
			(observable, oldValue, newValue) -> {
				tableUserCompoController.getUserProperty().set(null);
			}
		);
		
		// 사용자가 '이전' 버튼을 클릭했을 때의 동작을 지정한다.
		bookEditAddCompoController.getBackProperty().addListener(
			(observable, oldValue, newValue) -> {
				tableBookCompoController.getBookProperty().set(null);
				
				userEditAddCompoController.setBackBtnVisible(false);
			}
		);
		
		// 사용자가 다음으로 수행할 데이터베이스 작업을 변경했을 때의 동작을 지정한다.
		userEditAddCompoController.getDatabaseModeProperty().addListener(
			(observable, oldValue, newValue) -> {
				if (newValue == DatabaseMode.RELOAD) {
					ApiFutures.addCallback(
						provider.getUserByUid(user.getUid()),
						new ApiFutureCallback<User>() {
			                @Override
			                public void onSuccess(User result) {
			                	Platform.runLater(
			                		() -> {
			                			user = result;
			                			
			                			tableUserCompoController.reloadUsers();
			                			
			                			userEditAddCompoController.updateData(user);
			        					userEditAddCompoController.getDatabaseModeProperty()
			        						.set(DatabaseMode.EDIT);
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
				}
			}
		);
		
		// 사용자가 다음으로 수행할 데이터베이스 작업을 변경했을 때의 동작을 지정한다.
		bookEditAddCompoController.getDatabaseModeProperty().addListener(
			(observable, oldValue, newValue) -> {
				if (newValue == DatabaseMode.RELOAD) {
					ApiFutures.addCallback(
						provider.getUserByUid(user.getUid()),
						new ApiFutureCallback<User>() {
			                @Override
			                public void onSuccess(User result) {
			                	Platform.runLater(
			                		() -> {
			                			user = result;
			                			
			                			tableBookCompoController.reloadBooks();
			        					
			        					userEditAddCompoController.updateData(user);
			        					bookEditAddCompoController.getDatabaseModeProperty()
			        						.set(DatabaseMode.EDIT);
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
			userMenuBtn.setVisible(false);
			bookMenuBtn.setVisible(false);
			
			bookEditAddCompoController.getEditToggleButton().setVisible(false);
			bookEditAddCompoController.getSaveBtn().setVisible(false);
			bookEditAddCompoController.getDeleteBtn().setVisible(false);
		}
    	
    	nameLabel.setText(
    		user.getName()
    		+ (user.isAdmin() ? " (관리자)" : " (일반)")
    	);
    	
    	emailLabel.setText(user.getEmail());
	}
	
	@FXML
	public void onAddToTableBtnAction() {
		String name = addToTableField.getText();
		
		if (name.isBlank()) {
			// TODO: 오류 메시지 출력
			
			return;
		}
		
		if (menuTypeProperty.get() == MenuType.USER) 
			tableUserCompoController.addToTable(name);
		else if (menuTypeProperty.get() == MenuType.BOOK) 
			tableBookCompoController.addToTable(name);
	}
	
	@FXML
	public void onUserMenuBtnAction() {
		menuTypeProperty.set(MenuType.USER);
	}
	
	@FXML
	public void onBookMenuBtnAction() {
		menuTypeProperty.set(MenuType.BOOK);
	}
}