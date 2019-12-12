package Business_Logic;

import Business_Logic.Card;
import Business_Logic.Column;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Board {
    private String name;
    private ArrayList<Column> columns;
    private ArrayList<String> log;
    private Date date;
    private SimpleDateFormat formatter;

    public Board(String inputName){
        log = new ArrayList<String>();
        columns = new ArrayList<>();
        name = inputName;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Column makeColumn(String name, int roleNum){
        date = new Date();
        String dateString = formatter.format(date);
        log.add("A new column has been made. " + "Name: " + name + dateString);
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
        return newCol;
        
    }

    public void removeColumn(Column columnToRemove){
        date = new Date();
        String dateString = formatter.format(date);
        log.add("A column has been moved. " + "Name: " + columnToRemove.getName() + dateString);
        columns.remove(columnToRemove);
    }

    public void importColumn(Column importedCol){
        date = new Date();
        String dateString = formatter.format(date);
        log.add("A new column has been made. " + "Name: " + importedCol.getName() + dateString);
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