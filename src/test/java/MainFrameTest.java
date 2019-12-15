import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GUI.Board_GUI.Mainframe;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.athaydes.automaton.Swinger;
import static java.awt.event.KeyEvent.*;

public class MainFrameTest{
    
    private Mainframe mainFrame;
    private Swinger swinger;

    
    @Before
    public void setUp(){
        mainFrame = new Mainframe();
        swinger = Swinger.forSwingWindow();
        swinger.setRoot(mainFrame.getMainFrame());
    }
    
    @Test
    public void testMainFrame(){
        swinger.pause(250);
        swinger.clickOn("name:newKanbanBoard")
               .pause(250)
               .type("First Board")
               .pause(250)
               .pressSimultaneously(VK_ENTER)
               .pause(250);
        JTextField boardTitleField = (JTextField) Swinger.getUserWith(mainFrame.getBoardGUI())
                                                        .getAt("name:boardTitle");
        
        Swinger.getUserWith(mainFrame.getBoardGUI())
               .pause(250);
        assertThat(boardTitleField.getText(), equalTo("First Board"));
        swinger.pause(250);
    }
    @After 
    public void tearDown(){
        mainFrame.getMainFrame().dispose();
    } 
    
}