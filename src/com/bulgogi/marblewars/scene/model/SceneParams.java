package com.bulgogi.marblewars.scene.model;

import com.bulgogi.marblewars.config.Constants.Chapter;

public class SceneParams {
	public final Chapter chapter;
	public final int maxLevel;
	public final int unlockedLevel;
	
	public SceneParams(final Chapter chapter) {
		this.chapter = chapter;
		this.maxLevel = -1;
		this.unlockedLevel = -1;
	}
	
	public SceneParams(final Chapter chapter, final int maxLevel, final int unlockedLevel) {
		this.chapter = chapter;
		this.maxLevel = maxLevel;
		this.unlockedLevel = unlockedLevel;
	}
}
