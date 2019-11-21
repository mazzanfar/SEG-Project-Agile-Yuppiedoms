import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Mainframe {

  private static final int WIDTH = 618;
  private static final int HEIGHT = 250;
  private static final Font font1 = new Font("Sans-Serif", Font.BOLD, 15);

  private JFrame mainFrame;

  private JPanel contentPane;

  private JLabel splashTitle;
  private JButton newKanbanBoard;
  private JButton loadKanbanBoard;

  public Mainframe() {

    mainFrame = new JFrame("YUPPIEDOMS - KANBAN");

    contentPane = (JPanel)mainFrame.getContentPane();
    contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
    contentPane.setLayout(new BorderLayout(6, 6));

    splashTitle = new JLabel("To get started, create or load a Kanban board:");
    splashTitle.setFont(font1);
    contentPane.add(splashTitle, BorderLayout.PAGE_START);
    splashTitle.setHorizontalAlignment(JLabel.CENTER);

    mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setResizable(true);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);

    splashButtons();
    addActions();
 }

  public void splashButtons() {

    newKanbanBoard = new JButton("+ New Kanban Board");
    newKanbanBoard.setPreferredSize(new Dimension(300,150));
    newKanbanBoard.setBackground(Color.CYAN);

    loadKanbanBoard = new JButton("+ Load Kanban Board");
    loadKanbanBoard.setPreferredSize(new Dimension(300,150));
    loadKanbanBoard.setBackground(Color.CYAN);

    contentPane.add(newKanbanBoard, BorderLayout.LINE_START);
    contentPane.add(loadKanbanBoard, BorderLayout.LINE_END);
    mainFrame.pack();
  }

  public void addActions() {
    newKanbanBoard.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Input name of Kanban Board");
        if (input.length() > 0) {
          Board board = new Board(input);
        } else {
          JOptionPane.showMessageDialog(null, "Name required");
        }
      }
    });
  }
}
