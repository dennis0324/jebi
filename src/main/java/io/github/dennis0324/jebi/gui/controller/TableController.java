
package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.palexdev.materialfx.utils.NodeUtils;
import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.gui.TableViewHelper.Type;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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
import javafx.scene.paint.Color;
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
    private Type type;
    private MFXComboBox<String> searchFilterComboBox; 

    @FXML
    private MFXButton addDBBtn;

    @FXML
    private TextField addDBText;

    @FXML
    private MFXButton book;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private HBox searchContainer;

    @FXML
    private StackPane contentArea; //일반 창 변경에 필요함

    @FXML
    private StackPane tableArea; //테이블 창 변경에 필요함

    @FXML
    private MFXButton user;

    @FXML
    private StackPane buttonContainer;

    @FXML
    private MFXButton Msetting;

    @FXML
    private MFXIconWrapper setting;


    @FXML
    void onAddDBBtnClicked(ActionEvent event) {
        String name = addDBText.getText();
        addDBText.setText("");
        // TODO dennis ko: 책 편집/추가 인터페이스 구현 & 유저 편집/추가 인터페이스 구현
        if(type == Type.Book){
            // TODO 데이터베이스 연결
        }
        else if(type == Type.User){
            // TODO 데이터베이스 연결
        }

    }

    @FXML
    void onSelectBook(ActionEvent event) {
        getPageLoader().to(tableArea, "/pages/component/tableBookComponent.fxml", this);
        System.out.println("tseting");
        this.type = Type.Book;
        searchFilterComboBox.setItems(SearchCompoController.BookSelectList);

    }

    @FXML
    void onSelectUser(ActionEvent event) {
        getPageLoader().to(tableArea, "/pages/component/tableUserComponent.fxml", this);
        this.type = Type.User;
        searchFilterComboBox.setItems(SearchCompoController.UserSelectList);

    }
    


    @Override
    public void onPageLoad() {
        //첫 화면 설정
        setDefaultcontentArea();
        setDefaultTableContent();
        
    }

    public void initialize() {
        // 지우지 말아주세요!
        // searchBar.focusedProperty().addListener(new InvalidationListener() {               
        //     @Override
        //     public void invalidated(Observable observable) {
        //         //here it is changing
        //         if(searchBar.isFocused()){
        //             System.out.print("testing");
        //         }
        //         else{
        //             System.out.print("testing2");
        //         }
        //     }
        // });

        //설정 버튼 샐성 구역
        MaterialIconView icon = new MaterialIconView(MaterialIcon.SETTINGS, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        icon.fillProperty().set(Color.WHITE);
        // icon.setStyle("-fx-fill:#fff");
        setting.setIcon(icon);
        setting.defaultRippleGeneratorBehavior();
        //지역을 둥글게 만들어준다.
        NodeUtils.makeRegionCircular(setting);
    }

    /**
     * 클릭 액션 설정구역 기본내용 설정
     * 
     * 
     */

    private void setDefaultcontentArea(){
        getPageLoader().to(contentArea, "/pages/component/SearchComponent.fxml",this);
    }

    /**
     * 테이블 기본구역 기본 테이블 설정
     * 
     * 
     */
    private void setDefaultTableContent(){
        getPageLoader().to(tableArea, "/pages/component/tableBookComponent.fxml",this);
        this.type = Type.Book;
    }

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

    /**
     * 현재 테이블 컨트롤러의 기본 타입을 반환해준다.
     * 
     * @return 현재 테이블의 타입
     */
    public Type getType(){
        return type;
    }

    public void setComboBox(MFXComboBox<String> searchFilterComboBox){
        this.searchFilterComboBox = searchFilterComboBox;
    }
}
