import javax.swing.*;
import java.awt.*;

public class Wall {
   //fields
   private int side;
   private int x;  //x coordinate of top left corner
   private int y;  //y coordinate of top left corner
   private Color c;
   
   
   public Wall(int xValue, int yValue)
   {
      side = 20;
      x = xValue;
      y = yValue;
      c = Color.BLUE;
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
      g.drawRect(x, y, side, side);
   }
    //toString() returns the values in this object's fields
   public String toString()
   {
      return "Wall at " + x + ", " + y + " with side length " + side + " and color set to " + c;
   }
   
   
}