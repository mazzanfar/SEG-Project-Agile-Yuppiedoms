package GUI.Column_GUI;

import Business_Logic.Column;
import GUI.Transfer.DropHandler;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;

public class DropPane extends JPanel {

    DropTarget dropTarget;
    DropHandler dropHandler;
    Column column;
    public DropPane(Column column) {
        this.column = column;
        setBackground(Color.BLUE);
    }

    public Column getColumn(){
        return column;
    }

    @Override
    public void addNotify() {
        
        super.addNotify(); //To change body of generated methods, choose Tools | Templates.
        dropHandler = new DropHandler();
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify() {
        super.removeNotify(); //To change body of generated methods, choose Tools | Templates.
        dropTarget.removeDropTargetListener(dropHandler);
    }
}