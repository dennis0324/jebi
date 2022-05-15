package io.github.dennis0324.jebi.gui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterController extends Controller {
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
    public void onPageLoad() {}

    public void initialize() {
        
        // Image image = new Image(getClass().getResource("/fxml/register.fxml").toString());
        // logo.setImage(image);
        // logo.setImage(image);
        
    }
}
