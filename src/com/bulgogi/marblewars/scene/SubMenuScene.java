package com.bulgogi.marblewars.scene;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.base.BaseScene;
import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.config.Constants.Chapter;
import com.bulgogi.marblewars.config.Constants.SceneType;
import com.bulgogi.marblewars.event.Event;
import com.bulgogi.marblewars.resource.SubMenuResource;
import com.bulgogi.marblewars.scene.model.SceneParams;
import com.bulgogi.marblewars.scene.widget.LevelSelector;

import de.greenrobot.event.EventBus;

public class SubMenuScene extends BaseScene {
	private SubMenuResource resource;
	private LevelSelector levelSelector;
	
	public SubMenuScene(Context context, Engine engine, BaseResource resource) {
		super(engine);
		
		this.resource = (SubMenuResource) resource;
		this.resource.load(context, engine);
		
		populateScene();
	}
	
	@Override
	public void populateScene() {
		final Sprite background = new Sprite(Constants.CENTER_X, Constants.CENTER_Y, resource.getBackgroundTextureRegion(), engine.getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(background));
		
		float x = Constants.CENTER_X;
		float y = Constants.CAMERA_HEIGHT - (resource.getTitleTextureRegion().getHeight() * 0.5f);
		final Sprite title = new Sprite(x, y, resource.getTitleTextureRegion(), engine.getVertexBufferObjectManager());
		scene.attachChild(title);
		
		x = (resource.getNavigationBackTextureRegion().getWidth() * 0.5f) + 10;
		y = Constants.CAMERA_HEIGHT - (resource.getNavigationBackTextureRegion().getHeight() * 0.5f) - 10;
		final Sprite navigationBack = new Sprite(x, y, resource.getNavigationBackTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.SUB_MENU, SceneType.MAIN_MENU, sceneParams));
					return true;
				}
				return false;
			}
		};
		scene.registerTouchArea(navigationBack);
		scene.attachChild(navigationBack);
		
		scene.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public void onSceneChanged(final SceneParams params) {
		this.params = params;
		
		if (levelSelector != null) {
			levelSelector.detachChildren();
			levelSelector.dispose();
			levelSelector.detachSelf();
			levelSelector = null;
		}
		
		levelSelector = new LevelSelector(engine, scene, this.params.chapter, this.params.maxLevel, this.params.unlockedLevel);
		levelSelector.createTiles(resource.getTileTextureRegion(), resource.getLockTextureRegion(), resource.getFont());
		scene.attachChild(levelSelector);
	}
}
