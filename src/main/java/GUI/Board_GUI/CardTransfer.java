package GUI.Board_GUI;

import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

import java.awt.event.MouseListener;
 
public class CardTransfer implements MouseListener, MouseMotionListener
 {
    private int X;
    private int Y;

    public CardTransfer(JButton card)
    {
        card.addMouseListener(this);
        card.addMouseMotionListener(this);
    }
    @Override
	public void mouseDragged(MouseEvent e) {
		e.getComponent().setLocation((e.getX() + e.getComponent().getX() - X), (e.getY() + e.getComponent().getY() - Y));
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 //@Override
 
 }