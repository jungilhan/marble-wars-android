package com.bulgogi.marblewars.game;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.debug.Debug;

import android.content.Context;

import com.bulgogi.marblewars.game.base.BaseLoader;

public class SplashLoader implements BaseLoader {
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion titleTextureRegion;
	private ITextureRegion marbleLargeTextureRegion;
	private ITextureRegion marbleSmallTextureRegion;
	private ITextureRegion logoTextureRegion;
	
	@Override
	public void load(Engine engine, Context context) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 
				1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);

		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_background.png");
		titleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_title.png");
		marbleLargeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_marble_l.png");
		marbleSmallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_marble_s.png");
		logoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "splash_logo.png");
		
		try {
			bitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			bitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	@Override
	public void unload() {
		BuildableBitmapTextureAtlas bitmapTextureAtlas = (BuildableBitmapTextureAtlas) backgroundTextureRegion.getTexture();
		bitmapTextureAtlas.unload();
		
		System.gc();		
	}

	public ITextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}
	
	public ITextureRegion getTitleTextureRegion() {
		return titleTextureRegion;
	}
	
	public ITextureRegion getMarbleLargeTextureRegion() {
		return marbleLargeTextureRegion;
	}
	
	public ITextureRegion getMarbleSmallTextureRegion() {
		return marbleSmallTextureRegion;
	}
	
	public ITextureRegion getLogoTextureRegion() { 
		return logoTextureRegion;
	}
}
