import javax.swing.*;
import java.awt.*;


public class x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman extends x6DebreczyDavidDsouzaArthurMallangiAbhirajSquare implements x6DebreczyDavidDsouzaArthurMallangiAbhirajAnimatable {
   public int x;
   public int y;
   public int dX;
   public int dY;
   public boolean cherryUp;
   
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman() {
       super(10, 20, 20, Color.YELLOW);
      
       dX=0;
       dY=0;
   }
    
   public x6DebreczyDavidDsouzaArthurMallangiAbhirajPacman(int xValue, int yValue) {
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
      ImageIcon up = new ImageIcon("up.png");
      ImageIcon down = new ImageIcon("down.png");
      ImageIcon left = new ImageIcon("left.png");
      ImageIcon right = new ImageIcon("right.png");
      
      if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getStatus() == "up") {
         g.drawImage(up.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getStatus() == "down") {
         g.drawImage(down.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getStatus() == "left") {
         g.drawImage(left.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (x6DebreczyDavidDsouzaArthurMallangiAbhirajMap.getStatus() == "right") {
         g.drawImage(right.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      }
   }




   
    
   public void step() {
      setX(getX());
      setY(getY());
   }
}