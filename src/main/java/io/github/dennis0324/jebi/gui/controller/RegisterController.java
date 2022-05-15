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
import com.google.cloud.firestore.WriteResult;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.StringUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * 계정 생성 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class RegisterController extends Controller {
	// 계정 생성 페이지 컨트롤러의 `DataProvider` 인스턴스.
    private DataProvider provider;
    
	@FXML
	private ImageView logoImage;
	
	@FXML
	private MFXTextField lastNameField;
	
	@FXML
	private MFXTextField firstNameField;
	
	@FXML
	private MFXTextField emailField;
	
	@FXML
	private MFXPasswordField passwordField;
	
	@FXML
	private MFXTextField phoneNumberField;
	
	@FXML
	private MFXButton submitBtn;

	@Override
	public void initialize() {
		provider = DataProvider.getInstance();
	}
	
	@FXML
	public void onSubmitBtnAction() {
		if (lastNameField.getText().isBlank()) { /* TODO: ... */ return; }
		if (firstNameField.getText().isBlank()) { /* TODO: ... */ return; }
		if (!StringUtils.isValidEmail(emailField.getText())) { /* TODO: ... */ return; }
		if (!StringUtils.isValidPassword(passwordField.getText())) { /* TODO: ... */ return; }
		if (!StringUtils.isValidPhoneNumber(phoneNumberField.getText())) { /* TODO: ... */ return; }
			
		String name = lastNameField.getText() + firstNameField.getText();
		
		User user = new User(
			name, 
			emailField.getText(), 
			StringUtils.encrypt(passwordField.getText()), 
			phoneNumberField.getText()
		);
		
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
			Executors.newCachedThreadPool()
		);
	}
}
