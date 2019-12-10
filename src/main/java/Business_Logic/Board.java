package Business_Logic;
import Business_Logic.Card;
import Business_Logic.Column;
import java.util.ArrayList;

public class Board {
    private String name;
    private ArrayList<Column> columns;

    public Board(String inputName){
        columns = new ArrayList<>();
        name = inputName;
    }

    public Column makeColumn(String name, int roleNum){
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
        return newCol;
    }

    public void removeColumn(Column columnToRemove){
        columns.remove(columnToRemove);
    }

    public void importColumn(Column importedCol){
        columns.add(importedCol);
    }
    

    public ArrayList<Column> getColumns(){
        return columns;
    }

    public java.lang.String getName() {
        return name;
    }
}