package GUI.Transfer;

import GUI.Column_GUI.DropPane;
import GUI.Column_GUI.Column_GUI;
import GUI.Card_GUI.CardGui;
import Business_Logic.Card;

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
        setParent(parent);
    
        // Create our transferable wrapper
        Transferable transferable = new PanelTransferable(getPanel());
        // Start the "drag" process...
        DragSource ds = dge.getDragSource();
        ds.startDrag(dge, null, transferable, this);
        parent.remove(getPanel()); // This line removes the dragpane from the droppane, we would also like to do this in the backend at the same time
        // Parent must be a DropPane, so we cast to one and access column

        // REMOVAL OF Card from column and dragpane from droppane (syncing up frontend and backend) occurs here
        DropPane PRNT = (DropPane) parent;
        Column_GUI ColGui = (Column_GUI) PRNT.getParent().getParent().getParent(); // now we have access to column and column GUI

        CardGui CdGui = (CardGui) getPanel(); // cast dragPane to card gui, because it's the only possible thing it could be
        Card c = CdGui.getCard();
        ColGui.removeFromCol(c);

        // REMOVAL OF Card from column and dragpane from droppane (syncing up frontend and backend) occurs here

        
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
        // we also need to return the card to col in backend
        if (!dsde.getDropSuccess()) {
            getParent().add(getPanel());
            // Here
            CardGui CdGui = (CardGui) getPanel();
            Card c = CdGui.getCard();

            DropPane PRNT = (DropPane) parent;
            Column_GUI ColGui = (Column_GUI) PRNT.getParent().getParent().getParent();
            ColGui.addToCol(c);

        } else {
            getPanel().remove(getPanel());
        }
        getParent().invalidate();
        getParent().repaint();
    }
}