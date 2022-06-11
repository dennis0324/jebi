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
    // `PageLoader`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(PageLoader.class);
    
    // 각 페이지의 경로와 페이지에 대응하는 장면이 저장된 해시맵.
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    
    // 프로그램의 창을 나타내는 변수.
    private final Stage stage;
    
    // 다음 페이지에 넘겨줄 객체.
    private Object arg;
    
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
        
        LOG.info("다음 페이지로 이동합니다. (페이지 경로: " + path + ")");
        
        Controller controller = (Controller) scene.getUserData();
        
        if (scene != null || scene != stage.getScene()) 
            stage.setScene(scene);
        
        controller.onPageLoad();
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
        
        LOG.info("새로운 페이지를 생성합니다. (페이지 경로: " + path + ")");
        
        try {
            Parent parent = loader.load();
            
            Controller controller = loader.getController();
            
            if (controller != null) {
                controller.setPageLoader(this);
                controller.setParentNode(parent);
                
                controller.onPageLoad();
            }
            
            Scene result = new Scene(parent);
            
            // FXML 문서로 생성된 장면에도 이벤트 컨트롤러의 레퍼런스를 저장한다.
            result.setUserData(controller);
            
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
