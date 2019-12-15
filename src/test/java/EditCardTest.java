import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GUI.Board_GUI.Mainframe;
import GUI.Card_GUI.CardGui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.athaydes.automaton.Swinger;
import static java.awt.event.KeyEvent.*;

public class EditCardTest{

    private Mainframe mainFrame;
    private Swinger swinger;


    @Before
    public void setUp(){
        mainFrame = new Mainframe();
        swinger = Swinger.forSwingWindow();
        swinger.setRoot(mainFrame.getMainFrame());
    }

    @Test
    public void testEditCard(){
        swinger.pause(250);
        swinger.clickOn("name:newKanbanBoard")
               .pause(250)
               .type("First Board")
               .pause(250)
               .pressSimultaneously(VK_ENTER)
               .pause(250);
        Swinger.getUserWith(mainFrame.getBoardGUI())
               .clickOn("name:newColumn")
               .pause(250)
               .type("first Column")
               .pressSimultaneously(VK_ENTER)
               .pause(250)
               .pressSimultaneously(VK_ENTER)
               .pause(250)
               .clickOn("name:addCardButton")
               .pause(250);
        Swinger.getUserWith(mainFrame.getBoardGUI())
               .clickOn("name:editButton")
               .pause(2500);
        // Swinger swinger2 = Swinger.forSwingWindow();
        // swinger2.setRoot(swinger.getAt("name:popUpFrame"));
        //        //((JFrame)swinger.getAt("name:popUpFrame"))
        // swinger2.clickOn("name:changeButton")
        //         .pause(250);
        // swinger.pause(250);
    }
    @After
    public void tearDown(){
        mainFrame.getMainFrame().dispose();
    }

}
