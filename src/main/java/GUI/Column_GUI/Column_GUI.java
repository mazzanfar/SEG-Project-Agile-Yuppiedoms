package GUI.Column_GUI;

import Business_Logic.Card;
import Business_Logic.Column;
import GUI.Board_GUI.BoardGui;
import GUI.Card_GUI.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Column_GUI extends JPanel{


    private static final int WIDTH = 250;
    private static final int HEIGHT = 960;
    private DropPane cardsPanel;
    private Column column;

    public Column_GUI(Column column) {
        this.column = column;
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        makeColumn(); // does all column building activities
        for(Card c : column.getCards())
        {
            CardGui cg = new CardGui(c);
            cg.repaint();
            cg.revalidate();
            SwingUtilities.updateComponentTreeUI(cg);
            cardsPanel.add(cg);
            cardsPanel.repaint();
            cardsPanel.revalidate();
            SwingUtilities.updateComponentTreeUI(cardsPanel);
        }

    }

    public void removeFromCol(Card c){
        column.removeCard(c);
    }

    public void addToCol(Card c){
        column.addCard(c);
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

    public void makeColumn() {
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBounds(1,1,WIDTH,HEIGHT);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.PAGE_AXIS));

        JTextField titleLabel = new JTextField(column.getName());
        titleLabel.setPreferredSize(new Dimension(60,25));
        titleLabel.setName("titleLabel");

        Object[] roles = { "Backlog", "In-progress", "Completed"};
        JComboBox comboBox = new JComboBox(roles); comboBox.setSelectedIndex(1);
        if(column.getRole()==0){comboBox.setSelectedItem("Backlog");}
        if(column.getRole()==1){comboBox.setSelectedItem("In-progress");}
        if(column.getRole()==2){comboBox.setSelectedItem("Completed");}

        JButton saveTitleButton = new JButton("Save");
        saveTitleButton.setName("saveTitleButton");
        saveTitleButton.setPreferredSize(new Dimension(92, 25));
        saveTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                column.setName(titleLabel.getText());
                titleLabel.setText(titleLabel.getText());
                String role = (String) comboBox.getSelectedItem();
                if(role.equals("Backlog")){column.setRole(0);}
                if(role.equals("In-progress")){column.setRole(1);}
                if(role.equals("Completed")){column.setRole(2);}

            }
        });
        JButton addCardButton = new JButton("+ Card");
        addCardButton.setName("addCardButton");
        System.out.println(addCardButton.getName());
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            addCard(new Card("Default","Default","1"));

            cardsPanel.revalidate();
            cardsPanel.repaint();
            SwingUtilities.updateComponentTreeUI(cardsPanel);
            cardsPanel.getRootPane().revalidate();
            cardsPanel.getRootPane().repaint();
            SwingUtilities.updateComponentTreeUI(cardsPanel.getRootPane());
        }
        });
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(20, 20));
        removeButton.setName("removeButton");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardGui boardGui = (BoardGui) Column_GUI.this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                JPanel dp = (JPanel)Column_GUI.this.getParent();
                dp.remove(Column_GUI.this);
                boardGui.removeCol(Column_GUI.this.column);
                dp.repaint();
                dp.revalidate();
                SwingUtilities.updateComponentTreeUI(dp);
                boardGui.repaint();
                boardGui.revalidate();
                SwingUtilities.updateComponentTreeUI(boardGui);
            }
        });
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel(new GridLayout(3,1,1,1));

        addCardButton.setPreferredSize(new Dimension(92, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(comboBox);
        buttonPanel.add(saveTitleButton);
        buttonPanel.add(addCardButton);
        buttonPanel.add(removeButton);

        upperPanel.add(titlePanel);
        upperPanel.add(buttonPanel);

        cardsPanel = new DropPane();

        cardsPanel.setLayout(new GridLayout(20,1,10,0));
        cardsPanel.setPreferredSize(new Dimension(WIDTH,3000));

        JScrollPane scrollableCards = new JScrollPane();
        scrollableCards.setPreferredSize(new Dimension(250,700));
        scrollableCards.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JViewport viewport = new JViewport();
        viewport.setView(cardsPanel);
        scrollableCards.setViewport(viewport);

        this.add(upperPanel, BorderLayout.NORTH);
        this.add(scrollableCards, BorderLayout.CENTER);
    }

    public Column getColumn(){
        return column;
    }

    public Column_GUI getColumn_GUI(){
        return this;
    }

}
