package io.github.dennis0324.jebi.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SecondLoginPageController implements Initializable {
    
    private PageLoader pageLoader;


    public SecondLoginPageController(PageLoader pageLoader){
        this.pageLoader = pageLoader;
    }

    @FXML
    private MFXButton loginPswd;

    @FXML
    private MFXButton forgotPswd;

    @FXML
    private Label nameID;

    @FXML
    private Label emailID;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginPswd.setOnMouseClicked(event -> {

        });
        forgotPswd.setOnMouseClicked(event -> {
            //비밀번호 로그인 데이터베이스 가지고 온거에서 연결필요
            
        });
        nameID.setOnMouseClicked(event -> {
            
        });
        emailID.setOnMouseClicked(event -> {
            
        });

    }

    public void set(String testing){
        nameID.setText(testing);
    }

    
}
