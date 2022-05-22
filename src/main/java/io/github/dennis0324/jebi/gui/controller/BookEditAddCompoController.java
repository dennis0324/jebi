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

import io.github.dennis0324.jebi.core.DataProvider;
import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.User;
import javafx.beans.property.SimpleObjectProperty;

/**
 * 사용자 추가 및 수정 영역 컨트롤러를 나타내는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class BookEditAddCompoController extends Controller {
	// `DataProvider` 인스턴스.
    private DataProvider provider = DataProvider.getInstance();
    
	// `BookEditAddCompoController`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(BookEditAddCompoController.class);
    
	/* ::: 컨트롤러 기본 메소드 정의... ::: */
	
	@Override
	public void initialize() {
    	/* TODO: ... */
    }

    @Override
    public void onPageLoad() {
       /* TODO: ... */
    }
}