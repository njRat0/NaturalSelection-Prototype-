import java.util.*;
import java.awt.*;

public class Handler {

  private LinkedList<GameObject> gameObjects;

  public Handler(){
    gameObjects = new LinkedList<GameObject>();
  }

  public GameObject getObject(int i){
    return gameObjects.get(i);
  }

  public ArrayList<GameObject> getByID(ID i){
    ArrayList<GameObject> go = new ArrayList<GameObject>();
    for(GameObject g : gameObjects){
      if(g.getID() == i){
        go.add(g);
      }
    }
    return go;
  }

  public void addObject(GameObject object){
    gameObjects.add(object);
  }

  public void removeObject(GameObject object){
    gameObjects.remove(object);
  }

  public void render(Graphics g){
    for(int i = 0; i < gameObjects.size(); i++){
      gameObjects.get(i).render(g);
    }
  }

  public void tick(){
    for(int i = 0; i<gameObjects.size(); i++){
      gameObjects.get(i).tick();
    }
  }

}
