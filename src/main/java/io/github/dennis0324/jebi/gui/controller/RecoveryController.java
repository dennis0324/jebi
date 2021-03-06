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

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;

/**
 * 이메일 및 비밀번호 찾기 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class RecoveryController extends Controller {
	// `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    @FXML
    private TabPane tabPane;
    
    /* ::: '이메일 찾기' 탭... ::: */
    
    @FXML
    private MFXTextField lastNameField0;
    
    @FXML
    private MFXTextField firstNameField0;
    
    @FXML
    private Label nameMsgLabel0;
    
    @FXML
    private MFXTextField phoneNumberField;
    
    @FXML
    private Label phoneNumberMsgLabel;
    
    @FXML
    private Label emailQueryLabel;
    
    @FXML
    private MFXButton findEmailBtn;
    
    /* ::: '비밀번호 찾기' 탭... ::: */
    
    @FXML
    private MFXTextField lastNameField1;
    
    @FXML
    private MFXTextField firstNameField1;
    
    @FXML
    private Label nameMsgLabel1;
    
    @FXML
    private MFXTextField emailField;
    
    @FXML
    private Label emailMsgLabel;
    
    @FXML
    private MFXButton findPasswordBtn;

	@FXML
    private MFXIconWrapper backIconBtn;
    
    /* ::: 컨트롤러 기본 메소드 정의... ::: */

	@Override
	public void initialize() {
		// 탭이 변경되어도 사용자가 작성한 이름이 지워지지 않도록 한다.
		tabPane.getSelectionModel().selectedIndexProperty().addListener(
			(observable, oldValue, newValue) -> {
				if (oldValue.intValue() == 0) {
					lastNameField1.setText(lastNameField0.getText());
					firstNameField1.setText(firstNameField0.getText());
					
					nameMsgLabel0.setManaged(false);
					phoneNumberMsgLabel.setManaged(false);
					
					emailQueryLabel.setManaged(false);
				} else if (oldValue.intValue() == 1) {
					lastNameField0.setText(lastNameField1.getText());
					firstNameField0.setText(firstNameField1.getText());
					
					nameMsgLabel1.setManaged(false);
					emailMsgLabel.setManaged(false);
				}
			}
		);


		setupIconBtn();
	}
	
	@Override
	public void onPageLoad() {
		int arg = (int) getPageLoader().getArgument();
		
		// 페이지 매개 변수에 따라 적절한 탭을 선택한다.
		tabPane.getSelectionModel().select(arg);
		
		nameMsgLabel0.setManaged(false);
		phoneNumberMsgLabel.setManaged(false);
		
		emailQueryLabel.setManaged(false);
		
		nameMsgLabel1.setManaged(false);
		emailMsgLabel.setManaged(false);
	}
	
	@FXML
	public void onFindEmailBtnAction() {
		if (lastNameField0.getText().isBlank() || firstNameField0.getText().isBlank()) { 
			Animations.updateLabel(nameMsgLabel0, Messages.ERROR_INVALID_NAME);
			
			return;
		}
		
		String phoneNumber = phoneNumberField.getText();

		if (!StringUtils.isValidPhoneNumber(phoneNumberField.getText())) { 
			Animations.updateLabel(phoneNumberMsgLabel, Messages.ERROR_INVALID_PHONE_NUMBER);
			
			return;
		}
		
		ApiFutures.addCallback(
            provider.getUserByPhoneNumber(phoneNumber),
            new ApiFutureCallback<User>() {
                @Override
                public void onSuccess(User result) {
                	String uid = result.getUid();
                	
                    if (uid != null) {
	                	String name = lastNameField0.getText() + firstNameField0.getText();
	                	
	                	Platform.runLater(
	                		() -> Animations.updateLabel(
	                			nameMsgLabel0, 
	                			name.equals(result.getName()) ? result.getEmail() : Messages.ERROR_USER_NOT_FOUND
	                		)
	                	);
                    } else {
                    	Platform.runLater(() -> Animations.updateLabel(phoneNumberMsgLabel, Messages.ERROR_USER_NOT_FOUND));
                    }
                }
                
                @Override
                public void onFailure(Throwable t) {
                	Platform.runLater(() -> Animations.updateLabel(emailQueryLabel, Messages.ERROR_UNKNOWN));
                	
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
	}
	
	@FXML
	public void onFindPasswordBtnAction() {
		if (lastNameField1.getText().isBlank() || firstNameField1.getText().isBlank()) { 
			Animations.updateLabel(nameMsgLabel1, Messages.ERROR_INVALID_NAME);
			
			return;
		}
		
		String email = emailField.getText();

		if (!StringUtils.isValidEmail(email)) { 
			Animations.updateLabel(emailMsgLabel, Messages.ERROR_INVALID_EMAIL);
			
			return;
		}
		
		ApiFutures.addCallback(
            provider.getUserByEmail(email),
            new ApiFutureCallback<User>() {
                @Override
                public void onSuccess(User result) {
                	String uid = result.getUid();
                	
                    if (uid != null) {
	                	String name = lastNameField1.getText() + firstNameField1.getText();
	                	
	                	if (name.equals(result.getName()))
	                		Platform.runLater(() -> getPageLoader().to("/pages/ResetPassword.fxml", result));
	                	else 
	                		Platform.runLater(() -> Animations.updateLabel(nameMsgLabel1, Messages.ERROR_USER_NOT_FOUND));
                    } else {
                    	Platform.runLater(() -> Animations.updateLabel(emailMsgLabel, Messages.ERROR_USER_NOT_FOUND));
                    }
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
	}
	
	@FXML
    public void onBackIconBtnClicked() {
		getPageLoader().to("/pages/LoginFirst.fxml");
    }

	/**
     * 아이콘 버튼을 초기화한다.
     */
    private void setupIconBtn() {
    	MaterialIconView icon = new MaterialIconView(MaterialIcon.CHEVRON_LEFT, "35");
		
    	backIconBtn.setIcon(icon);
    	backIconBtn.defaultRippleGeneratorBehavior();
    	backIconBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    	
    	NodeUtils.makeRegionCircular(backIconBtn);
	}
}
