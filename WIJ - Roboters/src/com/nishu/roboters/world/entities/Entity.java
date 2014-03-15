package com.nishu.roboters.world.entities;

import com.nishu.roboters.misc.Tickable;
import com.teama.merc.geo.Vec2;

public class Entity implements Tickable {

	private Vec2 pos;
	
	public Entity(float x, float y) {
		this.pos = new Vec2(x, y);
	}

	@Override
	public void tick() {
	}

	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public Vec2 getPos() {
		return pos;
	}
	
	public void addPos(float x, float y) {
		this.pos.x += x;
		this.pos.y += y;
	}
	
	public void setPos(float x, float y) {
		this.pos.set(x, y);
	}
}
