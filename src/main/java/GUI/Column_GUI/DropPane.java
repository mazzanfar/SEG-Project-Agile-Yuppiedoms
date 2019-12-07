package GUI.Column_GUI;


import GUI.Transfer.DropHandler;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;

public class DropPane extends JPanel {

    DropTarget dropTarget;
    DropHandler dropHandler;

    public DropPane() {
        setBackground(Color.BLUE);
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