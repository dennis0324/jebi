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

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.*;
import javafx.animation.Animation.Status;
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
	private DataProvider provider;
	
	private SequentialTransition errorMsgLabelAnim;
	
	@FXML
	private MFXTextField emailTextField;
	
	@FXML
	private Label errorMsgLabel;

	@FXML
	private Button forgotEmailBtn;
	
	@FXML
    private MFXButton registerBtn;
	 
    @FXML
    private MFXButton nextStepBtn;

	@Override
	public void initialize() {
		provider = DataProvider.getInstance();
		errorMsgLabelAnim = getErrorMsgLabelTransition();
		
		errorMsgLabel.setManaged(false);
	}
	
	@FXML
	public void onForgotEmailBtnAction() {
		/* TODO: ... */
	}
	
	@FXML
	public void onRegisterBtnAction() {
		this.getPageLoader().to("/pages/Register.fxml");
	}
	
	@FXML
	public void onNextStepBtnAction() {
		String errorMsg = null;
		
		if (!StringUtils.isValidEmail(emailTextField.getText()))
			errorMsg = Messages.ERROR_INVALID_EMAIL;
		
		if (errorMsg != null) {
			errorMsgLabel.setText(errorMsg);
			errorMsgLabel.setManaged(true);
			
			if (errorMsgLabelAnim.getStatus() != Status.RUNNING)
				errorMsgLabelAnim.playFromStart();
		}
	}
	
	/**
	 * 오류 메시지 레이블의 애니메이션을 반환한다.
	 * 
	 * @return 오류 메시지 레이블의 애니메이션.
	 */
	private SequentialTransition getErrorMsgLabelTransition() {
		PauseTransition pauseAnim = new PauseTransition(Duration.seconds(0.5));
		FadeTransition fadeAnim = new FadeTransition(Duration.seconds(1.0), errorMsgLabel);
		
		fadeAnim.setFromValue(1.0);
		fadeAnim.setToValue(0.0);
		
		SequentialTransition result = new SequentialTransition(pauseAnim, fadeAnim);
		
		result.setOnFinished(event -> errorMsgLabel.setManaged(false));
		
		return result;
	}
}
