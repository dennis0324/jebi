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


package io.github.dennis0324.jebi.core;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import io.github.dennis0324.jebi.util.Constants;

/**
 * Firebase를 통한 서버 데이터 조작을 지원하는 클래스.
 * 
 * @author jdeokkim
 */
public class DataProvider {
	// `DataProvider` 클래스의 로거.
	private static final Logger LOG = LoggerFactory.getLogger(DataProvider.class);
	
	// Firebase 애플리케이션의 초기화 여부.
	private static boolean initialized = false;
		
	// Firebase의 Firebase 인증 인스턴스.
	private FirebaseAuth auth;
	
	// Firebase의 Firestore 데이터베이스 인스턴스.
	private Firestore db;
	
	/**
	 * `DataProvider` 클래스의 생성자.
	 */
	public DataProvider() {
		if (!initialized) {
			try {
				InputStream stream = getClass().getResourceAsStream(Constants.CONFIG_PATH);
				
				LOG.info("Firebase 설정 파일을 불러왔습니다.");
				
				FirebaseOptions options = FirebaseOptions.builder()
				    .setCredentials(GoogleCredentials.fromStream(stream))
				    .setDatabaseUrl(Constants.DATABASE_URL)
				    .build();
				
				FirebaseApp.initializeApp(options);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
			LOG.info("Firebase SDK 초기화가 완료되었습니다.");
			
			initialized = true;
		}
		
		this.auth = FirebaseAuth.getInstance();
		this.db = FirestoreClient.getFirestore();
	}
	
	/* TODO: ... */
}
