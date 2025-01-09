import java.awt.*;
import java.awt.image.*;
import java.awt.Event;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 42l;

  public static Game gameInstance;
  public Handler handler;
  public GUIHandler gui;

  private Thread thread;
  private boolean isRunning = false;

  public Dimension size;
  public MouseMotionInput mmi;
  public MouseInput mi;
  public KeyInput ki;

  private Camera camera;

  public Game(){
    size = new Dimension(1000, 600);
    new Window("Game", size, this);
    start();

    handler = new Handler();
    gui = new GUIHandler();
    gameInstance = this;

    mmi = new MouseMotionInput(handler);
    mi = new MouseInput(handler);
    ki = new KeyInput(handler);

    this.setFocusable(true);

    this.addMouseListener(mi);
    this.addMouseWheelListener(mi);
    this.addMouseMotionListener(mmi);
    this.addKeyListener(ki);

    createWorld();
  }

  public void start(){
    isRunning = true;
    thread = new Thread(this);
    thread.start();
  }

  public void stop(){
    isRunning = false;
    try{
      thread.join();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        tick();
        render();
        delta--;
        frames++;
      }
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
      long endTime = System.nanoTime();
      long elapsedTime = endTime - now;
      try {
        Thread.sleep((Math.abs((long) 16666666 - elapsedTime)) / 1000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    stop();
  }

  public void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs==null){
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    //////////////////////////////////
    ///////----DRAW IN HERE----///////
    //////////////////////////////////

    g.setColor(new Color(190, 190, 190));
    g.fillRect(0, 0, size.width, size.height);
    


    handler.render(g);
    gui.render(g);

    //////////////////////////////////
    g.dispose();
    bs.show();
  }


  //Runs every frame
  public void tick(){
    handler.tick();
    gui.tick();
    if(MouseInput.scrollDown) MouseInput.scrollDown = false;
    if(MouseInput.scrollUp) MouseInput.scrollUp = false;
  }


  //Runs before first tick method
  public void createWorld(){
    camera = new Camera();
    camera.id = ID.Camera;
    handler.addObject(camera);

    BackGround backGround = new BackGround();
    backGround.id = ID.BackGround;
    handler.addObject(backGround);

    MyButton button = new MyButton(0,0,50,50);
    button.id = ID.Button;
    button.isChangingByZoom = true;
    handler.addObject(button);
  }

  public static void main(String[] args) {
    new Game();
  }
}
