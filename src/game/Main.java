package game;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {
	public static Frame frame;
	public static GameLoop gameLoop;
	public static UserInputService userInputService;

    public static void main(String[] args) {
		frame = new Frame("NaturalSelection");	
		userInputService = new UserInputService();
		gameLoop = new GameLoop();
    }
}
