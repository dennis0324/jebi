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

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class ResetPasswordController extends Controller {
	// `DataProvider` 인스턴스.
    private DataProvider provider;
    
    // 사용자 계정 정보.
    private User user;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private MFXPasswordField passwordField0;
    
    @FXML
    private MFXPasswordField passwordField1;
    
    @FXML
    private Label passwordMsgLabel;
    
    @FXML
    private MFXButton resetPasswordBtn;
    
    /* ::: 컨트롤러 기본 메소드 정의... ::: */

    @Override
    public void initialize() {
        provider = DataProvider.getInstance();
        
        passwordMsgLabel.setManaged(false);
    }
    
    @Override
	public void onPageLoad() {
    	user = (User) getPageLoader().getArgument();
    	
    	nameLabel.setText(user.getName());
    	emailLabel.setText(user.getEmail());
	}
    
    @FXML
    public void onResetPasswordBtnAction() {
    	String password0 = passwordField0.getText();
    	String password1 = passwordField1.getText();
    	
    	if (!password0.equals(password1)) {
    		Animations.updateLabel(passwordMsgLabel, Messages.ERROR_PASSWORD_MISMATCH);
    		
    		return;
    	}
    	
    	ApiFutures.addCallback(
            provider.updateUser(user),
            new ApiFutureCallback<WriteResult>() {
                @Override
                public void onSuccess(WriteResult result) {
                	Platform.runLater(() -> getPageLoader().to("/pages/LoginFirst.fxml"));
                }
                
                @Override
                public void onFailure(Throwable t) {
                	Platform.runLater(() -> Animations.updateLabel(passwordMsgLabel, Messages.ERROR_UNKNOWN));
                	
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
    }
}
