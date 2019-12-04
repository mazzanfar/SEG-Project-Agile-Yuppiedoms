package GUI.Board_GUI;
import javax.swing.*;
import Business_Logic.*;
import GUI.Card_GUI.CardGui;

public class GUI
{
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable() {
      public void run(){
          new Mainframe();
      }
    });
  }
}
