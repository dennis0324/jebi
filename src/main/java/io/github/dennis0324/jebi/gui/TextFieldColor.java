package io.github.dennis0324.jebi.gui;

import java.io.File;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author dennis ko
 */
public class TextFieldColor {
    private TextField textField;

    public enum TextfieldType{
        idle,
        wrong
    }

    public TextFieldColor(TextField textField){
        this.textField = textField;
    }
    public void setState(TextfieldType type){
        if(type == TextfieldType.idle){
        }
        else if(type == TextfieldType.wrong){
            for(int i = 0; i < textField.getStylesheets().size(); i++){
                textField.getStylesheets().remove(i);
            }
            textField.getStylesheets().add(getClass().getResource("/css/customMFXTextFieldWrong.css").toString());
        }
    }
}
