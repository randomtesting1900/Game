import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class PauseButton extends Square {
   private int x;
   private int y;
   public boolean pause;
   public boolean unpause;
   public PauseButton(int x, int y, Color c) {
      super(16, x, y, c);      
      this.x = x;
      this.y = y;
   }
   public void drawMe(Graphics g) {
      if (!pause && unpause) {
         ImageIcon tommy = new ImageIcon("pause.png");
         g.drawImage(tommy.getImage(), getX()+2, getY()+2, 15, 15, null);
      } else if (pause && !unpause) {
          g.setColor(Color.BLACK);
          g.fillRect(getX() + 2, getY() + 2, 15, 15);
      }
   }
   
   public void mouseClicked(MouseEvent e) {
      int mx = e.getX();
      int my = e.getY();
      if (x == mx && y == my && !pause && unpause) {
         pause = true;
         unpause = false;
      } else if (x == mx && y == my && pause && !unpause) {
         pause = false;
         unpause = true;
      }
   }
}