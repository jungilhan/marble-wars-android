package com.bulgogi.marblewars.scene;

import java.util.ArrayList;

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
import com.bulgogi.marblewars.resource.MainMenuResource;
import com.bulgogi.marblewars.scene.model.SceneParams;

import de.greenrobot.event.EventBus;

public class MainMenuScene extends BaseScene {
	private final int UNLOCK = 0;
	private final int LOCK = 1;
	
	private MainMenuResource resource;
	private ArrayList<Sprite> normalSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> hardSprites = new ArrayList<Sprite>();
	
	public MainMenuScene(Context context, Engine engine, BaseResource resource) {
		super(engine);
		
		this.resource = (MainMenuResource) resource;
		this.resource.load(context, engine);
		
		populateScene();
	}

	@Override
	public void populateScene() {
		final Sprite background = new Sprite(Constants.CENTER_X, Constants.CENTER_Y, resource.getBackgroundTextureRegion(), engine.getVertexBufferObjectManager());			
		scene.setBackground(new SpriteBackground(background));
		
		float x = Constants.CENTER_X;
		float y = Constants.CAMERA_HEIGHT - (resource.getTitleTextureRegion().getHeight() * 0.5f) - 15;
		final Sprite title = new Sprite(x, y, resource.getTitleTextureRegion(), engine.getVertexBufferObjectManager());
		scene.attachChild(title);
		
		x = Constants.CENTER_X * 0.35f;
		y = Constants.CENTER_Y - 30;
		final Sprite easyUnlock = new Sprite(x, y, resource.getEasyUnlockTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.MAIN_MENU, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				
				return false;
			}
		};
		scene.registerTouchArea(easyUnlock);
		scene.attachChild(easyUnlock);
		
		x = Constants.CENTER_X;
		final Sprite normalUnlock = new Sprite(x, y, resource.getNormalUnlockTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp() && this.isVisible()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.MAIN_MENU, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				
				return false;
			}
		};
		scene.registerTouchArea(normalUnlock);
		scene.attachChild(normalUnlock);
		
		x = Constants.CENTER_X * 1.65f;
		final Sprite hardUnlock = new Sprite(x, y, resource.getHardUnlockTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp() && this.isVisible()) {
					SceneParams sceneParams = new SceneParams(params.chapter, params.maxLevel, params.unlockedLevel);
					EventBus.getDefault().post(new Event.StartScene(SceneType.MAIN_MENU, SceneType.SUB_MENU, sceneParams));
					return true;
				}
				
				return false;
			}
		};
		scene.registerTouchArea(hardUnlock);
		scene.attachChild(hardUnlock);
		
		x = Constants.CENTER_X;
		final Sprite normalLock = new Sprite(x, y, resource.getNormalLockTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp() && this.isVisible()) {
					return true;
				}
				
				return false;
			}
		};
		scene.registerTouchArea(normalLock);
		scene.attachChild(normalLock);
		
		x = Constants.CENTER_X * 1.65f;
		final Sprite hardLock = new Sprite(x, y, resource.getHardLockTextureRegion(), engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionUp() && this.isVisible()) {
					return true;
				}
				
				return false;
			}
		};
		scene.registerTouchArea(hardLock);
		scene.attachChild(hardLock);
		
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		
		normalSprites.add(normalUnlock);
		normalSprites.add(normalLock);
		
		hardSprites.add(hardUnlock);
		hardSprites.add(hardLock);
	}

	@Override
	public void onSceneChanged(final SceneParams params) {
		this.params = params;
		changeSpritesVisibility(this.params.chapter);
	}
	
	private void changeSpritesVisibility(final Chapter chapter) {
		for (Sprite normalSprite : normalSprites) {
			normalSprite.setVisible(false);
		}
		
		for (Sprite hardSprite : hardSprites) {
			hardSprite.setVisible(false);
		}
		
		switch (chapter) {
		case EASY:
			normalSprites.get(LOCK).setVisible(true);
			hardSprites.get(LOCK).setVisible(true);
			break;
		case NORMAL:
			normalSprites.get(UNLOCK).setVisible(true);
			hardSprites.get(LOCK).setVisible(true);
			break;
		case HARD:
			normalSprites.get(UNLOCK).setVisible(true);
			hardSprites.get(UNLOCK).setVisible(true);
			break;
		}
	}
}
