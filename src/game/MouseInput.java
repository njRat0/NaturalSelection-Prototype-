import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Point;

public class MouseInput extends MouseAdapter{

  private Handler handler;
  public static boolean isMousePress = false;
  public static Point mousePos;
  public static boolean scrollUp;
  public static boolean scrollDown;


  public MouseInput(Handler handler){
    this.handler = handler;
  }

  public void mousePressed(MouseEvent e){
    int mx = (int)(e.getX());
    int my = (int)(e.getY());
    isMousePress = true;
  }

  public void mouseReleased(MouseEvent e) {
    isMousePress = false;
  }

  public void mouseWheelMoved(MouseWheelEvent e) {
    if (e.getWheelRotation() < 0)
    {
      scrollDown = false;
      scrollUp = true;
    }
    else
    {
      scrollUp = false;
      scrollDown = true;
    }
  }

  public void mouseClicked(MouseEvent e){
    mousePos = e.getPoint();
  }
}
