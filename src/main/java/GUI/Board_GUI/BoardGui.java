
package GUI.Board_GUI;

import Business_Logic.*;
import GUI.Column_GUI.Column_GUI;
import GUI.Column_GUI.DropPane;
import GUI.State.State;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.io.File;


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
  private JMenuItem showState;

  private Business_Logic.Board board;
  /**
   * Initialise all elements of a board.
   * Create an instance of a board gui with informtion from the business_Logic board
   */
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
    boardTitle.setName("boardTitle");

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

    this.setJMenuBar(makeMenuBar());
    this.setVisible(true);
    try{
      int size = board.getColumns().size();
      for(int i = 0; i<size;i++)
      {
        makeColumn(board.getColumns().get(i));
        System.out.println(i);
      }
    }
    catch(java.util.ConcurrentModificationException exception){

    }
  }
  /**
   * create the buttons for the board gui
   */
  public void newButtons() {
    newColumn = new JButton("+ New Column");
    newColumn.setPreferredSize(new Dimension(20,20));
    newColumn.setName("newColumn");
    newColumn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputName;
        inputName = JOptionPane.showInputDialog("New Column Name");

        JPanel panel = new JPanel(new GridBagLayout());
        Object[] roles = { "Backlog", "In-progress", "Completed"};
        JComboBox comboBox = new JComboBox(roles); comboBox.setSelectedIndex(1);
        JOptionPane.showMessageDialog(null, comboBox, "Select a role", JOptionPane.QUESTION_MESSAGE);
        panel.add(comboBox);
        String result = (String) comboBox.getSelectedItem();
        if(result.equals("Backlog")){makeColumn(new Column(inputName,0));}
        if(result.equals("In-progress")){makeColumn(new Column(inputName,1));}
        if(result.equals("Completed")){makeColumn(new Column(inputName,2));}
      }
    });
  }
  /**
   * create menu bar in the board gui
   */
  public JMenuBar makeMenuBar() {
    menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    menuBar.add(file);

    JMenu makeNew = new JMenu("New");
    menuBar.add(makeNew);

    exit = new JMenuItem("Exit");
    newBoard = new JMenuItem("Kanban Board");
    save = new JMenuItem("Save");
    save.setName("save");
    boardActivity = new JMenuItem("Board Activity");
    boardActivity.setName("activity");
    showState = new JMenuItem("Show State");
    boardActivity.setName("showState");
    assignActions();
    makeShortcuts();

    file.add(exit);
    file.add(save);
    file.add(boardActivity);
    file.add(showState);
    file.setName("file");
    makeNew.add(newBoard);

    return menuBar;
  }
  /**
   * Assign the actions to the buttons
   */
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
            bufferedWriter.write("," + i.getRole());
            for(Card c : i.getCards()){
              bufferedWriter.newLine();
              bufferedWriter.write(c.getTitle());
              bufferedWriter.write("," + c.getID());
              bufferedWriter.write("," + c.getDescription());
              bufferedWriter.write("," + c.getStoryPoints());
              bufferedWriter.write("," + c.getIdCounter());
            }
          }
          bufferedWriter.close();
        }
        catch(IOException ex){
          System.out.println("Error writing to file '" + fileName + "'");
        }
        panelNumber++;
      }
    });

    boardActivity.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev)
      {
        JFrame c = new JFrame();
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

    showState.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev)
      {
        double backLog = 0;
        double InProgress = 0;
        double completed = 0;
        for(Column c : board.getColumns())
        {
          if(c.getRole() == 0){backLog++;}
          if(c.getRole() == 1){InProgress++;}
          if(c.getRole() == 2){completed++;}
        }
        State state = new State(backLog,InProgress,completed);
      }
    });
  }
  /**
   * Assign the short cuts to the buttons
   */
  public void makeShortcuts() {
    KeyStroke exitShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
    exit.setAccelerator(exitShortcut);

    KeyStroke newBoardShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    newBoard.setAccelerator(newBoardShortcut);

    KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    save.setAccelerator(saveShortcut);

    KeyStroke activityShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
    boardActivity.setAccelerator(activityShortcut);

    KeyStroke stateShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
    showState.setAccelerator(stateShortcut);
  }
  /**
   * Add the column the business logic board
   */
  public void addColumn(Column inputColumn)
  {
    this.board.importColumn(inputColumn);
  }
  /**
   * Create a column to the board
   * @param inputColumn
   */
  public void makeColumn(Column inputColumn) {
    boolean check = true;
    if(columnsPanel.getComponents().length !=0 )
    {
      for(Component component : columnsPanel.getComponents())
      {
        DropPane dp = (DropPane) component;
        if(dp.getComponents().length ==0)// if the dp is empty
        {
          addColumn(inputColumn);
          JPanel columnPanel = new Column_GUI(inputColumn);
          columnPanel.setVisible(true);
          dp.add(columnPanel);
          columnsPanel.add(dp);
          columnPanel.repaint();
          columnPanel.revalidate();
          this.repaint();
          this.revalidate();
          SwingUtilities.updateComponentTreeUI(this);
          check = false;
        }
        else{continue;}// if the dp is not empty
      }
    }
    if(check)
    {
      DropPane dp = new DropPane();
      dp.setPreferredSize(new Dimension(250,960));
      addColumn(inputColumn);
      JPanel columnPanel = new Column_GUI(inputColumn);
      columnPanel.setVisible(true);
      dp.add(columnPanel);
      columnPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
      columnsPanel.add(dp);
      columnPanel.repaint();
      columnPanel.revalidate();
      this.repaint();
      this.revalidate();
      SwingUtilities.updateComponentTreeUI(this);
      check = true;
    }
    else{check = true; }
  }
  /**
   * Get the business logic board
   * @return The business logic board
   */
  public Board getBoard(){
    return board;
  }
  /**
   * Remove the column from the business logic board
   * @param column to be removed
   */
  public void removeCol(Column column){ board.removeColumn(column);}
}
