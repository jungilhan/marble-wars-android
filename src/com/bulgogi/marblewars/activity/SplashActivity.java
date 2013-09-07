package com.bulgogi.marblewars.activity;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.SequenceEntityModifier.ISubSequenceShapeModifierListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.modifier.IModifier;

import android.content.Intent;
import android.content.res.Configuration;

import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.game.SplashLoader;
import com.bulgogi.marblewars.manager.ResourceManager;
import com.bulgogi.marblewars.util.ModifierHelper;

public class SplashActivity extends SimpleBaseGameActivity {
	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera camera = new Camera(0, 0, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		camera.setResizeOnSurfaceSizeChanged(true);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() throws IOException {
		ResourceManager.getInstance().getSplash().load(mEngine, getApplicationContext());
	}

	@Override
	protected Scene onCreateScene() {
		final Scene scene = new Scene();
		final SplashLoader splashLoader = ResourceManager.getInstance().getSplash();
		final float centerX = Constants.CAMERA_WIDTH * 0.5f;
		final float centerY = Constants.CAMERA_HEIGHT * 0.5f;
		
		Sprite background = new Sprite(centerX, centerY, splashLoader.getBackgroundTextureRegion(), getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(background));
		
		Sprite title = new Sprite(centerX, centerY, splashLoader.getTitleTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(title);

		Sprite logo = new Sprite(centerX, 50, splashLoader.getLogoTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(logo);		
		
		Sprite leftMarble = new Sprite(-122.5f, Constants.CAMERA_HEIGHT - 60, splashLoader.getMarbleSmallTextureRegion(), getVertexBufferObjectManager());
		leftMarble.registerEntityModifier(ModifierHelper.sequence(
				ModifierHelper.delay(leftMarble, 1.5f),
				ModifierHelper.moveX(leftMarble, 0.2f, leftMarble.getWidth() * -0.5f, 60)));
		scene.attachChild(leftMarble);
		
		float fromX = Constants.CAMERA_WIDTH + 122.5f;
		float toX = Constants.CAMERA_WIDTH - 50;
		Sprite rightBackMarble = new Sprite(fromX, 200, splashLoader.getMarbleSmallTextureRegion(), getVertexBufferObjectManager());
		rightBackMarble.registerEntityModifier(ModifierHelper.sequence(
				ModifierHelper.delay(rightBackMarble, 2f),
				ModifierHelper.moveX(rightBackMarble, 0.2f, fromX, toX)));
		scene.attachChild(rightBackMarble);
		
		fromX = Constants.CAMERA_WIDTH + 138f;
		toX = Constants.CAMERA_WIDTH - 70;
		Sprite rightFrontMarble = new Sprite(fromX, 70, splashLoader.getMarbleLargeTextureRegion(), getVertexBufferObjectManager());
		rightFrontMarble.registerEntityModifier(ModifierHelper.sequence(
				new IEntityModifierListener() {
					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
						Intent intent = new Intent(SplashActivity.this, MainMenuActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
						startActivity(intent);
						finish();
					}
				},
				ModifierHelper.delay(rightFrontMarble, 2f),
				ModifierHelper.moveX(rightFrontMarble, 0.2f, fromX, toX),
				ModifierHelper.delay(rightFrontMarble, 1f)));
		
		scene.attachChild(rightFrontMarble);
 
		return scene;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
