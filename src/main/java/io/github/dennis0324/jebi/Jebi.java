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

package io.github.dennis0324.jebi;

import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 애플리케이션을 나타내는 클래스.
 * 
 * @author jdeokkim
 */
public class Jebi extends Application {
	/**
	 * 애플리케이션을 실행한다.
	 * 
	 * @param args 애플리케이션의 명령 인수가 저장된 배열.
	 */
	public static void init(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PageLoader pageLoader = new PageLoader(primaryStage);
		
		primaryStage.setTitle(Messages.PRIMARY_STAGE_TITLE);
		
		primaryStage.setWidth(Constants.SCREEN_WIDTH);
		primaryStage.setHeight(Constants.SCREEN_HEIGHT);
		
		primaryStage.setResizable(false);
		
		primaryStage.setOnCloseRequest(event -> Platform.exit());

		pageLoader.to("/pages/LoginFirst.fxml");
		
		primaryStage.show();
	}
}