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

package io.github.dennis0324.jebi.gui.component;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.effects.ripple.RippleClipType;
import io.github.palexdev.materialfx.factories.RippleClipTypeFactory;

/**
 * 캡슐 버튼을 나타내는 클래스.
 * 
 * @author dennis0324
 */
public class CapsuleButton extends MFXButton {
    @Override
    protected void setupRippleGenerator() {
        super.setupRippleGenerator();
        
        getRippleGenerator().setClipSupplier(
        	() -> {
        		return new RippleClipTypeFactory(RippleClipType.ROUNDED_RECTANGLE)
        			.setArcs(40).build(this);
        	}
        );
    }
}
