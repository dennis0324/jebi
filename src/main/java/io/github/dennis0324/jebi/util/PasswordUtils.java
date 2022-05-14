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

package io.github.dennis0324.jebi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 문자열 해싱 메소드가 정의된 클래스.
 * 
 * @author jdeokkim
 */
public final class PasswordUtils {
	private static final int MIN_LENGTH = 8;
	
	/**
	 * SHA-256 해싱 알고리즘을 사용하여, 주어진 문자열을 암호화한다.
	 * 
	 * @param str SHA-256 해싱 알고리즘으로 암호화할 문자열.
	 * @return SHA-256 해싱 알고리즘으로 암호화된 문자열.
	 */
	public static String encrypt(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update(str.getBytes());
			
			byte[] bytes = md.digest();
			
			final StringBuilder sb = new StringBuilder();
			
		    for (int i = 0; i < bytes.length; i++)
		        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		    
		    return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * 주어진 문자열이 올바른 비밀번호인지 확인한다.
	 * 
	 * @param str 올바른 비밀번호인지 확인할 문자열.
	 * @return 문자열이 올바른 비밀번호인지 여부.
	 */
	public static boolean isValid(String str) {
		return !str.isBlank() && str.length() > MIN_LENGTH;
	}
}
