package com.nishu.roboters.misc;

import java.io.IOException;

import com.teama.merc.gfx.Texture;
import com.teama.merc.res.Loader;

public class Data {

	public static Texture title, enemy, player, heart, win, bullet, firing, lost;
	
	public static void load() throws IOException {
		title = Texture.loadTexture(Loader.streamFromClasspath("res/title.png"));
		enemy = Texture.loadTexture(Loader.streamFromClasspath("res/enemy.png"));
		player = Texture.loadTexture(Loader.streamFromClasspath("res/player.png"));
		heart = Texture.loadTexture(Loader.streamFromClasspath("res/heart.png"));
		win = Texture.loadTexture(Loader.streamFromClasspath("res/win.png"));
		bullet = Texture.loadTexture(Loader.streamFromClasspath("res/bullet.png"));
		firing = Texture.loadTexture(Loader.streamFromClasspath("res/fire.png"));
		lost = Texture.loadTexture(Loader.streamFromClasspath("res/death.png"));
	}
	
}
