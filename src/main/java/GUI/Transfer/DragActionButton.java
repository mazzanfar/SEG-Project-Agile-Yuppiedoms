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


public class DragActionButton  extends JPanel {
    private DragGestureRecognizer dgr;
    private DragGestureHandler dragGestureHandler;
    private JPanel actionPanel;
  public DragActionButton (JPanel actionPanel, String buttonText)
   {
   this.actionPanel = actionPanel;
   }   
  
  @Override
  public void addNotify() {
  
      super.addNotify();
  
      if (dgr == null) {
  
          dragGestureHandler = new DragGestureHandler(this.actionPanel);
          dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                  this,
                  DnDConstants.ACTION_MOVE,
                  dragGestureHandler);
  
      }
  
  }
  
  @Override
  public void removeNotify() {
  
      if (dgr != null) {
  
          dgr.removeDragGestureListener(dragGestureHandler);
          dragGestureHandler = null;
  
      }
  
      dgr = null;
  
      super.removeNotify();
  
  }
  
  }