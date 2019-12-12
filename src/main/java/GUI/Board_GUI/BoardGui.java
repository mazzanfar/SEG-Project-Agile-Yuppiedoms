
package GUI.Board_GUI;

import Business_Logic.*;
import GUI.Column_GUI.Column_GUI;
import GUI.Column_GUI.DropPane;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class BoardGui extends JFrame{

  private static final int WIDTH = 900;
  private static final int HEIGHT = 600;
  private static final Font font1 = new Font("Sans-Serif", Font.BOLD, 15);

  private int panelNumber = 1;

  private JPanel boardPanel;
  private JPanel columnsPanel;
  private JPanel northPane;
  private JTextField boardTitle;
  private JTextArea boardLog;

  private JButton newColumn;

  private JMenuBar menuBar;

  private JMenuItem exit;
  private JMenuItem newBoard;
  private JMenuItem save;
  private JMenuItem boardActivity;

  private Business_Logic.Board board;

  public BoardGui(Business_Logic.Board board) {
    this.board = board;

    this.setName(board.getName());
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    this.setResizable(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    boardPanel = new JPanel(new BorderLayout());
    boardPanel.setLayout(new BorderLayout());
    boardTitle = new JTextField();
    boardTitle.setFont(font1);
    boardTitle.setText(board.getName());
    boardTitle.setEditable(false);
    boardTitle.setHorizontalAlignment(JTextField.CENTER);


    this.add(boardPanel);

    newButtons();

    northPane = new JPanel(new GridLayout(2,1,100,10));
    northPane.add(boardTitle);
    northPane.add(newColumn);

    columnsPanel = new JPanel();
    columnsPanel.setLayout(new GridLayout(0,20,0,0));
    columnsPanel.setPreferredSize(new Dimension(9000,400));
    JScrollPane scrollableCols = new JScrollPane();
    scrollableCols.setPreferredSize(new Dimension(80,500));
    scrollableCols.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    JViewport viewport = new JViewport();
    viewport.setView(columnsPanel);
    scrollableCols.setViewport(viewport);

    boardPanel.add(northPane,BorderLayout.NORTH);
    boardPanel.add(scrollableCols,BorderLayout.CENTER);

    for(Column c : board.getColumns()){
      Column_GUI columnPanel = new Column_GUI(c);
      columnPanel.setVisible(true);
      columnPanel.setBorder(BorderFactory.createLineBorder(Color.red));
      columnsPanel.add(columnPanel);
      columnsPanel.repaint();
      columnsPanel.revalidate();
      SwingUtilities.updateComponentTreeUI(columnsPanel);
    }

    this.setJMenuBar(makeMenuBar());
    this.setVisible(true);

  }

  public void newButtons() {
    newColumn = new JButton("+ New Column");
    newColumn.setPreferredSize(new Dimension(20,20));
    newColumn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputName;
        inputName = JOptionPane.showInputDialog("New Column Name");
        String roleNumber;
        roleNumber = JOptionPane.showInputDialog("New Column Role Number");
        if(isNumeric(roleNumber)){
          JOptionPane.showMessageDialog(null, "New Column Created");
          makeColumn(new Column(inputName,Integer.parseInt(roleNumber)));
        }
        else{
          JOptionPane.showMessageDialog(null, "Please type integers");
        }

      }
    });
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
    boardActivity = new JMenuItem("Board Activity");
    assignActions();
    makeShortcuts();

    file.add(exit);
    file.add(save);
    file.add(boardActivity);
    makeNew.add(newBoard);

    return menuBar;
  }

  public void assignActions() {
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        BoardGui.this.dispose();
      }
    });


    save.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent ev) {
        String fileName = boardTitle.getText() + ".csv";
        try {
          FileWriter fileWriter = new FileWriter(new File("src/main/resources", fileName), false);
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

          bufferedWriter.write(board.getName());
          for(Column i : board.getColumns()){
            bufferedWriter.newLine();
            bufferedWriter.write("-");
            bufferedWriter.newLine();
            bufferedWriter.write(i.getName());
            bufferedWriter.write("," + Integer.toString(i.getRole()));
           // bufferedWriter.write("," + retrieveString(i.getActivity()));
            for(Card c : i.getCards()){
              bufferedWriter.newLine();
              bufferedWriter.write(c.getTitle());
              bufferedWriter.write("," + Integer.toString(c.getID()));
              bufferedWriter.write("," + c.getDescription());
              bufferedWriter.write("," + c.getStoryPoints());
              bufferedWriter.write("," + c.getIdCounter());
              //bufferedWriter.write("," + retriveString(c.getActivity()));
            }
          }
          bufferedWriter.close();
        }
        catch(IOException ex){
          System.out.println("Error writing to file '" + fileName + "'");
        }

        takePicture(boardPanel);
        panelNumber++;

      }
    });

    boardActivity.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev)
      {
        JFrame c = new JFrame();
        //c.setSize(100, 300);
        c.setVisible(true);
        JPanel cp = new JPanel();
        cp.setSize(500, 1000);
        boardLog = new JTextArea(15, 50);
        boardLog.setEditable(false);

        cp.add(boardLog);
        c.add(cp);
        c.pack();
        
        try
        {
          for (int i = 0; i < board.getActivity().size(); i++)
          {
            boardLog.setText(boardLog.getText() + board.getActivity().get(i) + "\n");
          }
        }
        catch(NullPointerException e)
        { }
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

  public void addColumn(Column inputColumn)
  {
    this.board.importColumn(inputColumn);
  }

  public void makeColumn(Column inputColumn) {
    boolean check = true;
    for(Component component : columnsPanel.getComponents())
    {
      Column_GUI dp = (Column_GUI) component;
      if(dp.getComponents().length ==0)
      {
        addColumn(inputColumn);
        JPanel columnPanel = new Column_GUI(inputColumn);
        columnPanel.setVisible(true);
        dp.add(columnPanel);
        columnPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        columnsPanel.add(dp);
        columnPanel.repaint();
        columnPanel.revalidate();
        this.repaint();
        this.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        check = false;
      }
      else{continue;}
    }
    if(check)
    {
      DropPane dp = new DropPane();
      dp.setPreferredSize(new Dimension(250,960));
      addColumn(inputColumn);
      JPanel columnPanel = new Column_GUI(inputColumn);
      columnPanel.setVisible(true);
      dp.add(columnPanel);
      columnPanel.setBorder(BorderFactory.createLineBorder(Color.red));
      columnsPanel.add(dp);
      columnPanel.repaint();
      columnPanel.revalidate();
      this.repaint();
      this.revalidate();
      SwingUtilities.updateComponentTreeUI(this);
      System.out.println(board.getColumns().size());
    }
    else{check = true; }
  }
  public Board getBoard(){
    return board;
  }

  public boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public void removeCol(Column column){ board.removeColumn(column);}
}