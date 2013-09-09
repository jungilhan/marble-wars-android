package com.bulgogi.marblewars.event;

import com.bulgogi.marblewars.config.Constants.SceneType;
import com.bulgogi.marblewars.scene.model.SceneParams;

public class Event {
	private Event() {
	}
	
	public static class StartScene {
		final public SceneType fromScene;
		final public SceneType toScene;
		final public SceneParams params;
		
		public StartScene(final SceneType fromScene, final SceneType toScene, final SceneParams params) {
			this.fromScene = fromScene;
			this.toScene = toScene;
			this.params = params;
		}
	}
}
