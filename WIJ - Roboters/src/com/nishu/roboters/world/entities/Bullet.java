package com.nishu.roboters.world.entities;

import com.nishu.roboters.misc.Data;

public class Bullet extends Mob {

	private float moveSpeed = 1.5f;
	private boolean dir;
	
	public Bullet(boolean dir, float x, float y) {
		super(Data.bullet, x, y);
		this.dir = dir;
	}

	@Override
	public void tick() {
		if(!dir) 
			addPos(moveSpeed, 0);
		else if (dir) 
			addPos(-moveSpeed, 0);
		getBox().setBounds((int) getX(), (int) getY(), Data.bullet.getTextureWidth(), Data.bullet.getTextureHeight()); 
	}
}
