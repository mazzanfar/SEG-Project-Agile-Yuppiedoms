package GUI.Column_GUI;

import javax.swing.*;
import java.awt.*;



public class Column_GUI {


    private static final int WIDTH = 200;
    private static final int HEIGHT = 600;
  
  
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JPanel columnPanel;
    private JTextField columnTitle;
    private JButton newButton;

    private JButton newCard;

    public Column_GUI() {

        mainFrame = new JFrame();
    
        mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        
        makeColumnPanel("Backlog");
    }    

    public void makeColumnMainPanel() {
        mainPanel = new JPanel(new FlowLayout());
        mainFrame.add(mainPanel);
    }


    public void makeNewButtons() {
        newCard = new JButton("+ New Card");
    }
    
    public void makeColumnPanel(String name) {
        Font font1 = new Font("Arial", Font.BOLD, 30);
    
    
        columnPanel = new JPanel();
        columnPanel.setLayout(new FlowLayout());
        columnTitle = new JTextField();
        columnTitle.setFont(font1);
        columnTitle.setHorizontalAlignment(JTextField.LEFT);
    
        columnTitle.setText(name);
        columnTitle.setEditable(false);
        mainFrame.add(columnPanel);
        columnPanel.add(columnTitle);
    
        makeNewButtons();
    
        columnPanel.add(newCard);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
          public void run(){
              new Column_GUI();
          }
        });
      }
    
}

