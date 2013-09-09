package com.bulgogi.marblewars.scene.model;

import com.bulgogi.marblewars.config.Constants.Chapter;

public class GameSceneParams extends SceneParams {
	public final int currentLevel;
	
	public GameSceneParams(Chapter chapter, int maxLevel, int unlockedLevel, int currentLevel) {
		super(chapter, maxLevel, unlockedLevel);
		this.currentLevel = currentLevel; 
	}

}
