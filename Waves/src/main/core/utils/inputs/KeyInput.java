package main.core.utils.inputs;

import main.core.enums.ID;
import main.core.gameobjects.GameObject;
import main.core.utils.rendering.Renderer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Renderer renderer;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Renderer renderer){
		this.renderer = renderer;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < renderer.getGameObjects().size(); i++){
			GameObject tmpObj = renderer.getGameObjects().get(i);
			
			if(tmpObj.getId() == ID.Player){
				//key events for Player 1
				
				if(key == KeyEvent.VK_UP) { tmpObj.setVelY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_DOWN) { tmpObj.setVelY(5); keyDown[1] = true; }
				if(key == KeyEvent.VK_RIGHT) { tmpObj.setVelX(5); keyDown[2] = true; }
				if(key == KeyEvent.VK_LEFT) { tmpObj.setVelX(-5); keyDown[3] = true; }
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < renderer.getGameObjects().size(); i++){
			GameObject tmpObj = renderer.getGameObjects().get(i);
			
			if(tmpObj.getId() == ID.Player){
				//key events for Player 1
				
				if(key == KeyEvent.VK_UP) keyDown[0] = false; //tmpObj.setVelY(0);
				if(key == KeyEvent.VK_DOWN) keyDown[1] = false; //tmpObj.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tmpObj.setVelX(0);
				if(key == KeyEvent.VK_LEFT) keyDown[3] = false; //tmpObj.setVelX(0);
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tmpObj.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tmpObj.setVelX(0);
			
			}
			
		}
	}
}
