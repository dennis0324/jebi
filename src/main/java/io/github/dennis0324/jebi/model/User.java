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
 * 사용자를 나타내는 클래스.
 * 
 * @author jdeokkim
 */
public class User {
	// 사용자의 고유 번호.
	private String uid;
	
	// 사용자의 이메일 주소.
	private String email;
	
	// 사용자의 이름.
	private String name;
	
	// 사용자의 전화번호.
	private String phoneNumber;
	
	// 사용자의 관리자 여부.
	private boolean _isAdmin;
	
	/**
	 * `User` 클래스의 생성자.
	 * 
	 * @param uid 사용자의 고유 번호.
	 */
	public User(String uid) {
		// TODO: ...
		this.uid = uid;
	}
	
	/**
	 * 사용자의 고유 번호를 반환한다.
	 * 
	 * @return 사용자의 고유 번호.
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * 사용자의 이메일 주소를 반환한다.
	 * 
	 * @return 사용자의 이메일 주소.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 사용자의 이름을 반환한다.
	 * 
	 * @return 사용자의 이름.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 사용자의 전화번호를 반환한다.
	 * 
	 * @return 사용자의 전화번호.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * 사용자의 이름을 반환한다.
	 * 
	 * @return 사용자의 이름.
	 */
	public boolean isAdmin() {
		return _isAdmin;
	}
}
