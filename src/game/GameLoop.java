package game;

import java.awt.Graphics2D;
import java.util.Date;

public class GameLoop {

    final static int MAX_FPS = 30;

    public static int curLayout = 0;

    public GameLoop(){
        Update();
    }


    private static void Update(){
        long startTime = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis();
        int countOfSec = 0;
        int countOfFrames = 0;
        System.out.println("gameloop start");

        while(true){
            startTime = System.currentTimeMillis();
            while (startTime - elapsedTime > 1000 / MAX_FPS) {
                //perform db poll/check
                elapsedTime = startTime;
                countOfFrames++;
                //Main.frame.ToRender(null);
                if(countOfFrames >= MAX_FPS){
                    countOfSec++;
                    countOfFrames = 0;
                }
                System.out.println(countOfSec);
            }
        }
    }
}
