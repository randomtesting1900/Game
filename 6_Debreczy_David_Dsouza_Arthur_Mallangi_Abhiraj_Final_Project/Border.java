import javax.swing.*;
import java.awt.*;
/** 
* Border class, used as outer edge limits of the map
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Border extends Square {
    private int side; // side length
    private int x; // x location
    private int y; // y location
/** Constructs a Border with initial position specified by xValue and yValue
* @param xValue   X position
* @param yValue   Y position
*/
    public Border(int xValue, int yValue) {
        super(20, xValue, yValue, new Color(54, 107, 227));
    }
/** Default constructor, initial location at (20,20)
*/   
    public Border() {
      super(20, 20, 20, new Color(54, 107, 227));
    }
/** Returns X position
* @return   X position
*/
    public int getX() {
      return x;
   }
/** Returns Y position
* @return   Y position
*/
   
   public int getY() {
      return y;
   }
/** Sets X position
* @param xx    X position
*/
   public void setX(int xx) {
      x = xx;
   }
/** Sets Y position
* @param yy    Y position
*/
   public void setY(int yy) {
      y = yy;
   }
}