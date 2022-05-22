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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.cloud.firestore.DocumentSnapshot;

/**
 * 사용자를 나타내는 클래스.
 * 
 * @author jdeokkim
 */
public class User {
	// 사용자의 고유 ID.
	private String uid;
	
	// 사용자의 이름.
	private String name;
	
	// 사용자의 이메일 주소.
	private String email;
	
	// 사용자의 비밀번호 해시 값.
	private String pwdHash;
	
	// 사용자의 전화번호.
	private String phoneNumber;
	
	// 사용자의 관리자 여부.
	private boolean _isAdmin;
	
	// 사용자가 빌린 책의 고유 ID 배열.
	private ArrayList<String> bookIds;
	
	/**
	 * `User` 클래스의 생성자.
	 * 
	 * @param name 사용자의 이름.
	 * @param email 사용자의 이메일 주소.
	 * @param pwdHash 사용자의 비밀번호 해시 값.
	 * @param phoneNumber 사용자의 전화번호.
	 */
	public User(String name, String email, String pwdHash, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.pwdHash = pwdHash;
		this.phoneNumber = phoneNumber;
		this.bookIds = new ArrayList<String>();
	}
	
	/**
	 * `User` 클래스의 생성자.
	 * 
	 * @param snapshot 데이터베이스에 저장된 사용자 데이터.
	 */
	public User(DocumentSnapshot snapshot) {
		this.bookIds = new ArrayList<String>();
		
		if (snapshot != null) {
			this.uid = snapshot.getString("uid");
			this.name = snapshot.getString("name");
			this.email = snapshot.getString("email");
			this.pwdHash = snapshot.getString("pwdHash");
			this.phoneNumber = snapshot.getString("phoneNumber");
			this._isAdmin = snapshot.contains("isAdmin") 
				? snapshot.getBoolean("isAdmin") 
				: false;
			
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) snapshot.get("bookIds");
			
			this.bookIds = new ArrayList<String>(list);
		}
	}
	
	/**
	 * 사용자의 고유 ID를 반환한다.
	 * 
	 * @return 사용자의 고유 ID.
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
	 * 사용자의 비밀번호 해시 값을 반환한다.
	 * 
	 * @return 사용자의 비밀번호 해시 값.
	 */
	public String getPwdHash() {
		return pwdHash;
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
	 * 사용자의 관리자 여부를 반환한다.
	 * 
	 * @return 사용자의 관리자 여부.
	 */
	public boolean isAdmin() {
		return _isAdmin;
	}
	
	/**
	 * 사용자가 빌린 모든 책의 고유 ID 배열을 반환한다.
	 * 
	 * @return 사용자가 빌린 모든 책의 고유 ID 배열.
	 */
	public ArrayList<String> getBookIds() {
		return bookIds;
	}
	
	/**
	 * 사용자 정보를 해시맵 형태로 반환한다.
	 * 
	 * @return 해시맵 형태로 변환된 사용자 정보.
	 */
	public HashMap<String, Object> getData() {
		HashMap<String, Object> result = new HashMap<>();
		
		result.put("uid", uid);
		result.put("name", name);
		result.put("email", email);
		result.put("pwdHash", pwdHash);
		result.put("phoneNumber", phoneNumber);
		result.put("isAdmin", _isAdmin);
		result.put("bookIds", bookIds);
		
		return result;
	}
	
	/**
	 * 사용자의 고유 ID를 설정한다.
	 * 
	 * @param 사용자의 고유 ID.
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * 사용자의 비밀번호 해시 값을 설정한다.
	 * 
	 * @param pwdHash 사용자의 비밀번호 해시 값.
	 */
	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}
}
