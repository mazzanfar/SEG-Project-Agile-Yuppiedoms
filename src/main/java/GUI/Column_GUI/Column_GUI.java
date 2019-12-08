package GUI.Column_GUI;

import Business_Logic.Card;
import Business_Logic.Column;
import GUI.Card_GUI.*;
import GUI.Transfer.*;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Column_GUI extends JPanel{


    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;
    private DropPane cardsPanel;
    private Column column;

    public Column_GUI(Column column) {
        this.column = column;
        prepareFrame(); // makes a frame
        makeColumn(); // does all column building activities

    }

    public void removeFromCol(Card c){
        column.removeCard(c);
    }

    public void addToCol(Card c){
        column.addCard(c);
    }

    public Column_GUI(Board brd) {

        
        prepareFrame(); // makes a frame
        for(int i : brd.column_IDs){
            makeColumn(i); // does all column building activities
        }
        mainFrame.pack(); // make sure this is always the last method to be called
        
    }
    
    public void prepareFrame(){
        this.setVisible(true);
    }

    public void addCard(Card card){
        column.addCard(card);
        CardGui cg = new CardGui(card);
        cg.repaint();
        cg.revalidate();
        SwingUtilities.updateComponentTreeUI(cg);
        cardsPanel.add(cg);
        cardsPanel.repaint();
        cardsPanel.revalidate();
        SwingUtilities.updateComponentTreeUI(cardsPanel);
    }

    public void makeColumn(Column col) {


        JPanel rootPanel = new JPanel(); // the root panel
        rootPanel.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.PAGE_AXIS));

        JLabel testLabel = new JLabel("tempName");
        JButton addCardButton = new JButton("+ Card");
        addCardButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            addCard();
            mainFrame.pack();
        }
        });
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        for(int f : col.cardIDs){

        }

        //titlePanel.setLayout(new BorderLayout());
        //buttonPanel.setLayout(new BorderLayout());
        addCardButton.setPreferredSize(new Dimension(90, 25));
        titlePanel.add(testLabel);
        buttonPanel.add(addCardButton);

        upperPanel.add(titlePanel);
        upperPanel.add(buttonPanel);

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollableCards = new JScrollPane(cardsPanel);  
    
        rootPanel.add(upperPanel, BorderLayout.NORTH);
        rootPanel.add(scrollableCards, BorderLayout.CENTER);
        mainFrame.add(rootPanel);
    }

    public void makeColumn() {

        Column column = new Column();

        JPanel rootPanel = new JPanel(); // the root panel
        rootPanel.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.PAGE_AXIS));

        JTextField titleLabel = new JTextField(column.getName());
        titleLabel.setPreferredSize(new Dimension(60,25));
        JButton saveTitleButton = new JButton("Save");
        saveTitleButton.setPreferredSize(new Dimension(90, 25));
        saveTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                column.setName(titleLabel.getText());
                titleLabel.setText(titleLabel.getText());
            }
        });
        JButton addCardButton = new JButton("+ Card");
        addCardButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addCard(new Card("","","1"));
            
            cardsPanel.revalidate();
            cardsPanel.repaint();
            SwingUtilities.updateComponentTreeUI(cardsPanel);
            cardsPanel.getRootPane().revalidate();
            cardsPanel.getRootPane().repaint();
            SwingUtilities.updateComponentTreeUI(cardsPanel.getRootPane());
        }
        });
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();


        addCardButton.setPreferredSize(new Dimension(90, 25));
        titlePanel.add(titleLabel);
        buttonPanel.add(addCardButton);
        buttonPanel.add(saveTitleButton);


        upperPanel.add(titlePanel);
        upperPanel.add(buttonPanel);

        cardsPanel = new DropPane();


        cardsPanel.setLayout(new GridLayout(20,1,10,0));
        cardsPanel.setPreferredSize(new Dimension(250,3000));


        JScrollPane scrollableCards = new JScrollPane();
        scrollableCards.setPreferredSize(new Dimension(250,700));
        scrollableCards.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JViewport viewport = new JViewport();
        viewport.setView(cardsPanel);
        scrollableCards.setViewport(viewport);

        rootPanel.add(upperPanel, BorderLayout.NORTH);
        rootPanel.add(scrollableCards, BorderLayout.CENTER);

        JButton toRemove = new JButton("Click me to print column state");
        toRemove.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Number of items in this column: " + column.getCards().size());
        }
        });
        upperPanel.add(toRemove);
        this.add(rootPanel);
    }
    public static void main(String[] args){

    }
}

