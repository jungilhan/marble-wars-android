package com.bulgogi.marblewars.factory;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.resource.MainMenuResource;
import com.bulgogi.marblewars.resource.SplashResource;

public class ResourceFactory {
	private volatile static ResourceFactory instance;
	
	public enum Type {
		SPLASH, MAIN_MENU
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
		}
		
		throw new IllegalArgumentException();
	}
}
