package io.github.dennis0324.jebi.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;



import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FirstLoginPageController implements Initializable {

    private PageLoader pageLoader;


    public FirstLoginPageController(PageLoader pageLoader){
        this.pageLoader = pageLoader;
    }

    @FXML
    private MFXButton login;

    @FXML
    private MFXButton makeID;

    @FXML
    private MFXButton forgotID;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

        login.setOnMouseClicked(event -> {
            System.out.print("loginBTN click");
            // System.out.println(pageLoader.getScene("/fxml/login_second.fxml").getUserData());
            
        });

        makeID.setOnMouseClicked(event -> {
            System.out.println("register ID Btn click");
        });

        forgotID.setOnMouseClicked(event -> {
            System.out.println("forgot ID");
        });

    }

    
}
