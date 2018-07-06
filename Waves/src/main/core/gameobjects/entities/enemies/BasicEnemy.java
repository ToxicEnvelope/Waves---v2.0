package main.core.gameobjects.entities.enemies;

import main.Game;
import main.core.utils.rendering.Renderer;
import main.core.gameobjects.features.trails.Trail;
import main.core.enums.ID;
import main.core.gameobjects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Renderer renderer;
	
	public BasicEnemy(int x, int y, ID id, Renderer renderer) {
		super(x, y, id);
		
		this.renderer = renderer;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
			
		if(y <= 0 || y >= Game.HEIGHT - 30) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 30) velX *= -1;
		
		renderer.addObj(new Trail((int)x, (int)y, ID.Trail, Color.RED, 16, 16, 0.03f, renderer));
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.BasicEnemy) g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
