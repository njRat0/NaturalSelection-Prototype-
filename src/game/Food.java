import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Food extends GameObject{
    private BufferedImage image;
    public boolean isAlive = true;
    public final static float givenFoodAmount = 15;
    private Handler handler;

    public Food(int x, int y, Handler handler){
        super(x, y);
        image = BufferedImageLoader.loadImage("res\\Food.png");
        this.handler = handler;
    }

    @Override
    public void tick() {
        if(isAlive == false){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, (int)(image.getWidth() * Camera.screenZoom), (int)(image.getHeight() * Camera.screenZoom), null);
    }

}
