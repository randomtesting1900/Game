import javax.swing.*;
import java.awt.*;

public class Ghost extends Square implements Animatable {
   private int x;
   private int y;
   public boolean north = false;
   public boolean south = false;
   public boolean east = false;
   public boolean west = false;
   public boolean proceed = false;
   
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
      if (proceed) {
         // Update position based on direction
         if (north) setY(getY() - 20);
         if (south) setY(getY() + 20);
         if (west) setX(getX() - 20);
         if (east) setX(getX() + 20);
         
         proceed = false; // Reset proceed after moving
      }
   }
   
   public void ghostMove() {
      double choose = Math.random();

      if (north && (matrix[getX() / 20][(getY() / 20) - 1] != 5) && (matrix[getX() / 20][(getY() / 20) - 1] != 0)) {
         proceed = true;
      } else if (south && (matrix[getX() / 20][(getY() / 20) + 1] != 5) && (matrix[getX() / 20][(getY() / 20) + 1] != 0)) {
         proceed = true;
      } else if (west && (matrix[(getX() / 20) - 1][getY() / 20] != 5) && (matrix[(getX() / 20) - 1][getY() / 20] != 0)) {
         proceed = true;
      } else if (east && (matrix[(getX() / 20) + 1][getY() / 20] != 5) && (matrix[(getX() / 20) + 1][getY() / 20] != 0)) {
         proceed = true;
      } else if (!north && !south && !east && !west) {
         if (choose < 0.25 && (matrix[getX() / 20][(getY() / 20) - 1] != 5) && (matrix[getX() / 20][(getY() / 20) - 1] != 0)) {
            north = true;
            proceed = true;
         } else if (choose < 0.5 && (matrix[getX() / 20][(getY() / 20) + 1] != 5) && (matrix[getX() / 20][(getY() / 20) + 1] != 0)) {
            south = true;
            proceed = true;
         } else if (choose < 0.75 && (matrix[(getX() / 20) - 1][getY() / 20] != 5) && (matrix[(getX() / 20) - 1][getY() / 20] != 0)) {
            west = true;
            proceed = true;
         } else if (choose >= 0.75 && (matrix[(getX() / 20) + 1][getY() / 20] != 5) && (matrix[(getX() / 20) + 1][getY() / 20] != 0)) {
            east = true;
            proceed = true;
         }
      } else {
         north = south = east = west = false;
         proceed = false;
      }
   }
}