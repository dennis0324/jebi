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
import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Constants;

/**
 * Firebase를 통한 서버 데이터 조작을 지원하는 클래스.
 * 
 * @author jdeokkim
 */
public class DataProvider {
	// `DataProvider`의 로거.
	private static final Logger LOG = LoggerFactory.getLogger(DataProvider.class);
	
	// `DataProvider`의 인스턴스.
	private static DataProvider instance = null;
	
	// Firebase 애플리케이션의 초기화 여부.
	private static boolean initialized = false;
	
	// Firebase의 Cloud Firestore 인스턴스.
	private Firestore db;
	
	/**
	 * `DataProvider` 클래스의 생성자.
	 */
	private DataProvider() {
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
		
		this.db = FirestoreClient.getFirestore();
	}
	
	/**
	 * `DataProvider`의 인스턴스를 반환한다.
	 * 
	 * @return `DataProvider`의 인스턴스.
	 */
	public static DataProvider getInstance() {
		if (instance == null) 
			instance = new DataProvider();
		
		return instance;
	}
	
	/**
	 * 주어진 사용자 정보를 통해 사용자 계정을 생성한다.
	 * 
	 * @param user 사용자 계정의 정보.
	 * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
	 */
	public ApiFuture<WriteResult> createUser(User user) {
		if (user.getUid() == null)
			user.setUid(UUID.randomUUID().toString());
		
		DocumentReference ref = db.collection("users").document(user.getUid());
		
		HashMap<String, Object> data = new HashMap<>();
		
		data.put("uid", user.getUid());
		data.put("email", user.getEmail());
		data.put("name", user.getName());
		data.put("phoneNumber", user.getPhoneNumber());
		
		return ref.set(data);
	}
	
	/**
	 * 주어진 고유 ID에 대응하는 사용자 계정을 삭제한다.
	 * 
	 * @param uid 사용자의 고유 ID.
	 * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
	 */
	public ApiFuture<WriteResult> deleteUser(String uid) {
		DocumentReference ref = db.collection("users").document(uid);
		
		return ref.delete();
	}
}
