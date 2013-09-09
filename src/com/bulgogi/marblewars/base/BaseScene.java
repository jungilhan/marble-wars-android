package com.bulgogi.marblewars.base;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

import com.bulgogi.marblewars.scene.model.SceneParams;

public abstract class BaseScene {
	protected Engine engine;
	protected Scene scene;
	protected SceneParams params;
	
	protected BaseScene(Engine engine) {
		this.engine = engine;
		this.scene = new Scene();
	}

	public Scene getScene() {
		return scene;
	}
	
	public abstract void populateScene();
	public abstract void onSceneChanged(final SceneParams params);
}
