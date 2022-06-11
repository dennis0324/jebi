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
import com.google.cloud.firestore.WriteResult;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 계정 생성 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class RegisterController extends Controller {
	// `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();;
    
    // 입력한 사용자 정보로 계정 생성이 가능한지 여부.
    private boolean canRegister;
    
	@FXML
	private ImageView logoImage;
	
	@FXML
	private MFXTextField lastNameField;
	
	@FXML
	private MFXTextField firstNameField;
	
	@FXML
	private Label nameMsgLabel;
	
	@FXML
	private MFXTextField emailField;
	
	@FXML
	private Label emailMsgLabel;
	
	@FXML
	private MFXPasswordField passwordField;
	
	@FXML
	private Label passwordMsgLabel;
	
	@FXML
	private MFXTextField phoneNumberField;
	
	@FXML
	private Label phoneNumberMsgLabel;
	
	@FXML
	private MFXButton submitBtn;

	@FXML
    private MFXIconWrapper backBtn;

	@Override
	public void initialize() {
		nameMsgLabel.setManaged(false);
		emailMsgLabel.setManaged(false);
		passwordMsgLabel.setManaged(false);
		phoneNumberMsgLabel.setManaged(false);
		setupIconBtn();
	}
	
	@Override
	public void onPageLoad() {
		emailField.setText("");
		lastNameField.setText("");
		firstNameField.setText("");
		passwordField.setText("");
		phoneNumberField.setText("");
	}
	
	@FXML
	public void onSubmitBtnAction() {
		if (lastNameField.getText().isBlank() || firstNameField.getText().isBlank()) { 
			Animations.updateLabel(nameMsgLabel, Messages.ERROR_INVALID_NAME);
			
			return; 
		}

		if (!StringUtils.isValidEmail(emailField.getText())) { 
			Animations.updateLabel(emailMsgLabel, Messages.ERROR_INVALID_EMAIL);
			
			return; 
		}
		
		if (!StringUtils.isValidPassword(passwordField.getText())) { 
			Animations.updateLabel(passwordMsgLabel, Messages.ERROR_INVALID_PASSWORD);
			
			return; 
		}
		
		if (!StringUtils.isValidPhoneNumber(phoneNumberField.getText())) { 
			Animations.updateLabel(phoneNumberMsgLabel, Messages.ERROR_INVALID_PHONE_NUMBER);
			
			return; 
		}
			
		String name = lastNameField.getText() + firstNameField.getText();
		String email = emailField.getText();
		
		User user = new User(
			name, 
			email, 
			StringUtils.encrypt(passwordField.getText()), 
			phoneNumberField.getText()
		);
		
		canRegister = true;
		
		ApiFutures.addCallback(
            provider.getUserByEmail(email),
            new ApiFutureCallback<User>() {
                @Override
                public void onSuccess(User result) {
                	Platform.runLater(
                		() -> {
                			Animations.updateLabel(emailMsgLabel, Messages.ERROR_EMAIL_ALREADY_REGISTERED);
                		}
                	);
                	
                	canRegister = false;
                }
                
                @Override
                public void onFailure(Throwable t) {
                	Platform.runLater(
                		() -> {
                			Animations.updateLabel(emailMsgLabel, Messages.ERROR_UNKNOWN);
                		}
                	);
                }
            },
            provider.getThreadPool()
        );
		
		if (canRegister) {
			ApiFutures.addCallback(
				provider.createUser(user), 
				new ApiFutureCallback<WriteResult>() {
	                @Override
	                public void onSuccess(WriteResult result) {
	                	Platform.runLater(() -> getPageLoader().to("/pages/LoginFirst.fxml"));
	                }
	
	                @Override
	                public void onFailure(Throwable t) {
	                	/* TODO: ... */
	                }
	            },
				provider.getThreadPool()
			);
		}
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
		
    	backBtn.setIcon(icon);
    	backBtn.defaultRippleGeneratorBehavior();
    	backBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    	
    	NodeUtils.makeRegionCircular(backBtn);
	}
}
