import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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

    private Handler handler;

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
    }
    
    @Override
    public void tick() {
        counterOfmSec++;
        if(counterOfmSec >= 60){
            counterOfmSec = 0;
            counterOfSec++;
            curFood -= costOfFood;
        }

        if(isTarget == false){
            if(curTarget == null){
                for(GameObject food : handler.getByID(ID.Food)){
                    if(food.isAlive){
                        if(food.getX() - x < visionRadius && food.getX() - x > -visionRadius){
                            if(food.getY() - y < visionRadius && food.getY() - y > -visionRadius){
                                isTarget = true;
                                curTarget = new Point(food.getX(), food.getY());
                                gameObjectOfCurTarget = food;
                                break;
                            }
                        }
                    }
                }

                if(curTarget == null){

                }
            }
        }
        else{
            if(gameObjectOfCurTarget != null){
                

                if(gameObjectOfCurTarget.isAlive == false){
                    isTarget = false;
                    gameObjectOfCurTarget = null;
                    curTarget = null;
                }
            }
            else{
                //<- stop here
            }


        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, (int)(size * 50 * Camera.screenZoom), (int)(size * 50 * Camera.screenZoom));
    }

}
