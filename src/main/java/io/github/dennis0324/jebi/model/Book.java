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

import java.util.HashMap;

import com.google.cloud.firestore.DocumentSnapshot;

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
	private String publishDate;
	
	// 책의 분류.
	private int categoryNumber;
	
	// 책을 빌린 사용자의 고유 ID.
	private String borrowerId;
	
	// 책을 빌려간 날짜.
	private String borrowDate;
	
	/**
	 * `Book` 클래스의 생성자.
	 * 
	 * @param name 책의 이름.
	 */
	public Book(String name) {
		this.name = name;
		this.author = "";
		this.publisher = "";
		this.publishDate = "";
		this.categoryNumber = -1;
		this.borrowerId = "";
		this.borrowDate = "";
	}
	
	/**
	 * `User` 클래스의 생성자.
	 * 
	 * @param snapshot 데이터베이스에 저장된 책의 데이터.
	 */
	public Book(DocumentSnapshot snapshot) {
		this.uid = snapshot.getString("uid");
		this.name = snapshot.getString("name");
		this.author = snapshot.getString("author");
		this.publisher = snapshot.getString("publisher");
		this.publishDate = snapshot.getString("publishDate");
		this.categoryNumber = snapshot.getLong("categoryNumber").intValue();
		this.borrowerId = snapshot.getString("borrowerId");
		this.borrowDate = snapshot.getString("borrowDate");
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
	public String getPublishDate() {
		return publishDate;
	}
	
	/**
	 * 책의 분류를 반환한다.
	 * 
	 * @return 책의 분류.
	 */
	public int getCategoryNumber() {
		return categoryNumber;
	}
	
	/**
	 * 책을 빌린 사용자의 고유 ID를 반환한다. 
	 * 
	 * @return 책을 빌린 사용자의 고유 ID.
	 */
	public String getBorrowerId() {
		return borrowerId;
	}
	
	/**
	 * 책을 빌려간 날짜를 반환한다.
	 * 
	 * @return 책을 빌려간 날짜.
	 */
	public String getBorrowDate() {
		return borrowDate;
	}
	
	/**
	 * 책의 정보를 해시맵 형태로 반환한다.
	 * 
	 * @return 해시맵 형태로 변환된 책의 정보.
	 */
	public HashMap<String, Object> getData() {
		HashMap<String, Object> result = new HashMap<>();
		
		result.put("uid", uid);
		result.put("name", name);
		result.put("author", author);
		result.put("publisher", publisher);
		result.put("publishDate", publishDate);
		result.put("categoryNumber", categoryNumber);
		result.put("borrowerId", borrowerId);
		result.put("borrowDate", borrowDate);
		
		return result;
	}
	
	/**
	 * 책의 고유 ID를 설정한다.
	 * 
	 * @param uid 책의 고유 ID.
	 */
	public void setUid(String uid) {
		this.uid = uid;
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
	 * 책의 저자를 설정한다.
	 * 
	 * @param author 책의 저자.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 책의 출판사를 설정한다.
	 * 
	 * @param publisher 책의 출판사.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * 책의 출판 날짜를 설정한다.
	 * 
	 * @param publishDate 책의 출판 날짜.
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	/**
	 * 책의 분류를 설정한다.
	 * 
	 * @param categoryNumber 책의 분류.
	 */
	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}
	
	/**
	 * 책을 빌린 사용자의 고유 ID를 설정한다. 
	 * 
	 * @param borrowerId 책을 빌린 사용자의 고유 ID.
	 */
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	
	/**
	 * 책을 빌려간 날짜를 설정한다.
	 * 
	 * @param borrowDate 책을 빌려간 날짜.
	 */
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
}
