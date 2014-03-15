package com.nishu.roboters.world.entities;

import org.lwjgl.input.Keyboard;

import com.nishu.roboters.misc.Data;
import com.teama.merc.in.Input;

public class Player extends Mob {

	private Input in;
	private int moveSpeed = 4;
	private int hearts = 9;
	
	public Player(Input input, float x, float y) {
		super(Data.player, x, y);
		this.in = input;
	}
	
	@Override
	public void tick() {
		if(in.keyDown(Keyboard.KEY_W)) {
			addPos(0, -moveSpeed);
		}
		if(in.keyDown(Keyboard.KEY_S)) {
			addPos(0, moveSpeed);
		}
		if(in.keyDown(Keyboard.KEY_A)) {
			addPos(-moveSpeed, 0);
		}
		if(in.keyDown(Keyboard.KEY_D)) {
			addPos(moveSpeed, 0);
		}
		getBox().setBounds((int) getX(), (int) getY(), 40, 40); 
	}
	
	public void reset() {
		hearts = 3;
	}
	
	public int getHearts() {
		return hearts;
	}
	
	public void removeHeart() {
		hearts--;
	}
	
	public void addHeart() {
		hearts++;
	}
}
