import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PacmanDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PAC-MAN");
        frame.setSize(600, 600);
        frame.setLocation(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel
        MainPanel mainPanel = new MainPanel(frame, args);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}

// MainPanel class with level buttons and title
class Home extends JPanel {
    private JFrame myOwner;
    private String[] args;
    
    public Home(JFrame f, String[] args) {
        myOwner = f;
        this.args = args;
        
        setLayout(new BorderLayout());
        
        // Title label
        JLabel titleLabel = new JLabel("Welcome to Pacman Ultra!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        
        // Button panel
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(4, 1));
        
        JButton levelOne = new JButton("Level 1");
        menu.add(levelOne);
        JButton levelTwo = new JButton("Level 2");
        menu.add(levelTwo);
        JButton levelThree = new JButton("Level 3");
        menu.add(levelThree);
        JButton instructions = new JButton("How to Play");
        menu.add(instructions);
        
        add(menu, BorderLayout.CENTER);
        
        // Add action listeners for buttons
        levelOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapPanel("level1.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapPanel("level2.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customLevel = (args.length > 0) ? args[0] : "default.txt";
                myOwner.setContentPane(new MapPanel(customLevel, myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        /*
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customLevel = (args.length > 0) ? args[0] : "default.txt";
                myOwner.setContentPane(new MapPanel(customLevel, myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });*/
    }
}

// MapPanel class with map display and back button
class MapPanel extends JPanel {
    public MapPanel(String mapFile, JFrame myOwner) {
        setLayout(new BorderLayout());
        
        // Map display
        Map map = new Map();
        add(map, BorderLayout.CENTER);
        
        map.setFocusable(true);
        map.requestFocusInWindow();
        
        // Back button
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MainPanel(myOwner, new String[]{}));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        add(backButton, BorderLayout.SOUTH);
    }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /*
 
 
 
 
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
         frame.setContentPane(new Map("testing.txt"));
         frame.setVisible(true);
      }
   }   */