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
 * 프로그램에 사용되는 모든 상수가 저장된 클래스.
 * 
 * @author jdeokkim
 */
public final class Constants {
	// Firebase의 설정 파일이 저장된 경로.
	public static final String CONFIG_PATH = "/firebase-config.json";
	
	// 프로그램 테스트에 사용될 Firebase 데이터베이스 URL.
	public static final String DATABASE_URL = "https://jebi-test.firebaseapp.com";
	
	// 프로그램의 메인 창의 가로 길이.
	public static final int SCREEN_WIDTH = 1440;
	
	// 프로그램의 메인 창의 세로 길이.
	public static final int SCREEN_HEIGHT = 960;
}
