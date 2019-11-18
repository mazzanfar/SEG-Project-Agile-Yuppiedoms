//package GUI.Board_GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Display {

  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;

  private int panelNumber = 1;

  private JFrame mainFrame;
  private JFrame newPanelFrame;
  private JPanel mainPanel;
  private JPanel boardPanel;
  private JTextField boardTitle;
  private JButton newButton;

  private JButton newColumn;
  private JButton newCard;

  private JMenuBar menuBar;

  private JMenuItem exit;
  private JMenuItem newBoard;
  private JMenuItem save;

  public Display() {

    mainFrame = new JFrame("YUPPIEDOMS - KANBAN");

    mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    mainFrame.setResizable(true);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);

    makeBoardButton();
    makeMainPanel();
    makeMenuBar();
 }

  public void makeMainPanel() {
    mainPanel = new JPanel(new FlowLayout());
    mainPanel.add(newButton);
    mainFrame.add(mainPanel);
  }


  public void makeBoardButton() {
    newButton = new JButton("+ New Kanban Board");
  }

  public void makeNewButtons() {
    newColumn = new JButton("+ New Column");
    newCard = new JButton("+ New Card");
  }

  public void makeBoardPanel(String name) {
    Font font1 = new Font("SansSerif", Font.BOLD, 30);

    newPanelFrame = new JFrame(name);

    newPanelFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    newPanelFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    newPanelFrame.setResizable(true);
    newPanelFrame.setLocationRelativeTo(null);
    newPanelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    newPanelFrame.setVisible(true);

    boardPanel = new JPanel();
    boardPanel.setLayout(new FlowLayout());
    boardTitle = new JTextField();
    boardTitle.setFont(font1);
    boardTitle.setHorizontalAlignment(JTextField.LEFT);

    boardTitle.setText(name);
    boardTitle.setEditable(false);
    newPanelFrame.add(boardPanel);
    boardPanel.add(boardTitle);

    makeNewButtons();

    boardPanel.add(newColumn);
    boardPanel.add(newCard);
  }


  public void makeMenuBar() {
    menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    menuBar.add(file);

    JMenu makeNew = new JMenu("New");
    menuBar.add(makeNew);

    exit = new JMenuItem("Exit");
    newBoard = new JMenuItem("Kanban Board");
    save = new JMenuItem("Save");

    assignActions();
    makeShortcuts();

    file.add(exit);
    file.add(save);
    makeNew.add(newBoard);

    mainFrame.setJMenuBar(menuBar);
  }

  public void assignActions() {
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

    exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
          System.exit(0);
        }
    });

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


    save.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent ev) {
        String fileName = "temp.csv";
        try {
          FileWriter fileWriter = new FileWriter(fileName, true);

          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

          bufferedWriter.write(boardTitle.getText());
          bufferedWriter.newLine();
          bufferedWriter.close();
        }
        catch(IOException ex){
          System.out.println("Error writing to file '" + fileName + "'");
        }

        takePicture(boardPanel);
        panelNumber++;

      }
    });

  }

  public void makeShortcuts() {
    KeyStroke exitShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
    exit.setAccelerator(exitShortcut);

    KeyStroke newBoardShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    newBoard.setAccelerator(newBoardShortcut);

    KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    save.setAccelerator(saveShortcut);
  }

  // code sourced from https://stackoverflow.com/questions/18806287/how-to-save-jpanel-as-jpeg-with-its-components
  public void takePicture(JPanel panel) {
      BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);

      panel.print(img.getGraphics());
      try {
          ImageIO.write(img, "jpg", new File("Kanban-Board-" + panelNumber + ".jpg"));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

}
