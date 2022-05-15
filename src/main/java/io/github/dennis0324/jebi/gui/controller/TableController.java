/*

package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.palexdev.materialfx.utils.NodeUtils;
import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

import javafx.scene.text.Font;


// class TextFieldListener implements EventHandler<ActionEvent> {
//     private final TextField textField ;
//     TextFieldListener(TextField textField) {
//       this.textField = textField ;
//     }

//     @Override
//     public void handle(ActionEvent arg0) {
//         System.out.println("testing");
//         // TODO Auto-generated method stub
        
//     }
// }

class BtnMouseEvent implements EventHandler<ActionEvent>{
    private String path;
    private StackPane contentArea; 
    private TableController tableController;
    private HashMap<String,Parent> childerns;
    public BtnMouseEvent(StackPane contentArea, String path,TableController tableController){
        this.path = path;
        this.contentArea = contentArea;
        this.tableController = tableController;
    }


    @Override
    public void handle(ActionEvent arg0) {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
            tableController.getPageLoader().setArgument(tableController);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }        
    }

}

/**
 * 로그인 페이지 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class TableController extends Controller {


    @FXML
    private MFXButton book;

    @FXML
    private StackPane contentArea; //일반 창 변경에 필요함

    @FXML
    private TextField searchBar;

    @FXML
    private MFXButton searchButton;

    @FXML
    private HBox searchContainer;

    @FXML
    private Button setting;

    @FXML
    private StackPane tableArea; //테이블 창 변경에 필요함

    @FXML
    private MFXButton user;

    @FXML
    private StackPane buttonContainer;

    @FXML
    private MFXButton Msetting;

    @FXML
    private MFXIconWrapper testing;


    @FXML
    void testingAction(ActionEvent event) {
        System.out.println(event);
    }

    
    @FXML
    void onSelectBook(ActionEvent event) {
        // getPageLoader().to(tableArea, "/pages/component/tableBookComponent.fxml", this);
        System.out.println("tseting");
    }

    @FXML
    void onSelectUser(ActionEvent event) {
        // try{
        //     Parent parent = FXMLLoader.load(getClass().getResource("/pages/component/tableBookComponent.fxml"));
        // }
        // contentArea.getChildren().removeAll();
        // contentArea.getChildren().setAll(parent);
        // getPageLoader().setArgument(this);
    }
    


    @Override
    public void onPageLoad() {
        //첫 화면 설정
        setDefaultcontentArea();
        setDefaultTableContent();
        
    }

    public void initialize() {
        // Font font = new Font()
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

        //설정 버튼 샐성 구역
        MaterialIconView icon = new MaterialIconView(MaterialIcon.SETTINGS, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        testing.setIcon(icon);
        testing.defaultRippleGeneratorBehavior();
        //지역을 둥글게 만들어준다.
        NodeUtils.makeRegionCircular(testing);
    }

    /**
     * 클릭 액션 설정구역 기본내용 설정
     * 
     * 
     */

    private void setDefaultcontentArea(){
        getPageLoader().to(contentArea, "/pages/component/defaultSearchComponent.fxml",this);
    }

    /**
     * 테이블 기본구역 기본 테이블 설정
     * 
     * 
     */
    private void setDefaultTableContent(){
        getPageLoader().to(tableArea, "/pages/component/tableBookComponent.fxml",this);
    }

<<<<<<< HEAD
    /**
     * 기본 클릭 액션 설정구역의 정보를 반환
     * 
     * @return 변경해야하는 지역의 정보를 반환함
     */
    public StackPane getContentArea(){
        return contentArea;
    }

    /**
     * 테이블 구역의 정보 변환
     * 
     * @return 변경해야하는 지역의 정보를 반환함
     */
    public StackPane getTablePane(){
        return tableArea;
    }

}
=======
}
*/
>>>>>>> bf019fc... 계정 생성 컨트롤러 업데이트
