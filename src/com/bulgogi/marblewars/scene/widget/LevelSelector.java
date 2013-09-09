package com.bulgogi.marblewars.scene.widget;

import org.andengine.engine.Engine;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.bulgogi.marblewars.config.Constants;
import com.bulgogi.marblewars.config.Constants.Chapter;
import com.bulgogi.marblewars.config.Constants.SceneType;
import com.bulgogi.marblewars.event.Event;
import com.bulgogi.marblewars.scene.model.GameSceneParams;

import de.greenrobot.event.EventBus;

public class LevelSelector extends Entity {
	private final int TOP_MARGIN = 40;
	private final int TILE_WIDTH = 77;
	private final int TILE_HEIGHT = 79;
	private final int TILE_PADDING = 3;
	private final int COLUMNS = 9;
	private final int ROWS = 4;

	private final Engine engine;
	private final Scene scene;
	private final Chapter chapter;
	private final int maxLevel;
	private final int unlockedLevel;
	private final float positionTile[] = new float[2];

	public LevelSelector(final Engine engine, final Scene scene, final Chapter chapter, final int maxLevel, final int unlockedLevel) {
		super();
		
		this.engine = engine;
		this.scene = scene;
		this.chapter = chapter;
		this.maxLevel = maxLevel;
		this.unlockedLevel = unlockedLevel;

		final float halfWidth = ((TILE_WIDTH * COLUMNS) + TILE_PADDING * (COLUMNS - 1)) * 0.5f;
		positionTile[0] = Constants.CAMERA_WIDTH * 0.5f - halfWidth;

		final float halfHeight = ((TILE_HEIGHT * ROWS) + TILE_PADDING * (ROWS - 1)) * 0.5f;
		positionTile[1] = Constants.CAMERA_HEIGHT * 0.5f + halfHeight - TOP_MARGIN;
	}

	public void createTiles(final ITextureRegion tileTextureRegion, final ITextureRegion lockTextureRegion, final Font font) {
		float tempX = positionTile[0] + TILE_WIDTH * 0.5f;
		float tempY = positionTile[1] - TILE_HEIGHT * 0.5f;

		int currentLevel = 1;

		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				if (currentLevel > maxLevel) {
					break;
				}
				
				boolean locked = currentLevel <= unlockedLevel ? false : true;
				LevelTile levelTile = new LevelTile(tempX, tempY, locked, currentLevel, engine, tileTextureRegion, lockTextureRegion, font);
				levelTile.attatchText();

				scene.registerTouchArea(levelTile);
				attachChild(levelTile);
				
				tempX = tempX + TILE_WIDTH + TILE_PADDING;
				currentLevel++;
			}
			
			tempX = positionTile[0] + TILE_WIDTH * 0.5f;
			tempY = tempY - TILE_HEIGHT - TILE_PADDING;
		}
	}

	private class LevelTile extends Sprite {
		private final boolean locked;
		private final int level;
		private final Engine engine;
		private final Font font;
		private final ITextureRegion lockTextureRegion;
		private Entity foregreound;

		public LevelTile(final float x, final float y, final boolean locked, final int level, final Engine engine, 
				final ITextureRegion tileTextureRegion, final ITextureRegion lockTextureRegion, final Font font) {
			super(x, y, LevelSelector.this.TILE_WIDTH, LevelSelector.this.TILE_HEIGHT, tileTextureRegion, engine.getVertexBufferObjectManager());

			this.locked = locked;
			this.engine = engine;
			this.level = level;
			this.lockTextureRegion = lockTextureRegion;
			this.font = font;
		}

		public boolean isLocked() {
			return locked;
		}
		
		public int getLevel() {
			return level;
		}
		
		public void attatchText() {
			String text = null;
			
			if (foregreound == null) {
				final float x = LevelSelector.this.TILE_WIDTH * 0.5f;
				final float y = LevelSelector.this.TILE_HEIGHT * 0.5f;

				if (this.locked) {
					foregreound = new Sprite(x, y, lockTextureRegion, engine.getVertexBufferObjectManager());
				} else {
					text = String.valueOf(level);
					foregreound = new Text(x, y, font, text, text.length(), engine.getVertexBufferObjectManager());
				}
				
				 attachChild(foregreound);
			}
		}
		
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
			if (LevelSelector.this.isVisible() && pSceneTouchEvent.isActionUp()) {
				if (!locked) {
					GameSceneParams params = new GameSceneParams(LevelSelector.this.chapter, LevelSelector.this.maxLevel, LevelSelector.this.unlockedLevel, level);
					EventBus.getDefault().post(new Event.StartScene(SceneType.SUB_MENU, SceneType.GAME, params));
				}
			}
			
			return false;
		}
	}
		
}
