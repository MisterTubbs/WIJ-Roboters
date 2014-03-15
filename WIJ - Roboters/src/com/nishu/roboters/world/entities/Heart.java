package com.nishu.roboters.world.entities;

import com.nishu.roboters.misc.Data;

public class Heart extends Mob {

	private float moveSpeed = 3.5f;
	
	public Heart(float x, float y) {
		super(Data.heart, x, y);
	}
	
	public void tick() {
		addPos(0, -moveSpeed);
		getBox().setBounds((int) getX(), (int) getY(), 48, 48); 
	}
}
