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


public class DropHandler implements DropTargetListener, Serializable {

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

        // Determine if we can actually process the contents coming in.
        // You could try and inspect the transferable as well, but 
        // there is an issue on the MacOS under some circumstances
        // where it does not actually bundle the data until you accept the
        // drop.
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {

            dtde.acceptDrag(DnDConstants.ACTION_MOVE);

        } else {
            dtde.rejectDrag();
        }

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        boolean success = false;
        //        // Basically, we want to unwrap the present...
        if (dtde.isDataFlavorSupported(PanelDataFlavor.SHARED_INSTANCE)) {
            Transferable transferable = dtde.getTransferable();
            try {
                Object data = transferable.getTransferData(PanelDataFlavor.SHARED_INSTANCE);
                if (data instanceof JPanel) {
                    JPanel panel = (JPanel) data;
                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component component = dtc.getComponent();
                    if (component instanceof JComponent) {
                        Container parent = panel.getParent();
                        if (parent != null) {
                            parent.remove(panel);
                            parent.revalidate();
                            parent.repaint();
                        }
                        ((JComponent) component).add(panel);
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        ((JComponent) component).invalidate();
                        ((JComponent) component).repaint();
                    } else {
                        success = false;
                        dtde.rejectDrop();
                    }
                } else {
                    success = false;
                    dtde.rejectDrop();
                }
            } catch (Exception exp) {
                success = false;
                dtde.rejectDrop();
                exp.printStackTrace();
            }
        } else {
            success = false;
            dtde.rejectDrop();
        }
        dtde.dropComplete(success);
    }

}