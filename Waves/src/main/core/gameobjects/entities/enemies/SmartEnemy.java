package main.core.gameobjects.entities.enemies;

import main.Game;
import main.core.utils.rendering.Renderer;
import main.core.gameobjects.features.trails.Trail;
import main.core.enums.ID;
import main.core.gameobjects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Renderer renderer;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Renderer renderer) {
		super(x, y, id);
		this.renderer = renderer;
		
		for(int i = 0; i < renderer.getGameObjects().size(); i++){
			if(renderer.getGameObjects().get(i).getId() == ID.Player) player = renderer.getGameObjects().get(i);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = (float) ((-1.0/distance) * diffX);
		velY = (float) ((-1.0/distance) * diffY);
			
		if(y <= 0 || y >= Game.HEIGHT - 30) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 30) velX *= -1;
		
		renderer.addObj(new Trail((int)x, (int)y, ID.Trail, Color.ORANGE, 16, 16, 0.03f, renderer));
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.SmartEnemy) g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
