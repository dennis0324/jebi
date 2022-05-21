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

/**
 * 책을 나타내는 클래스.
 * 
 * @author jdeokkim
 */
public class Book {
	// 책의 고유 ID.
	private String uid;
	
	// 책의 이름.
	private String name;
	
	// 책의 저자.
	private String author;
	
	// 책의 출판사.
	private String publisher;
	
	// 책의 출판 날짜.
	private String pubDate;
	
	// 책의 분류.
	private int category;
	
	/**
	 * `Book` 클래스의 생성자.
	 * 
	 * @param uid 책의 고유 ID.
	 */
	public Book(String uid) {
		/* TODO: ... */
		
		this.uid = uid;
	}
	
	/**
	 * 책의 고유 ID를 반환한다. 
	 * 
	 * @return 책의 고유 ID.
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * 책의 이름을 반환한다.
	 * 
	 * @return 책의 이름.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 책의 이름을 설정한다.
	 * 
	 * @param name 책의 이름.
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * 책의 저자를 반환한다.
	 * 
	 * @return 책의 저자.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * 책의 출판사를 반환한다.
	 * 
	 * @return 책의 출판사.
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * 책의 출판 날짜를 반환한다.
	 * 
	 * @return 책의 출판 날짜.
	 */
	public String getPubDate() {
		return pubDate;
	}
	
	/**
	 * 책의 분류를 반환한다.
	 * 
	 * @return 책의 분류.
	 */
	public int getCategory() {
		return category;
	}
}
