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


/**
 * Initialise all elements of a card.
 * Create an instance of a Card with informtion from the business_Logic.
 */
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

    private JFrame popUpFrame;

    private Card card;
    public CardGui(Card inputCard) {
        this.setLayout(new GridBagLayout());
        card = inputCard;
        makeCard();
    }

    /**
     * Method to retrive a card.
     * @return Card
     */
    public Card getCard(){
        return card;
    }

    public CardGui getCardGui(){
        return this;
    }

    /**
     * Create a card.
     * Uses Grid Bag Layout for the instance of a card in the column.
     * Uses method makeframe() to create a new JFrame to open an instance of the editable card platform.
     * Creates the remove button for each card.
     */
    public void makeCard(){
        Border blackline, raisedetched, loweredetched,
                raisedbevel, loweredbevel, empty;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();

        this.setLayout(new GridLayout(3,1,0,2));


        //make the card instance in the column.
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();


        this.setPreferredSize(new Dimension(WIDTH, 100));

        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100,30));
        JButton removeCardButton = new JButton("Remove");
        removeCardButton.setPreferredSize(new Dimension(100,30));

        this.setVisible(true);

        JLabel little_title = new JLabel("Title: " + card.getTitle());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 1, 1, 1);
        c.anchor = GridBagConstraints.NORTH; // place component on the North
        this.add(little_title,c);
        c.anchor = GridBagConstraints.CENTER; // place component on the North
        this.add(editButton,c);
        c.anchor = GridBagConstraints.SOUTH; // place component on the North
        this.add(removeButton,c);
        this.setVisible(true);
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
        this.add(removeCardButton,gbc);
        this.setMaximumSize(new Dimension(WIDTH+50, 100));
        this.setBorder(blackline);

        editButton.setName("editButton");
        removeCardButton.setName("removeCardButton");

        //Instance of the editable card with Jframe pop up.
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = makeFrame();
                jFrame.addWindowListener(new WindowAdapter() {

                    public void windowClosed(WindowEvent e) {
                        little_title.setText("Title: " + card.getTitle());
                        CardGui.this.revalidate();
                        CardGui.this.repaint();
                        SwingUtilities.updateComponentTreeUI(CardGui.this);
                    }

                });

                System.out.println("card click"); // card click functionality
            }
        });

        removeCardButton.addActionListener(new ActionListener() {
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

    /**
     * Create the JFrame Pop-up to edit the card.
     * @return JFrame.
     */
    public JFrame makeFrame(){
        popUpFrame = new JFrame();
        popUpFrame.setPreferredSize(new Dimension(900, 600));
        popUpFrame.setMinimumSize(new Dimension(900, 600));
        popUpFrame.setVisible(true);
        popUpFrame.setResizable(false);
        popUpFrame.setLocationRelativeTo(null);
        popUpFrame.setName("popUpFrame");
        makeButton(popUpFrame);
        makeTitle(popUpFrame);
        makeDescription(popUpFrame);
        makeStoryPoint(popUpFrame);
        makeID(popUpFrame);
        return popUpFrame;
    }

    /**
     * Creates the buttons that appears on the JFrame pop-up of a card.
     * (Save and Change buttons)
     * @param popUpFrame
     */
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
        changeButton.setName("changeButton");
        saveButton.setName("saveButton");
    }

    /**
     * Set the location that will store the card title.
     * @param popUpFrame
     */
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

    /**
     * Set the location that will store the card ID.
     * @param popUpFrame
     */
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
    }

    /**
     * Set the location that will store the card Story Points.
     * @param popUpFrame
     */
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

    /**
     * Set the location that will store the card description.
     * @param popUpFrame
     */
    public void makeDescription(JFrame popUpFrame) {

        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("Courier New", Font.ITALIC, 24));
        descriptionPanel = new JPanel(new BorderLayout());

        description = new JTextField(20);
        description.setText(card.getDescription());
        description.setEditable(false);

        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        popUpFrame.add(descriptionPanel, BorderLayout.CENTER);
    }

    public JFrame getPopUpFrame(){
        return popUpFrame;
    }
}
