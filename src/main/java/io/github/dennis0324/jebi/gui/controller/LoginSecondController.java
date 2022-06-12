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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Animations;
import io.github.dennis0324.jebi.util.Messages;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class LoginSecondController extends Controller {
	// `LoginSecondController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(LoginSecondController.class);
    
    // 사용자 계정 정보.
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
    private MFXIconWrapper backIconBtn;
    
    @FXML
    private MFXButton forgotPwdBtn;
    
    @FXML
    private MFXButton loginBtn;
    
    /* ::: 컨트롤러 기본 메소드 정의... ::: */

    @Override
    public void initialize() {
    	passwordMsgLabel.setManaged(false);
        setupIconBtn();
    }
    
    @Override
	public void onPageLoad() {
    	user = (User) getPageLoader().getArgument();
    	
    	nameLabel.setText(user.getName());
    	emailLabel.setText(user.getEmail());
	}

    @FXML
    void onEnterPressed(ActionEvent event) {
        if(passwordField.getText() == "") return;

        onLoginBtnAction();
    }
    
    @FXML
    private void onBackIconBtnClicked() {
        getPageLoader().to("/pages/LoginFirst.fxml");
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
    	
    	if (success) {
    		LOG.info("사용자가 로그인 중입니다. (고유 ID: " + user.getUid() + ")");
    		
    		getPageLoader().to("/pages/Table.fxml", user);
    	} else {
    		Animations.updateLabel(passwordMsgLabel, Messages.ERROR_PASSWORD_MISMATCH);
    	}
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
