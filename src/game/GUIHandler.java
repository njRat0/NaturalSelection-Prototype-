import java.util.*;
import java.awt.*;

public class GUIHandler {

  private LinkedList<GameObject> elements;

  public GUIHandler(){
    elements = new LinkedList<GameObject>();
  }

  public GameObject getObject(int i){
    return elements.get(i);
  }

  public void addObject(GameObject object){
    elements.add(object);
  }

  public void removeObject(GameObject object){
    elements.remove(object);
  }

  public void render(Graphics g){
    for(int i = 0; i < elements.size(); i++){
      elements.get(i).render(g);
    }
  }

  public void tick(){
    for(int i = 0; i<elements.size(); i++){
      elements.get(i).tick();
    }
  }

}
