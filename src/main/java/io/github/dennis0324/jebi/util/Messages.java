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

/**
 * 프로그램에 사용되는 모든 문자열이 저장된 클래스.
 * 
 * @author jdeokkim
 */
public final class Messages {
	/* TODO: `java.util.ResourceBundle` 클래스를 통한 국제화 (i18n) 지원 */
	
	// 프로그램의 메인 창의 이름.
	public static final String PRIMARY_STAGE_TITLE = "jebi - JavaFX로 만든 도서 관리 소프트웨어";
	
	// 성 또는 이름이 올바르지 않을 때 출력할 오류 메시지.
	public static final String ERROR_INVALID_NAME = "올바르지 않은 이름입니다.";
	
	// 이메일 주소가 올바르지 않을 때 출력할 오류 메시지.
	public static final String ERROR_INVALID_EMAIL = "올바르지 않은 이메일 주소입니다.";
	
	// 비밀번호가 올바르지 않을 때 출력할 오류 메시지.
	public static final String ERROR_INVALID_PASSWORD = "비밀번호는 최소 " 
			+ StringUtils.MIN_PASSWORD_LENGTH + "자 이상이어야 합니다.";
	
	// 전화번호가 올바르지 않을 때 출력할 오류 메시지.
	public static final String ERROR_INVALID_PHONE_NUMBER = "올바르지 않은 전화번호입니다.";
	
	// 이메일 주소가 이미 등록되어 있을 때 출력할 오류 메시지.
	public static final String ERROR_EMAIL_ALREADY_REGISTERED = "이미 등록된 이메일 주소입니다.";
	
	// 입력한 이메일 주소를 가진 사용자가 없을 때 출력할 오류 메시지.
	public static final String ERROR_USER_NOT_FOUND = "사용자를 찾을 수 없습니다.";
	
	// 비밀번호가 틀렸을 때 출력할 오류 메시지.
	public static final String ERROR_PASSWORD_MISMATCH = "잘못된 비밀번호입니다. 다시 시도해주세요.";
	
	// 알 수 없는 오류가 발생했을 때 출력할 오류 메시지.
	public static final String ERROR_UNKNOWN = "알 수 없는 오류가 발생하였습니다.";
}
