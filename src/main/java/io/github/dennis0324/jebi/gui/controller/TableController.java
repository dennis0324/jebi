package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.palexdev.materialfx.effects.ripple.MFXCircleRippleGenerator;
import io.github.palexdev.materialfx.beans.PositionBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.animation.ScaleTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;



class TextFieldListener implements EventHandler<ActionEvent> {
    private final TextField textField ;
    TextFieldListener(TextField textField) {
      this.textField = textField ;
    }

    @Override
    public void handle(ActionEvent arg0) {
        System.out.println("testing");
        // TODO Auto-generated method stub
        
    }
}

class BtnMouseEvent implements EventHandler<ActionEvent>{
    private String path;
    private StackPane contentArea; 
    public BtnMouseEvent(StackPane contentArea, String path){
        this.path = path;
        this.contentArea = contentArea;
    }


    @Override
    public void handle(ActionEvent arg0) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }        
    }

}


<<<<<<< HEAD
public class TableController extends Controller implements Initializable {
=======
public class TableController extends Controller {
>>>>>>> 5d97164... 컨트롤러 추가


    @FXML
    private MFXButton book;

    @FXML
    private StackPane contentArea;

    @FXML
    private TextField searchBar;

    @FXML
    private MFXButton searchButton;

    @FXML
    private HBox searchContainer;

    @FXML
    private Button setting;

    @FXML
    private StackPane tableArea;

    @FXML
    private MFXButton user;

    @FXML
    private StackPane buttonContainer;

    @FXML
    private MFXButton Msetting;


    @FXML
    void testingAction(ActionEvent event) {
        System.out.println(event);
    }


<<<<<<< HEAD
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
=======
    public void initialize() {
>>>>>>> 5d97164... 컨트롤러 추가
        

        searchBar.focusedProperty().addListener(new InvalidationListener() {               
            @Override
            public void invalidated(Observable observable) {
                //here it is changing
                if(searchBar.isFocused()){
                    System.out.print("testing");
                }
                else{
                    System.out.print("testing2");
                }
            }
        });

        searchButton.setOnMouseClicked(event -> {
            System.out.println("mouse clicked");
        });


        MaterialIconView icon = new MaterialIconView(MaterialIcon.SETTINGS, "22"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        
<<<<<<< HEAD
        MFXIconWrapper filterIcon = new MFXIconWrapper(icon, 32).defaultRippleGeneratorBehavior(); // this will create the ripple effect, 32 is the ripple size
        
        Msetting.makeRegionCircular(filterIcon); // this will make the ripple circular
        Msetting.make
=======
        // MFXIconWrapper filterIcon = new MFXIconWrapper(icon, 32).defaultRippleGeneratorBehavior(); // this will create the ripple effect, 32 is the ripple size
        
        // Msetting.makeRegionCircular(filterIcon); // this will make the ripple circular
        // Msetting.make
>>>>>>> 5d97164... 컨트롤러 추가
        
        //
        //각 메뉴 사용자와 책에 클릭 이벤트 부여
        user.setOnAction(new BtnMouseEvent(tableArea, "/pages/component/tableUserComponent.fxml"));
        book.setOnAction(new BtnMouseEvent(tableArea,"/pages/component/tableBookComponent.fxml"));
        
        //첫 화면 설정
        setDefaultcontentArea();
        setDefaultTableContent();
    }

    private void setDefaultcontentArea(){
        Parent parent;
        try{
            parent = FXMLLoader.load(getClass().getResource("/pages/component/defaultSearchComponent.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void setDefaultTableContent(){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/pages/component/tableBookComponent.fxml"));
            tableArea.getChildren().removeAll();
            tableArea.getChildren().setAll(parent);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
