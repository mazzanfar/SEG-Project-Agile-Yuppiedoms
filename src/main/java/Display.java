import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display {

  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;

  private JFrame mainFrame;
  private JPanel mainPanel;
  private JPanel boardPanel;
  private JTextField boardTitle;
  private JButton newButton;

  public Display() {

    mainFrame = new JFrame("YUPPIEDOMS - KANBAN");

    mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setResizable(true);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);


    makeButton();
    makeMainPanel();
    makeMenuBar();
    // makeTextArea();

 }

  public void makeMainPanel() {
    mainPanel = new JPanel(new FlowLayout());
    mainPanel.add(newButton);
    mainFrame.add(mainPanel);
  }


  public void makeButton() {
    newButton = new JButton("+ New Kanban Board");

    newButton.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Input name of Kanban Board");
        if (input.length() > 0) {
          makeBoardPanel(input);
        } else {
          JOptionPane.showMessageDialog(null, "Name required");
        }
      }
    });
  }

  public void makeBoardPanel(String name) {
    Font font1 = new Font("SansSerif", Font.BOLD, 30);

    boardPanel = new JPanel(new BorderLayout());
    boardTitle = new JTextField();

    boardTitle.setFont(font1);
    boardTitle.setHorizontalAlignment(JTextField.CENTER);

    boardTitle.setText(name);
    boardTitle.setEditable(false);
    mainFrame.remove(mainPanel);
    mainFrame.add(boardPanel);
    boardPanel.add(boardTitle, BorderLayout.PAGE_START);
    mainFrame.repaint();
    mainFrame.pack();
  }


  public void makeMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    menuBar.add(file);

    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          System.exit(0);
        }
      });

    KeyStroke exitShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
    exit.setAccelerator(exitShortcut);

    JMenuItem newBoard = new JMenuItem("New Kanban Board");
    newBoard.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent ev) {
        String input = JOptionPane.showInputDialog("Input name of Kanban Board");
        if (input.length() > 0) {
          makeBoardPanel(input);
        } else {
          JOptionPane.showMessageDialog(null, "Name required");
        }
      }
    });

    KeyStroke newBoardShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    newBoard.setAccelerator(newBoardShortcut);

    file.add(exit);
    file.add(newBoard);
    mainFrame.setJMenuBar(menuBar);
  }

}
