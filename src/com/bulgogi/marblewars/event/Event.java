package com.bulgogi.marblewars.event;

import com.bulgogi.marblewars.config.Constants.SceneType;

public class Event {
	private Event() {
	}
	
	public static class StartScene {
		final public SceneType fromScene;
		final public SceneType toScene;
		
		public StartScene(final SceneType fromScene, final SceneType toScene) {
			this.fromScene = fromScene;
			this.toScene = toScene;
		}
	}
}
