package Business_Logic;
import Business_Logic.Card;
import Business_Logic.Column;
import java.util.ArrayList;
public class Board {

    private String name;
    private ArrayList<int> column_IDs;

    public Board(String inputName){
        name = inputName;
    }
<<<<<<< HEAD

    public Board(String inputName, ArrayList<Int> CIDs){
        name = inputName;
        column_IDs = CIDs;
    }

    public void makeColumn(String name, int roleNum, ArrayList<int> ){
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
    }

=======
>>>>>>> 50dacd94bc33547ba12485f3bda5365dc19aa2c6
    public void makeColumn(String name, int roleNum){
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
    }

    public void removeColumn(Column columnToRemove){
        columns.remove(columnToRemove);
    }

    public void moveCard(Card theCard, Column startingCol, Column endingCol){
        startingCol.removeCard(theCard);
        endingCol.addCard(theCard);
    }

}
