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

import io.github.dennis0324.jebi.gui.PageLoader;
import javafx.scene.Parent;

/**
 * 이벤트 컨트롤러를 나타내는 추상 클래스.
 * 
 * @author jdeokkim
 */
public abstract class Controller {
	// 이벤트 컨트롤러의 `PageLoader` 인스턴스.
	private PageLoader pageLoader;
	
	// 이벤트 컨트롤러의 부모 노드.
	private Parent parent;
	
	/**
	 * 이벤트 컨트롤러의 `PageLoader` 인스턴스를 설정한다.
	 * 
	 * @return `PageLoader` 인스턴스.
	 */
	public PageLoader getPageLoader() {
		return pageLoader;
	}
	
	/**
	 * 이벤트 컨트롤러의 `PageLoader` 인스턴스를 설정한다.
	 * 
	 * @param pageLoader `PageLoader` 인스턴스.
	 */
	public void setPageLoader(PageLoader pageLoader) {
		this.pageLoader = pageLoader;
	}
	
	/**
	 * 이벤트 컨트롤러의 부모 노드를 반환한다.
	 * 
	 * @return 이벤트 컨트롤러의 부모 노드.
	 */
	public Parent getParent() {
		return parent;
	}
	
	/**
	 * 이벤트 컨트롤러의 부모 노드를 설정한다.
	 * 
	 * @param parent 이벤트 컨트롤러의 부모 노드.
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	/**
     * 이벤트 컨트롤러를 초기화한다.
     * 
     * 이 메소드는 JavaFX에 의해 자동으로 호출된다.
     */
	public abstract void initialize();
	
	/**
	 * 이벤트 컨트롤러의 초기화 직후에 호출되는 메소드이다.
	 */
	public abstract void onPageLoad();
}
