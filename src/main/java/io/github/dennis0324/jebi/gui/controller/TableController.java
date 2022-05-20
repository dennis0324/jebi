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

import io.github.dennis0324.jebi.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * 계정 생성 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableController extends Controller {
	// 사용자 계정 정보.
    private User user;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label emailLabel;
	
	@Override
	public void initialize() {
		/* TODO: ... */
	}

	@Override
	public void onPageLoad() {
		user = (User) getPageLoader().getArgument();
    	
    	nameLabel.setText(user.getName());
    	emailLabel.setText(user.getEmail());
	}
}