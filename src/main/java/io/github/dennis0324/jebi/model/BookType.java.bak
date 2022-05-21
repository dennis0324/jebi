package io.github.dennis0324.jebi.model;

import java.text.ParseException;
import java.util.Arrays;

import io.github.dennis0324.jebi.gui.controller.BookEditAddCompoController;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Parent;

public class BookType {
    
   
    
    public static final String[] BIGCATEGORY = {
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

    public static final String[] SMALLCATEGORY_0 = {
        "도서학, 서지학",
        "문헌정보학",
        "백과사전",
        "강연집,수필집,연설문집",
        "일반연속간행물",
        "일반학회,단체,협회,기관",
        "신문,언론,저널리즘",
        "일반전집,총서",
        "향토자료"
    };

    public static final String[] SMALLCATEGORY_1 = {
        "형이상학",
        "인식론,인과론,인간학",
        "철학의 체계",
        "경학",
        "동양철학,사상",
        "서양철학",
        "논리학",
        "심리학",
        "윤리학,도덕철학"
    };

    public static final String[] SMALLCATEGORY_2 = {
        "비교종교",
        "불교",
        "기독교",
        "도교",
        "천도교",
        "신도",
        "힌두교,브라만교",
        "이슬람교(회교)",
        "기타 제종교"
    };
    public static final String[] SMALLCATEGORY_3 = {
        "통계학",
        "경제학",
        "사회학,사회문제",
        "정치학",
        "행정학",
        "법학",
        "교육학",
        "풍속,예절,민속학",
        "국방,군사학"
    };
    public static final String[] SMALLCATEGORY_4 = {
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
    public static final String[] SMALLCATEGORY_5 = {
        "의학",
        "농업,농학",
        "공학,공업일반,토목공학,환경공학",
        "건축공학",
        "기계공학",
        "전기공학,전자공학",
        "화학공학",
        "제조업",
        "생활과학"
    };
    public static final String[] SMALLCATEGORY_6 = {
        "건축물",
        "조각,조형미술",
        "공예,장식미술",
        "서예",
        "회화,도화",
        "사진예술",
        "음악",
        "공연예술,매체예술",
        "오락,스포츠"
    };
    public static final String[] SMALLCATEGORY_7 = {
        "한국어",
        "중국어",
        "일본어,기타아시아제어",
        "영어",
        "독일어",
        "프랑스어",
        "스페인어,포르투갈어",
        "이탈리어",
        "기타제어"
    };
    public static final String[] SMALLCATEGORY_8 = {
        "한국문학",
        "중국문학",
        "일본문학,기타아시아문학",
        "영미문학",
        "독일문학",
        "프랑스문학",
        "스페인,포르투갈문학",
        "이탈리아문학",
        "기타제문학"
    };
    public static final String[] SMALLCATEGORY_9 = {
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

    private static String[][] smallCategoryArr = {
        SMALLCATEGORY_0,
        SMALLCATEGORY_1,
        SMALLCATEGORY_3,
        SMALLCATEGORY_4,
        SMALLCATEGORY_5,
        SMALLCATEGORY_6,
        SMALLCATEGORY_7,
        SMALLCATEGORY_8,
        SMALLCATEGORY_9
    };

    public static void setMainComboBox(BookEditAddCompoController controller){
        for(int i = 0; i < BIGCATEGORY.length; i++){
            controller.getBigCategory().getItems().add(new Item(BIGCATEGORY[i], i, controller));
        
        }
        controller.getBigCategory().valueProperty().addListener((obs,oldItem,newItem) -> {
            newItem.getSmallCategory().setText("");
            newItem.getCategoryNum().setText("");
            setSubComboxBox(controller,newItem.getValue());
            // String num = String.format("%03d", index * 100 + newItem.getValue() * 10);
            // controller.getCategoryNumber().setText(num);
        });
    }

    public static void setSubComboxBox(BookEditAddCompoController controller,int index){

        for(int i = 0; i < controller.getSmallCategory().getItems().size(); i++){
            controller.getSmallCategory().getItems().remove(i);
        }

        for(int i = 0; i < smallCategoryArr[index].length; i++){
            controller.getSmallCategory().getItems().add(new Item(smallCategoryArr[index][i],i,controller));
        }
        controller.getSmallCategory().valueProperty().addListener((obs,oldItem,newItem) -> {
            String num = String.format("%03d", index * 100 + newItem.getValue() * 10);
            controller.getCategoryNumber().setText(num);
        });
    }


    public static class Item {
        private final String name ;
        // private final StringProperty details = new SimpleStringProperty() ;
        private int value;
        private BookEditAddCompoController controller;
        // private MFXComboBox category 

        public Item(String name, int number, BookEditAddCompoController controller) {
            this.name = name ;
            this.controller = controller;
            // setDetails(details) ;
            this.value = number;
        }

        public String getName() {
            return name ;
        }

        @Override
        public String toString() {
            return getName();
        }

        public final int getValue(){
            return value;
        }

        public MFXComboBox<Item> getSmallCategory(){
            return controller.getSmallCategory();
        }

        public MFXTextField getCategoryNum(){
            return controller.getCategoryNumber();
        }



    }

    public static class CategoryChangeListener implements ChangeListener<String>{
        private BookEditAddCompoController controller;
        private int number;
        public CategoryChangeListener(BookEditAddCompoController controller){
            this.controller = controller;
        }
        @Override
        public void changed(ObservableValue<? extends String> obs, String oldString, String number) {
            // TODO Auto-generated method stub
            try{
                Integer.parseInt(number);
            }
            catch(NumberFormatException e){
                controller.setErrorText("숫자만 입력하세요");
                return;
            }
            StringBuffer strNumber = new StringBuffer(number);
            String first,second;
            int firstNum, secondNum;
            if(number.length() == 3){
                controller.setErrorText("");

                first = strNumber.substring(0,1);
                second = strNumber.substring(1,2);
                firstNum = Integer.parseInt(first);
                secondNum = Integer.parseInt(second);
                controller.getBigCategory().setText(BookType.BIGCATEGORY[firstNum]);
                controller.getSmallCategory().setText(smallCategoryArr[firstNum][secondNum]);      
            }
            else{
                controller.setErrorText("3자리 숫자입니다.");
            }
            
        }


    }
}
