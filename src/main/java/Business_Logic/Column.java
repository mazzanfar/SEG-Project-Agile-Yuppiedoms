package Business_Logic;
import Business_Logic.Card;

import java.io.Serializable;
import java.util.ArrayList;
public class Column implements Serializable {

    private String name;
    private int role; // 0 - Backlog    1 - In progress    2 - Completed
    private ArrayList<Card> cards;

    public Column(String inputName, int roleNum){
        cards = new ArrayList<Card>();
        name = inputName;
        role = roleNum;

    }

    public void setName(String newName){
        name = newName;
    }

    public void setRole(int roleNum){
        role = roleNum;
    }

    public void addCard(Card theCard){
        cards.add(theCard);
    }

    public void removeCard(Card theCard){
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
    }
}