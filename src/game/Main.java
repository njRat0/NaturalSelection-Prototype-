package game;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {
	public static Frame frame;
	public static GameLoop gameLoop;
	public static UserInputService userInputService;

    public static void main(String[] args) {
		userInputService = new UserInputService();
		frame = new Frame("NaturalSelection");	
		gameLoop = new GameLoop();
    }
}
