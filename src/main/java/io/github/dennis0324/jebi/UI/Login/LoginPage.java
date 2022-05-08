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

package io.github.dennis0324.jebi.UI.Login;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.ScheduledService;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * 프로그램의 메인 클래스.
 * 
 * @author dennis ko
 */
public class LoginPage {
    private Stage primaryStage;
    private Scene scene;

    /**
     * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
     * 
     * 
     */
    public LoginPage(Stage primaryStage){
        this.primaryStage = primaryStage;
        scene = new Scene(LoginForm.create());
    }

    /**
     * 로그인 페이지를 열어주는 메소드이다.
     * 
     * @param scence scence를 추가해준다.
     */
    public void open(){
        this.primaryStage.setScene(this.scene);
    }    

    

}
