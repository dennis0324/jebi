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
import javafx.stage.Stage;

/**
 * 프로그램의 메인 클래스.
 * 
 * @author jdeokkim
 */
public class Main extends Application {
	/**
	 * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
	 * 
	 * @param args 프로그램의 명령 인수가 저장된 배열.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PageLoader pageLoader = new PageLoader(primaryStage);
		
		primaryStage.setTitle(Messages.PRIMARY_STAGE_TITLE);
		
		primaryStage.setWidth(Constants.SCREEN_WIDTH);
		primaryStage.setHeight(Constants.SCREEN_HEIGHT);
		
		primaryStage.setResizable(false);
<<<<<<< HEAD

		pageLoader.to("/pages/Table.fxml");
=======
    
		pageLoader.to("/pages/Books.fxml");
>>>>>>> a14cd96... `unstable` 브랜치의 내용을 `main` 브랜치와 병합 (#7)
		
		primaryStage.show();
	}
}