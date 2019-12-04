package GUI.Column_GUI;

import Business_Logic.Card;
import Business_Logic.Column;
import GUI.Card_GUI.*;
import GUI.Board_GUI.CardTransfer;
import GUI.Board_GUI.CardTransfer.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

import java.util.ArrayList;
import java.awt.dnd.*;


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
    private JPanel cardsPanel;
    private JFrame mainFrame;
    private Column column;

    public Column_GUI(String inputName, int roleNum) {
        column = new Column(inputName,roleNum);
        prepareFrame(); // makes a frame
        makeColumn(); // does all column building activities
        mainFrame.pack(); // make sure this is always the last method to be called
    }
    
    public void prepareFrame(){
        mainFrame = new JFrame();
    
        mainFrame.setPreferredSize(new Dimension(WIDTH+50, HEIGHT));
        mainFrame.setMinimumSize(new Dimension(WIDTH+50, HEIGHT));
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }



    public void addCard(){
        Border blackline, raisedetched, loweredetched,
        raisedbevel, loweredbevel, empty;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();
        
        CardTransfer ct = new CardTransfer();
        JPanel cardPanel = new JPanel();

        
    
        GridBagLayout gridbag = new GridBagLayout();
        cardPanel.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

    
        cardPanel.setPreferredSize(new Dimension(WIDTH, 100));
        
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");
        DragActionButton dragButton = ct.new DragActionButton(cardPanel, "");
        dragButton.setVisible(true);

        cardPanel.add(dragButton);
        cardPanel.add(editButton);
        cardPanel.add(removeButton);
        cardPanel.setVisible(true);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 50;
        gbc.weighty = 1.0;      //make this component tall
        gbc.weightx = 1.0;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        cardPanel.add(dragButton, gbc);

        gbc.ipady = 1;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 0;
        cardPanel.add(editButton, gbc);

        gbc.ipady = 1;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 0;
        cardPanel.add(removeButton,gbc);
        cardPanel.setMaximumSize(new Dimension(WIDTH+50, 100));
        cardsPanel.add(cardPanel);
        Card card = new Card("","","1"); // Card test you can take it out later
        column.addCard(card);
        JLabel little_title = new JLabel("Title: " );
        dragButton.add(little_title);
        cardPanel.setBorder(blackline);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardGui cardGui = new CardGui(card); //to test Cards are unique
                cardGui.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        little_title.setText("Title: " + card.getTitle());
                        dragButton.add(little_title);
                        cardsPanel.revalidate();
                        cardsPanel.repaint();
                    }

                });

                System.out.println("card click"); // card click functionality
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardsPanel.remove(cardPanel);
                cardsPanel.revalidate();
                cardsPanel.repaint();
                column.removeCard(card);
            }
        });
    }
    
    public void makeColumn() {
        JPanel rootPanel = new JPanel(); // the root panel
        rootPanel.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.PAGE_AXIS));

        JLabel testLabel = new JLabel(column.getName());
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
    public static void main(String[] args){
        Column_GUI testRun = new Column_GUI("backlog",1);
    }
}

