package GUI.Card_GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import Business_Logic.Card;
//import javax.imageio.ImageIO;
//import javax.smartcardio.Card;
public class CardGui extends JFrame implements ActionListener{
    private JPanel titlePanel;
    private JPanel descriptionPanel;
    private JPanel buttonPane;
    private JButton changeButton;
    private JButton saveButton;
    private JTextField title;
    private JTextField description;
    private JPanel SpPanel;
    private JComboBox<String> SpDropDown;
    private JLabel ID;
    private JLabel descriptionLabel;
    private JLabel titleLabel;
    private Card card;

    private Card temp;

    public CardGui(Card inputCard) {
        card = inputCard;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        buttonPane = new JPanel(new FlowLayout());
        buttonPane.setVisible(true);
        buttonPane.setBorder(BorderFactory.createLineBorder(Color.black));

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

        buttonPane.add(changeButton);
        buttonPane.add(saveButton);
        this.add(buttonPane,BorderLayout.SOUTH);
    }

    public void  makeTitle() {
        titleLabel = new JLabel("Title: ");
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setVisible(true);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.red));

        title = new JTextField(5);
        title.setVisible(true);
        title.setText(card.getTitle());
        title.setEditable(false);

        //add actions

        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
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
        }
    }

    public void makeID()
    {
        ID = new JLabel("10");
        this.add(ID, BorderLayout.WEST);
        //TODO: make jlabel ID by getting the id from Card class.
    }

    public void makeStoryPoint()
    {
        String[] choices = { "1", "2", "3", "4", "5"};

        SpDropDown = new JComboBox<String>(choices);
        SpPanel = new JPanel(new BorderLayout());
        SpPanel.add(SpDropDown, BorderLayout.CENTER);
        this.add(SpPanel, BorderLayout.EAST);
        SpDropDown.setVisible(true);
    }

    public void makeDescription() {
        
        descriptionLabel = new JLabel("Description: ");
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.green));

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
                new CardGui(new Card("11","dd","1111"));
                //just for testing.
                //remove later.
            }
        });
    }
}
