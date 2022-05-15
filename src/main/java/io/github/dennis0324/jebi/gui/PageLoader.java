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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 프로그램 창의 페이지를 관리하는 클래스.
 * 
 * @author jdeokkim
 */
public final class PageLoader {
    // `PageLoader`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(PageLoader.class);
    
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
        Scene scene = getScene(path);
        
        if (scene != null || scene != stage.getScene()) 
            stage.setScene(scene);
    }

    /**
     * 주어진 경로에 해당하는 페이지로 이동한다.
     * 
     * @param pane 컴포넌트의 종착지
     * @param parent 컴포넌트
     */
    public void to(Pane pane, Parent parent) {
        if (pane != null || parent != null) {
            pane.getChildren().removeAll();
            pane.getChildren().setAll(parent);
        }
    }
    
    /**
     * 주어진 경로에 해당하는 페이지로 이동한다.
     * 
     * @param path 페이지의 FXML 문서 경로.
     * @param arg 다음 페이지에 넘겨줄 객체.
     */
    public void to(String path, Object arg) {
        this.arg = arg;
        
        to(path);
    }

    /**
     * 주어진 경로에 해당하는 컴포넌트를 부모의 컴포넌트에 붙힌다.
     * 
     * @param parent 컴포넌트를 붙힐 부모 클라스
     * @param path 페이지의 FXML 문서 경로.
     * @param arg 다음 페이지에 넘겨줄 객체.
     */
    public void to(Pane pane, String path , Object arg) {
        this.arg = arg;
        to(pane,load(pane, path));
    }
    
    /**
     * 다음 페이지에 넘겨줄 객체를 반환한다.
     * 
     * @return 다음 페이지에 넘겨줄 객체.
     */
    public Object getArgument() {
        return arg;
    }

    
    /**
     * 주어진 경로에 해당하는 페이지를 반환한다.
     * 
     * @param path 페이지의 FXML 문서 경로.
     * @return 주어진 경로에 해당하는 페이지.
     */
    private Scene getScene(String path) {
        // JDK 8: 메소드 레퍼런스 (method references)를 이용한 해시맵 조작
        return scenes.computeIfAbsent(path, this::load);
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
     * 다음 페이지에 넘겨줄 객체를 설정한다.
     * 
     * @param arg 다음 페이지에 넘겨줄 객체.
     */
    public void setArgument(Object arg) {
    	this.arg = arg;
    }
    
    /**
     * 주어진 경로에 해당하는 FXML 문서로 페이지를 생성한다.
     * 
     * @param path 페이지의 FXML 문서 경로.
     * @return 주어진 경로에 해당하는 페이지.
     */
    private Scene load(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        
        try {
            Parent parent = loader.load();
            
            Controller controller = loader.getController();
            
            if (controller != null) {
                controller.setPageLoader(this);
                controller.setParent(parent);
                
                controller.onPageLoad();
            }
            
            return new Scene(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 주어진 경로에 해당하는 FXML 문서로 페이지를 생성한다.
     * 
     * @param path 페이지의 FXML 문서 경로.
     * @return 주어진 경로에 해당하는 `Parent`.
     */
    private Parent load(Pane pane, String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        try {
            Parent parent = loader.load();
            
            Controller controller = loader.getController();
            
            if (controller != null){
                controller.setPageLoader(this);
                controller.onPageLoad();
            }

            return parent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
