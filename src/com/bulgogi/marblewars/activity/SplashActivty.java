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
		Sprite bgSprite = new Sprite(0, 0, ResourceManager.getInstance().getGameBackgroundTextureRegion(), getVertexBufferObjectManager());			
		bgSprite.setOffsetCenter(0f, 0f);
		scene.setBackground(new SpriteBackground(bgSprite));
		return scene;
	}
}
