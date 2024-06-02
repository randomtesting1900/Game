import javax.swing.*;
import java.awt.*;

public class Circle
{
   //fields
   public int r;
   public int x;  
   public int y;  
   public Color c;
   
   
   
   //constructors
   public Circle()
   {
      r = 10;
      x = 200;
      y = 20;
      c = Color.BLACK;
      
   }
   public Circle(int radiusValue, int xValue, int yValue, Color cValue)
   {
      r = radiusValue;
      x = xValue;
      y = yValue;
      c = cValue;
   }
   
   //accessors
   public int getRadius()
   {
      return r;
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
   public void setRadius(int radiusValue)
   {
      r = radiusValue;
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
      g.fillOval(x, y, r*2, r*2);
   }
    //toString() returns the values in this object's fields
   public String toString()
   {
      return "Circle at " + x + ", " + y + " with radius " + r + " and color set to " + c;
   }
   
   
}