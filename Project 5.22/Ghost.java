
import javax.swing.*;
import java.awt.*;

public class Ghost extends Square implements Animatable {
   private int x;
   private int y;
   
   private int[][] matrix = new int[30][30];
   
   public Ghost(int x, int y, Color c) {
      super(20, x, y, c);
      this.x = x;
      this.y = y;
      this.matrix = Map.getLevel();
   }
   
   public void drawMe(Graphics g) {
      ImageIcon tommy = new ImageIcon("ghost.png");
      g.drawImage(tommy.getImage(), getX(), getY(), 20, 20, null);
   }
      
   public void step() {
      setX(getX());
      setY(getY());
   }
}