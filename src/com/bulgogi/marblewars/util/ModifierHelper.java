package com.bulgogi.marblewars.util;

import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.SequenceEntityModifier.ISubSequenceShapeModifierListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.ease.EaseLinear;

public class ModifierHelper {
	public static MoveXModifier moveX(final Sprite sprite, final float duration, final float fromX, final float toX) {
		MoveXModifier moveXModifier = new MoveXModifier(duration, fromX, toX, EaseLinear.getInstance());
		return moveXModifier;
	}
	
	public static DelayModifier delay(final Sprite sprite, final float duration) {
		return new DelayModifier(duration);
	}
	
	public static SequenceEntityModifier sequence(final IEntityModifier... entityModifiers) {
		return new SequenceEntityModifier(entityModifiers);
	}
	
	public static SequenceEntityModifier sequence(final IEntityModifierListener entityModifierListener, final IEntityModifier... entityModifiers) {
		return new SequenceEntityModifier(entityModifierListener, entityModifiers);
	}
	 
}
