package main.core.utils.spawner;


import main.Game;
import main.core.ui.hud.HUD;
import main.core.enums.ID;
import main.core.gameobjects.entities.enemies.BasicEnemy;
import main.core.gameobjects.entities.enemies.FastEnemy;
import main.core.utils.rendering.Renderer;

import java.util.Random;

public class Spawner {

	private Renderer renderer;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	public Spawner(Renderer renderer, HUD hud){
		this.renderer = renderer;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 500){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			//level mechanism
			if(hud.getLevel() == 2) {
				renderer.addObj(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, renderer));
			}
			else if(hud.getLevel() == 3) {
				renderer.addObj(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, renderer));
			}
		}
	}
	
}
