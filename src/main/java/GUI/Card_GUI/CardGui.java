package GUI.Card_GUI;
import Business_Logic.Card;
import GUI.Column_GUI.Column_GUI;
import GUI.Column_GUI.DragPane;
import GUI.Column_GUI.DropPane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.image.BufferedImage;

public class CardGui extends DragPane {
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
        makeCard();
    }

    public Card getCard(){
        return card;
    }

    public void makeCard(){
        Border blackline, raisedetched, loweredetched,
                raisedbevel, loweredbevel, empty;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();

        this.setLayout(new GridLayout(3,1,0,2));



        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();


        this.setPreferredSize(new Dimension(WIDTH, 100));

        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100,30));
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100,30));

        this.setVisible(true);

        JLabel little_title = new JLabel("Title: " + card.getTitle());
        this.add(little_title);
        this.add(editButton);
        this.add(removeButton);
        this.setVisible(true);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.ipady = 50;
//        gbc.weighty = 1.0;      //make this component tall
//        gbc.weightx = 1.0;
//        gbc.gridwidth = 3;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        cardPanel.add(cardPanel, gbc);

        gbc.ipady = 1;
        gbc.weightx = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 0;
        this.add(editButton, gbc);

        gbc.ipady = 1;
        gbc.weightx = 10;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 0;
        this.add(removeButton,gbc);
        this.setMaximumSize(new Dimension(WIDTH+50, 100));
        this.setBorder(blackline);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = makeFrame();
                jFrame.addWindowListener(new WindowAdapter() {

                    public void windowClosed(WindowEvent e) {
                        little_title.setText("Title: " + card.getTitle());
                        CardGui.this.add(little_title);
                        CardGui.this.revalidate();
                        CardGui.this.repaint();
                        SwingUtilities.updateComponentTreeUI(CardGui.this);
                    }

                });

                System.out.println("card click"); // card click functionality
            }
        });
         removeButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                  DropPane dp = (DropPane)CardGui.this.getParent();
                  Column_GUI ColGui = (Column_GUI) dp.getParent().getParent().getParent();
                  ColGui.removeFromCol(CardGui.this.card);
                  dp.remove(CardGui.this);
                  dp.repaint();
                  dp.revalidate();
                  SwingUtilities.updateComponentTreeUI(dp);
             }
         });
    }

    public JFrame makeFrame(){
        JFrame popUpFrame = new JFrame();
        popUpFrame.setPreferredSize(new Dimension(900, 600));
        popUpFrame.setMinimumSize(new Dimension(900, 600));
        popUpFrame.setVisible(true);
        popUpFrame.setResizable(false);
        popUpFrame.setLocationRelativeTo(null);
        makeButton(popUpFrame);
        makeTitle(popUpFrame);
        makeDescription(popUpFrame);
        makeStoryPoint(popUpFrame);
        makeID(popUpFrame);
        return popUpFrame;
    }



    public void makeButton(JFrame popUpFrame) {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setVisible(true);
        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        changeButton = new JButton();
        changeButton.setRolloverEnabled(true);
        changeButton.setText("Change");
        changeButton.setActionCommand("change");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                description.setEditable(true);
                description.setText(description.getText());


                title.setEditable(true);
                title.setText(title.getText());

                SpDropDown.setEditable((true));
                SpDropDown.setSelectedItem(SpDropDown.getSelectedItem());
            }
        });

        saveButton = new JButton();
        saveButton.setRolloverEnabled(true);
        saveButton.setText("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setEditable(false);
                description.setEditable(false);
                SpDropDown.setEditable((false));
                card.setDescription(description.getText());
                card.setTitle(title.getText());
                card.setStoryPoints((String) SpDropDown.getSelectedItem());
                popUpFrame.dispose();
            }
        });

        buttonPanel.add(changeButton);
        buttonPanel.add(saveButton);
        popUpFrame.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void  makeTitle(JFrame popUpFrame) {
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
        popUpFrame.add(titlePanel, BorderLayout.NORTH);
    }

    public void makeID(JFrame popUpFrame)
    {
        IdPanel = new JPanel(new GridLayout(1,2,0,0));
        IdLabel = new JLabel("ID:");

        ID = new JLabel(Integer.toString(card.getID()));
        IdLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        ID.setFont(new Font("Courier New", Font.ITALIC, 12));


        IdPanel.add(IdLabel);
        IdPanel.add(ID);
        IdPanel.setBorder(new EmptyBorder(450, 0, 0, 0));
        popUpFrame.add(IdPanel, BorderLayout.WEST);
        //TODO: make jlabel ID by getting the id from Card class.
    }

    public void makeStoryPoint(JFrame popUpFrame)
    {
        String[] choices = { "1", "2", "3", "4", "5"};

        SpLabel = new JLabel("StoryPoint:");
        SpLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        SpDropDown = new JComboBox<String>(choices);
        SpDropDown.setSelectedItem(card.getStoryPoints());
        SpDropDown.setFont(new Font("Courier New", Font.ITALIC, 12));
        SpDropDown.setMaximumSize(new Dimension(0,10));
        SpDropDown.setSelectedItem(card.getStoryPoints());
        SpPanel = new JPanel(new FlowLayout(1,0,0));
        SpPanel.add(SpLabel);
        SpPanel.add(SpDropDown);
        SpPanel.setBorder(new EmptyBorder(450, 0, 0, 0));
        popUpFrame.add(SpPanel, BorderLayout.EAST);
        SpDropDown.setVisible(true);
        SpDropDown.setEditable(false);
    }

    public void makeDescription(JFrame popUpFrame) {

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("Courier New", Font.ITALIC, 24));
        descriptionPanel = new JPanel(new BorderLayout());
        //descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.green));

        description = new JTextField(20);
        description.setText(card.getDescription());
        description.setEditable(false);

        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        popUpFrame.add(descriptionPanel, BorderLayout.CENTER);
    }
}
