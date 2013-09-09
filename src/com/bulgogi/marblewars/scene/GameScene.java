package com.bulgogi.marblewars.scene;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.base.BaseScene;
import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.config.Constants.Chapter;
import com.bulgogi.marblewars.config.Constants.SceneType;
import com.bulgogi.marblewars.event.Event;
import com.bulgogi.marblewars.resource.GameResource;
import com.bulgogi.marblewars.scene.model.GameSceneParams;
import com.bulgogi.marblewars.scene.model.SceneParams;
import com.bulgogi.marblewars.scene.widget.NavigationBackSprite;

import de.greenrobot.event.EventBus;

public class GameScene extends BaseScene {
	private GameResource resource;
	private ArrayList<NavigationBackSprite> navigationBackSprites = new ArrayList<NavigationBackSprite>();
	
	public GameScene(Context context, Engine engine, BaseResource resource) {
		super(engine);
		
		this.resource = (GameResource) resource;
		this.resource.load(context, engine);
		
		populateScene();
	}
	
	@Override
	public void populateScene() {
		final Sprite background = new Sprite(Constants.CENTER_X, Constants.CENTER_Y, resource.getBackgroundTextureRegion(), engine.getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(background));
		
		float x = (resource.getNavigationEasyTextureRegion().getWidth() * 0.5f) + 10;
		float y = Constants.CAMERA_HEIGHT - (resource.getNavigationEasyTextureRegion().getHeight() * 0.5f) - 10;
		final NavigationBackSprite navigationBackEasy = new NavigationBackSprite(x, y, engine, resource.getNavigationEasyTextureRegion(), resource.getFont()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (this.isVisible() && pSceneTouchEvent.isActionUp()) {
					// TODO unlocked level에 대한 처리 필요
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, 12);
					EventBus.getDefault().post(new Event.StartScene(SceneType.GAME, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				return false;
			}
		};
		navigationBackEasy.setVisible(true);
		scene.registerTouchArea(navigationBackEasy);
		scene.attachChild(navigationBackEasy);
		
		final NavigationBackSprite navigationBackNormal = new NavigationBackSprite(x, y, engine, resource.getNavigationNormalTextureRegion(), resource.getFont()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (this.isVisible() && pSceneTouchEvent.isActionUp()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.GAME, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				return false;
			}
		};
		navigationBackNormal.setVisible(false);
		scene.registerTouchArea(navigationBackNormal);
		scene.attachChild(navigationBackNormal);
		
		final NavigationBackSprite navigationBackHard = new NavigationBackSprite(x, y, engine, resource.getNavigationHardTextureRegion(), resource.getFont()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (this.isVisible() && pSceneTouchEvent.isActionUp()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.GAME, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				return false;
			}
		};
		navigationBackHard.setVisible(false);
		scene.registerTouchArea(navigationBackHard);
		scene.attachChild(navigationBackHard);
		
		x = Constants.CAMERA_WIDTH - (resource.getRetryTextureRegion().getWidth() * 0.5f) - 90;
		y = Constants.CAMERA_HEIGHT - (resource.getRetryTextureRegion().getHeight() * 0.5f) - 10;
		final Sprite retry = new Sprite(x, y, resource.getRetryTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					return true;
				}
				return false;
			}
		};
		scene.registerTouchArea(retry);
		scene.attachChild(retry);
		
		x = Constants.CAMERA_WIDTH - (resource.getGoDownTextureRegion().getWidth() * 0.5f) - 45;
		y = (resource.getGoDownTextureRegion().getHeight() * 0.5f) + 70;
		final ButtonSprite go = new ButtonSprite(x, y, resource.getGoUpTextureRegion(), resource.getGoDownTextureRegion(), engine.getVertexBufferObjectManager(), new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
			}
		});
		go.setVisible(true);
		scene.registerTouchArea(go);
		scene.attachChild(go);
		
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		
		navigationBackSprites.add(navigationBackEasy);
		navigationBackSprites.add(navigationBackNormal);
		navigationBackSprites.add(navigationBackHard);
	}

	@Override
	public void onSceneChanged(SceneParams params) {
		this.params = params;
		NavigationBackSprite navigationBackSprite = getNavigationBackSprite(this.params.chapter);
		navigationBackSprite.setText(((GameSceneParams) this.params).currentLevel + "/" + this.params.maxLevel);		
	}
	
	private NavigationBackSprite getNavigationBackSprite(final Chapter chapter) {
		for (NavigationBackSprite navigationBackSprite : navigationBackSprites) {
			navigationBackSprite.setVisible(false);
		}
		
		switch (chapter) {
		case EASY:
			navigationBackSprites.get(0).setVisible(true);
			return navigationBackSprites.get(0);
		case NORMAL:
			navigationBackSprites.get(1).setVisible(true);
			return navigationBackSprites.get(1);
		case HARD:
			navigationBackSprites.get(2).setVisible(true);
			return navigationBackSprites.get(2);
		}
		
		throw new IllegalArgumentException();
	}
}
