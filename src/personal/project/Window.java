package personal.project;

import javax.swing.*;
import java.awt.*;

public class Window {
    
    private static JFrame frame;
    private static String title;
    
    /**
     * constructor for the GUI window.
     */
    public Window(int width, int height, int gridSize, String title, Game game, Handler handler) {
        Window.title = title;
        frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setResizable(false);
        // terminates the window whenever cross is clicked.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        
        JPanel panel = new Grid(new GridLayout(gridSize, gridSize), handler);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true); // visible to user.
        
        
        
    }
    
    public static void update(int flagged) {
        frame.setTitle (title + " Mines: " + Game.MINECOUNT + ", Flags: " 
                + flagged);
    }
    

}
