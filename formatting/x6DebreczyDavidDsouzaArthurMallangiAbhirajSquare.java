import javax.swing.*;
import java.awt.*;

public class x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare
{
   //fields
   public int side;
   public int x;  //x coordinate of top left corner
   public int y;  //y coordinate of top left corner
   public Color c;
   
   //constructors
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare()
   {
      side = 20;
      x = 240;
      y = 240;
      c = Color.WHITE;
   }
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare(int sideValue, int xValue, int yValue, Color cValue)
   {
      side = sideValue;
      x = xValue;
      y = yValue;
      c = cValue;
   }
   
   //accessors
   public int getSide()
   {
      return side;
   }
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public Color getColor()
   {
      return c;
   }
   
   //modifiers
   public void setSide(int sideValue)
   {
      side = sideValue;
   }
   public void setX(int xValue)
   {
      x = xValue;
   }
   public void setY(int yValue)
   {
      y = yValue;
   }
   public void setColor(Color cValue)
   {
      c = cValue;
   }
   
   //instance methods
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillRect(x, y, side, side);
   }
    //toString() returns the values in this object's fields
   public String toString()
   {
      return "Square at " + x + ", " + y + " with side length " + side + " and color set to " + c;
   }
   
   
}