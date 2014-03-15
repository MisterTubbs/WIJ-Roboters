package com.nishu.roboters.misc;

import java.util.Random;

public class Constants {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static int tileSize = 48;
	
	public static int tileX = WIDTH / tileSize + 1;
	public static int tileY = HEIGHT / tileSize;
	
	public static Random rand = new Random();
}