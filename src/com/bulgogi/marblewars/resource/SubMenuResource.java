package com.bulgogi.marblewars.resource;

import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import android.content.Context;
import android.graphics.Typeface;

import com.bulgogi.marblewars.base.BaseResource;
import com.bulgogi.marblewars.config.Constants;

public class SubMenuResource implements BaseResource {
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion titleTextureRegion;
	private ITextureRegion navigationBackTextureRegion;
	private ITextureRegion tileTextureRegion;
	private ITextureRegion lockTextureRegion;
	private Font font;
	
	@Override
	public void load(Context context, Engine engine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/sub-menu/");
		
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 
				1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
		
		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "background.png");
		titleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "title.png");
		navigationBackTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "navigation_back.png");
		tileTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "tile.png");
		lockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "lock.png");
		
		loadFont(engine);
		
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
		navigationBackTextureRegion = null;
		tileTextureRegion = null;
		lockTextureRegion = null;
		bitmapTextureAtlas.unload();
		
		System.gc();		
	}
	

	private void loadFont(Engine engine) {
		BitmapTextureAtlas fontTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 128, 128);
		font = new Font(engine.getFontManager(), fontTextureAtlas, Typeface.create(Typeface.SERIF, Typeface.BOLD), Constants.LEVEL_FONT_SIZE, true, Color.BLACK);
		font.load();
	}

	public ITextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}

	public ITextureRegion getTitleTextureRegion() {
		return titleTextureRegion;
	}

	public ITextureRegion getNavigationBackTextureRegion() {
		return navigationBackTextureRegion;
	}

	public ITextureRegion getTileTextureRegion() {
		return tileTextureRegion;
	}

	public Font getFont() {
		return font;
	}

	public ITextureRegion getLockTextureRegion() {
		return lockTextureRegion;
	}
}
