package personal.project;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class Cell extends JButton {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int type;
    private int position;
    private boolean discovered;
    private boolean flagged;
    private Handler handler;
    
    public Cell(int type, int position, boolean discovered, boolean flagged, Handler handler) {
        this.type = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;
        this.setFont(new Font("Arial", Font.BOLD, 16));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton (e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}    
            public void mouseReleased(MouseEvent e) {}
        });
    }
    
    public int getType() {
        /*
         * TYPES
         * 0 = blank;
         * 1 = mine;
         * 2 = adjacent;
         */
        return type;
    }
    
    public int getPosition() {
        return position;
    }
    
    public boolean isDiscovered() {
        return discovered;
    }
    
    public void setDiscovered(boolean d) {
        this.discovered = d;
    }
    
    public boolean isFlagged() {
        return flagged;
    }
    
    public void setFlagged(boolean f) {
        this.flagged = f;
    }

    public void clickButton() {
        handler.click(this);
    }
    
    public void rightClickButton() {
        handler.rightClick(this);
    }

}
