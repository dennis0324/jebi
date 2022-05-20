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
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class LoginSecondController extends Controller {
	// 로그인 페이지 컨트롤러의 `DataProvider` 인스턴스.
    private DataProvider provider;
    
    // 로그인 페이지 컨트롤러의 사용자 정보.
    private User user;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private MFXPasswordField passwordField;
    
    @FXML
    private Label passwordMsgLabel;
    
    @FXML
    private MFXButton forgotPwdBtn;
    
    @FXML
    private MFXButton loginBtn;

    @Override
    public void initialize() {
        // provider = DataProvider.getInstance();
    	
    	passwordMsgLabel.setManaged(false);
    }
    
    @Override
	public void onPageLoad() {
    	user = (User) getPageLoader().getArgument();
    	
    	nameLabel.setText(user.getName());
    	emailLabel.setText(user.getEmail());
	}
    
    @FXML
    public void onForgotPwdBtnAction() {
    	getPageLoader().to("/pages/Recovery.fxml", 1);
    }
    
    @FXML
    public void onLoginBtnAction() {
    	String password = passwordField.getText();
    	
    	if (!StringUtils.isValidPassword(password)) {
    		Animations.updateLabel(passwordMsgLabel, Messages.ERROR_INVALID_PASSWORD);
            
            return;
        }
    	
    	boolean success = StringUtils.encrypt(password)
    		.equals(user.getPwdHash());
    	
    	if (!success) Animations.updateLabel(passwordMsgLabel, Messages.ERROR_PASSWORD_MISMATCH);
    	else getPageLoader().to("/pages/Table.fxml", user);
    }
}
