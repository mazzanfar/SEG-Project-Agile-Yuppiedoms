//package main.java;

public class Card {
    private String title;
    private String description;
    private String sp;
    private static int IDcounter = 1;
    public final int Id;

    public Card(String inputTitle, String inputDescription, String inputSP)
    {
        this.Id = IDcounter++;
        title = inputTitle;
        description = inputDescription;
        sp = inputSP;
    }
     

    public void setDescription(String inputDescription)
    {
        description = inputDescription;
    } 

    public void setStoryPoints(String inputSP)
    {
        sp = inputSP;
    }

    public void setTitle(String inputTitle)
    {
        title = inputTitle;
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