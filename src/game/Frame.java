/*** In The Name of Allah ***/
package game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame {
	public static int windowSizeX;
	public static int windowSizeY;
	public static int gameHeight = 720;                  // 720p game resolution
	public static int gameWidth = 1280;  // wide aspect ratio
	public static int gameCenterY;
	public static int gameCenterX;
	public static float coeficient;

	public Frame(String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		gameCenterY = gameHeight/2;
		gameCenterX = gameWidth/2;
		//setFocusTraversalKeysEnabled(false);
		setSize(gameWidth, gameHeight);
		setLocationRelativeTo(null); // put frame at center of screen
		setVisible(true);
		setTitle(title);

	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0,0,0));
		g2d.fillRect(20, 20, gameWidth, gameHeight);
	}
}
