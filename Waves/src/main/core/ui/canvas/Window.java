package main.core.ui.canvas;

import main.Game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -240840600533728354L;

	public Window(int width, int height, String title, Game game){
		
		// the frame of the window
		JFrame frame = new JFrame(title);
		
		// send dimension  
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// make X be functional
		frame.setResizable(false);									// can't resize the frame  
		frame.setLocationRelativeTo(null);							// center the frame
		frame.add(game);											// add game class into frame
		frame.setVisible(true);										// setting the frame to visible
		game.start();												// run start method
	}

}
