package com.bulgogi.marblewars.activity;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.res.Configuration;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.factory.ResourceFactory;
import com.bulgogi.marblewars.factory.ResourceFactory.Type;
import com.bulgogi.marblewars.listener.SceneListener;
import com.bulgogi.marblewars.scene.MainMenuScene;
import com.bulgogi.marblewars.scene.SplashScene;

public class GameActivity extends SimpleBaseGameActivity {
	private SplashScene splashScene;
	private MainMenuScene mainMenuScene;

	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera camera = new Camera(0, 0, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		camera.setResizeOnSurfaceSizeChanged(true);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() throws IOException {
		final BaseResource splashResource = ResourceFactory.getInstance().create(Type.SPLASH);
		splashScene = new SplashScene(this, getEngine(), splashResource);

		final BaseResource mainMenuResource = ResourceFactory.getInstance().create(Type.MAIN_MENU);
		mainMenuScene = new MainMenuScene(this, getEngine(), mainMenuResource);

		splashScene.setListener(new SceneListener() {
			@Override
			public void onSceneEnd() {
				mEngine.registerUpdateHandler(new TimerHandler(1f, new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						getEngine().setScene(mainMenuScene.getScene());
					}
				}));
			}
		});
	}

	@Override
	protected Scene onCreateScene() {
		return splashScene.getScene();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
