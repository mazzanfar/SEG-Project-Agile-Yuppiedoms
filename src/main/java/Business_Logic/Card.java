package Business_Logic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Card implements Serializable {
    private String title;
    private String description;
    private String sp;
    private static int IDcounter = 1;
    public final int Id;
    private ArrayList<String> log;
    private Date date;
    private SimpleDateFormat formatter;

    /**
     * Constructor of card business logic which initialises the activity log for the card aswell.
     * @param inputTitle
     * @param inputDescription
     * @param inputSP
     */
    public Card(String inputTitle, String inputDescription, String inputSP)
    {
        this.Id = IDcounter++;
        title = inputTitle;
        description = inputDescription;
        sp = inputSP;
        log = new ArrayList<>();
    }

    /**
     * gets the activity log for each card. 
     * @return Activity log
     */
    public ArrayList<String> getActivity()
    {
        return log;
    }

    /*
    Overrided constructor to allow for loading a card, the ID doesn't get incremented with this one
    */
    public Card(String inputTitle, int Id, String inputDescription, String inputSP, int IdC)
    {
        this.Id = Id;
        title = inputTitle;
        description = inputDescription;
        sp = inputSP;
        IDcounter = IdC;
        log = new ArrayList<>();
    }
    
    public void setTitle(String inputTitle)
    {
        if (!title.equals(inputTitle))
        {
            log.add("Card title has been edited. Card ID:" + Integer.toString(Id));
            title = inputTitle;
        }
            
    }

    public void setDescription(String inputDescription)
    {
        
        if (!description.equals(inputDescription))
        {
            log.add("Card description has been edited. Card ID:" + Integer.toString(Id));
            description = inputDescription;
        }
    } 

    public void setStoryPoints(String inputSP)
    {
        if (!sp.equals(inputSP))
        {
            log.add("Story Points have been edited. Card ID:" + Integer.toString(Id));
            sp = inputSP;
        }
    }

    public void setIdCounter(int newId){
        IDcounter = newId;
    }

    public int getIdCounter(){
        return IDcounter;
    }

    

    public String getTitle()
    {
        return title;
    }

    public int getID()
    {
        return Id;
    }

    public String getDescription()
    {
       return description;
    }

    public String getStoryPoints()
    {
        return sp;
    }

    // public static void main(String[]args)
    // {
    //     Card name1 = new Card("","","");
    //     Card name2 = new Card("","","");
    //     Card name3 = new Card("","","");
    //     System.out.println(name1.getID());
    //     System.out.println(name2.getID());
    //     System.out.println(name3.getID());

    // }
}