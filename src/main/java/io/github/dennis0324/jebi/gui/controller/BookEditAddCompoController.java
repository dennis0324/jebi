/*
 * jebi: A book management software made with JavaFX.
 * 
 * Copyright (c) 2022 Dennis Ko (https://github.com/dennis0324)
 * Copyright (c) 2022 Jaedeok Kim (https://github.com/jdeokkim)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.dennis0324.jebi.gui.controller;

import java.util.Arrays;

import com.google.firebase.database.snapshot.Index;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.gui.PageLoader;
import io.github.dennis0324.jebi.gui.TextFieldColor;
import io.github.dennis0324.jebi.gui.TextFieldColor.TextfieldType;
import io.github.dennis0324.jebi.gui.component.CapsuleButton;
import io.github.dennis0324.jebi.gui.component.CustomButtonEvent;
import io.github.dennis0324.jebi.gui.controller.UserEditAddCompoController.AddWindowType;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.BookType;
import io.github.dennis0324.jebi.model.BookType.CategoryChangeListener;
import io.github.dennis0324.jebi.model.BookType.Item;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class BookEditAddCompoController extends Controller {

    private TableController tableController;
    private int mainCategoryNum;
    private int subCategoryNum;
    private AddWindowType windowType;


    private CapsuleButton saveButton;
    private CapsuleButton deleteButton;
    private CapsuleButton borrowBtn;
    @FXML
    private MFXIconWrapper backBtn;

    @FXML
    private HBox deleteBtnContainer;

    @FXML
    private MFXToggleButton editModeSelector;

    @FXML
    private MFXTextField author;
    
    @FXML
    private MFXTextField name;
    
    @FXML
    private MFXTextField publishDate;
    
    @FXML
    private MFXTextField publisher;

    @FXML
    private MFXComboBox<Item> bigCategory;

    @FXML
    private MFXComboBox<Item> smallCategory;

    @FXML
    private MFXTextField categoryNumber;

    @FXML
    private HBox categorySelectorHbox;

    @FXML
    private VBox bookInfoContainer;

    @FXML
    private Label errorText;

    @FXML
    private HBox buttonContainer;

    @FXML
    void onBackBtnClicked(MouseEvent event) {
        getPageLoader().to(tableController.getContentArea(),"/pages/Component/SearchComponent.fxml",tableController);
    }

    @FXML
    void onBigCategoryAction(ActionEvent event) {
        System.out.println();
    }

    @FXML
    void onChangeEditMode(ActionEvent event) {
        setEditMode(editModeSelector.isSelected());

    }

    @FXML
    void onSmallCategoryAction(ActionEvent event) {

    }

    @FXML
    void onFieldAction(ActionEvent event) {
        System.out.println(event.getEventType());
    }
    
    
    @Override
    public void initialize() {
        categoryNumber.textProperty().addListener(new CategoryChangeListener(this));

        //저장하기 버튼
        saveButton = new CapsuleButton();
        saveButton.setText("저장");
        saveButton.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        buttonContainer.getChildren().add(saveButton);
        
        //빌리기 버튼
        borrowBtn = new CapsuleButton();
        borrowBtn.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        borrowBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new CustomButtonEvent.sendToDB(getPageLoader()));
        buttonContainer.getChildren().add(borrowBtn);

        //삭제 버튼
        deleteButton = new CapsuleButton();
        deleteButton.setText("삭제하기");
        deleteButton.getStylesheets().add(getClass().getResource("/css/customMFXButtonWarning.css").toString());
        buttonContainer.getChildren().add(deleteButton);

        //기본적인 화면 초기화 함수
        setMainComboBox();
        setErrorText("");
        setEditMode(false);
        windowType = AddWindowType.add;

        MaterialIconView icon = new MaterialIconView(MaterialIcon.CHEVRON_LEFT, "35"); // 'PERSON' is my icon from fontawesomefx, 22 is the icon size

        backBtn.setIcon(icon);
        backBtn.defaultRippleGeneratorBehavior();
        backBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    }

    @Override
    public void onPageLoad() {
        tableController = (TableController)getPageLoader().getArgument();
        windowType = tableController.getWindowType();


        //현재 수정인지 추가인지 확인후에 버튼을 설정하는 단계입니다.
        if(windowType == AddWindowType.add){
            editModeSelector.setVisible(false);
            borrowBtn.setText("추가하기");
            String bookTitle = tableController.getBook().getName();

            name.setText(bookTitle);

            setEditMode(true);
        }
        else{
            borrowBtn.setText("빌리기");
            Book tempBook = tableController.getBook();
            if(tempBook.getName() == null)
                name.setPromptText("정보없음");
            else 
                name.setText(tempBook.getName());

            if(tempBook.getAuthor() == null)
                author.setPromptText("정보없음");
            else
                author.setText(tempBook.getAuthor());  

            if(tempBook.getPublisher() == null)
                publishDate.setPromptText("정보없음");
            else
                publishDate.setText(tempBook.getPubDate());

            if(tempBook.getPublisher() == null)
                publisher.setPromptText("정보없음");
            else
                publisher.setText(tempBook.getPubDate());

            categoryNumber.setText(String.format("%03d",tempBook.getCategory()));
        }

    }

    /**
     * 책 편집 설정 모드이다.
     * 
     * @param input 참 거짓으로 설정한다.
     */
    public void setEditMode(boolean input){
        author.setSelectable(input);
        author.setEditable(input);
        name.setSelectable(input);
        name.setEditable(input);
        publishDate.setSelectable(input);
        publishDate.setEditable(input);
        publisher.setSelectable(input);
        publisher.setEditable(input);
        categorySelectorHbox.setManaged(input);
        categorySelectorHbox.setVisible(input);
        //456
        if(input)
            bookInfoContainer.setPrefHeight(534);
        else
            bookInfoContainer.setPrefHeight(456);
        saveButton.setVisible(input);
        saveButton.setManaged(input);
        deleteButton.setVisible(input);
        deleteButton.setManaged(input);
        categoryNumber.setSelectable(input);
        categoryNumber.setEditable(input);
    }

    /**
     * 주 카테고리를 반환해준다.
     * 
     * @return `MFXcomboBox<Item>의 반환
     */
    public MFXComboBox<Item> getBigCategory(){
        return bigCategory;
    }
    /**
     * 보조 카테고리를 반환해준다.
     * 
     * @return `MFXcomboBox<Item>`의 반환
     */
    public MFXComboBox<Item> getSmallCategory(){
        return smallCategory;
    }
    /**
     * `categoryNumber`라는 텍스트필드를 반환한다.
     * 
     * @return `MFXTextField`의 반환
     */
    public MFXTextField getCategoryNumber(){
        return categoryNumber;
    }

    /**
     * `errorText`라는 레이블을 반환한다.
     * @return `Label`의 반환
     */
    public Label getErrorText(){
        return errorText;
    }

    /**
     * 메인 콤보박스를 설정한다.
     */
    private void setMainComboBox(){
        BookType.setMainComboBox(this);
    }

    /**
     * `errorText`의 텍스트를 설정한다.
     * @param text 오류 메세지
     */
    public void setErrorText(String text){
        if(text == ""){
            errorText.setVisible(false);
        }
        else{
            errorText.setVisible(true);
        }
        errorText.setText(text);
    }
    
}