package com.bulgogi.marblewars.base;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;

import com.bulgogi.marblewars.listener.SceneListener;

public abstract class BaseScene {
	protected Engine engine;
	protected Scene scene;
	protected SceneListener listener;
	
	protected BaseScene(Engine engine) {
		this.engine = engine;
		this.scene = new Scene();
	}

	public void setListener(SceneListener listener) {
		this.listener = listener;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public abstract void populateScene();
}
