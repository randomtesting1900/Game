import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map extends JPanel {


   private final int n = 30;
   public int[][] matrix = new int[n][n];
   public ArrayList<Food> foods = new ArrayList<>();
   public ArrayList<Wall> walls = new ArrayList<>();
   public ArrayList<Border> borders = new ArrayList<>();
   public ArrayList<Powerup> powerups = new ArrayList<>();

   public Map() {
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; ++j) {
            if (i == 0 || j == 0 || i == 29 || j == 29) {matrix[i][j] = 3;}
            else {matrix[i][j] = 0;}
         }
      }

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {foods.add(new Food(i * 20, j * 20));}
            else if (matrix[i][j] == 1) {powerups.add(new Powerup(i * 20, j * 20));}
            else if (matrix[i][j] == 2) {walls.add(new Wall(i * 20, j * 20));}
            else {borders.add(new Border(i * 20, j * 20));}
         }
      }
   }
   
   public void paintComponent(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 600, 600);

      for (Food a : foods) {a.drawMe(g);}
      for (Powerup a : powerups) {a.drawMe(g);}
      for (Wall a : walls) {a.drawMe(g);}
      for (Border a : borders) {a.drawMe(g);}
   }
}