package main.core.ui.hud;

import main.Game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	
	public static int HEALTH = 250;
	private int greenValue = 255; //RGB
	
	private int score = 0;
	private int level = 1;
	
	public void tick(){
		HEALTH = (int) Game.clamp(HEALTH, 0, 100);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH*2;
		
		score++;
	}
	
	//Health bar :)
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: "+ score, 16, 64);
		g.drawString("Level: "+ level, 16, 80);
	}
	
	public void score(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
