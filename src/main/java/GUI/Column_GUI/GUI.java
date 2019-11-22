package GUI.Column_GUI;
import javax.swing.*;

public class GUI
{
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable() {
      public void run(){
          new Column_GUI();
      }
    });
  }
}
