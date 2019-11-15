import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Properties;

public class Display {

  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;

  private int panelNumber = 1;

  private JFrame mainFrame;
  private JPanel mainPanel;
  private JPanel boardPanel;
  private JTextField boardTitle;
  private JButton newButton;

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

    makeButton();
    makeMainPanel();
    makeMenuBar();
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
    boardTitle.setText("");

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

    JMenu makeNew = new JMenu("New");
    menuBar.add(makeNew);

    exit = new JMenuItem("Exit");
    newBoard = new JMenuItem("New Kanban Board");
    save = new JMenuItem("Save");

    assignActions();
    makeShortcuts();

    file.add(exit);
    file.add(save);
    makeNew.add(newBoard);

    mainFrame.setJMenuBar(menuBar);
  }

  public void assignActions() {
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

      panel.print(img.getGraphics()); // or: panel.printAll(...);
      try {
          ImageIO.write(img, "jpg", new File("Kanban-Board-" + panelNumber + ".jpg"));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

}
