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
import com.bulgogi.marblewars.scene.model.MainMenuParams;
import com.bulgogi.marblewars.scene.widget.LevelSelector;

import de.greenrobot.event.EventBus;

public class SubMenuScene extends BaseScene {
private SubMenuResource resource;
	
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
					// [TODO] 
					MainMenuParams params = new MainMenuParams(Chapter.EASY);
					EventBus.getDefault().post(new Event.StartScene(SceneType.SUB_MENU, SceneType.MAIN_MENU, params));
					return true;
				}
				return false;
			}
		};
		scene.registerTouchArea(navigationBack);
		scene.attachChild(navigationBack);
		
		// [TODO] 
		LevelSelector levelSelector = new LevelSelector(engine, scene, Chapter.EASY, 22, 25);
		levelSelector.createTiles(resource.getTileTextureRegion(), resource.getLockTextureRegion(), resource.getFont());
		scene.attachChild(levelSelector);
		
		scene.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public void setParams(Object params) {
		
	}
}
