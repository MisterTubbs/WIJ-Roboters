package com.nishu.roboters.world.entities;

import com.nishu.roboters.misc.Constants;
import com.nishu.roboters.misc.Data;
import com.nishu.roboters.world.World;

public class Enemy extends Mob {

	private World world;
	private boolean side;
	
	public Enemy(World world, float x, float y) {
		super(Data.enemy, x, y);
		this.world = world;
		if(x / Constants.tileSize == 10)
			side = false;
		else 
			side = true;
	}

	@Override
	public void tick() {
		world.addBullet(new Bullet(side, getX(), getY()));
	}
}
