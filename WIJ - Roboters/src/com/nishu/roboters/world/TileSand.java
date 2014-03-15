package com.nishu.roboters.world;

import java.io.IOException;

import com.teama.merc.gfx.Graphics;
import com.teama.merc.gfx.Texture;
import com.teama.merc.res.Loader;

public class TileSand extends Tile {

	private Texture texture;
	
	public TileSand() {
		try {
			texture = Texture.loadTexture(Loader.streamFromClasspath("res/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(Graphics g, float x, float y) {
		g.drawTexture(texture, x, y);
	}

	@Override
	public byte getID() {
		return 1;
	}
}
