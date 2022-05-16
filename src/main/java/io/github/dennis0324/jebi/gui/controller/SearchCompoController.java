package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;


import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.gui.TableViewHelper.Type;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.NodeUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SearchCompoController extends Controller {

    private TableController tableController;
    private Type type;

    @FXML
    private MFXTextField searchBox;

    @FXML
    private MFXIconWrapper  sumbitButton;

    @FXML
    private VBox searchResultContainer;

    @FXML
    void onSearchButtonAction(ActionEvent event) {

    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        MaterialIconView icon = new MaterialIconView(MaterialIcon.SEARCH, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        sumbitButton.setIcon(icon);
        sumbitButton.defaultRippleGeneratorBehavior();
        sumbitButton.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
        NodeUtils.makeRegionCircular(sumbitButton);
        
    }

    @Override
    public void onPageLoad() {
        this.tableController = (TableController)getPageLoader().getArgument();
        type = tableController.getType();
        getPageLoader().to(searchResultContainer, "/pages/Component/SearchUserResultComponent.fxml", tableController);
    }
    
}
