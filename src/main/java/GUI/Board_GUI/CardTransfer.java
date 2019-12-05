package GUI.Board_GUI;

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

public class CardTransfer
{
	public static class PanelDataFlavor extends DataFlavor {
		// This saves me having to make lots of copies of the same thing
		public static final PanelDataFlavor SHARED_INSTANCE = new PanelDataFlavor();

		public PanelDataFlavor() {

			super(JPanel.class, null);

		}

	}

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
			// Basically, we want to unwrap the present...
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
}