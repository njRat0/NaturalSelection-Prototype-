import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Food extends GameObject{
    private BufferedImage image;
    public final static float givenFoodAmount = 15;
    private Handler handler;
    private int counterOfMSec = 0;
    private float secOfLife = 0;
    private boolean isRemoving = false;

    public Food(int x, int y, Handler handler, float secOfLife){
        super(x, y);
        this.secOfLife = secOfLife;
        image = BufferedImageLoader.loadImage("res\\Food.png");
        this.handler = handler;
    }

    @Override
    public void tick() {
        counterOfMSec++;
        if(counterOfMSec >= secOfLife*60*1000){
            isAlive = false;
        }
        if(isRemoving == true){
            handler.removeObject(this);
        }

        if(isAlive == false){
            isRemoving = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)(x-Camera.worldPosX*(Camera.screenZoom)), (int)(y-Camera.worldPosY*(Camera.screenZoom)), (int)(image.getWidth() * Camera.screenZoom), (int)(image.getHeight() * Camera.screenZoom), null);
    }

}
