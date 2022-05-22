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

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * 사용자 추가 및 수정 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class UserEditAddCompoController extends Controller {
	// `UserEditAddCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(UserEditAddCompoController.class);
    
	// `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 테이블에서 선택한 사용자가 빌린 모든 책 정보가 저장된 배열.
    private ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();
    
    // 이전 화면으로 돌아갈지의 "관찰 가능한" 여부. 
    private SimpleBooleanProperty backProperty = new SimpleBooleanProperty(false);
    
    // 테이블에서 선택한 사용자.
    private User selectedUser = null;
    
	@FXML
	private MFXIconWrapper backIconBtn;
	
	@FXML
	private MFXToggleButton editToggleBtn;
	
	@FXML
	private MFXTextField nameField;
	
	@FXML
	private MFXTextField emailField;
	
	@FXML
	private MFXTextField phoneNumberField;
	
	@FXML
	private MFXPaginatedTableView<Book> borrowedBookTable;
	
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
    public void initialize() {
		setupIconBtn();
		
		onPageLoad();
    }

    @Override
    public void onPageLoad() {
    	setupBorrowedBookTable();
    }
    
    @FXML
    public void onBackIconBtnClicked() {
    	backProperty.set(true);
    }
    
    @FXML
    public void onEditToggleBtnAction() {
    	boolean editMode = editToggleBtn.isSelected();
    	
    	nameField.setEditable(editMode);
    	emailField.setEditable(editMode);
    	phoneNumberField.setEditable(editMode);
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
     * 사용자 추가 및 수정 영역을 업데이트한다.
     * 
     * @param user 테이블에서 선택한 사용자.
     */
    public void updateData(User selectedUser) {
    	if (this.selectedUser == selectedUser) return;
    	
    	this.selectedUser = selectedUser;
    	
    	backProperty.set(false);
    	
    	borrowedBooks.clear();
    	borrowedBookTable.setItems(null);
    	
    	if (selectedUser == null) {
    		nameField.clear();
    		emailField.clear();
    		phoneNumberField.clear();
    	} else {
    		nameField.setText(selectedUser.getName());
    		emailField.setText(selectedUser.getEmail());
    		phoneNumberField.setText(selectedUser.getPhoneNumber());
    		
    		for (String uid : selectedUser.getBookIds()) {
    			ApiFutures.addCallback(
		            provider.getBookByUid(uid),
		            new ApiFutureCallback<Book>() {
		                @Override
		                public void onSuccess(Book result) {
		                	Platform.runLater(() -> borrowedBooks.add(result));
		                }
		                
		                @Override
		                public void onFailure(Throwable t) {
		                	DataProvider.getLogger().warn(t.toString());
		                }
		            },
		            provider.getThreadPool()
		        );
    		}
    		
    		borrowedBookTable.setItems(borrowedBooks);
    	}
    }
    
    /**
   	 * 테이블의 셀을 클릭했을 때 호출되는 메소드이다.
   	 * 
   	 * @param event 마우스 이벤트의 종류.
   	 */
   	private void onTableRowCellClicked(MouseEvent event) {
   		/* TODO: ... */
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
    
    /**
     * 테이블에서 선택한 사용자가 빌린 모든 책 정보의 테이블을 초기화한다.
     */
    @SuppressWarnings("unchecked")
	private void setupBorrowedBookTable() {
    	LOG.info("빌린 책 정보 테이블을 초기화합니다.");
    	
    	MFXTableColumn<Book> nameColumn = new MFXTableColumn<>("이름", false, Comparator.comparing(Book::getName));
        MFXTableColumn<Book> authorColumn = new MFXTableColumn<>("작가", false, Comparator.comparing(Book::getAuthor));
        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("출판사", false, Comparator.comparing(Book::getPublisher));

        nameColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getName, this::onTableRowCellClicked));
        authorColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getAuthor, this::onTableRowCellClicked));
        publisherColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getPublisher, this::onTableRowCellClicked));
        
        borrowedBookTable.getTableColumns().addAll(
        	nameColumn,
        	authorColumn,
        	publisherColumn
        );

        borrowedBookTable.getFilters().addAll(
            new StringFilter<>("이름", Book::getName),
            new StringFilter<>("작가", Book::getAuthor),
            new StringFilter<>("출판사", Book::getPublisher)
        );
        
        // borrowedBookTable.autosizeColumnsOnInitialization();
    }
}
