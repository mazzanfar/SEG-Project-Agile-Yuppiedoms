package Business_Logic;

import Business_Logic.Card;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Column implements Serializable {

    private String name;
    private int role; // 0 - Backlog    1 - In progress    2 - Completed
    private ArrayList<Card> cards;
    private ArrayList<String> log;
    private Date date;
    private SimpleDateFormat formatter;

    public Column(String inputName, int roleNum){
        cards = new ArrayList<Card>();
        name = inputName;
        role = roleNum;
        log = new ArrayList<>();
        date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
    }

    public void setName(String newName){
        if (!name.equals(newName))
        {
            String dateString = formatter.format(date);
            log.add("Column title has been edited." + dateString);
            name = newName;
        }
        
        
    }

    public void setRole(int roleNum){
        String dateString = formatter.format(date);
        if (!(role == roleNum))
        {
            log.add("Column role has been edited." + dateString);
        }
        role = roleNum;
        
    }

    public void addCard(Card theCard){
        String dateString = formatter.format(date);
        log.add("A card has been added. Card ID:" + Integer.toString(theCard.getID()) + dateString);
        cards.add(theCard);
    }

    public void importCardList(ArrayList<Card> cardList){
        
        cards = cardList;
    }

    public void removeCard(Card theCard){
        String dateString = formatter.format(date);
        log.add("A card has been removed. Card ID:" + Integer.toString(theCard.getID()) + dateString);
        cards.remove(theCard);
    }

    public Card getCard(int cardId){
        return cards.get(cardId);
    }

    public ArrayList<Card> getCards(){ return cards;}

    public String getName(){ return name; }

    public int getRole(){ return role; }

    public void moveCard(Card theCard, Column endingCol)
    {
        this.removeCard(theCard);
        endingCol.addCard(theCard);
        String dateString = formatter.format(date);
        log.add("A card has been moved. Card ID:" + Integer.toString(theCard.getID()) + "Moved from: " + this.name + "Moved to: " + endingCol.name + dateString);
    }

    public ArrayList<String> getActivity()
    {
        ArrayList<String> cardAndColumnActivity = new ArrayList<>();
        try
        {
            cardAndColumnActivity.addAll(log);
            for (Card card : cards)
            {
                cardAndColumnActivity.addAll(card.getActivity());
            }
        }
        catch (NullPointerException e)
        { }
        return cardAndColumnActivity;
    }
}