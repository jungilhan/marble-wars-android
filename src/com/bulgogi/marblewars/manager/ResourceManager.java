package com.bulgogi.marblewars.manager;import org.andengine.engine.Engine;import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;import org.andengine.opengl.texture.region.ITextureRegion;import org.andengine.util.debug.Debug;import android.content.Context;public class ResourceManager {	private ITextureRegion splashBackgroundTextureRegion;	private ITextureRegion splashTitleTextureRegion;	private ITextureRegion splashMarbleLargeTextureRegion;	private ITextureRegion splashMarbleSmallTextureRegion;	private ITextureRegion splashLogoTextureRegion;		private volatile static ResourceManager instance;		private ResourceManager() {	}	public static ResourceManager getInstance() {		if (instance == null) {			synchronized (ResourceManager.class) {				if (instance == null) {					instance = new ResourceManager();				}			}		}		return instance;	}		public synchronized void loadGameTextures(Engine engine, Context context) {		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");				BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 1024, 1024);				splashBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_background.png");		splashTitleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_title.png");		splashMarbleLargeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_marble_l.png");		splashMarbleSmallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_marble_s.png");		splashLogoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_logo.png");				try {			bitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));			bitmapTextureAtlas.load();		} catch (TextureAtlasBuilderException e) {			Debug.e(e);		}	}		public synchronized void unloadGameTextures() {		BuildableBitmapTextureAtlas bitmapTextureAtlas = (BuildableBitmapTextureAtlas) splashBackgroundTextureRegion.getTexture();		bitmapTextureAtlas.unload();				System.gc();	}	public ITextureRegion getSplashBackgroundTextureRegion() {		return splashBackgroundTextureRegion;	}		public ITextureRegion getSplashTitleTextureRegion() {		return splashTitleTextureRegion;	}		public ITextureRegion getSplashMarbleLargeTextureRegion() {		return splashMarbleLargeTextureRegion;	}		public ITextureRegion getSplashMarbleSmallTextureRegion() {		return splashMarbleSmallTextureRegion;	}		public ITextureRegion getSplashLogoTextureRegion() { 		return splashLogoTextureRegion;	}}