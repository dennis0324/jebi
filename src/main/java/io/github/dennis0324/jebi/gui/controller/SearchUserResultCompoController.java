package io.github.dennis0324.jebi.gui.controller;

import io.github.dennis0324.jebi.gui.TableViewHelper.Type;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class SearchUserResultCompoController extends Controller {
    private TableController tableController;
    
    @FXML
    private MFXButton editButton;

    @FXML
    void onEditBtnPressed(ActionEvent event) {
        if(tableController.getType() == Type.Book){
            System.out.println("book");
        }
        else if(tableController.getType() == Type.User){
            System.out.println("User");
        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPageLoad() {
        this.tableController = (TableController)getPageLoader().getArgument();
        StackPane ContentArea = tableController.getContentArea();

        // getPageLoader().to(ContentArea,"",tableController);
        // TODO Auto-generated method stub
        
    }

    
}
