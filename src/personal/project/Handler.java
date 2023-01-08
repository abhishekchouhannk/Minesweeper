package personal.project;

import java.awt.Color;
import java.util.ArrayList;

public class Handler {
    
    private ArrayList<Cell> current = new ArrayList<Cell>();
    
    // cells that are to be processed.
    private ArrayList<Cell> queue = new ArrayList<Cell>();
    
    private static int flaggedCells = 0;
    
    public void click(Cell cell) {
        int discoveredCells = 0;
        if (!cell.isFlagged()) {
            cell.setEnabled(false);
            cell.setDiscovered(true);    
            int position = cell.getPosition();
    
            int up = position - Game.GRIDSIZE; // position of upper cell
            int upRight = position - Game.GRIDSIZE + 1; // position of upper right cell
            int upLeft = position - Game.GRIDSIZE - 1; // position of upper left cell
            int down = position + Game.GRIDSIZE; // position of the cell below
            int downRight = position + Game.GRIDSIZE + 1; // position of down right cell
            int downLeft = position + Game.GRIDSIZE - 1; // position of down left cell
            int left = position - 1; // position of left adjacent cell
            int right = position + 1; // position of right adjacent cell
            
            // check if the cell is part of the left-most column
            boolean firstColumn = position % Game.GRIDSIZE == 0;
            // check if the cell is part of the top most row 
            boolean firstRow = position < Game.GRIDSIZE;
            // check if the cell is part of the bottom-most row
            boolean lastRow = position >= (Game.GRIDSIZE * (Game.GRIDSIZE - 1));
            // check if the cell part of the right most column
            boolean lastColumn = position % Game.GRIDSIZE == Game.GRIDSIZE - 1;
            
            switch (cell.getType()) {
                case (0):
                    if (firstRow) {
                        // when the cell is in the top row and the adjacent cell cases
                        if (firstColumn) {
                            queue.add(Grid.cellGrid.get(up));
                            queue.add(Grid.cellGrid.get(downRight));
                            queue.add(Grid.cellGrid.get(right));
                        } else if (lastColumn) {
                            queue.add(Grid.cellGrid.get(down));
                            queue.add(Grid.cellGrid.get(downLeft));
                            queue.add(Grid.cellGrid.get(left));
                        } else {
                            queue.add(Grid.cellGrid.get(down));
                            queue.add(Grid.cellGrid.get(downLeft));
                            queue.add(Grid.cellGrid.get(downRight));
                            queue.add(Grid.cellGrid.get(right));
                            queue.add(Grid.cellGrid.get(left));
                        }
                    } else if (lastRow) {
                        if (firstColumn) {
                            queue.add(Grid.cellGrid.get(up));
                            queue.add(Grid.cellGrid.get(upRight));
                            queue.add(Grid.cellGrid.get(right));
                        } else if (lastColumn) {
                            queue.add(Grid.cellGrid.get(up));
                            queue.add(Grid.cellGrid.get(upLeft));
                            queue.add(Grid.cellGrid.get(left));
                        } else {
                            queue.add(Grid.cellGrid.get(up));
                            queue.add(Grid.cellGrid.get(upLeft));
                            queue.add(Grid.cellGrid.get(upRight));
                            queue.add(Grid.cellGrid.get(right));
                            queue.add(Grid.cellGrid.get(left));
                        }
                    } else if (firstColumn) {
                        queue.add(Grid.cellGrid.get(up));
                        queue.add(Grid.cellGrid.get(down));
                        queue.add(Grid.cellGrid.get(upRight));
                        queue.add(Grid.cellGrid.get(downRight));
                        queue.add(Grid.cellGrid.get(right));
                    } else if (lastColumn) {
                        queue.add(Grid.cellGrid.get(up));
                        queue.add(Grid.cellGrid.get(down));
                        queue.add(Grid.cellGrid.get(upLeft));
                        queue.add(Grid.cellGrid.get(downLeft));
                        queue.add(Grid.cellGrid.get(left));
                    } else { // case for all the normal cells not on the ends of the board
                        queue.add(Grid.cellGrid.get(up));
                        queue.add(Grid.cellGrid.get(down));
                        queue.add(Grid.cellGrid.get(upLeft));
                        queue.add(Grid.cellGrid.get(upRight));
                        queue.add(Grid.cellGrid.get(downLeft));
                        queue.add(Grid.cellGrid.get(downRight));
                        queue.add(Grid.cellGrid.get(left));
                        queue.add(Grid.cellGrid.get(right));
                    }
                    break;
                // case when the cell is a mine (1 is used for mine cells)
                case (1):
                    for (int i = 0; i < Grid.cellGrid.size(); i++) {
                        Grid.cellGrid.get(i).setEnabled(false);
                        Grid.cellGrid.get(i).setText("");
                        if (Grid.cellGrid.get(i).getType() == 1) {
                            Grid.cellGrid.get(i).setText("X");
                        }
                        cell.setText("*");
                        cell.setBorderPainted(false);
                        cell.setOpaque(true);
                        cell.setBackground(Color.PINK);
                    }
                    break;
                // case when the cell is adjacent to a mine (2 is used to denote such cells)
                case (2):
                    int dangerCount = 0;
                    if (firstRow) {
                        if (firstColumn) {
                            if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(downRight).getType() == 1) dangerCount++;                       
                        } else if (lastColumn) {
                            if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(downLeft).getType() == 1) dangerCount++;
                        } else {
                            if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(downLeft).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(downRight).getType() == 1) dangerCount++;
                        }
                    } else if (lastRow) {
                        if (firstColumn) {
                            if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(upRight).getType() == 1) dangerCount++;                       
                        } else if (lastColumn) {
                            if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(upLeft).getType() == 1) dangerCount++;
                        } else {
                            if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;                 
                            if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(upLeft).getType() == 1) dangerCount++;
                            if (Grid.cellGrid.get(upRight).getType() == 1) dangerCount++;
                        }
                    } else if (firstColumn) {
                        if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;                 
                        if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(upRight).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(downRight).getType() == 1) dangerCount++;
                    } else if (lastColumn) {
                        if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;                 
                        if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(downLeft).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(upLeft).getType() == 1) dangerCount++;
                    } else {
                        if (Grid.cellGrid.get(up).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(upRight).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(upLeft).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(left).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(right).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(down).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(downLeft).getType() == 1) dangerCount++;
                        if (Grid.cellGrid.get(downRight).getType() == 1) dangerCount++;
                    }
                    cell.setText(String.valueOf(dangerCount));
                    break;
            }
            for (int x = 0; x < queue.size(); x++) {
                if (!queue.get(x).isDiscovered()) {
                    current.add(queue.get(x));
                    queue.get(x).setDiscovered(true);
                }
            }
            queue.clear();
            while(!current.isEmpty()) {
             // this removes the first element in the arraylist and the list gets
                // reduced by 1 in length.
                Cell temp = current.get(0); 
                current.remove(0);
                temp.clickButton();
            }
            for (int i = 0; i < Grid.cellGrid.size(); i++) {
                if(Grid.cellGrid.get(i).isDiscovered()) {
                    discoveredCells++;
                }
            }
            if (discoveredCells == Grid.cellGrid.size() - Game.MINECOUNT) {
                for (int x = 0; x < Grid.cellGrid.size(); x++) {
                    if (Grid.cellGrid.get(x).getType() == 1) {
                        Grid.cellGrid.get(x).setEnabled(false);
                        Grid.cellGrid.get(x).setText("X");
                    } else {
                        Grid.cellGrid.get(x).setEnabled(false);
                        Grid.cellGrid.get(x).setText(":)");
                    }
                }
            }
        }
    }
    
    public void rightClick(Cell cell) {
        if (!cell.isDiscovered()) {
            if (!cell.isFlagged()) {
                cell.setFlagged(true);
                cell.setText("F");
                flaggedCells++;
                Window.update(flaggedCells);
            } else {
                cell.setFlagged(false);
                cell.setText("");
                flaggedCells--;
                Window.update(flaggedCells);
            }
        }
    }
}
