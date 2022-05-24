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

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.component.CapsuleButton;
import io.github.dennis0324.jebi.model.Book;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * 사용자 추가 및 수정 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class BookEditAddCompoController extends Controller {  
	// `BookEditAddCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(BookEditAddCompoController.class);
    
    // `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 이전 화면으로 돌아갈지의 "관찰 가능한" 여부. 
    private SimpleBooleanProperty backProperty = new SimpleBooleanProperty(false);
    
    // 다음으로 수행할 데이터베이스 작업의 "관찰 가능한" 종류. (0은 추가, 1은 편집)
    private SimpleIntegerProperty databaseModeProperty = new SimpleIntegerProperty(-1);
    
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
	
	// @FXML
	// private MFXComboBox<?> smallCategoryComboBox;
	
	// @FXML
	// private MFXComboBox<?> bigCategoryComboBox;
	
	@FXML
	private HBox btnContainer;
	
	/* ::: 나머지 버튼 영역... ::: */
	
	// '저장' 버튼.
	private CapsuleButton saveCapsuleBtn;
	
	// '대출' 버튼.
	private CapsuleButton borrowCapsuleBtn;
	
	// '삭제' 버튼.
	private CapsuleButton deleteCapsuleBtn;
    
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
	public void initialize() {
		errorMsgLabel.setVisible(false); // setmanaged에서 setvisible로 바꼈음 이거 사라지게 설계한게 아니라서 없애면 형태가 바뀜(dennis ko)
		
		setupCapsuleBtns();
		setupIconBtn();
		
		onPageLoad();
    }

    @Override
    public void onPageLoad() {
    	databaseModeProperty.addListener(
    		(observable, oldValue, newValue) -> { 
    			if (newValue.intValue() == 0) {
    				editToggleBtn.setVisible(true);
    				
    				nameField.setEditable(false);
    		    	authorField.setEditable(false);
    		    	publisherField.setEditable(false);
    		    	publishDateField.setEditable(false);
    		    	categoryField.setEditable(false);
    		    	
    		    	borrowCapsuleBtn.setText("대출");
    			} else if (newValue.intValue() == 1) {
    				editToggleBtn.setVisible(false);
    				
    				nameField.setEditable(true);
    		    	authorField.setEditable(true);
    		    	publisherField.setEditable(true);
    		    	publishDateField.setEditable(true);
    		    	categoryField.setEditable(true);
    		    	
    		    	borrowCapsuleBtn.setText("추가");
    			}
    		}
    	);
    	
    	databaseModeProperty.set(0);
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
	public SimpleIntegerProperty getDatabaseModeProperty() {
		return databaseModeProperty;
	}
	
	/**
     * 책 추가 및 수정 영역을 업데이트한다.
     * 
     * @param user 테이블에서 선택한 책.
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
    	} else {
    		if (selectedBook.getAuthor().isBlank()) databaseModeProperty.set(1);
        	else databaseModeProperty.set(0);
    		
    		nameField.setText(selectedBook.getName());
    		authorField.setText(selectedBook.getAuthor());
    		publisherField.setText(selectedBook.getPublisher());
    		publishDateField.setText(selectedBook.getPublishDate());
    		categoryField.setText(Integer.toString(selectedBook.getCategoryNumber()));
    		
    		/* TODO: ... */
    	}
    }
    
    /**
     * 아이콘 버튼을 초기화한다.
     */
    private void setupCapsuleBtns() {
        saveCapsuleBtn = new CapsuleButton();
        
        saveCapsuleBtn.setText("저장");
        saveCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        
        btnContainer.getChildren().add(saveCapsuleBtn);
        
        borrowCapsuleBtn = new CapsuleButton();
        
        borrowCapsuleBtn.setText("대출");
        borrowCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXbutton.css").toString());
        
        btnContainer.getChildren().add(borrowCapsuleBtn);

        deleteCapsuleBtn = new CapsuleButton();
        
        deleteCapsuleBtn.setText("삭제");
        deleteCapsuleBtn.getStylesheets().add(getClass().getResource("/css/customMFXButtonWarning.css").toString());
        
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