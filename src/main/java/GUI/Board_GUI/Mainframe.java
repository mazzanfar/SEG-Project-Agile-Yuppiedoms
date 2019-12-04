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
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

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

  */
  public void extractFromCSV(){
    //BufferedReader br =                   We need a buffered reader for the file
    //String line = br.readLine();

    //Board newBoard = new Board();
    while (line != null) {

            // use string.split to load a string array with the values from
            // each line of
            // the file, using a comma as the delimiter
            String[] attributes = line.split(",");

            Board board = createBoard(attributes);

            // adding book into ArrayList
            //books.add(book);

            // read next line before looping
            // if end of file reached, line would be null
            //line = br.readLine();
        }
  }

  private Board createBoard(String[] metadata) {
  String name = metadata[0];
  String[] cIDs = metadata[1].split("-");
  
  return new Board(name, cIDS);
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

    loadKanbanBoard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jfc.setDialogTitle("Select a CSV file to load");
        jfc.setAcceptAllFileFilterUsed(false);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		    jfc.addChoosableFileFilter(filter);

        int ret = jfc.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
			       File selectedFile = jfc.getSelectedFile();
			       System.out.println(selectedFile.getAbsolutePath());
		    }
      }
    });
  }
}
