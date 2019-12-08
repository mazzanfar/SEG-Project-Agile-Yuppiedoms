package Business_Logic;
import Business_Logic.Card;
import Business_Logic.Column;
import java.util.ArrayList;
public class Board {

    private String name;
    private ArrayList<int> column_IDs;

    public Board(String inputName){
        columns = new ArrayList<>();
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

    public void makeColumn(String name, int roleNum){
=======
    public Column makeColumn(String name, int roleNum){
>>>>>>> bc214000ca189af2c2f621247854ee2efe13c3ec
        Column newCol = new Column(name, roleNum);
        columns.add(newCol);
        return newCol;
    }

    public void removeColumn(Column columnToRemove){
        columns.remove(columnToRemove);
    }

    public java.lang.String getName() {
        return name;
    }
}
