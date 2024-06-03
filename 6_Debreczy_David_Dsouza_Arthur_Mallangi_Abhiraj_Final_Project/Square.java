import javax.swing.*;
import java.awt.*;
/** 
* Square, extended by numerous classes to create images and boundaries
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Square
{
/** Side length */
   public int side;
/** X position */
   public int x;  //x coordinate of top left corner
/** Y position */
   public int y;  //y coordinate of top left corner
/** Color (RGB or preset */
   public Color c;
   
 /** Default constructor, instantiates at (240,240), colored White */
   public Square()
   {
      side = 20;
      x = 240;
      y = 240;
      c = Color.WHITE;
   }
/** Constructs a square with side length of sideValue, at (xValue, yValue), with a color of cValue)
* @param sideValue   side length
* @param xValue   X position
* @param yValue   Y position
* @param cValue   Color (RGB or preset) */
   public Square(int sideValue, int xValue, int yValue, Color cValue)
   {
      side = sideValue;
      x = xValue;
      y = yValue;
      c = cValue;
   }
/** Returns side length 
* @return side length 
*/
   public int getSide()
   {
      return side;
   }
/** Returns X position
* @return X position
*/
   public int getX()
   {
      return x;
   }
/** Returns Y position
* @return Y position
*/
   public int getY()
   {
      return y;
   }
/** Returns color
* @return Color (RGB or preset)
*/
   public Color getColor()
   {
      return c;
   }
   
/** Sets side to sideValue
* @param sideValue   Requested side length */
   public void setSide(int sideValue)
   {
      side = sideValue;
   }
/** Sets x to xValue
* @param xValue   Requested X position
*/
   public void setX(int xValue)
   {
      x = xValue;
   }
/** Sets y to yValue
* @param yValue   Requested Y position
*/
   public void setY(int yValue)
   {
      y = yValue;
   }
/** Sets Color to cValue
* @param cValue Requested Color (RGB or preset) */
   public void setColor(Color cValue)
   {
      c = cValue;
   }
/** Draws square onto screen
* @param g  Graphics tool
*/
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillRect(x, y, side, side);
   }
/** Returns info on square
* @return X,Y,Side length, Color */
   public String toString()
   {
      return "Square at " + x + ", " + y + " with side length " + side + " and color set to " + c;
   }
   
   
}