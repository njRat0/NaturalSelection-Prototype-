import java.awt.Graphics;

public class Camera extends GameObject{

    private float moveSpeed = 5f;
    private float zoomSpeed = 0.1f;
    public static float screenZoom = 1f;
    public static int worldPosX = 0;
    public static int worldPosY = 0;
    public static boolean wasCameraMoved = false;

    public Camera(){
        super(0, 0);
    }

    @Override
    public void tick() {
        //float curSpeed = ((2-screenZoom) * moveSpeed) + 1;

        if(wasCameraMoved == true){
            wasCameraMoved = false;
        }

        if(KeyInput.keyUP){
            worldPosY -= moveSpeed;
            wasCameraMoved = true;
        }

        if(KeyInput.keyDOWN){
            worldPosY += moveSpeed;
            wasCameraMoved = true;
        }

        if(KeyInput.keyLEFT){
            worldPosX -= moveSpeed;
            wasCameraMoved = true;
        }

        if(KeyInput.keyRIGHT){
            worldPosX += moveSpeed;
            wasCameraMoved = true;
        }

        if(MouseInput.scrollUp && screenZoom < 4){
            screenZoom += zoomSpeed;
            //System.out.println(MouseMotionInput.mousePos.getX()); // 560 //983
            if(screenZoom > 4) screenZoom = 4;
            worldPosX += (int)((MouseMotionInput.mousePos.getX() - 491) * (4-zoomSpeed) / 100);
            worldPosY += (int)((MouseMotionInput.mousePos.getY() - 230) * (4-zoomSpeed) / 100);
            wasCameraMoved = true;
        }
        else if(MouseInput.scrollDown && screenZoom > 0.2f){
            screenZoom -= zoomSpeed / ((1 - zoomSpeed) * 4);
            if(screenZoom < 0.2f) screenZoom = 0.2f;
            wasCameraMoved = true;
        }
    }

    @Override
    public void render(Graphics g) {
        
    }

}
