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

/*
    HOW TO RUN THIS CODE:
    I haven't tried this on lab pc's yet but you can do this on your own windows machine:
    - Navigate to the above folder Column_GUI on your terminal
    - type 'set CLASSPATH=%CLASSPATH%;PATH_TO_THIS_FOLDER'
    - then type 'javac Column_GUI.java'
    - and finally 'java Column_GUI'

    TODO: 
    - Figure out how to add package to use actual BL classes
    - Refactor to made code cleaner

*/

public class Column_GUI extends JPanel{


    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;
    private DropPane cardsPanel;
    //private JFrame mainFrame;
    private Column column;

    public Column_GUI(Column column) {
        this.column = column;
        prepareFrame(); // makes a frame
        makeColumn(); // does all column building activities

        // DragPane drag = new DragPane();
        // cardsPanel.add(drag);
        
        // add(cardsPanel);
        // add(new DropPane());
        //mainFrame.pack(); // make sure this is always the last method to be called
    }
    
    public void prepareFrame(){
//        mainFrame = new JFrame();

        this.setPreferredSize(new Dimension(WIDTH+50, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH+50, HEIGHT));
//        this.setResizable(true);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

//    public void actionPerformed (ActionEvent e)
//    {
//        if(e.getActionCommand().equals("remove")){
//            cardsPanel.remove();
//        }
//    }



    public void addCard(Card card){
        CardGui cg = new CardGui(card);
        cg.repaint();
        cg.revalidate();
        SwingUtilities.updateComponentTreeUI(cg);
        cardsPanel.add(cg);
    }
    
    public void makeColumn() {
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
            column.addCard(new Card("","","1"));
            cardsPanel.removeAll();
            for(Card card : column.getCards())
            {
                addCard(card);
            }
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

        //titlePanel.setLayout(new BorderLayout());
        //buttonPanel.setLayout(new BorderLayout());
        addCardButton.setPreferredSize(new Dimension(90, 25));
        titlePanel.add(titleLabel);
        buttonPanel.add(addCardButton);
        buttonPanel.add(saveTitleButton);


        upperPanel.add(titlePanel);
        upperPanel.add(buttonPanel);

        cardsPanel = new DropPane(column);

        PropertyChangeListener propChangeListn = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if(column.getCards().size() != cardsPanel.getComponents().length){
                    cardsPanel.removeAll();
                    for(Card card : column.getCards())
                    {
                        addCard(card);
                    }
                }
            }
        };
        cardsPanel.addPropertyChangeListener(propChangeListn);
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
        this.add(rootPanel);
    }
    public static void main(String[] args){
//        Column_GUI testRun = new Column_GUI(new Column("",1));
    }
}

