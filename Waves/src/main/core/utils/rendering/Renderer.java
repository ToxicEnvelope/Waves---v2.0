package main.core.utils.rendering;

import main.core.gameobjects.GameObject;

import java.awt.Graphics;
import java.util.LinkedList;

public class Renderer {
	
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i < gameObjects.size(); i++){
			GameObject tmpObj = gameObjects.get(i);
			
			tmpObj.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < gameObjects.size(); i++){
			GameObject tmpObj = gameObjects.get(i);
			
			tmpObj.render(g);
		}
	}

	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void addObj(GameObject obj){
		gameObjects.add(obj);
	}
	
	public void removeObj(GameObject obj){
		gameObjects.remove(obj);
	}
}
