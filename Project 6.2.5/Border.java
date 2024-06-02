import javax.swing.*;
import java.awt.*;

public class Border extends Square {
    private int side;
    private int x;
    private int y;

    public Border(int xValue, int yValue) {
        super(20, xValue, yValue, new Color(54, 107, 227));
    }
    
    public Border() {
      super(20, 20, 20, new Color(54, 107, 227));
    }
    
    public int getX() {
      return x;
   }
   public int getY() {
      return y;
   }
   public void setX(int xx) {
      x = xx;
   }
   public void setY(int yy) {
      y = yy;
   }
}