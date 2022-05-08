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

package io.github.dennis0324.jebi.UI;

import io.github.dennis0324.jebi.UI.Login.LoginPage;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageStarter {
    private Stage primaryStage;

    /**
     * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
     * 
     * 
     */
    public StageStarter(Stage primaryStage){
        this.primaryStage = primaryStage;
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
		System.out.println("Height: " + primaryStage.getHeight() + " Width: " + primaryStage.getWidth());
		
		this.primaryStage.widthProperty().addListener(stageSizeListener);
		this.primaryStage.heightProperty().addListener(stageSizeListener);
    }

    /**
     * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
     * 
     * @param scence scence를 추가해준다.
     */
    public void setStage(Scene scene){

    }    

    /**
    * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
    * 
    * @param width 로그인 화면의 최소사이즈를 정해준다.
    * @param height 로그인 화면의 최소사이즈를 정해준다.
    */
    public void setMinSize(double width, double height){
        this.primaryStage.minWidthProperty().setValue(width);
        this.primaryStage.minHeightProperty().setValue(height);
    }

    
    /**
     * 프로그램이 실행될 때 가장 먼저 호출되는 메소드이다.
     * 
     * @param width 프로그램의 명령 인수가 저장된 배열.
     * @param height 프로그램의 명령 인수가 저장된 배열.
     */
    public void setMaxSize(double width, double height){
        this.primaryStage.maxWidthProperty().setValue(width);
        this.primaryStage.maxHeightProperty().setValue(height);
    }


    /**
     * 스페이지 관리자를 실행하는 메소드이다.
     * 
     * 
     */
    public void start(){
        LoginPage loginPage = new LoginPage(this.primaryStage);
        loginPage.open();
        System.out.print(this.primaryStage);
        this.primaryStage.show();

    }

}
