package GUI.Column_GUI;

import Business_Logic.Card;
import Business_Logic.Column;
import GUI.Card_GUI.*;
import GUI.Transfer.DragGestureHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import javax.swing.JPanel;

public class DragPane extends JPanel {

    private DragGestureRecognizer dgr;
    private DragGestureHandler dragGestureHandler;

    public DragPane() {
        setBackground(Color.RED);
        dragGestureHandler = new DragGestureHandler(this);
        dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler);
    }
}