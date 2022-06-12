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

package io.github.dennis0324.jebi.gui.controller;


import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

/**
 * 테이블 미 클릭시 노출되는 컴포넌트 관리 클라스
 * 
 * @author dennis ko
 */
public class IdleStateCompoController extends Controller {

    /* ::: '아이콘 관련 컴포넌트' ... ::: */
    @FXML
    private MFXIconWrapper WarningIcon;

    /* ::: 컨트롤러 기본 메소드 정의... ::: */

    @Override
    public void initialize() {
        setupIcon();
    }

    @Override
    public void onPageLoad() {
        
    }

    /**
     * 아이콘을 초기화한다.
     * 
     */
    private void setupIcon(){

        MaterialIconView icon = new MaterialIconView(MaterialIcon.WARNING, "35");
        
        WarningIcon.setIcon(icon);
    	WarningIcon.defaultRippleGeneratorBehavior();
    	WarningIcon.getRippleGenerator().setRippleColor(Color.rgb(190, 190, 190));
    }
    
}
