import java.util.ArrayList;
public class Board {
    private String name;
    private ArrayList<Column> columns;

    public Board(String inputName){
        name = inputName;
    }

    public void makeColumn(String name){
        Column newCol = new Column(name);
        columns.add(newCol);
        /**
         * TO DO:
         *      - Add extra parameter to set the column's 'role' once we've figured out what roles are
         */
    }

    public void removeColumn(Column columnToRemove){
        columns.remove(columnToRemove);
    }

    public void moveCard(Column startingCol, Column endingCol){
        /**
         * TO DO:
         *      - Implement code for moving a card from one column to another
         */
    }

}
