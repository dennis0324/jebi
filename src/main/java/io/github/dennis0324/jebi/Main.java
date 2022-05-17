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
		
=======
    
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 1d61eb3... 테이블 아이템 클릭 이벤트 추가 및 PageLoader파일 수정
=======
		//Component/SearchComponent
<<<<<<< HEAD
>>>>>>> 2de52cb... 옆 검색에 카드 추가
		pageLoader.to("/pages/Table.fxml");
=======
		pageLoader.to("/pages/LoginFirst.fxml");
>>>>>>> 7cb4f2b... 계정 생성 페이지 구현 완료
=======
		pageLoader.to("/pages/Search.fxml");
>>>>>>> 27af3bc... 오른쪽 구역 버튼 이벤트 추가
		
		primaryStage.show();
	}
}