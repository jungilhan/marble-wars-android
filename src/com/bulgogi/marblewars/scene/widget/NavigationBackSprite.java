package com.bulgogi.marblewars.scene.widget;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

public class NavigationBackSprite extends Sprite {
	private final Engine engine;
	private final ITextureRegion textureRegion;
	private final Font font;
	private Text text;
	
	public NavigationBackSprite(final float x, final float y, final Engine engine, final ITextureRegion textureRegion, final Font font) {
		super(x, y, textureRegion, engine.getVertexBufferObjectManager());
		
		this.engine = engine;
		this.textureRegion = textureRegion;
		this.font = font;
	}

	public void setText(final String msg) {
		final float x = textureRegion.getWidth() * 0.75f;
		final float y = textureRegion.getHeight() * 0.5f;

		if (text == null) {
			text = new Text(x, y, font, msg, msg.length(), engine.getVertexBufferObjectManager());
			attachChild(text);
		} else {
			text.setText(msg);
		}
	}
}
