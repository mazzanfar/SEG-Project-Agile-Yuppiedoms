package GUI.Card_GUI;
import Business_Logic.Card;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.image.BufferedImage;

public class CardGui extends JFrame implements ActionListener{
    private JPanel titlePanel;
    private JPanel descriptionPanel;
    private JPanel buttonPanel;
    private JPanel IdPanel;
    private JPanel SpPanel;

    private JButton changeButton;
    private JButton saveButton;

    private JTextField title;
    private JTextField description;

    private JComboBox<String> SpDropDown;

    private JLabel ID;
    private JLabel descriptionLabel;
    private JLabel titleLabel;
    private JLabel IdLabel;
    private JLabel SpLabel;

    private Card card;
    public CardGui(Card inputCard) {
        card = inputCard;

        this.setPreferredSize(new Dimension(900, 600));
        this.setMinimumSize(new Dimension(900, 600));
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        makeButton();
        makeTitle();
        makeDescription();
        makeStoryPoint();
        makeID();
    }

    public void makeButton() {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setVisible(true);
        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        changeButton = new JButton();
        changeButton.setRolloverEnabled(true);
        changeButton.setText("Change");
        changeButton.setActionCommand("change");
        changeButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setRolloverEnabled(true);
        saveButton.setText("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        buttonPanel.add(changeButton);
        buttonPanel.add(saveButton);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void  makeTitle() {
        titleLabel = new JLabel("Title: ");
        titleLabel.setFont(new Font("Courier New", Font.ITALIC, 24));
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setVisible(true);

        title = new JTextField(5);
        title.setVisible(true);
        title.setText(card.getTitle());
        title.setEditable(false);

        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.setBorder(new EmptyBorder(0, 43, 0, 112));
//        titlePanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.add(titlePanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("change"))
        {
            description.setEditable(true);
            description.setText(description.getText());


            title.setEditable(true);
            title.setText(title.getText());

            //Add card class and then can do card.setTitle() etc...
            //to link business logic to gui.
            //Final should look like card instance.get...
        }
        if(e.getActionCommand().equals("save"))
        {
            title.setEditable(false);
            description.setEditable(false);
            card.setDescription(description.getText());
            card.setTitle(title.getText());
            card.setStoryPoints(SpDropDown.getToolTipText());
            this.dispose();
        }
    }

    public void makeID()
    {
        IdPanel = new JPanel(new GridLayout(1,2,0,0));
        IdLabel = new JLabel("ID:");

        ID = new JLabel(Integer.toString(card.getID()));
        IdLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        ID.setFont(new Font("Courier New", Font.ITALIC, 12));


        IdPanel.add(IdLabel);
        IdPanel.add(ID);
        IdPanel.setBorder(new EmptyBorder(450, 0, 0, 0));
        this.add(IdPanel, BorderLayout.WEST);
        //TODO: make jlabel ID by getting the id from Card class.
    }

    public void makeStoryPoint()
    {
        String[] choices = { "1", "2", "3", "4", "5"};

        SpLabel = new JLabel("StoryPoint:");
        SpLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        SpDropDown = new JComboBox<String>(choices);
        SpDropDown.setSelectedItem(card.getStoryPoints());
        SpDropDown.setFont(new Font("Courier New", Font.ITALIC, 12));
        SpDropDown.setMaximumSize(new Dimension(0,10));
        SpPanel = new JPanel(new FlowLayout(1,0,0));
        SpPanel.add(SpLabel);
        SpPanel.add(SpDropDown);
        SpPanel.setBorder(new EmptyBorder(450, 0, 0, 0));
        this.add(SpPanel, BorderLayout.EAST);
        SpDropDown.setVisible(true);
    }

    public void makeDescription() {

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("Courier New", Font.ITALIC, 24));
        descriptionPanel = new JPanel(new BorderLayout());
        //descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.green));

        description = new JTextField(20);
        description.setText(card.getDescription());
        description.setEditable(false);

        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        this.add(descriptionPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new CardGui(new Card("11","dd","3"));
                new CardGui(new Card("11","dd","4"));
                //just for testing.
                //remove later.
            }
        });
    }
}
