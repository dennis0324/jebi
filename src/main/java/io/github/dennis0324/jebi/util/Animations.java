package io.github.dennis0324.jebi.util;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * UI 애니메이션을 관리하는 클래스.
 * 
 * @author jdeokkim
 */
public class Animations {
	/**
     * 메시지 레이블의 애니메이션을 반환한다.
     * 
     * @return 메시지 레이블의 애니메이션.
     */
    public static SequentialTransition getLabelAnimation(Label label) {
        PauseTransition pauseAnim = new PauseTransition(Duration.seconds(2.5));
        FadeTransition fadeAnim = new FadeTransition(Duration.seconds(0.5), label);
        
        fadeAnim.setFromValue(1.0);
        fadeAnim.setToValue(0.0);
        
        SequentialTransition result = new SequentialTransition(pauseAnim, fadeAnim);
        
        result.setOnFinished(event -> label.setManaged(false));
        
        return result;
    }
    
    /**
     * 메시지 레이블을 업데이트한다.
     * 
     * @param message 레이블이 보여줄 오류 메시지.
     */
    public static void updateLabel(Label label, String message) {
    	if (label.getUserData() == null) 
    		label.setUserData(getLabelAnimation(label));
    	
    	SequentialTransition labelAnim = (SequentialTransition) label.getUserData();
    	
    	label.setText(message);
    	label.setManaged(true);
        
        if (labelAnim.getStatus() != Status.RUNNING)
        	labelAnim.playFromStart();
    }
}
