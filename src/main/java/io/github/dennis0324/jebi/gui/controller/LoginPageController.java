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

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class LoginPageController implements Controller {
	@FXML
	private MFXTextField emailField;
	
    @FXML
    private MFXButton forgotEmail;
    
    @FXML
    private MFXButton loginNext;

    @FXML
    private MFXButton createAccount;

    @FXML
    public void initialize() {
    	emailField.setOnMouseClicked(event -> {
            System.out.println(".");
        });

    	forgotEmail.setOnMouseClicked(event -> {
            System.out.println("..");
        });

    	loginNext.setOnMouseClicked(event -> {
            System.out.println("...");
        });
    	
    	createAccount.setOnMouseClicked(event -> {
            System.out.println("....");
        });
    }
}
