package com.bulgogi.marblewars.base;

import org.andengine.engine.Engine;

import android.content.Context;

public interface BaseResource {
	public void load(Context context, Engine engine);
	public void unload();
}
