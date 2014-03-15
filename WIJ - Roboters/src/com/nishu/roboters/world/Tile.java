package com.nishu.roboters.world;

import java.util.HashMap;

import com.teama.merc.gfx.Graphics;

public abstract class Tile {

	public static Tile Grass = new TileGrass();
	public static Tile Sand = new TileSand();
	
	private static HashMap<Byte, Tile> tiles = new HashMap<Byte, Tile>();
	
	public abstract void render(Graphics g, float x, float y);
	public abstract byte getID();
	
	public static Tile getTile(byte id) {
		return tiles.get(id);
	}
	
	public static void loadTiles() {
		tiles.put((byte) 0, Grass);
		tiles.put((byte) 1, Sand);
	}
}
