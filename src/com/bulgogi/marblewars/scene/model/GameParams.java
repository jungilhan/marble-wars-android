package com.bulgogi.marblewars.scene.model;

import com.bulgogi.marblewars.config.Constants.Chapter;

public class GameParams {
	public final Chapter chapter;
	public final int maxLevel;
	public final int level;
	
	public GameParams(final Chapter chapter, final int maxLevel, final int level) {
		this.chapter = chapter;
		this.maxLevel = maxLevel;
		this.level = level;
	}
}
