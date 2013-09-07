package com.bulgogi.marblewars.game.base;

import org.andengine.engine.Engine;

import android.content.Context;

public interface BaseLoader {
	public void load(Engine engine, Context context);
	public void unload();
}
