package main.core.gameobjects.entities.player;

import main.Game;
import main.core.ui.hud.HUD;
import main.core.utils.rendering.Renderer;
import main.core.enums.ID;
import main.core.gameobjects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Renderer renderer;
	
	public Player(int x, int y, ID id, Renderer renderer) {
		super(x, y, id);
		this.renderer = renderer;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
	}

	private void collision(){
		for(int i = 0; i < renderer.getGameObjects().size(); i++){
			
			GameObject tmpObj = renderer.getGameObjects().get(i);
			
			//--TO DO-- 
			//ArayList.ID.Obj()
			if(tmpObj.getId() == ID.BasicEnemy || tmpObj.getId() == ID.FastEnemy || tmpObj.getId() == ID.Boss1Bullets || tmpObj.getId() == ID.Boss1){ // tmpObj is now BasicEnemy
				if(getBounds().intersects(tmpObj.getBounds())){
					// collision code
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
					
		if(id == ID.Player) g.setColor(Color.WHITE); 
		g.fillRect((int)x, (int)y, 32, 32);	
	}

}
