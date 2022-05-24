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
 * 책의 분류 (카테고리)를 나타내는 열거형. 
 * 
 * @author dennis0324, jdeokkim
 */
public class BookCategory {
	// 책의 대분류.
	private static final String[] BIG_CATEGORIES = {
        "총류",
        "철학",
        "종교",
        "사회과학",
        "자연과학",
        "기술과학",
        "예술",
        "언어",
        "문학",
        "역사"
    };
	
	// "총류" 아래의 소분류.
	public static final String[] SMALL_CATEGORIES_0 = {
        "도서학, 서지학",
        "문헌정보학",
        "백과사전",
        "강연집, 수필집, 연설문집",
        "일반연속간행물",
        "일반학회, 단체, 협회, 기관",
        "신문, 언론, 저널리즘",
        "일반전집, 총서",
        "향토자료"
    };
	
	// "철학" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_1 = {
        "형이상학",
        "인식론, 인과론, 인간학",
        "철학의 체계",
        "경학",
        "동양철학, 사상",
        "서양철학",
        "논리학",
        "심리학",
        "윤리학, 도덕철학"
    };
    
    // "종교" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_2 = {
        "비교종교",
        "불교",
        "기독교",
        "도교",
        "천도교",
        "신도",
        "힌두교, 브라만교",
        "이슬람교 (회교)",
        "기타 제종교"
    };
    
    // "사회과학" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_3 = {
        "통계학",
        "경제학",
        "사회학, 사회문제",
        "정치학",
        "행정학",
        "법학",
        "교육학",
        "풍속, 예절, 민속학",
        "국방, 군사학"
    };
    
    // "자연과학" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_4 = {
        "수학",
        "물리학",
        "화학",
        "천문학",
        "지학",
        "광물학",
        "생명과학",
        "식물학",
        "동물학"
    };
    
    // "기술과학" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_5 = {
        "의학",
        "농업, 농학",
        "공학, 공업일반, 토목공학, 환경공학",
        "건축공학",
        "기계공학",
        "전기공학, 전자공학",
        "화학공학",
        "제조업",
        "생활과학"
    };
    
    // "예술" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_6 = {
        "건축물",
        "조각, 조형미술",
        "공예, 장식미술",
        "서예",
        "회화, 도화",
        "사진예술",
        "음악",
        "공연예술, 매체예술",
        "오락,스포츠"
    };
    
    // "언어" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_7 = {
        "한국어",
        "중국어",
        "일본어, 기타아시아제어",
        "영어",
        "독일어",
        "프랑스어",
        "스페인어, 포르투갈어",
        "이탈리어",
        "기타제어"
    };
    
    // "문학" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_8 = {
        "한국문학",
        "중국문학",
        "일본문학, 기타아시아문학",
        "영미문학",
        "독일문학",
        "프랑스문학",
        "스페인, 포르투갈문학",
        "이탈리아문학",
        "기타제문학"
    };
    
    // "역사" 아래의 소분류.
    private static final String[] SMALL_CATEGORIES_9 = {
        "아시아",
        "유럽",
        "아프리카",
        "북아메리카",
        "남아메리카",
        "오세아니아",
        "양극지방",
        "지리",
        "전기"
    };
    
    // 책의 소분류.
    private static final String[][] SMALL_CATEGORIES = {
    	SMALL_CATEGORIES_0,
    	SMALL_CATEGORIES_1,
    	SMALL_CATEGORIES_2,
    	SMALL_CATEGORIES_3,
    	SMALL_CATEGORIES_4,
    	SMALL_CATEGORIES_5,
    	SMALL_CATEGORIES_6,
    	SMALL_CATEGORIES_7,
    	SMALL_CATEGORIES_8,
    	SMALL_CATEGORIES_9
    };
    
    /**
     * 주어진 책의 대분류와 소분류를 반환한다.
     * 
     * @param book 대분류와 소분류를 찾을 책.
     * @return 책의 대분류와 소분류.
     */
    public static String[] getCategories(Book book) {
    	return getCategories(book.getCategoryNumber());
    }
    
    /**
     * 주어진 분류 번호에 해당하는 책의 대분류와 소분류를 반환한다.
     * 
     * @param categoryNumber 책의 분류 번호.
     * @return 책의 대분류와 소분류.
     */
    public static String[] getCategories(int categoryNumber) {
    	if (categoryNumber < 100 || categoryNumber > 999) return null;
    	
    	int firstDigit = categoryNumber / 100;
    	int secondDigit = (categoryNumber % 100) / 10;
    	
    	return new String[] { 
    		BIG_CATEGORIES[firstDigit], 
    		SMALL_CATEGORIES[firstDigit][secondDigit]
    	};
    }
}
