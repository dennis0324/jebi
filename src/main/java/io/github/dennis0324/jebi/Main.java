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

import io.github.dennis0324.jebi.UI.StageStarter;
import io.github.dennis0324.jebi.UI.Login.LoginForm;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 프로그램의 메인 클래스.
 * 
 * @author jdeokkim
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageStarter stage = new StageStarter(primaryStage);
		stage.setMinSize(1000, 700);
		stage.start();
		


	}
	
	/**
	 * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
	 * 
	 * @param args 프로그램의 명령 인수가 저장된 배열.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}