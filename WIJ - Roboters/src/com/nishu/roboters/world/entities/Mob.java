package com.nishu.roboters.world.entities;

import java.awt.Rectangle;

import com.nishu.roboters.misc.Renderable;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.gfx.Texture;

public class Mob extends Entity implements Renderable {

	private Texture texture;
	private Rectangle box;
	
	public Mob(Texture texture, float x, float y) {
		super(x, y);
		this.texture = texture;
		this.box = new Rectangle();
	}

	@Override
	public void render(Graphics g) {
		g.drawTexture(texture, getX(), getY());
	}
	
	public Rectangle getBox() {
		return box;
	}
}
