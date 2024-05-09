import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map extends JPanel {

   private final int N = 20;
   public int[][] matrix = new int[20][20];
   public ArrayList<Food> foods = new ArrayList<>();
   public ArrayList<Wall> walls = new ArrayList<>();



// If [x][x] = 0, blank, if 1, circle, if 2, powerup, if 5, wall
   public Map() {
      
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < N; c++) {
            if (matrix[r][c] == 1) {
               foods.add(new Food(r * 20, c * 20));
            } else if (matrix[r][c] == 5) {
               walls.add(new Wall(r * 20, c * 20));
            }
         }
      }
   }
   
   public void buildObjects(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 400, 400);
      for (Food a : foods) {
         a.drawMe(g);
      }
      for (Wall b : walls) {
         b.drawMe(g);
      }
   }
   
   
}