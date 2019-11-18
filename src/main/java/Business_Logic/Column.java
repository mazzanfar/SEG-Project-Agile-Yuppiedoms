import java.util.ArrayList;
public class Column {

    private String name;
    private int role; // 0 - Backlog    1 - In progress    2 - Completed
    private ArrayList<Card> cards;

    public Column(String inputName, int roleNum){
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


}
