package Business_Logic;
import Business_Logic.Card;

import java.io.Serializable;
import java.util.ArrayList;
public class Column implements Serializable {

    private String name;
    private int role; // 0 - Backlog    1 - In progress    2 - Completed
    private ArrayList<Card> cards;
    private ArrayList<String> log;

    public Column(String inputName, int roleNum){
        cards = new ArrayList<Card>();
        name = inputName;
        role = roleNum;
        log = new ArrayList<>();
    }

    public void setName(String newName){
        if (!name.equals(newName))
        {
            log.add("Column title has been edited.");
            name = newName;
        }
        
        
    }

    public void setRole(int roleNum){
        if (!(role == roleNum))
        {
            log.add("Column role has been edited.");
        }
        role = roleNum;
        
    }

    public void addCard(Card theCard){
        log.add("A card has been added.");
        cards.add(theCard);

    }

    public void importCardList(ArrayList<Card> cardList){
        
        cards = cardList;
    }

    public void removeCard(Card theCard){
        log.add("A card has been removed.");
        cards.remove(theCard);
    }

    public Card getCard(int cardId){
        return cards.get(cardId);
    }

    public ArrayList<Card> getCards(){ return cards;}

    public String getName(){ return name; }

    public int getRole(){ return role; }

    public void moveCard(Card theCard, Column endingCol){
        this.removeCard(theCard);
        endingCol.addCard(theCard);
        log.add("A card has been moved");
    }

    public ArrayList<String> getActivity()
    {
        ArrayList<String> cardAndColumnActivity = new ArrayList<>();
        cardAndColumnActivity.addAll(log);
        for (Card card : cards)
        {
            cardAndColumnActivity.addAll(card.getActivity());
        }
        return cardAndColumnActivity;
    }
}