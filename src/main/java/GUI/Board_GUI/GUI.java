package GUI.Board_GUI;
import javax.swing.*;

public class GUI
{
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable() {
      public void run(){
          new Display();
      }
    });
  }
}
