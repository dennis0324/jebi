﻿/*
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
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import io.github.dennis0324.jebi.model.Book;
import io.github.dennis0324.jebi.model.User;
import io.github.dennis0324.jebi.util.Constants;

/**
 * Firebase를 통한 서버 데이터 조작을 지원하는 클래스.
 * 
 * @author jdeokkim
 */
public final class DataProvider {
    // `DataProvider`의 로거.
    private static final Logger LOG = LoggerFactory.getLogger(DataProvider.class);
    
    // `DataProvider`의 인스턴스.
    private static DataProvider instance = null;
    
    // Firebase 애플리케이션의 초기화 여부.
    private static boolean initialized = false;
    
    // 비동기 작업 처리를 위한 스레드 풀.
    private ExecutorService pool;
    
    // Firebase의 Cloud Firestore 인스턴스.
    private Firestore db;
    
    /**
     * `DataProvider` 클래스의 생성자.
     */
    private DataProvider() {
        if (!initialized) {
            try {
                InputStream stream = getClass().getResourceAsStream(Constants.CONFIG_PATH);
                
                LOG.info("Firebase SDK를 초기화합니다.");
                
                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .setDatabaseUrl(Constants.DATABASE_URL)
                    .build();
                
                FirebaseApp.initializeApp(options);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            initialized = true;
        }
        
        this.pool = Executors.newCachedThreadPool();
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
     * `DataProvider`의 로거를 반환한다.
     * 
     * @return `DataProvider`의 로거.
     */
    public static Logger getLogger() {
    	return LOG;
    }
    
    /** 
     * 비동기 작업 처리를 위한 스레드 풀을 반환한다.
     * 
     * @return 비동기 작업 처리를 위한 스레드 풀.
     */
    public ExecutorService getThreadPool() {
    	return pool;
    }
    
    /**
     * 주어진 책의 정보를 통해 책을 생성한다.
     * 
     * @param book 책의 정보.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> createBook(final Book book) {
    	if (book.getUid() == null)
    		book.setUid(UUID.randomUUID().toString());
    	
    	DocumentReference ref = db.collection("books").document(book.getUid());
        
        LOG.info("책을 생성합니다. (고유 ID: " + book.getUid() + ")");
        
        return ref.set(book.getData());
    }
    
    /**
     * 주어진 사용자 정보를 통해 사용자 계정을 생성한다.
     * 
     * @param user 사용자 계정의 정보.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> createUser(final User user) {
        if (user.getUid() == null)
            user.setUid(UUID.randomUUID().toString());
        
        DocumentReference ref = db.collection("users").document(user.getUid());
        
        LOG.info("사용자 계정을 생성합니다. (고유 ID: " + user.getUid() + ")");
        
        return ref.set(user.getData());
    }
    
    /**
     * 주어진 책의 정보를 통해, 데이터베이스에 저장된 책의 정보를 업데이트한다.
     * 
     * @param user 책의 정보.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> updateBook(final Book book) {
    	DocumentReference ref = db.collection("books").document(book.getUid());
    	
    	LOG.info("책의 정보를 업데이트합니다. (고유 ID: " + book.getUid() + ")");
    	
    	return ref.update(book.getData());
    }
    
    /**
     * 주어진 사용자 정보를 통해, 데이터베이스에 저장된 사용자 정보를 업데이트한다.
     * 
     * @param user 사용자 계정의 정보.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> updateUser(final User user) {
    	DocumentReference ref = db.collection("users").document(user.getUid());
    	
    	LOG.info("사용자 정보를 업데이트합니다. (고유 ID: " + user.getUid() + ")");
    	
    	return ref.update(user.getData());
    }
    
    /**
     * 주어진 고유 ID에 대응하는 책을 삭제한다.
     * 
     * @param uid 책의 고유 ID.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> deleteBook(final String uid) {
        DocumentReference ref = db.collection("books").document(uid);
        
        LOG.info("책을 삭제합니다. (고유 ID: " + uid + ")");
        
        return ref.delete();
    }
    
    /**
     * 주어진 고유 ID에 대응하는 사용자 계정을 삭제한다.
     * 
     * @param uid 사용자의 고유 ID.
     * @return 데이터베이스 처리 작업 (비동기 연산)의 결과값.
     */
    public ApiFuture<WriteResult> deleteUser(final String uid) {
        DocumentReference ref = db.collection("users").document(uid);
        
        LOG.info("사용자 계정을 삭제합니다. (고유 ID: " + uid + ")");
        
        return ref.delete();
    }
    
    /**
     * 주어진 고유 ID를 가진 책을 반환한다.
     * 
     * @param uid 책의 고유 ID.
     * @return 주어진 고유 ID를 가진 책.
     */
    public ApiFuture<Book> getBookByUid(final String uid) {
    	ApiFuture<DocumentSnapshot> future = db.collection("books")
    		.document(uid)
            .get();
            
        return ApiFutures.transform(future, (snapshot) -> new Book(snapshot), pool);
    }
    
    /**
     * 주어진 고유 ID를 가진 사용자 계정을 반환한다.
     * 
     * @param uid 사용자의 고유 ID.
     * @return 주어진 고유 ID를 가진 사용자 계정.
     */
    public ApiFuture<User> getUserByUid(final String uid) {
        ApiFuture<DocumentSnapshot> future = db.collection("users")
            .document(uid)
            .get();
        
        return ApiFutures.transform(future, (snapshot) -> new User(snapshot), pool);
    }
    
    /**
     * 주어진 이메일 주소를 가진 사용자 계정을 반환한다.
     * 
     * @param email 사용자의 이메일 주소.
     * @return 주어진 이메일 주소를 가진 사용자 계정.
     */
    public ApiFuture<User> getUserByEmail(final String email) {
        ApiFuture<QuerySnapshot> future = db.collection("users")
            .whereEqualTo("email", email)
            .limit(1)
            .get();
        
        return ApiFutures.transform(
            future,
            (query) -> {
            	DocumentSnapshot snapshot = (query.getDocuments().size() > 0) 
    	            ? query.getDocuments().get(0) 
    	    	    : null;
            	
            	return new User(snapshot);
    		},
            pool
        );
    }
    
    /**
     * 주어진 전화번호를 가진 사용자 계정을 반환한다.
     * 
     * @param email 사용자의 전화번호.
     * @return 주어진 전화번호를 가진 사용자 계정.
     */
    public ApiFuture<User> getUserByPhoneNumber(final String phoneNumber) {
        ApiFuture<QuerySnapshot> future = db.collection("users")
            .whereEqualTo("phoneNumber", phoneNumber)
            .limit(1)
            .get();
        
        return ApiFutures.transform(
            future,
            (query) -> {
            	DocumentSnapshot snapshot = (query.getDocuments().size() > 0) 
    	            ? query.getDocuments().get(0) 
    	    	    : null;
            	
            	return new User(snapshot);
    		},
            pool
        );
    }
}
