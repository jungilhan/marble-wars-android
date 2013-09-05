package com.bulgogi.marblewars.activity;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.manager.ResourceManager;

public class SplashActivty extends SimpleBaseGameActivity {
	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera camera = new Camera(0, 0, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		camera.setResizeOnSurfaceSizeChanged(true);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() throws IOException {
		ResourceManager.getInstance().loadGameTextures(mEngine, getApplicationContext());
	}

	@Override
	protected Scene onCreateScene() {
		Scene scene = new Scene();
		
		float centerX = Constants.CAMERA_WIDTH / 2;
		float centerY = Constants.CAMERA_HEIGHT / 2;
		
		Sprite bgSprite = new Sprite(centerX, centerY, ResourceManager.getInstance().getSplashBackgroundTextureRegion(), getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(bgSprite));
		
		Sprite titleSprite = new Sprite(centerX, centerY, ResourceManager.getInstance().getSplashTitleTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(titleSprite);

		Sprite logoSprite = new Sprite(centerX, 50, ResourceManager.getInstance().getSplashLogoTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(logoSprite);		
		
		Sprite marbleSmallSprite1 = new Sprite(60, Constants.CAMERA_HEIGHT - 60, ResourceManager.getInstance().getSplashMarbleSmallTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(marbleSmallSprite1);
		
		Sprite marbleSmallSprite2 = new Sprite(Constants.CAMERA_WIDTH - 50, 200, ResourceManager.getInstance().getSplashMarbleSmallTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(marbleSmallSprite2);
		
		Sprite marbleLargeSprite = new Sprite(Constants.CAMERA_WIDTH - 70, 70, ResourceManager.getInstance().getSplashMarbleLargeTextureRegion(), getVertexBufferObjectManager());
		scene.attachChild(marbleLargeSprite);
		
		return scene;
	}
}
