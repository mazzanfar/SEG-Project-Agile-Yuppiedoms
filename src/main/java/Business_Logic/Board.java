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
    /**
     * Initialize the board with a input string
     * @param inputName
     */
    public Board(String inputName){
        log = new ArrayList<String>();
        columns = new ArrayList<>();
        name = inputName;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    /**
     * Remove a column.
     * @param columnToRemove
     */
    public void removeColumn(Column columnToRemove){
        date = new Date();
        String dateString = formatter.format(date);
        log.add("A column has been moved. " + "Name: " + columnToRemove.getName() + " " + dateString);
        columns.remove(columnToRemove);
    }
    /**
     * Add column to the board
     * @param importedCol
     */
    public void importColumn(Column importedCol){
        date = new Date();
        String dateString = formatter.format(date);
        log.add("A new column has been made. " + "Name: " + importedCol.getName() + " " + dateString);
        columns.add(importedCol);
    }
    /**
     * Get the ArrayList which stores the columns
     * @return ArrayList<Column>
     */
    public ArrayList<Column> getColumns(){
        return columns;
    }

    public Column getColumn(int index){
        return columns.get(index);    
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