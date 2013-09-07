package com.bulgogi.marblewars.resource;

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

import com.bulgogi.marblewars.base.BaseResource;

public class SplashResource implements BaseResource {
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion titleTextureRegion;
	private ITextureRegion marbleLargeTextureRegion;
	private ITextureRegion marbleSmallTextureRegion;
	private ITextureRegion logoTextureRegion;
	
	@Override
	public void load(Context context, Engine engine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
		
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 
				1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);

		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "background.png");
		titleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "title.png");
		marbleLargeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "marble_l.png");
		marbleSmallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "marble_s.png");
		logoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "logo.png");
		
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
		backgroundTextureRegion = null;
		titleTextureRegion = null;
		marbleLargeTextureRegion = null;
		marbleSmallTextureRegion = null;
		logoTextureRegion = null;
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
