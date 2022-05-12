package io.github.dennis0324.jebi.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterController implements Initializable {
    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField firstName;

    @FXML
    private MFXTextField lastName;

    @FXML
    private ImageView logo;

    @FXML
    private MFXTextField phoneNumber;

    @FXML
    private MFXButton submitButton;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // logo.setImage(image);
        
    }
}
