import javax.swing.*;
import java.awt.*;
/** 
* Wall, extends Square
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Wall {
/** Side length */
   private int side;
/** X position */
   private int x;
/** Y position */
   private int y;
/** Constructs a Wall with a side length of 20 pixels at (xValue, yValue)
* @param xValue   X position
* @param yValue   Y position
*/
   public Wall(int xValue, int yValue)  {
      side = 20;
      x = xValue;
      y = yValue;
   }
/** Draws the Wall onto the screen (blue)
* @param g  Graphics tool
*/
   public void drawMe(Graphics g) {
      g.setColor(Color.BLUE);
      g.drawRect(x, y, side, side);
   }
}