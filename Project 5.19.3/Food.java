import javax.swing.*;
import java.awt.*;

public class Food extends Circle {
   public int x;
   public int y;

   public Food(int xValue, int yValue) {
      super(4, xValue+4, yValue+4, Color.WHITE);
   }
}