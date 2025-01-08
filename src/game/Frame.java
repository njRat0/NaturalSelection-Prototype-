/*** In The Name of Allah ***/
package game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame extends JFrame {
	public static int windowSizeX;
	public static int windowSizeY;
	public static int gameHeight = 720;                  // 720p game resolution
	public static int gameWidth = 1280;  // wide aspect ratio
	public static int gameCenterY;
	public static int gameCenterX;
	public static float coeficient;

	private BufferedImage backgroundImage;

	private JPanel backGround;

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
		addKeyListener(UserInputService.getKeyListener());
		addMouseListener(UserInputService.getMouseListener());
		addMouseMotionListener(UserInputService.getMouseMotionListener());

		try {
			backgroundImage = ImageIO.read(new File("res\\BackGround.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(gameCenterX, gameCenterY, 5000, 5000);

		backGround.paintComponents(g2d);
		g2d.drawImage(backgroundImage, (int)(gameCenterX - 1920 + Player.positionOfViewX), (int)(gameCenterY - 1080 + Player.positionOfViewY), 3840, 2160, rootPane);
	}
}
