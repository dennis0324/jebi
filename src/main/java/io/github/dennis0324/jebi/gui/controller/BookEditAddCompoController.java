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

import io.github.dennis0324.jebi.gui.TextFieldColor;
import io.github.dennis0324.jebi.gui.TextFieldColor.TextfieldType;
import io.github.dennis0324.jebi.model.BookType;
import io.github.dennis0324.jebi.model.BookType.CategoryChangeListener;
import io.github.dennis0324.jebi.model.BookType.Item;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.cell.MFXComboBoxCell;
import javafx.scene.control.Label;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookEditAddCompoController extends Controller {


    private int mainCategoryNum;
    private int subCategoryNum;
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
    void onBackBtnClicked(MouseEvent event) {

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
        setMainComboBox();
        setErrorText("");
        setEditMode(false);
    }

    @Override
    public void onPageLoad() {
        // FXCollections.observableList(Arrays.asList(""));
        // bigCategory.
    }

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
        categoryNumber.setSelectable(input);
        categoryNumber.setEditable(input);
    }


    public MFXComboBox<Item> getBigCategory(){
        return bigCategory;
    }
    public MFXComboBox<Item> getSmallCategory(){
        return smallCategory;
    }
    public MFXTextField getCategoryNumber(){
        return categoryNumber;
    }
    public void setMainCategoryNum(int number){
        this.mainCategoryNum = number;
    }
    public Label getErrorText(){
        return errorText;
    }

    private void setMainComboBox(){
        BookType.setMainComboBox(this);
        // for(int i = 0; i< BookType.BIGCATEGORY.length;i++){
        //     bigCategory.getItems().add(new Item(BookType.BIGCATEGORY[i], i, this));
        // }

        // bigCategory.valueProperty().addListener((obs,oldItem,newItem) -> {
        //     // System.out.println(obs);
        //     newItem.getSmallCategory().getItems().removeAll();
        //     newItem.setMainNum();

        // });
    }

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