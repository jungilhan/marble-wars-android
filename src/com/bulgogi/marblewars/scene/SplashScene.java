package com.bulgogi.marblewars.scene;

import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.IModifier;

import android.content.Context;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.base.BaseScene;
import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.resource.SplashResource;
import com.bulgogi.marblewars.util.ModifierHelper;

public class SplashScene extends BaseScene {
	private SplashResource resource;
	
	public SplashScene(Context context, Engine engine, BaseResource resource) {
		super(engine);
		
		this.resource = (SplashResource) resource;
		this.resource.load(context, engine);
		
		populateScene();
	}

	@Override
	public void populateScene() {
		Sprite background = new Sprite(Constants.CENTER_X, Constants.CENTER_Y, resource.getBackgroundTextureRegion(), engine.getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(background));
		
		Sprite title = new Sprite(Constants.CENTER_X, Constants.CENTER_Y, resource.getTitleTextureRegion(), engine.getVertexBufferObjectManager());
		scene.attachChild(title);

		Sprite logo = new Sprite(Constants.CENTER_X, 50, resource.getLogoTextureRegion(), engine.getVertexBufferObjectManager());
		scene.attachChild(logo);		
		
		Sprite leftMarble = new Sprite(-122.5f, Constants.CAMERA_HEIGHT - 60, resource.getMarbleSmallTextureRegion(), engine.getVertexBufferObjectManager());
		leftMarble.registerEntityModifier(ModifierHelper.sequence(
				ModifierHelper.delay(leftMarble, 1.5f),
				ModifierHelper.moveX(leftMarble, 0.2f, leftMarble.getWidth() * -0.5f, 60)));
		scene.attachChild(leftMarble);
		
		float fromX = Constants.CAMERA_WIDTH + 122.5f;
		float toX = Constants.CAMERA_WIDTH - 50;
		Sprite rightBackMarble = new Sprite(fromX, 200, resource.getMarbleSmallTextureRegion(), engine.getVertexBufferObjectManager());
		rightBackMarble.registerEntityModifier(ModifierHelper.sequence(
				ModifierHelper.delay(rightBackMarble, 2f),
				ModifierHelper.moveX(rightBackMarble, 0.2f, fromX, toX)));
		scene.attachChild(rightBackMarble);
		
		fromX = Constants.CAMERA_WIDTH + 138f;
		toX = Constants.CAMERA_WIDTH - 70;
		Sprite rightFrontMarble = new Sprite(fromX, 70, resource.getMarbleLargeTextureRegion(), engine.getVertexBufferObjectManager());
		rightFrontMarble.registerEntityModifier(ModifierHelper.sequence(
				new IEntityModifierListener() {
					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
						if (listener != null) {
							listener.onSceneEnd();
							scene.detachSelf();
						}
					}
				},
				ModifierHelper.delay(rightFrontMarble, 2f),
				ModifierHelper.moveX(rightFrontMarble, 0.2f, fromX, toX)));
		
		scene.attachChild(rightFrontMarble);
	}
}
