import javax.swing.*;
import java.awt.*;
/** 
* Circle class, extended by various food/powerup objects
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Circle
{
   public int r; // radius
   public int x; // X position
   public int y; // Y Position
   public Color c; // Color
/** Default circle constructor, sets basic parameters
*/
   public Circle()
   {
      r = 10;
      x = 200;
      y = 20;
      c = Color.BLACK;
/** Constructs a circle at (X,Y) with a radius of radiusValue and a color of cValue
* @param radiusValue    radius of circle
* @param xValue   X position
* @param yValue   Y position
* @param cValue   Color
*/      
   }
   public Circle(int radiusValue, int xValue, int yValue, Color cValue)
   {
      r = radiusValue;
      x = xValue;
      y = yValue;
      c = cValue;
   }
/** Returns radius
* @return   radius
*/
   public int getRadius()
   {
      return r;
   }
/** Returns X position
* @return   X position
*/
   public int getX()
   {
      return x;
   }
/** Returns Y position
* @return   Y position
*/
   public int getY()
   {
      return y;
   }
/** Returns color
* @return   Color (RGB or preset)
*/
   public Color getColor()
   {
      return c;
   }
/** Sets radius to radiusValue
* @param radiusValue    requested radius
*/
   public void setRadius(int radiusValue)
   {
      r = radiusValue;
   }
/** Sets x to xValue
* @param xValue    X position
*/
   public void setX(int xValue)
   {
      x = xValue;
   }
/** Sets y to yValue
* @param yValue    Y position
*/
   public void setY(int yValue)
   {
      y = yValue;
   }
/** Sets color to cValue
* @param cValue    Color (RGB or preset)
*/
   public void setColor(Color cValue)
   {
      c = cValue;
   }
/** Draws circle onto the screen
* @param g  Graphics tool
*/
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillOval(x, y, r*2, r*2);
   }
/** Returns details of circle
* @return X,Y,Radius,Color
*/
   public String toString()
   {
      return "Circle at " + x + ", " + y + " with radius " + r + " and color set to " + c;
   }
}