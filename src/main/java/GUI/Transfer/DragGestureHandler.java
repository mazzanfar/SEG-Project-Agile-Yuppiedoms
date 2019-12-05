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


public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {

    private Container parent;
    private JPanel child;

    public DragGestureHandler(JPanel child) {

        this.child = child;

    }

    public JPanel getPanel() {
        return child;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public Container getParent() {
        return parent;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        // When the drag begins, we need to grab a reference to the
        // parent container so we can return it if the drop
        // is rejected
        Container parent = getPanel().getParent();
        System.out.println("parent = " + parent.hashCode());
        setParent(parent);
    
        // Remove the panel from the parent.  If we don't do this, it
        // can cause serialization issues.  We could overcome this
        // by allowing the drop target to remove the component, but that's
        // an argument for another day
        // This is causing a NullPointerException on MacOS 10.13.3/Java 8
        //      parent.remove(getPanel());
        //      // Update the display
        //      parent.invalidate();
        //      parent.repaint();
    
        // Create our transferable wrapper
        System.out.println("Drag " + getPanel().hashCode());
        Transferable transferable = new PanelTransferable(getPanel());
        // Start the "drag" process...
        DragSource ds = dge.getDragSource();
        ds.startDrag(dge, null, transferable, this);
    
        parent.remove(getPanel());
        // Update the display
        parent.invalidate();
        parent.repaint();
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        // If the drop was not successful, we need to
        // return the component back to it's previous
        // parent
        if (!dsde.getDropSuccess()) {
            getParent().add(getPanel());
        } else {
            getPanel().remove(getPanel());
        }
        getParent().invalidate();
        getParent().repaint();
    }
}