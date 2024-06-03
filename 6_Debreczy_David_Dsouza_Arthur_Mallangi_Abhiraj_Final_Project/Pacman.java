import javax.swing.*;
import java.awt.*;
/** 
* Pacman, controlled by the player using arrow keys
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class Pacman extends Square implements Animatable {
/** X position
*/
   public int x;
/** Y position
*/
   public int y;
/** Change in X
*/
   public int dX;
/** Change in Y
*/
   public int dY;
/** Status of cherry power up
*/
   public boolean cherryUp;
/** Default constructor, instantiates at (20,20)
*/   
   public Pacman () {
       super(10, 20, 20, Color.YELLOW);
       dX=0;
       dY=0;
   }
/** Constructs a Pacman at (xValue, yValue)
* @param xValue   X position
* @param yValue   Y position
*/
   public Pacman (int xValue, int yValue) {
       super(10, xValue, yValue, Color.YELLOW);
       this.x = xValue;
       this.y = yValue;
       dX=0;
       dY=0;
   }
/** Returns X position
* @return X position
*/
   public int getX() {
     return x;
   }
/** Returns Y position
* @return Y position
*/
   public int getY() {
      return y;
   }
/** Returns dX
* @return dX value
*/
   public int getDX() {
      return dX;
   }
/** Returns dY
* @return dY value
*/
   public int getDY() {
      return dY;
   }
/** Set x to xv
* @param xv    X position 
*/
   public void setX(int xv) {
      x = xv;
   }
/** Set y to yv
* @param yv    Y position
*/
   public void setY(int yv) {
      y = yv;
   }
/** Set dX to dxv
* @param dxv   dX value
*/
   public void setDX(int dxv) {
      dX = dxv;
   }
/** Set dY to dyv
* @param dyv   dY value
*/
   public void setDY(int dyv) {
      dY = dyv;
   }
/** Draws Pacman onto the screen based on directional status
* @param g  Graphics tool
*/   
   public void drawMe(Graphics g)
   {
      ImageIcon up = new ImageIcon("up.png");
      ImageIcon down = new ImageIcon("down.png");
      ImageIcon left = new ImageIcon("left.png");
      ImageIcon right = new ImageIcon("right.png");
      
      if (Map.getStatus() == "up") {
         g.drawImage(up.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (Map.getStatus() == "down") {
         g.drawImage(down.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (Map.getStatus() == "left") {
         g.drawImage(left.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      } else if (Map.getStatus() == "right") {
         g.drawImage(right.getImage(), getX() + 2, getY() + 2, 20, 20, null);
      }
   }
/** Animates the Pacman, using Animatable */
   public void step() {
      setX(getX());
      setY(getY());
   }
}