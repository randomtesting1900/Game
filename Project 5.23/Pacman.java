import javax.swing.*;
import java.awt.*;


public class Pacman extends Circle implements Animatable {
   public int x;
   public int y;
   public int dX;
   public int dY;
   public boolean cherryUp;

   public Pacman (int xValue, int yValue) {
       super(10, xValue, yValue, Color.YELLOW);
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
   
   public void drawMe(Graphics g)
   {
      g.setColor(c);
      g.fillOval(x, y, r*2, r*2);
   }
    
   public void step() {
      setX(getX());
      setY(getY());
   }
}