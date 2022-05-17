package io.github.dennis0324.jebi.gui.component;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.effects.ripple.RippleClipType;
import io.github.palexdev.materialfx.factories.RippleClipTypeFactory;

public class CapsuleButton extends MFXButton {
    @Override
    protected void setupRippleGenerator() {
        super.setupRippleGenerator();
        getRippleGenerator().setClipSupplier(() -> new RippleClipTypeFactory(RippleClipType.ROUNDED_RECTANGLE).setArcs(40).build(this));
    }
}
