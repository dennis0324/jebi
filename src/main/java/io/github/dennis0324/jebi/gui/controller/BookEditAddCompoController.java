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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.WriteResult;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.component.CapsuleButton;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.BookCategories;
import io.github.dennis0324.jebi.model.DatabaseMode;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * 책 추가 및 수정 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class BookEditAddCompoController extends Controller {  
	// `BookEditAddCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(BookEditAddCompoController.class);
    
    // 책의 대분류가 저장된 "관찰 가능한" 배열.
    private static ObservableList<String> bigCategories = FXCollections.observableArrayList();
    
    // 책의 소분류가 저장된 "관찰 가능한" 배열.
    private static ObservableList<String> smallCategories = FXCollections.observableArrayList();
    
    // 이전 화면으로 돌아갈지의 "관찰 가능한" 여부. 
    private static SimpleBooleanProperty backProperty = new SimpleBooleanProperty(false);
    
    // 다음으로 수행할 데이터베이스 작업의 "관찰 가능한" 종류.
    private static SimpleObjectProperty<DatabaseMode> databaseModeProperty;
    
    // `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 테이블에서 선택한 책.
    private Book selectedBook = null;
    
    /* ::: '이전' 버튼과 '수정' 버튼 영역... ::: */
    
    @FXML
	private MFXIconWrapper backIconBtn;
	
	@FXML
	private MFXToggleButton editToggleBtn;
	
	/* ::: 텍스트 필드 영역... ::: */
	
	@FXML
	private MFXTextField nameField;
	
	@FXML
	private MFXTextField authorField;
	
	@FXML
	private MFXTextField publisherField;
	
	@FXML
	private MFXTextField publishDateField;
	
	@FXML
	private MFXTextField categoryField;
	
	@FXML
	private Label errorMsgLabel;
	
	/* ::: 대분류와 소분류 선택 영역... ::: */
	
	@FXML
	private HBox categorySelContainer;
	
	@FXML
	private MFXComboBox<String> smallCategoryComboBox;
	
	@FXML
	private MFXComboBox<String> bigCategoryComboBox;
	
	@FXML
	private HBox btnContainer;
	
	/* ::: 나머지 버튼 영역... ::: */
	
	// '저장' 버튼.
	private CapsuleButton saveCapsuleBtn;
	
	// '대출' 버튼.
	private CapsuleButton borrowCapsuleBtn;
	
	// '삭제' 버튼.
	private CapsuleButton deleteCapsuleBtn;
	
	/**
     * 클래스 생성자가 호출되기 전에 호출된다.
     */
	static {
		databaseModeProperty = new SimpleObjectProperty<>(DatabaseMode.RELOAD);
	}
    
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
	public void initialize() {
		// dennis0324: 편집 영역 형태 유지를 위해 
		// `setManaged()`에서 `setVisible()`로 변경
		errorMsgLabel.setVisible(false);
		
		setupComboBoxes();
		setupCapsuleBtns();
		setupIconBtn();
		
		onPageLoad();
    }

    @Override
    public void onPageLoad() {
    	databaseModeProperty.addListener(
    		(observable, oldValue, newValue) -> {
    			if (newValue == DatabaseMode.EDIT) {
    				editToggleBtn.setVisible(true);
    				
    				nameField.setEditable(false);
    		    	authorField.setEditable(false);
    		    	publisherField.setEditable(false);
    		    	publishDateField.setEditable(false);
    		    	categoryField.setEditable(false);
    		    	
    		    	bigCategoryComboBox.setEditable(false);
    		    	smallCategoryComboBox.setEditable(false);
    		    	
    		    	borrowCapsuleBtn.setText("대출");
    			} else if (newValue == DatabaseMode.ADD) {
    				editToggleBtn.setVisible(false);
    				
    				nameField.setEditable(true);
    		    	authorField.setEditable(true);
    		    	publisherField.setEditable(true);
    		    	publishDateField.setEditable(true);
    		    	categoryField.setEditable(true);
    		    	
    		    	bigCategoryComboBox.setEditable(true);
    		    	smallCategoryComboBox.setEditable(true);
    		    	
    		    	borrowCapsuleBtn.setText("추가");
    			}
    		}
    	);
    	
    	databaseModeProperty.set(DatabaseMode.EDIT);
    }
    
    @FXML
    public void onBackIconBtnClicked() {
    	backProperty.set(true);
    }
    
    @FXML
    public void onEditToggleBtnAction() {
        boolean editMode = editToggleBtn.isSelected();
    	
    	nameField.setEditable(editMode);
    	authorField.setEditable(editMode);
    	publisherField.setEditable(editMode);
    	publishDateField.setEditable(editMode);
    	categoryField.setEditable(editMode);
    	
    	bigCategoryComboBox.setEditable(editMode);
    	smallCategoryComboBox.setEditable(editMode);
    }
    
    /**
     * '저장' 버튼을 클릭했을 때 호출되는 메소드이다.
     * 
     * @param event 마우스 이벤트.
     */
    public void onSaveCapsuleBtnPressed(MouseEvent event) {
    	selectedBook.setName(nameField.getText());
    	selectedBook.setAuthor(authorField.getText());
    	selectedBook.setPublisher(publisherField.getText());
    	selectedBook.setPublishDate(publishDateField.getText());
    	
    	final int categoryNumber = (bigCategoryComboBox.getSelectedIndex()) * 100
    		+ (smallCategoryComboBox.getSelectedIndex()) * 10;
    	
    	selectedBook.setCategoryNumber(categoryNumber);
    	
    	ApiFutures.addCallback(
			provider.updateBook(selectedBook),
			new ApiFutureCallback<WriteResult>() {
                @Override
                public void onSuccess(WriteResult result) {
                	backProperty.set(true);
                	
                	databaseModeProperty.set(DatabaseMode.RELOAD);
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
		);
    }
    
    /**
     * '대출' 또는 '추가' 버튼을 클릭했을 때 호출되는 메소드이다.
     * 
     * @param event 마우스 이벤트.
     */
    public void onBorrowCapsuleBtnPressed(MouseEvent event) {
    	if (databaseModeProperty.get() == DatabaseMode.EDIT) {
    		/* TODO: '대출' 기능 구현 */
    	} else if (databaseModeProperty.get() == DatabaseMode.ADD) {
    		/* TODO: '추가' 기능 구현 */
    	}
    }
    
    /**
     * '삭제' 버튼을 클릭했을 때 호출되는 메소드이다.
     * 
     * @param event 마우스 이벤트.
     */
    public void onDeleteCapsuleBtnPressed(MouseEvent event) {
    	ApiFutures.addCallback(
			provider.deleteBook(selectedBook.getUid()),
			new ApiFutureCallback<WriteResult>() {
                @Override
                public void onSuccess(WriteResult result) {
                	Platform.runLater(
                		() -> {
                			backProperty.set(true);
                			
                			databaseModeProperty.set(DatabaseMode.RELOAD);
                		}
                	);
                }
                
                @Override
                public void onFailure(Throwable t) {
                	DataProvider.getLogger().warn(t.toString());
                }
            },
            provider.getThreadPool()
		);
    }
    
    /**
	 * 이전 화면으로 돌아갈지의 "관찰 가능한" 여부를 반환한다.
	 * 
	 * @return 이전 화면으로 돌아갈지의 "관찰 가능한" 여부.
	 */
	public SimpleBooleanProperty getBackProperty() {
		return backProperty;
	}
	
	/**
	 * 다음으로 수행할 데이터베이스 작업의 "관찰 가능한" 종류를 반환한다.
	 * 
	 * @return 다음으로 수행할 데이터베이스 작업의 "관찰 가능한" 종류.
	 */
	public SimpleObjectProperty<DatabaseMode> getDatabaseModeProperty() {
		return databaseModeProperty;
	}
	
	/**
     * 책 추가 및 수정 영역을 업데이트한다.
     * 
     * @param selectedBook 테이블에서 선택한 책.
     */
    public void updateData(Book selectedBook) {
    	if (this.selectedBook == selectedBook) return;
    	
    	this.selectedBook = selectedBook;
    	
    	backProperty.set(false);
    	
    	if (selectedBook == null) {
    		nameField.clear();
    		authorField.clear();
    		publisherField.clear();
    		publishDateField.clear();
    		categoryField.clear();
    		
    		bigCategoryComboBox.setText("알 수 없음");
        	smallCategoryComboBox.setText("알 수 없음");
    	} else {
    		if (selectedBook.getAuthor().isBlank()) databaseModeProperty.set(DatabaseMode.ADD);
        	else databaseModeProperty.set(DatabaseMode.EDIT);
    		
    		nameField.setText(selectedBook.getName());
    		authorField.setText(selectedBook.getAuthor());
    		publisherField.setText(selectedBook.getPublisher());
    		publishDateField.setText(selectedBook.getPublishDate());
    		categoryField.setText(Integer.toString(selectedBook.getCategoryNumber()));
    		
			final int[] indexes = BookCategories.getIndexes(selectedBook);
			
			smallCategories.setAll(BookCategories.SMALL_CATEGORIES[indexes[0]]);
    		
    		bigCategoryComboBox.selectIndex(indexes[0]);
	    	smallCategoryComboBox.selectIndex(indexes[1]);
    	}
    }
    
    /**
     * 콤보 박스를 초기화한다.
     */
    private void setupComboBoxes() {
    	bigCategories.setAll(BookCategories.BIG_CATEGORIES);
    	smallCategories.setAll(BookCategories.SMALL_CATEGORIES[0]);
    	
    	bigCategoryComboBox.setItems(bigCategories);
    	smallCategoryComboBox.setItems(smallCategories);
    }
    
    /**
     * 아이콘 버튼을 초기화한다.
     */
    private void setupCapsuleBtns() {
        saveCapsuleBtn = new CapsuleButton();
        
        saveCapsuleBtn.setText("저장");
        saveCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        saveCapsuleBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onSaveCapsuleBtnPressed);
        
        btnContainer.getChildren().add(saveCapsuleBtn);
        
        borrowCapsuleBtn = new CapsuleButton();
        
        // borrowCapsuleBtn.setText("대출");
        borrowCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        borrowCapsuleBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBorrowCapsuleBtnPressed);
        
        btnContainer.getChildren().add(borrowCapsuleBtn);

        deleteCapsuleBtn = new CapsuleButton();
        
        deleteCapsuleBtn.setText("삭제");
        deleteCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXButtonWarning.css").toString());
        deleteCapsuleBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onDeleteCapsuleBtnPressed);
        
        btnContainer.getChildren().add(deleteCapsuleBtn);
    }
    
    /**
     * 아이콘 버튼을 초기화한다.
     */
    private void setupIconBtn() {
    	MaterialIconView icon = new MaterialIconView(MaterialIcon.CHEVRON_LEFT, "35");
		
    	backIconBtn.setIcon(icon);
    	backIconBtn.defaultRippleGeneratorBehavior();
    	backIconBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    	
    	NodeUtils.makeRegionCircular(backIconBtn);
	}
}