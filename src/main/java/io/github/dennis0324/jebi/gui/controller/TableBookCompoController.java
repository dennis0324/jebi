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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.gui.TableViewHelper;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.User;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * 책 관리 메뉴 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableBookCompoController extends Controller {  
	// `TableBookCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(TableBookCompoController.class);
    
    // `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
    // 모든 책의 정보가 저장된 배열.
    private ObservableList<Book> books = FXCollections.observableArrayList();
    
    // 테이블에서 선택한 책의 "관찰 가능한" 정보.
    private SimpleObjectProperty<Book> bookProperty = new SimpleObjectProperty<>(null);
    
    @FXML
	private MFXTableView<Book> bookTable;
    
    /* ::: 컨트롤러 기본 메소드 정의... ::: */
    
    @Override
    public void initialize() {
    	onPageLoad();
    }
    
    @Override
	public void onPageLoad() {
    	setupBookTable();
	}
    
    /**
	 * 테이블에서 선택한 책의 "관찰 가능한" 정보를 반환한다.
	 * 
	 * @return 테이블에서 선택한 책의 "관찰 가능한" 정보.
	 */
	public SimpleObjectProperty<Book> getBookProperty() {
		return bookProperty;
	}
	
	/**
	 * 테이블의 셀 선택을 해제한다. 
	 */
	public void clearSelection() {
		bookTable.getSelectionModel().clearSelection();
		
		bookProperty.set(null);
	}
    
    /**
	 * 테이블의 셀을 클릭했을 때 호출되는 메소드이다.
	 * 
	 * @param event 마우스 이벤트의 종류.
	 */
	private void onTableRowCellClicked(MouseEvent event) {
		ObservableMap<Integer, Book> obMap = bookTable.getSelectionModel()
			.getSelection();
		
		Iterator<Entry<Integer, Book>> iterator = obMap.entrySet().iterator();
		
		if (!iterator.hasNext()) {
			bookProperty.set(null);
		} else {
			Entry<Integer, Book> entry = iterator.next();
			
			LOG.info("테이블에서 키 값이 " + entry.getKey() + "인 책을 선택했습니다.");
			
			bookProperty.set(entry.getValue());
		}
	}
    
    /**
	 * 책 정보 테이블을 초기화한다.
	 */
	@SuppressWarnings("unchecked")
	private void setupBookTable() {
		LOG.info("책 정보 테이블을 초기화합니다.");
		
        MFXTableColumn<Book> nameColumn = new MFXTableColumn<>("이름", false, Comparator.comparing(Book::getName));
        MFXTableColumn<Book> authorColumn = new MFXTableColumn<>("작가", false, Comparator.comparing(Book::getAuthor));
        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("출판사", false, Comparator.comparing(Book::getPublisher));
        MFXTableColumn<Book> pubDateColumn = new MFXTableColumn<>("출판 날짜", false, Comparator.comparing(Book::getPubDate));
        MFXTableColumn<Book> categoryColumn = new MFXTableColumn<>("카테고리", false, Comparator.comparing(Book::getCategory));
        // MFXTableColumn<Book> borrowerIdColumn = new MFXTableColumn<>("빌린 사람", false, Comparator.comparing(Book::getBorrowerId));
        // MFXTableColumn<Book> borrowDateColumn = new MFXTableColumn<>("빌린 날짜", false, Comparator.comparing(Book::getBorrowDate));
        
        nameColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getName, this::onTableRowCellClicked));
        authorColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getAuthor, this::onTableRowCellClicked));
        publisherColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getPublisher, this::onTableRowCellClicked));
        pubDateColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getPubDate, this::onTableRowCellClicked));
        categoryColumn.setRowCellFactory(book -> TableViewHelper.getRowCellFactory(Book::getCategory, this::onTableRowCellClicked));
        // borrowerIdColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getBorrowerId));
        // borrowDateColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getBorrowDate));
        
        bookTable.getTableColumns().addAll(
        	nameColumn,
        	authorColumn,
        	publisherColumn,
        	pubDateColumn,
        	categoryColumn
        	// borrowerIdColumn,
        	// borrowDateColumn
        );
        
        bookTable.getFilters().addAll(
            new StringFilter<>("이름", Book::getName),
            new StringFilter<>("작가", Book::getAuthor),
            new StringFilter<>("출판사", Book::getPublisher),
            new StringFilter<>("출판 날짜", Book::getPubDate),
            new IntegerFilter<>("카테고리", Book::getCategory),
            new StringFilter<>("빌린 사람", Book::getBorrowerId),
            new StringFilter<>("빌린 날짜", Book::getBorrowDate)
        );
        
        ApiFutures.addCallback(
            provider.getBooks(),
            new ApiFutureCallback<ArrayList<Book>>() {
                @Override
                public void onSuccess(ArrayList<Book> result) {
                	LOG.info("총 " + result.size() + "개의 책을 찾았습니다.");
                	
                	// 비동기 연산이 끝난 다음에 테이블을 업데이트한다.
                	Platform.runLater(
                		() -> {
                			books.addAll(result);
                			bookTable.setItems(books);
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
        
        // bookTable.autosizeColumnsOnInitialization();
	}
}
