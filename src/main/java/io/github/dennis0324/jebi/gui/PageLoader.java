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

package io.github.dennis0324.jebi.gui;

import java.io.IOException;
import java.util.HashMap;

import io.github.dennis0324.jebi.gui.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 프로그램 창의 페이지를 관리하는 클래스.
 * 
 * @author jdeokkim
 */
public final class PageLoader {
	// 각 페이지의 경로와 페이지에 대응하는 장면이 저장된 해시맵.
	private static final HashMap<String, Scene> scenes;
	
	// 프로그램의 창을 나타내는 변수.
	private final Stage stage;
	
	// 다음 페이지에 넘겨줄 객체.
	private Object arg;
	
	/**
	 * 클래스 생성자가 호출되기 전에 호출된다.
	 */
	static {
		scenes = new HashMap<>();
	}
	
	/**
	 * `PageLoader` 클래스의 생성자.
	 * 
	 * @param stage 프로그램의 창.
	 */
	public PageLoader(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * 주어진 경로에 해당하는 페이지로 이동한다.
	 * 
	 * @param path 페이지의 FXML 문서 경로.
	 */
	public void to(String path) {
		stage.setScene(getScene(path));
	}
	
	/**
	 * 주어진 경로에 해당하는 페이지를 반환한다.
	 * 
	 * @param path 페이지의 FXML 문서 경로.
	 * @return 주어진 경로에 해당하는 페이지.
	 */
	public Scene getScene(String path,Object object) {
		System.out.println(path);
		// JDK 8: 메소드 레퍼런스 (method references)를 이용한 해시맵 조작
		return scenes.computeIfAbsent(path, c -> load(c,object));
	}

	public Scene getScene(String path) {
		System.out.println(path);
		// JDK 8: 메소드 레퍼런스 (method references)를 이용한 해시맵 조작
		return scenes.computeIfAbsent(path,c -> load(c,null));
	}

	
	/**
	 * 프로그램의 창을 반환한다.
	 * 
	 * @return 프로그램의 창.
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * 주어진 경로에 해당하는 FXML 문서로 페이지를 생성한다.
	 * 
	 * @param path 페이지의 FXML 문서 경로.
	 * @param object 페이지의 이벤트 헨들러(Controller)
	 * @return 주어진 경로에 해당하는 페이지.
	 */
	private Scene load(String path,Object object) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		
		if(object != null)
			loader.setController(object);

		try {
			return new Scene(loader.load());
		} 
		catch (IOException e) {
			// return this.scenes.get(path);
			throw new RuntimeException(e);
		}

		

	}

	
}
