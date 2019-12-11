package Business_Logic;
import Business_Logic.Card;
import Business_Logic.Column;
import java.util.ArrayList;

public class Board {
    private String name;
    private ArrayList<Column> columns;
    private ArrayList<String> log;

    public Board(String inputName){
        log = new ArrayList<String>();
        columns = new ArrayList<>();
        name = inputName;
    }

    public Column makeColumn(String name, int roleNum){
        
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
        return newCol;
        
    }

    public void removeColumn(Column columnToRemove){
        log.add("A column has been moved");
        columns.remove(columnToRemove);
    }

    public void importColumn(Column importedCol){
        log.add("A column has been made.");
        columns.add(importedCol);
    }

    public ArrayList<Column> getColumns(){
        return columns;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getActivity()
    {
        ArrayList<String> activities = new ArrayList<>();
        activities.addAll(log);
        for (Column column : columns)
        {
            activities.addAll(column.getActivity());
        }
        return activities;
    }
}