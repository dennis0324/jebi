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

package io.github.dennis0324.jebi.gui;

import java.util.function.Function;

import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * 테이블의 셀을 생성하고, 테이블 관련 이벤트를 처리하는 클래스.
 * 
 * @author dennis0324, jdeokkim
 */
public class TableViewHelper {
	/**
	 * 테이블의 셀을 생성한다.
	 * 
	 * @param <T> 셀 생성에 사용되는 클래스.
	 * @param <V> 셀에 채울 내용을 나타내는 클래스.
	 * @param extractor 셀에 채울 내용을 만들기 위해 사용할 함수.
	 * @return 생성한 테이블의 셀.
	 */
	public static <T, V> MFXTableRowCell<T, V> getRowCellFactory(
		Function<T, V> extractor,
		EventHandler<? super MouseEvent> onTableRowCellClicked
	) {
		MFXTableRowCell<T, V> result = new MFXTableRowCell<>(extractor);
		
		// 셀을 클릭했을 때 수행할 작업을 설정한다.
		result.addEventHandler(MouseEvent.MOUSE_CLICKED, onTableRowCellClicked);
		
		return result;
	}
}