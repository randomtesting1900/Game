import javax.swing.*;
import java.awt.*;

public class Food {

   private int r;
   public int x;  
   public int y;  
   private Color c;
   
  
   public Food(int xValue, int yValue) {
      r = 3;
      x = xValue;
      y = yValue;
      c = Color.WHITE;
   }
  
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
      g.fillOval(x+7, y+7, r*2, r*2);
   }
    //toString() returns the values in this object's fields
   public String toString()
   {
      return "Food at " + x + ", " + y + " with radius " + r + " and color set to " + c;
   }
   
   
}