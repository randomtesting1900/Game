import javax.swing.*;
import java.awt.*;
/** 
* SpeedStop, stops Pacman from moving momentarily
* @author David Debreczy
* @author Arthur Dsouza
* @author Abhiraj Mallangi
* @version 2 June 2024
*/
public class SpeedStop extends Square {
/** X position */
   private int x;
/** Y position */
   private int y;
/** Collision status */
   public boolean col;
/** Constructs a SpeedStop at (x,y) with color c (RGB or preset) 
* @param x  X position
* @param y  Y position
* @param c  Color (RGB or preset)
*/
   public SpeedStop(int x, int y, Color c) {
      super(16, x, y, c);
      this.x = x;
      this.y = y;
   }
/** Draws SpeedStop onto the screen based on collision status
* @param g  Graphics tool
*/
   public void drawMe(Graphics g) {
      if (!col) {
         ImageIcon tommy = new ImageIcon("trash.png");
         g.drawImage(tommy.getImage(), getX()+2, getY()+2, 16, 16, null);
      } else {
          g.setColor(Color.BLACK);
          g.fillRect(getX() + 2, getY() + 2, 16, 16);
      }
   }
/** Returns and handles collision status with Pacman, returns either 1 or 0 dependent on if there is a new collision
* @param pacman   Pacman object
* @return 1,0 based on if previous/no collision, or new collision */   
   public int collide(Pacman pacman) {
      if (pacman.getX() >= getX() && pacman.getX() <= getX() + 15 &&
          pacman.getY() >= getY() && pacman.getY() <= getY() + 15 && !col) {
         col = true;
         return 1;
      } else {
         return 0;
      }
   }
}