import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
   public class PacmanDriver
   {
       public static void main(String[] args)
      {
         JFrame frame = new JFrame("PAC-MAN");
         frame.setSize(600, 600);
         frame.setLocation(500, 300);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Map());
         frame.setVisible(true);
      }
   }   