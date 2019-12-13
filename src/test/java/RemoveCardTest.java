import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GUI.Board_GUI.Mainframe;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.athaydes.automaton.Swinger;
import static java.awt.event.KeyEvent.*;

public class RemoveCardTest{
    
    private Mainframe mainFrame;
    private Swinger swinger;

    
    @Before
    public void setUp(){
        mainFrame = new Mainframe();
        swinger = Swinger.forSwingWindow();
        swinger.setRoot(mainFrame.getMainFrame());
    }
      
    @Test
    public void testRemoveCard(){
        swinger.pause(250);
        swinger.clickOn("name:newKanbanBoard")
               .pause(250)
               .type("First Board")
               .pause(250)
               .pressSimultaneously(VK_ENTER)
               .pause(250);
        
        swinger.pause(250);
        JTextField boardTitleField = (JTextField)swinger.getUserWith(mainFrame.getBoardGUI())
                                                        .getAt("name:boardTitle");
        swinger.getUserWith(mainFrame.getBoardGUI())
               .clickOn("name:newColumn")
               .pause(250)
               .type("first Column")
               .pressSimultaneously(VK_ENTER)
               .pause(250)
               .pressSimultaneously(VK_ENTER)
               .pause(2500);
        assertThat(boardTitleField.getText(), equalTo("First Board"));
        swinger.getUserWith(mainFrame.getBoardGUI())
               .pause(250)
               .clickOn("name:addCardButton")
               .pause(250);
        swinger.getUserWith(mainFrame.getBoardGUI())
               .pause(250)
               .clickOn("name:removeCardButton")
               .pause(250);
        assertThat(mainFrame.getBoardGUI().getBoard().getColumn(0).getCards().size(), equalTo(0));
    }
    @After 
    public void tearDown(){
        mainFrame.getMainFrame().dispose();
    }
    
} 