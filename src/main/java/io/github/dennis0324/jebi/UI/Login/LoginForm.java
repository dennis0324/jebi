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

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginForm {
    public void sizeEventListener(){
    }
    static public BorderPane create(){
        BorderPane loginForm = new BorderPane();

        VBox loginBox = new VBox();

        Pane textDiv = new Pane();

        HBox idBox = new HBox();
        Label idLabel = new Label();
        idLabel.setText("ID");
        idLabel.setAlignment(Pos.CENTER_RIGHT);

        textDiv.getChildren().add(idLabel);
        textDiv.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY,Insets.EMPTY))); 

        TextField idTextField = new TextField();

        idTextField.setMaxWidth(500);
        
        idBox.getChildren().addAll(textDiv,idTextField);
        idBox.setSpacing(10);
        idBox.setAlignment(Pos.CENTER);
        
        HBox.setHgrow(idTextField, Priority.ALWAYS);

        HBox passWdBox = new HBox();
        Label passWdLabel = new Label();
        passWdLabel.setText("Password");
        TextField passWTextField = new TextField();
        passWdBox.getChildren().addAll(passWdLabel,passWTextField);
        passWdBox.setSpacing(10);
        passWdBox.setAlignment(Pos.CENTER);

        loginBox.getChildren().addAll(idBox,passWdBox);
        loginBox.setSpacing(20);
        loginBox.setMaxWidth(500);
        loginBox.setBackground(new Background(new BackgroundFill(Color.CORAL,CornerRadii.EMPTY,Insets.EMPTY)));
        loginForm.setCenter(loginBox);
        

        return loginForm;
    }
}
