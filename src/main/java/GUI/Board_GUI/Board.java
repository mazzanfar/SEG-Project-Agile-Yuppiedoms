import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Board {
  
  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;
  private static final Font font1 = new Font("Sans-Serif", Font.BOLD, 15);

  private int panelNumber = 1;

  private JFrame boardFrame;

  private JPanel boardPanel;

  private JTextField boardTitle;

  private JButton newColumn;
  private JButton newCard;
  private JMenuBar menuBar;

  private JMenuItem exit;
  private JMenuItem newBoard;
  private JMenuItem save;


  public Board(String name) {
      boardFrame = new JFrame(name);

      boardFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      boardFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
      boardFrame.setResizable(true);
      boardFrame.setLocationRelativeTo(null);
      boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      boardPanel = new JPanel(new FlowLayout());
      boardTitle = new JTextField();
      boardTitle.setFont(font1);
      boardTitle.setText(name);
      boardTitle.setEditable(false);
      boardTitle.setHorizontalAlignment(JTextField.LEFT);


      boardFrame.add(boardPanel);
      boardPanel.add(boardTitle);

      newButtons();


      boardPanel.add(newColumn);
      boardPanel.add(newCard);
      boardFrame.setJMenuBar(makeMenuBar());
      boardFrame.setVisible(true);
    }

    public void newButtons() {
      newColumn = new JButton("+ New Column");
      newCard = new JButton("+ New Card");
    }

    public JMenuBar makeMenuBar() {
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

      return menuBar;
    }

    public void assignActions() {
      exit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ev) {
            boardFrame.dispose();
          }
      });


      save.addActionListener(new ActionListener () {
        public void actionPerformed(ActionEvent ev) {
          String fileName = boardTitle.getText() + ".csv";
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
