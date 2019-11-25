package GUI.Column_GUI;
import Business_Logic.Card;
import GUI.Card_GUI.*;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


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

public class Column_GUI {


    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;
    JPanel cardsPanel;
  
    private JFrame mainFrame;
    

    public Column_GUI() {

        
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

    public static void main(String[] args){
        Column_GUI testRun = new Column_GUI();
    }

    public void addCard(){
        JPanel card1Panel = new JPanel();
        card1Panel.setPreferredSize(new Dimension(WIDTH, 100));
        card1Panel.setLayout(new BorderLayout());
        JButton button1 = new JButton();
        card1Panel.add(button1, BorderLayout.CENTER);
        card1Panel.setMaximumSize(new Dimension(WIDTH+50, 100));
        cardsPanel.add(card1Panel);
        Card c = new Card("a","b","5"); // Card test you can take it out later
        button1.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            CardGui card = new CardGui(c); //to test Cards are unique
            System.out.println("card click"); // card click functionality
        }
        });
    }
    
    public void makeColumn() {
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
    
}

