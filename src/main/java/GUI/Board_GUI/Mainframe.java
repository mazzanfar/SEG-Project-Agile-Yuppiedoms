package GUI.Board_GUI;

import java.io.BufferedReader;
import Business_Logic.*;
import GUI.Column_GUI.Column_GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class Mainframe {

  private JFileChooser jfc;

  private static final int WIDTH = 618;
  private static final int HEIGHT = 250;
  private static final Font font1 = new Font("Sans-Serif", Font.BOLD, 15);

  private JFrame mainFrame;

  private JPanel contentPane;

  private JLabel splashTitle;
  private JButton newKanbanBoard;
  private JButton loadKanbanBoard;

  public Mainframe() {
    jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

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


  /*
  Inputs for this method will be csv data of some form
  This method will call methods which will create a board, an array of columns and an array of cards
  */
  public Board extractFromCSV(String fileName) throws IOException{
    Path path = Paths.get(fileName);
    System.out.println(path.toString());
    BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
    String line = br.readLine();
    Board board;
    ArrayList<Column> columnList = new ArrayList<>();
    
    
    // use string.split to load a string array with the values from
    // each line of
    // the file, using a comma as the delimiter
    String[] attributes = line.split(",");
    board = createBoard(attributes);
    line = br.readLine();
    
    

    while(line != null){ // reading card objects 
      line = br.readLine();// begin reading next section
      String[] ColAttributes = line.split(",");
      Column c = createColumn(ColAttributes);
      line = br.readLine();
      while(line != null && !line.equals("-")){
        String[] cardAttributes = line.split(",");
        Card card = createCard(cardAttributes);
        c.addCard(card);
        line = br.readLine();
      }
      board.importColumn(c);
    }
    return board;
  }

private Board createBoard(String[] metadata) {
  String name = metadata[0];
  return new Board(name);
}

private Column createColumn(String[] metadata) {
  String name = metadata[0];
  int role = Integer.parseInt(metadata[1]);
  return  new Column(name, role);
}

private Card createCard(String[] metadata) {
  String title = metadata[0];
  int Id = Integer.parseInt(metadata[1]);
  String description = metadata[2];
  String storyPoints = metadata[3];
  int IdCounter = Integer.parseInt(metadata[4]);
  
  return new Card(title, Id, description, storyPoints, IdCounter);
}

  public void addActions() {
    newKanbanBoard.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog("Input name of Kanban Board");
        if (input.length() > 0) {
          BoardGui board = new BoardGui(new Business_Logic.Board(input)) ;
        } else {
          JOptionPane.showMessageDialog(null, "Name required");
        }
      }
    });

    loadKanbanBoard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jfc.setDialogTitle("Select a CSV file to load");
        jfc.setAcceptAllFileFilterUsed(false);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
		    jfc.addChoosableFileFilter(filter);
        int ret = jfc.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
             File selectedFile = jfc.getSelectedFile();
             try{
              BoardGui board = new BoardGui(extractFromCSV(selectedFile.getPath()));
             }
             catch(IOException ioe){

             }
		    }
      }
    });
  }
}
