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

package io.github.dennis0324.jebi.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 책의 분류 (카테고리)를 나타내는 열거형. 
 * 
 * @author dennis0324, jdeokkim
 */
public class BookCategory {
	private static HashMap<String, ArrayList<String>> CATEGORIES;
	
	/**
     * 클래스 생성자가 호출되기 전에 호출된다.
     */
	static {
		
	}
}
