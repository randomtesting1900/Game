import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Food extends Circle {
   public int x;
   public int y;
   public boolean col = false;

   public Food(int xValue, int yValue) {
      super(2, xValue+8, yValue+8, new Color(237, 179, 149));
   }
   
   public int collide(Pacman pacman) {
      if (((getX() - 8) == (pacman.getX()) && ((getY() - 8) == (pacman.getY())
         && (col == false)))) {
         col = true;
         playSound("pacman_chomp.wav");
         setColor(Color.BLACK);
         return 1;
      } else {
         return 0;
      }
   }
   public static void playSound(String filename) {
      try {
         File soundFile = new File(filename);
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
         Clip clip = AudioSystem.getClip();
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
         e.printStackTrace();
      }
   }
      
      
}