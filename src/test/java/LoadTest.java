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

public class LoadTest{

    private Mainframe mainFrame;
    private Swinger swinger;


    @Before
    public void setUp(){
        mainFrame = new Mainframe();
        swinger = Swinger.forSwingWindow();
        swinger.setRoot(mainFrame.getMainFrame());
    }

    @Test
    public void testLoad(){
        swinger.pause(250);
        swinger.clickOn("name:loadKanbanBoard")
               .type("First Board.csv")
               .pressSimultaneously(VK_ENTER)
               .pause(2500);

    }
    @After
    public void tearDown(){
        mainFrame.getMainFrame().dispose();
    }

}
