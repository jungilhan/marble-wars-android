package com.bulgogi.marblewars.event;

import com.bulgogi.marblewars.config.Constants.SceneType;

public class Event {
	private Event() {
	}
	
	public static class StartScene {
		final public SceneType fromScene;
		final public SceneType toScene;
		final public Object params;
		
		public StartScene(final SceneType fromScene, final SceneType toScene, final Object params) {
			this.fromScene = fromScene;
			this.toScene = toScene;
			this.params = params;
		}
	}
}
