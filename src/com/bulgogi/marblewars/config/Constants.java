package com.bulgogi.marblewars.config;

public class Constants {
	private Constants() {}

	public static int CAMERA_WIDTH = 800;
	public static int CAMERA_HEIGHT = 480;
	public static float CENTER_X = CAMERA_WIDTH * 0.5f;
	public static float CENTER_Y = CAMERA_HEIGHT * 0.5f;
	
	public static int LEVEL_FONT_SIZE = 32;

	public enum SceneType {
		SPLASH, MAIN_MENU, SUB_MENU
	}
	
	public enum Chapter {
		EASY, NORMAL, HARD
	}
	
	public static class Config {
		public static final boolean DEBUG = true;
	}
	
	public static class Extra {
	}
}
