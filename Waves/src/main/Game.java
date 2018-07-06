package main;

import main.core.ui.hud.HUD;
import main.core.ui.canvas.Window;
import main.core.enums.ID;
import main.core.gameobjects.entities.enemies.bosses.Boss1;
import main.core.gameobjects.entities.player.Player;
import main.core.utils.inputs.KeyInput;
import main.core.utils.rendering.Renderer;
import main.core.utils.spawner.Spawner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread  thread;
	private boolean running = false;
	
	
	private Random r;
	private Renderer renderer;
	private HUD hud;
	private Spawner spawner;
	
	public Game() {
		renderer = new Renderer();
		this.addKeyListener(new KeyInput(renderer));
		
		new Window(WIDTH, HEIGHT, "Waves", this);
		
		hud = new HUD();
		spawner = new Spawner(renderer, hud);
		r = new Random();
		
		renderer.addObj(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, renderer ));
		
		renderer.addObj(new Boss1((Game.WIDTH/2)-48, -120, ID.Boss1, renderer));
		
		//renderer.addObj(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, renderer));
	}
	
	// check if the Thread is on
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	// check if the Thread is off
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	// game loop -- ticked update 
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
			}
		}
		stop();
	}
	
	private void tick(){
		renderer.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		renderer.render(g);;
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	//window  boarder
	public static float clamp(float var, float min , float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]){	
		new Game();
	}

}
