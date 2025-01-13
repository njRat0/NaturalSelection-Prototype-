import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    public static boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;

    private Handler handler;
    private Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                keyUP = true;
                break;
            case KeyEvent.VK_S:
                keyDOWN = true;
                break;
            case KeyEvent.VK_A:
                keyLEFT = true;
                break;
            case KeyEvent.VK_D:
                keyRIGHT = true;	
                break;
            case KeyEvent.VK_P:
                Game.curLayout = (Game.curLayout == -1)?0:-1;
                break;
            case KeyEvent.VK_R:
                game.restart();
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                keyUP = false;
                break;
            case KeyEvent.VK_S:
                keyDOWN = false;
                break;
            case KeyEvent.VK_A:
                keyLEFT = false;
                break;
            case KeyEvent.VK_D:
                keyRIGHT = false;
                break;
        }
    }
}
