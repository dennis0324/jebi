package io.github.dennis0324.jebi.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;


import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.util.DisppearText;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FirstLoginPageController extends Controller {

    @FXML
    private Label errorMessage;

    @FXML
    private Button forgetID;

    @FXML
    private MFXButton forgotID;

    @FXML
    private MFXTextField idInput;

    @FXML
    private MFXButton login;

    @FXML
    private MFXButton makeID;

    @FXML
    private HBox testing;


    public void onPageLoad() {};

    public void initialize() {
        System.out.print("testing");
        errorMessage.setVisible(false);

        login.setOnMouseClicked(event -> {
            DisppearText.setText(errorMessage, "testing", 500);
            
        });

        makeID.setOnMouseClicked(event -> {
            System.out.println("register ID Btn click");
        });

        forgotID.setOnMouseClicked(event -> {
            System.out.println("forgot ID");
        });

    }

    private void setErrorMessage(String text){
        errorMessage.setText(text);
        errorMessage.setVisible(true);
    }

    
}
