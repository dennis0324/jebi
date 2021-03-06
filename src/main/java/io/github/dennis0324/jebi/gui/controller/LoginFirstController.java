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
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class LoginFirstController extends Controller {
	// `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();;
    
    @FXML
    private MFXTextField emailField;
    
    @FXML
    private Label emailMsgLabel;

    @FXML
    private Button forgotEmailBtn;
    
    @FXML
    private MFXButton registerBtn;
     
    @FXML
    private MFXButton nextStepBtn;
    
    /* ::: 컨트롤러 기본 메소드 정의... ::: */

    @Override
    public void initialize() {
        emailMsgLabel.setManaged(false);
    }
    
    @Override
	public void onPageLoad() {
		/* TODO: ... */
	}
    
    @FXML
    void onEnterPressed(ActionEvent event) {
        if(emailField.getText() == "") return;

        onNextStepBtnAction();
    }

    @FXML
    public void onForgotEmailBtnAction() {
        getPageLoader().to("/pages/Recovery.fxml", 0);
    }
    
    @FXML
    public void onRegisterBtnAction() {
        getPageLoader().to("/pages/Register.fxml");
    }
    
    @FXML
    public void onNextStepBtnAction() {
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
                	
                    if (uid != null) Platform.runLater(() -> getPageLoader().to("/pages/LoginSecond.fxml", result));
                    else Platform.runLater(() -> Animations.updateLabel(emailMsgLabel, Messages.ERROR_USER_NOT_FOUND));
                }
                @Override
                public void onFailure(Throwable t) {
                	Platform.runLater(() -> Animations.updateLabel(emailMsgLabel, Messages.ERROR_UNKNOWN));
                	
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
        );
    }
}
