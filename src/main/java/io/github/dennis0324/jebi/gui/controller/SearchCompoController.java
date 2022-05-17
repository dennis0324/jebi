package io.github.dennis0324.jebi.gui.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.gui.TableViewHelper.Type;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.FXCollectors;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SearchCompoController extends Controller {

    private TableController tableController;
    private Type type;
    public static final ObservableList<String> UserSelectList = FXCollections.observableList(Arrays.asList("이름","전화번호"));
    public static final ObservableList<String> BookSelectList = FXCollections.observableList(Arrays.asList("이름","출판사","카테고리"));

    @FXML
    private MFXComboBox<String> SearchFilterComboBox;


    @FXML
    private MFXTextField searchBox;

    @FXML
    private MFXIconWrapper  sumbitButton;

    @FXML
    private VBox searchResultContainer;


    @FXML
    void onSumbitMouseBtnClicked(MouseEvent event) {
        String serachContent = searchBox.getText(); //검색할 내용
        String searchFilterContent = SearchFilterComboBox.getText();
        if(searchFilterContent == "") searchFilterContent = "제목";

        if(serachContent == "") return;
        searchBox.setText("");
        System.out.println(serachContent + " : " + searchFilterContent); // TODO 이거 데베랑 연동해서 하면 될것같음

        if(tableController.getType() == Type.Book){
        // getPageLoader().to(searchResultContainer, "/pages/Component/SearchBookResultComponent.fxml", tableController); //이거 검색할때 파베랑 연결하면 됨
            
            System.out.println("book");
        }
        else if(tableController.getType() == Type.User){
        // getPageLoader().to(searchResultContainer, "/pages/Component/SearchUserResultComponent.fxml", tableController); //이거 검색할때 파베랑 연결하면 됨

            System.out.println("User");
        }
    }

    
    @Override
    public void initialize() {
        // 버튼 설정
        MaterialIconView icon = new MaterialIconView(MaterialIcon.SEARCH, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size
        sumbitButton.setIcon(icon);
        sumbitButton.defaultRippleGeneratorBehavior();
        sumbitButton.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
        NodeUtils.makeRegionCircular(sumbitButton);
        //


        SearchFilterComboBox.setItems(BookSelectList); //선택 카테고리 설정
        
    }

    @Override
    public void onPageLoad() {
        this.tableController = (TableController)getPageLoader().getArgument();
        type = tableController.getType();
        if(type == Type.Book){
                SearchFilterComboBox.setItems(BookSelectList); //선택 카테고리 설정
            }
            else if(type == Type.User){
                SearchFilterComboBox.setItems(UserSelectList); //선택 카테고리 설정
            }
        this.tableController.setComboBox(this.SearchFilterComboBox);
    }
    
}
