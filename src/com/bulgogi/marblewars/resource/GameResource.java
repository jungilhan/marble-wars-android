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


public class GameResource implements BaseResource {
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion navigationEasyTextureRegion;
	private ITextureRegion navigationNormalTextureRegion;
	private ITextureRegion navigationHardTextureRegion;
	private ITextureRegion retryTextureRegion;
	private ITextureRegion marbleTextureRegion;
	private ITextureRegion goDownTextureRegion;
	private ITextureRegion goUpTextureRegion;
	private Font font;
	
	@Override
	public void load(Context context, Engine engine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 
				1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);
		
		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "background.png");
		navigationEasyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "navigation_back_easy.png");
		navigationNormalTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "navigation_back_normal.png");
		navigationHardTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "navigation_back_hard.png");
		retryTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "retry.png");
		marbleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "marble.png");
		goDownTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "go_down.png");
		goUpTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "go_up.png");
		
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
		navigationEasyTextureRegion = null;
		navigationNormalTextureRegion = null;
		navigationHardTextureRegion = null;
		retryTextureRegion = null;
		marbleTextureRegion = null;
		goDownTextureRegion = null;
		goUpTextureRegion = null;
		bitmapTextureAtlas.unload();
		
		System.gc();			
	}

	private void loadFont(Engine engine) {
		BitmapTextureAtlas fontTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 128, 128);
		font = new Font(engine.getFontManager(), fontTextureAtlas, Typeface.create(Typeface.SERIF, Typeface.BOLD), Constants.NAVATION_BACK_FONT_SIZE, true, Color.BLACK);
		font.load();
	}
	
	public ITextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}

	public ITextureRegion getNavigationEasyTextureRegion() {
		return navigationEasyTextureRegion;
	}

	public ITextureRegion getNavigationNormalTextureRegion() {
		return navigationNormalTextureRegion;
	}

	public ITextureRegion getNavigationHardTextureRegion() {
		return navigationHardTextureRegion;
	}
	
	public ITextureRegion getRetryTextureRegion() {
		return retryTextureRegion;
	}

	public ITextureRegion getMarbleTextureRegion() {
		return marbleTextureRegion;
	}

	public ITextureRegion getGoDownTextureRegion() {
		return goDownTextureRegion;
	}

	public ITextureRegion getGoUpTextureRegion() {
		return goUpTextureRegion;
	}

	public Font getFont() {
		return font;
	}
}
