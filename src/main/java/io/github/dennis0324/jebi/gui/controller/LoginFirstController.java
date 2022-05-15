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

import java.util.concurrent.Executors;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.*;
import javafx.animation.Animation.Status;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class LoginFirstController extends Controller {
	// 로그인 페이지 컨트롤러의 `DataProvider` 인스턴스.
    private DataProvider provider;
    
    // 오류 메시지 레이블의 애니메이션.
    private SequentialTransition errorMsgLabelAnim;
    
    @FXML
    private MFXTextField emailField;
    
    @FXML
    private Label errorMsgLabel;

    @FXML
    private Button forgotEmailBtn;
    
    @FXML
    private MFXButton registerBtn;
     
    @FXML
    private MFXButton nextStepBtn;

    @Override
    public void onPageLoad() {}
    

    @Override
    public void initialize() {
        provider = DataProvider.getInstance();
        errorMsgLabelAnim = getErrorMsgLabelTransition();
        
        errorMsgLabel.setManaged(false);
    }
    
    @Override
	public void onPageLoad() {
		/* TODO: ... */
	}
    
    @FXML
    public void onForgotEmailBtnAction() {
        getPageLoader().to("/pages/Search.fxml");
    }
    
    @FXML
    public void onRegisterBtnAction() {
        getPageLoader().to("/pages/Register.fxml");
    }
    
    @FXML
    public void onNextStepBtnAction() {
        String email = emailField.getText();
        
        if (!StringUtils.isValidEmail(email)) {
            updateErrorMsgLabel(Messages.ERROR_INVALID_EMAIL);
            
            return;
        }
        
        ApiFutures.addCallback(
            provider.getUserByEmail(email),
            new ApiFutureCallback<User>() {
                @Override
                public void onSuccess(User result) {
                	String uid = result.getUid();
                	
                    if (uid != null) Platform.runLater(() -> getPageLoader().to("/pages/LoginSecond.fxml", result));
                    else Platform.runLater(() -> updateErrorMsgLabel(Messages.ERROR_UNKNOWN));
                }

                @Override
                public void onFailure(Throwable t) {
                	Platform.runLater(() -> updateErrorMsgLabel(Messages.ERROR_USER_NOT_FOUND));
                }
            },
            Executors.newCachedThreadPool()
        );
    }
    
    /**
     * 오류 메시지 레이블의 애니메이션을 반환한다.
     * 
     * @return 오류 메시지 레이블의 애니메이션.
     */
    private SequentialTransition getErrorMsgLabelTransition() {
        PauseTransition pauseAnim = new PauseTransition(Duration.seconds(1.25));
        FadeTransition fadeAnim = new FadeTransition(Duration.seconds(0.5), errorMsgLabel);
        
        fadeAnim.setFromValue(1.0);
        fadeAnim.setToValue(0.0);
        
        SequentialTransition result = new SequentialTransition(pauseAnim, fadeAnim);
        
        result.setOnFinished(event -> errorMsgLabel.setManaged(false));
        
        return result;
    }
    
    /**
     * 오류 메시지 레이블을 업데이트한다.
     * 
     * @param message 레이블이 보여줄 오류 메시지.
     */
    private void updateErrorMsgLabel(String message) {
        errorMsgLabel.setText(message);
        errorMsgLabel.setManaged(true);
        
        if (errorMsgLabelAnim.getStatus() != Status.RUNNING)
            errorMsgLabelAnim.playFromStart();
    }
}
