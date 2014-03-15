package com.nishu.roboters;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.nishu.roboters.misc.Data;
import com.nishu.roboters.world.World;
import com.teama.merc.exc.MERCuryException;
import com.teama.merc.fmwk.Core;
import com.teama.merc.fmwk.Runner;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.in.Input;
import com.teama.merc.res.ResourceManager;

public class Main extends Core {

	private Runner rnr = Runner.getInstance();
	
	public static Input input;
	
	public static enum State {
		MENU, PLAY, DEATH;
	}
	
	public static State currentState = State.MENU;
	private World world;
	
	private boolean worldCreated = false;
	
	public Main() {
		super("Roboters - Nishu Studios[opiop65]");
		rnr.init(this, 1280, 720, false,  false);
		rnr.run();
	}

	@Override
	public void cleanup(ResourceManager RM) throws IOException, MERCuryException {
	}

	@Override
	public void init(ResourceManager RM) throws IOException, MERCuryException {
		Data.load();
	}

	@Override
	public void render(Graphics g) throws MERCuryException {
		renderState(g);
	}

	@Override
	public void update(float delta) throws MERCuryException {
		input();
	}
	
	private void input() {
		input = rnr.getInput();
		if(currentState == State.MENU) {
			if(input.keyClicked(Keyboard.KEY_RETURN)) {
				currentState = State.PLAY;
				if(!worldCreated) {
					world = new World(rnr.getInput());
					worldCreated = true;
				}
			}
		} else if(currentState == State.PLAY) {
			world.tick();
		}
	}

	private void renderState(Graphics g) {
		switch(currentState) {
		case MENU:
			renderMenu(g);
			break;
		case DEATH:
			break;
		case PLAY:
			renderWorld(g);
			break;
		default:
			break;
		}
	}
	
	private void renderWorld(Graphics g) {
		world.render(g);
	}
	
	private void renderMenu(Graphics g) {
		g.drawTexture(Data.title, 0, 0);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
 