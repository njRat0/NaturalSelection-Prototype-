import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BackGround extends GameObject{

    private BufferedImage image;

    public BackGround(){
        super(0, 0);
        image = BufferedImageLoader.loadImage("res\\BackGround.jpg");
    }

    @Override
    public void tick() {
        if(Camera.wasCameraMoved == true){
            x = -Camera.worldPosX;
            y = -Camera.worldPosY;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, (int)(image.getWidth() * Camera.screenZoom), (int)(image.getHeight() * Camera.screenZoom), null);
    }

}
