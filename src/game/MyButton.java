import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public  class MyButton extends GameObject{
    public BufferedImage image;
    public boolean isBackGroundVisible = true;
    public boolean isChangingByZoom = false;
    public boolean isActive = false;
    //<<<

    private boolean isMouseOver =  false;
    private boolean isPressed = false;

    private Color curColor = new Color(0,0,0);
    public Color colorOver = new Color(179, 250, 160);
    public Color colorClick = new Color(152, 184, 144);
    public Color colorBackground = new Color(30, 136, 56);
    public Color colorBorders = new Color(30, 136, 56);
    private int borderSize = 2;
    // public int locX = 0;
    // public int locY = 0;

    private Rectangle rectangleOfButton;
    
    public MyButton(int x , int y, int w, int h){
        super(x,y,w,h, false);
        rectangleOfButton = new Rectangle(x,y);
        rectangleOfButton.width = w;
        rectangleOfButton.height = h;
        //this.setLayout(new FlowLayout());
        curColor = colorBackground;
        //GameFrame.add(this);
    }

    public void tick() {
        if(isChangingByZoom && Camera.wasCameraMoved){
            rectangleOfButton.width = (int)(w * Camera.screenZoom);
            rectangleOfButton.height = (int)(h * Camera.screenZoom);
            x = (int)(-Camera.worldPosX * Camera.screenZoom);
            y = (int)(-Camera.worldPosY * Camera.screenZoom);
            rectangleOfButton.y = y;
            rectangleOfButton.x = x;
        }

        if(rectangleOfButton.contains(MouseMotionInput.mousePos.getX(), MouseMotionInput.mousePos.getY())) {
            if(isMouseOver == false){
                isMouseOver = true;
                curColor = colorOver;
            }
        }
        else{
            if(isMouseOver == true){
                isMouseOver = false;
                curColor = colorBackground;
            }
        }

        if(MouseInput.isMousePress == true && isMouseOver == true ){
            curColor = colorClick; 
            isPressed = true;
        }
        else{
            if(isMouseOver == true){
                curColor = colorOver;
                if(isPressed){
                    isPressed = false;
                    isActive = true;
                }
            }
            else{
                curColor = colorBackground;
            }
        }
    }

    public void render(Graphics g) {
        if(isChangingByZoom == false){
            if(isBackGroundVisible){
                g.setColor(colorBorders);
                g.fillRect(rectangleOfButton.x, rectangleOfButton.y, rectangleOfButton.width, rectangleOfButton.height);
                g.setColor(curColor);
                g.fillRect(rectangleOfButton.x + borderSize, rectangleOfButton.y + borderSize, rectangleOfButton.width - borderSize * 2 , rectangleOfButton.height - borderSize *2);
            }
            if(image != null){
                g.drawImage(image, rectangleOfButton.x, rectangleOfButton.y, rectangleOfButton.width, rectangleOfButton.height, null);
            }
        }
        else{
            if(isBackGroundVisible){
                g.setColor(colorBorders);
                g.fillRect(rectangleOfButton.x, rectangleOfButton.y, rectangleOfButton.width, rectangleOfButton.height);
                g.setColor(curColor);
                g.fillRect(rectangleOfButton.x + borderSize, rectangleOfButton.y + borderSize, rectangleOfButton.width - borderSize * 2 , rectangleOfButton.height - borderSize *2);
            }
            if(image != null){
                g.drawImage(image, rectangleOfButton.x, rectangleOfButton.y, (int)(rectangleOfButton.width * Camera.screenZoom), (int)(rectangleOfButton.height * Camera.screenZoom), null);
            }
        }
        
    }

}
