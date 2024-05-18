import javax.swing.*;
import java.awt.*;


public class Pacman extends Square implements Animatable {
   public int x;
   public int y;
   public int dX;
   public int dY;

   public Pacman (int xValue, int yValue) {
       super(20, xValue, yValue, Color.YELLOW);
       this.x = xValue;
       this.y = yValue;
       dX=0;
       dY=0;
   }
    
   public int getX() {
     return x;
   }
   public int getY() {
      return y;
   }
   public int getDX() {
      return dX;
   }
   public int getDY() {
      return dY;
   }
   public void setX(int xv) {
      x = xv;
   }
   public void setY(int yv) {
      y = yv;
   }
   public void setDX(int dxv) {
      dX = dxv;
   }
   public void setDY(int dyv) {
      dY = dyv;
   }
    
   
   public void step() {
      setX(getX() + dX);
      setY(getY() + dY);
   }
}