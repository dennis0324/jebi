package io.github.dennis0324.jebi.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;


import com.google.firebase.database.core.view.Event;

import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class BookController implements Initializable {

    private PageLoader pageLoader;


    public void setPageLoader(PageLoader pageLoader){
        this.pageLoader = pageLoader;
    }

    @FXML
    private MFXTableView table;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

        // forgotID.setOnMouseClicked(event -> {
        //     System.out.println("forget ID Btn click");
        // });

    }

    
}
