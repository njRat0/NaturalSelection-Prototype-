import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Bacteria extends GameObject{
    protected Color color;
    public float moveSpeed;
    public float size;
    public float mutationForce;
    private float curFood;
    private float maxFood;
    private float costOfFood;
    private int timerForNewGeneration;
    public int generation;
    public int visionRadius;

    private boolean isTarget = false;
    private Point curTarget = null;
    private GameObject gameObjectOfCurTarget;
    private int counterOfSec;
    private int counterOfmSec;
    private static final int collectingRadius = 5;

    private int afkTime = 1;

    private Handler handler;

    private Random r;

    public Bacteria(int x, int y, float ms, float size, int visionRadius, float mutationForce, int generation, Handler handler){
        super(x, y);
        this.moveSpeed = ms;
        this.size = size;
        this.visionRadius = visionRadius;
        this.generation = generation;
        this.mutationForce = mutationForce;
        this.handler = handler;
        
        if(Game.maxGen < generation) Game.maxGen = generation;

        DataStore.AddObject(this);
        int valueColor = (int)((ms*20>150)? 150 : ms*20);
        color = new Color(150-valueColor,150-valueColor,255);
        maxFood = size * 40;
        curFood = maxFood;
        costOfFood = (moveSpeed/8) * (size/4) + (visionRadius/200)+(mutationForce/10);
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

        if(counterOfSec > afkTime){

            if(curFood <= 0){
                DataStore.RemoveObject(this);
                handler.removeObject(this);
            }

            if(counterOfSec >= 6 && curFood == maxFood){
                curFood/=2;
                Bacteria bacteria = new Bacteria(x + r.nextInt(100) - 50, y + r.nextInt(100) - 50, (moveSpeed + r.nextFloat(1f) - 0.5f) * mutationForce, (size + r.nextFloat(0.4f) - 0.2f) * mutationForce, (int)((visionRadius + r.nextInt(50) - 25) * mutationForce), mutationForce, generation+1, handler);
                bacteria.id = ID.Bacteria;
                handler.addObject(bacteria);
            }
    
            if(isTarget == false){
                if(curTarget == null){
                    float lowestDistance = visionRadius + 1;
                    for(GameObject food : handler.getByID(ID.Food)){
                        if(food.isAlive == true){
                            
                            float deltaX = (float)food.getX() - x;
                            float deltaY = (float)food.getY() - y;
                            float dis = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                            if(dis < lowestDistance){
                                lowestDistance = dis;
                                isTarget = true;
                                curTarget = new Point(food.getX(), food.getY());
                                gameObjectOfCurTarget = food;
                            }
                        }
                    }
    
                    if(curTarget == null){
                        int xTarget = r.nextInt(-visionRadius, visionRadius) + x;
                        int yTarget = r.nextInt(-visionRadius, visionRadius) + y;
    
                        if(xTarget < 0)xTarget = 0;
                        else if(xTarget > 3835)xTarget = 3835;
                        
                        if(yTarget < 0)yTarget = 0;
                        else if(yTarget > 2155) yTarget = 2155;
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
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval( (int)((x-Camera.worldPosX)*(Camera.screenZoom)), (int)((y-Camera.worldPosY)*(Camera.screenZoom)), (int)(size * 50 * Camera.screenZoom), (int)(size * 50 * Camera.screenZoom));

        if(curTarget != null){
            g.setColor(Color.red);
            g.fillOval( (int)((curTarget.getX() + 20-Camera.worldPosX)*(Camera.screenZoom)), (int)((curTarget.getY() + 20 -Camera.worldPosY)*(Camera.screenZoom)), (int)(20 * Camera.screenZoom),(int)(20 * Camera.screenZoom));
        }
        
    }
}
