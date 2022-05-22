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
import java.util.List;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.NodeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 검색 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class SearchCompoController extends Controller {
	// 사용자 정보 필터 배열.
	public static final List<String> USER_FILTERS;
	
	// 책 정보 필터 배열.
    public static final List<String> BOOK_FILTERS;
    
    // 사용자 또는 책 검색에 사용되는 필터 배열.
    private ObservableList<String> filters = FXCollections.observableArrayList();
    
    @FXML
    private MFXTextField searchField;
    
    @FXML
    private MFXComboBox<String> searchFilterComboBox;
    
    @FXML
    private MFXIconWrapper submitIconBtn;
    
    @FXML
    private VBox searchResultContainer;
    
    /**
     * 클래스 생성자가 호출되기 전에 호출된다.
     */
    static {
    	USER_FILTERS = Arrays.asList("이름", "이메일", "전화번호");
    	BOOK_FILTERS = Arrays.asList("이름", "작가", "출판사", "출판 날짜", "카테고리");
    }
    
    @Override
    public void initialize() {
    	setupIconBtn();
    	onPageLoad();
    }

    @Override
    public void onPageLoad() {
    	// 관찰 가능한 `ArrayList`이므로 내부 데이터만 변경하면 된다.
    	searchFilterComboBox.setItems(filters);
    	
    	updateFilters(0);
    }
    
    @FXML
    public void onSubmitIconBtnClicked() {
    	/* TODO: ... */
    }
    
    /**
     * 주어진 인덱스에 따라 검색 필터를 업데이트한다.
     * 
     * @param filterIndex 사용자가 선택한 메뉴의 인덱스.
     */
    public void updateFilters(int filterIndex) {
    	searchFilterComboBox.selectFirst();
    	
    	if (filterIndex == 0) filters.setAll(USER_FILTERS);
    	else if (filterIndex == 1) filters.setAll(BOOK_FILTERS);
    }
    
    /**
     * 아이콘 버튼을 초기화한다.
     */
    private void setupIconBtn() {
    	MaterialIconView icon = new MaterialIconView(MaterialIcon.SEARCH, "35");
    	
    	submitIconBtn.setIcon(icon);
    	submitIconBtn.defaultRippleGeneratorBehavior();
    	submitIconBtn.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    	
        NodeUtils.makeRegionCircular(submitIconBtn);
    }
}
