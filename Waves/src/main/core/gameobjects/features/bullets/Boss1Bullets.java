package main.core.gameobjects.features.bullets;

import main.Game;
import main.core.gameobjects.features.trails.Trail;
import main.core.utils.rendering.Renderer;
import main.core.enums.ID;
import main.core.gameobjects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1Bullets extends GameObject {

	private Renderer renderer;
	Random r = new Random();
	
	public Boss1Bullets(int x, int y, ID id, Renderer renderer) {
		super(x, y, id);
		
		this.renderer = renderer;
		
		velX = (r.nextInt(5 -  -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 12, 12);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
			
		
		if(y >= Game.HEIGHT) renderer.removeObj(this);
		
		renderer.addObj(new Trail((int)x, (int)y, ID.Trail, Color.RED, 12, 12, 0.03f, renderer));
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.Boss1Bullets) g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 12, 12);
	}

}
