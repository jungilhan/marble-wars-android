package com.bulgogi.marblewars.factory;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.resource.GameResource;
import com.bulgogi.marblewars.resource.MainMenuResource;
import com.bulgogi.marblewars.resource.SplashResource;
import com.bulgogi.marblewars.resource.SubMenuResource;

public class ResourceFactory {
	private volatile static ResourceFactory instance;
	
	public enum Type {
		SPLASH, MAIN_MENU, SUB_MENU, GAME
	}
	
	private ResourceFactory() {
	}

	public static ResourceFactory getInstance() {
		if (instance == null) {
			synchronized (ResourceFactory.class) {
				if (instance == null) {
					instance = new ResourceFactory();
				}
			}
		}
		return instance;
	}

	public BaseResource create(Type type) {
		switch (type) {
		case SPLASH:
			return new SplashResource();
		case MAIN_MENU:
			return new MainMenuResource();
		case SUB_MENU:
			return new SubMenuResource();
		case GAME:
			return new GameResource();
		}
		
		throw new IllegalArgumentException();
	}
}
