package com.nishu.roboters.world;

import java.util.ArrayList;

import com.nishu.roboters.misc.Constants;
import com.nishu.roboters.misc.Data;
import com.nishu.roboters.misc.Renderable;
import com.nishu.roboters.misc.Tickable;
import com.nishu.roboters.world.entities.Bullet;
import com.nishu.roboters.world.entities.Enemy;
import com.nishu.roboters.world.entities.Heart;
import com.nishu.roboters.world.entities.Player;
import com.teama.merc.geo.Vec2;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.in.Input;

public class World implements Renderable, Tickable {

	private byte[][] tiles;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private Player player;
	private Heart heart;
	private Input input;

	private boolean heartCaught = false, firing = false, playerDead = false;
	private int timer = 40, worldTimer = 0, fireTimer = 40, delayTimer = 40;

	public World(Input input) {
		this.input = input;
		Tile.loadTiles();
		tiles = new byte[Constants.tileX][Constants.tileY];
		for (int x = 0; x < Constants.tileX; x++) {
			for (int y = 0; y < Constants.tileY; y++) {
				tiles[x][y] = Tile.Grass.getID();
				if (x == 12 || x == 13 || x == 14 || x == 11) {
					tiles[x][y] = Tile.Sand.getID();
				}
			}
		}
		player = new Player(input, 12.5f * Constants.tileSize, Constants.HEIGHT - 48);
		nextLevel();
	}

	private void nextLevel() {
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		for (int i = 0; i < Constants.rand.nextInt(30) + 5; i++) {
			int x = 0;
			if (Constants.rand.nextBoolean())
				x = 10 * Constants.tileSize;
			else
				x = 15 * Constants.tileSize;

			enemies.add(new Enemy(this, x, Constants.rand.nextInt(Constants.HEIGHT - 48) + 48));
		}
		player.setPos(12.5f * Constants.tileSize, Constants.HEIGHT - 48);
		heart = new Heart(12.5f * Constants.tileSize, player.getY() - 84);
		heartCaught = false;
		firing = false;
		fireTimer = 40;
		timer = 40;
		delayTimer = 40;
		for (Enemy e : enemies)
			e.tick();
	}

	@Override
	public void tick() {
		if (delayTimer <= 0) {
			if (!playerDead) {
				if (!heartCaught) {
					player.tick();
					if (player.getHearts() <= 0) {
						playerDead = true;
					}

					if (player.getY() <= -48)
						nextLevel();

					heart.tick();

					for (Bullet b : bullets) {
						b.tick();

						if (b.getBox().intersects(player.getBox())) {
							player.removeHeart();
							nextLevel();
						}
					}

					if (worldTimer % (Constants.rand.nextInt(400) + 1) == 0) {
						firing = true;
						for (Enemy e : enemies) {
							if (Constants.rand.nextBoolean())
								e.tick();
						}
					}
					if (firing) {
						if (fireTimer > 0) {
							fireTimer--;
							if (fireTimer <= 0) {
								firing = false;
								fireTimer = 40;
							}
						}
					}
					worldTimer++;
				}

				if (player.getBox().intersects(heart.getBox()) && !heartCaught) {
					player.addHeart();
					heartCaught = true;
				}

				if (heartCaught) {
					timer--;
					if (timer <= 0) {
						heartCaught = false;
						nextLevel();
					}
				}
			}
		}
		delayTimer--;
	}

	@Override
	public void render(Graphics g) {
		for (int x = 0; x < Constants.tileX; x++) {
			for (int y = 0; y < Constants.tileY; y++) {
				Tile.getTile(tiles[x][y]).render(g, x * Constants.tileSize, y * Constants.tileSize);
			}
		}
		for (Enemy e : enemies) {
			e.render(g);
		}

		for (Bullet b : bullets)
			b.render(g);

		player.render(g);
		heart.render(g);

		if (firing) {
			g.drawTexture(Data.firing, Constants.WIDTH / 2 - 400, Constants.HEIGHT / 2);
		}

		if (heartCaught) {
			g.drawTexture(Data.win, heart.getX(), heart.getY());
		}

		g.drawString(10, 10, "Hearts: " + player.getHearts());

		if (playerDead) {
			g.drawTexture(Data.lost, 0, 0);
		}
	}

	public void addBullet(Bullet b) {
		bullets.add(b);
	}
}
