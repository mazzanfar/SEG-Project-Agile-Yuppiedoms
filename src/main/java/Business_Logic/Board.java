import java.util.ArrayList;
public class Board {
    private String name;
    private ArrayList<Column> columns;

    public Board(String inputName){
        name = inputName;
    }

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
