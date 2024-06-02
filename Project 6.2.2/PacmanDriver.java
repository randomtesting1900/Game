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
        Home homePanel = new Home(frame, args);
        frame.setContentPane(homePanel);
        frame.setVisible(true);
    }
}

// Home class with level buttons and title
class Home extends JPanel {
    private JFrame myOwner;
    private String[] args;
    
    public Home(JFrame f, String[] args) {
        myOwner = f;
        this.args = args;
        
        setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1));
        
        ImageIcon pacmanImage = new ImageIcon("right.png"); // Adjust the path to your image file
        JLabel imageLabel = new JLabel(pacmanImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Pacman Ultra!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 24));
        menu.add(titleLabel);
        
        // Buttons
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
                myOwner.setContentPane(new MapPanel("levelOne.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapPanel("levelTwo.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        levelThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new MapPanel("levelThree.txt", myOwner));
                myOwner.revalidate();
                myOwner.repaint();            }
        });
        
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Define the action for the instructions button here
                JOptionPane.showMessageDialog(myOwner, "Welcome to Pacman Ultra! To begin, select one of three levels, Easy, Medium, or Hard. Once you are in the game, you can move up or down using");
            }
        });
    }
}

// MapPanel class with map display and back button
class MapPanel extends JPanel {
    public MapPanel(String mapFile, JFrame myOwner) {
        setLayout(new BorderLayout());
        
        // Map display
        Map map = new Map(mapFile);
        add(map, BorderLayout.CENTER);
        
        map.setFocusable(true);
        map.requestFocusInWindow();
        
        // Back button
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myOwner.setContentPane(new Home(myOwner, new String[]{}));
                myOwner.revalidate();
                myOwner.repaint();
            }
        });
        
        add(backButton, BorderLayout.SOUTH);
    }
}
