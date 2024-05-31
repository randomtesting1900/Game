import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ghost extends Square implements Animatable {
    private int x;
    private int y;
    public boolean north = false;
    public boolean south = false;
    public boolean east = false;
    public boolean west = false;
    public boolean proceed = false;

    public boolean isEat = false;
    public boolean notMove = false;
    public boolean sound = false;

    private int[][] matrix = new int[30][30];

    public Ghost(int x, int y, Color c) {
        super(20, x, y, c);
        this.x = x;
        this.y = y;
        this.matrix = Map.getLevel();
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

    public void drawMe(Graphics g) {
        ImageIcon health = new ImageIcon("ghost.png");
        ImageIcon vul = new ImageIcon("ghostvul.png");
        ImageIcon dead = new ImageIcon("ghostdead.png");
        while (notMove == true && sound == true) {
            g.drawImage(dead.getImage(), getX(), getY(), 20, 20, null);
            playSound("pacman_eatghost.wav");
            sound = false;
        } if (isEat == true && notMove != true) {
            g.drawImage(vul.getImage(), getX(), getY(), 20, 20, null);
        } else {
            g.drawImage(health.getImage(), getX(), getY(), 20, 20, null);
        }
    }

    public void step() {
        if (proceed && !notMove) {
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
        isEat = Map.getEatable();
        if ((isEat == true || isEat == false) && notMove != true) {
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

    public int ghostCollide(Pacman pacman) {
        isEat = Map.getEatable();
        if (getX() == pacman.getX() && getY() == pacman.getY() && !isEat) {
            return 1;
        } else if (getX() == pacman.getX() && getY() == pacman.getY() && isEat) {
            notMove = true;
            sound = true;
            return 2;
        } else {
            return 0;
        }
    }

}