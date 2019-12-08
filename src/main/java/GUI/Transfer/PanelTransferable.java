package GUI.Transfer;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class PanelTransferable implements Transferable {

    private DataFlavor[] flavors = new DataFlavor[]{PanelDataFlavor.SHARED_INSTANCE};
    private JPanel panel;

    public PanelTransferable(JPanel panel) {
        this.panel = panel;
    }
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) 
    {
        boolean supported = false;
        for (DataFlavor mine : getTransferDataFlavors()) 
        {

            if (mine.equals(flavor)) 
            {
                supported = true;
                break;
            }
        }
        return supported;
    }

    public JPanel getPanel() 
    {
        return panel;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException 
    {	
        Object data = null;
        if (isDataFlavorSupported(flavor)) 
        {
            data = getPanel();
        } 
        else 
        {
            throw new UnsupportedFlavorException(flavor);
        }	
        return data;
    }

}