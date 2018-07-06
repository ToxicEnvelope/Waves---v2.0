package main.core.gameobjects.entities.enemies.bosses;

import main.core.gameobjects.features.bullets.Boss1Bullets;
import main.Game;
import main.core.utils.rendering.Renderer;
import main.core.gameobjects.features.trails.Trail;
import main.core.enums.ID;
import main.core.gameobjects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss1 extends GameObject {

	private Renderer renderer;
	Random r = new Random();
	
	private int timer = 90;
	private int timer2 = 45;
	
	
	public Boss1(int x, int y, ID id, Renderer renderer) {
		super(x, y, id);
		
		this.renderer = renderer;
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 98, 98);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY= 0;
		else timer--;
			
		if(timer <= 0) timer2--;
		if(timer2 <= 0){
			
			if(velX == 0) velX = 2;
			if(velX > 0) 
				velX += 0.01f;
			if(velX < 0) 
				velX -= 0.01f;
			int spawner = r.nextInt(10);
			if(spawner == 0) renderer.addObj(new Boss1Bullets((int)x+48, (int)y+48, ID.Boss1Bullets, renderer));
		}
			
			
		//if(y <= 0 || y >= Game.HEIGHT - 30) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
		
		renderer.addObj(new Trail((int)x, (int)y, ID.Trail, Color.RED, 98, 98, 0.08f, renderer));
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.Boss1) g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
