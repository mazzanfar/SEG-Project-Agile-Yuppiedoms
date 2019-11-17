import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class CardGui extends JFrame {
    private JPanel titlePanel;
    private JPanel decriptionPanel;
    private JPanel buttonPane;
    private JButton changeButton;
    private JButton saveButton;
    private JTextField title;
    private JTextField description;
    public CardGui() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 600));
        this.setMinimumSize(new Dimension(900, 600));
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        makeButton();
        makeTitle();
        makeDescription();
    }

    public void makeButton() {
        buttonPane = new JPanel(new FlowLayout());
        buttonPane.setVisible(true);
        buttonPane.setBorder(BorderFactory.createLineBorder(Color.black));

        changeButton = new JButton();
        changeButton.setRolloverEnabled(true);
        changeButton.setText("Change");

        saveButton = new JButton();
        saveButton.setRolloverEnabled(true);
        saveButton.setText("Save");

        buttonPane.add(changeButton);
        buttonPane.add(saveButton);
        this.add(buttonPane,BorderLayout.SOUTH);
    }

    public void  makeTitle() {
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setVisible(true);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.red));

        title = new JTextField(5);
        title.setVisible(true);
        title.setEditable(false);

        //add actions

        titlePanel.add(title, BorderLayout.CENTER);

        this.add(titlePanel, BorderLayout.NORTH);
    }

    public void makeDescription() {
        decriptionPanel = new JPanel(new BorderLayout());
        decriptionPanel.setBorder(BorderFactory.createLineBorder(Color.green));

        description = new JTextField(20);
        description.setEditable(false);

        //add actions

        decriptionPanel.add(description, BorderLayout.CENTER);

        this.add(decriptionPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new CardGui();
            }
        });
    }
}
