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

public class MainMenuResource implements BaseResource {
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion titleTextureRegion;
	private ITextureRegion easyUnlockTextureRegion;
	private ITextureRegion normalUnlockTextureRegion;
	private ITextureRegion hardUnlockTextureRegion;
	private ITextureRegion normalLockTextureRegion;
	private ITextureRegion hardLockTextureRegion;
	
	@Override
	public void load(Context context, Engine engine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/main-menu/");
		
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 
				1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR);

		backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "background.png");
		titleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "title.png");
		easyUnlockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "easy_unlock.png");
		normalUnlockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "normal_unlock.png");
		hardUnlockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "hard_unlock.png");
		normalLockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "normal_lock.png");
		hardLockTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmapTextureAtlas, context, "hard_lock.png");
		
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
		easyUnlockTextureRegion = null;
		normalUnlockTextureRegion = null;
		hardUnlockTextureRegion = null;
		normalLockTextureRegion = null;
		hardLockTextureRegion = null;
		bitmapTextureAtlas.unload();
		
		System.gc();		
	}
	
	public ITextureRegion getBackgroundTextureRegion() {
		return backgroundTextureRegion;
	}

	public ITextureRegion getTitleTextureRegion() {
		return titleTextureRegion;
	}

	public ITextureRegion getEasyUnlockTextureRegion() {
		return easyUnlockTextureRegion;
	}

	public ITextureRegion getNormalUnlockTextureRegion() {
		return normalUnlockTextureRegion;
	}

	public ITextureRegion getHardUnlockTextureRegion() {
		return hardUnlockTextureRegion;
	}

	public ITextureRegion getNormalLockTextureRegion() {
		return normalLockTextureRegion;
	}

	public ITextureRegion getHardLockTextureRegion() {
		return hardLockTextureRegion;
	}
}
