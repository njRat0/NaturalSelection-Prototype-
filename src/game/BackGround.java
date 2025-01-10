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
        // if(Camera.wasCameraMoved == true){
        //     x = ;
        //     y = ;
        // }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)((x-Camera.worldPosX)*(Camera.screenZoom)), (int)((y-Camera.worldPosY)*(Camera.screenZoom)), (int)(image.getWidth() * Camera.screenZoom), (int)(image.getHeight() * Camera.screenZoom), null);
    }

}
