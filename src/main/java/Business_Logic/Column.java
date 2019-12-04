package Business_Logic;
import Business_Logic.Card;
import java.util.ArrayList;
public class Column {

    private String name;
    private int role; // 0 - Backlog    1 - In progress    2 - Completed
    private ArrayList<Int> cardIDs;

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

    public void addCard(int cardID){
        cardIDs.add(cardID);
    }

    public void removeCard(int cardID){
        cardIDs.remove(cardID);
    }


}
