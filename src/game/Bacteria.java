import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Bacteria extends GameObject{
    protected Color color;
    protected float moveSpeed;
    protected float size;
    protected float mutationForce;
    private float curFood;
    private float maxFood;
    private float costOfFood;
    private int timerForNewGeneration;
    protected int generation;
    protected int visionRadius;

    private boolean isTarget = false;
    private Point curTarget = null;
    private GameObject gameObjectOfCurTarget;
    private int counterOfSec;
    private int counterOfmSec;
    private static final int collectingRadius = 5;

    private Handler handler;

    private Random r;

    public Bacteria(int x, int y, Color color, float ms, float size, int visionRadius, int generation, Handler handler){
        super(x, y);
        this.color = color;
        this.moveSpeed = ms;
        this.size = size;
        this.visionRadius = visionRadius;
        this.generation = generation;
        this.handler = handler;
        maxFood = size * 30;
        curFood = maxFood;
        costOfFood = (moveSpeed/4) + (size/4) + (visionRadius/50)+(mutationForce/5);
        r = new Random();
    }
    
    @Override
    public void tick() {
        counterOfmSec++;
        if(counterOfmSec >= 60){
            counterOfmSec = 0;
            counterOfSec++;
            curFood -= costOfFood;
        }

        if(curFood <= 0){
            handler.removeObject(this);
        }

        if(isTarget == false){
            if(curTarget == null){
                for(GameObject food : handler.getByID(ID.Food)){
                    if(food.isAlive == true){
                        
                        float deltaX = (float)food.getX() - x;
                        float deltaY = (float)food.getY() - y;
                        float dis = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                        if(dis < visionRadius){
                            System.out.println("sssss");
                            isTarget = true;
                            curTarget = new Point(food.getX(), food.getY());
                            gameObjectOfCurTarget = food;
                            break;
                        }
                    }
                }

                if(curTarget == null){
                    int xTarget = r.nextInt(-visionRadius, visionRadius) + x;
                    int yTarget = r.nextInt(-visionRadius, visionRadius) + y;

                    if(xTarget < 0)xTarget = 0;
                    if(yTarget < 0)yTarget = 0;
                    isTarget = true;
                    curTarget = new Point(xTarget, yTarget);
                    gameObjectOfCurTarget = null;
                }
            }
        }
        else{
            if(gameObjectOfCurTarget != null){
                float deltaX = (float)curTarget.getX() - x;
                float deltaY = (float)curTarget.getY() - y;
                float dis = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

                if(dis < collectingRadius){
                    curFood += Food.givenFoodAmount;
                    if(curFood > maxFood ) curFood = maxFood;
                    gameObjectOfCurTarget.isAlive = false;
                }
                else{
                    float deltaX1 = (float)curTarget.getX() - x;
                    float deltaY1 = (float)curTarget.getY() - y;
                    double angle = Math.atan2( deltaY1, deltaX1 );
                    float movingForceX = (float)(moveSpeed * Math.cos( angle ));
                    float movingForceY = (float)(moveSpeed * Math.sin( angle ));
                    x += movingForceX < 0 ? (int)(movingForceX - 0.5f) : (int)(movingForceX + 0.5f);
                    y += movingForceY < 0 ? (int)(movingForceY - 0.5f) : (int)(movingForceY + 0.5f);
                }

                if(gameObjectOfCurTarget.isAlive == false){
                    isTarget = false;
                    gameObjectOfCurTarget = null;
                    curTarget = null;
                }
            }
            else{
                
                
                float deltaX = (float)curTarget.getX() - x;
                float deltaY = (float)curTarget.getY() - y;
                float dis = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

                if(dis < collectingRadius){
                    curTarget = null;
                    isTarget = false;
                    gameObjectOfCurTarget = null;
                    System.out.println("works2");
                }
                else{

                    float deltaX1 = (float)curTarget.getX() - x;
                    float deltaY1 = (float)curTarget.getY() - y;
                    double angle = Math.atan2( deltaY1, deltaX1 );
                    float movingForceX = (float)(moveSpeed * Math.cos( angle ));
                    float movingForceY = (float)(moveSpeed * Math.sin( angle ));
                    x += movingForceX < 0 ? (int)(movingForceX - 0.5f) : (int)(movingForceX + 0.5f);
                    y += movingForceY < 0 ? (int)(movingForceY - 0.5f) : (int)(movingForceY + 0.5f);
                }
            }


        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x - Camera.worldPosX, y - Camera.worldPosY, (int)(size * 50 * Camera.screenZoom), (int)(size * 50 * Camera.screenZoom));

        if(curTarget != null){
            g.setColor(Color.red);
            g.fillOval((int)curTarget.getX(),(int)curTarget.getY(), 50,50);
        }
        
    }
}
