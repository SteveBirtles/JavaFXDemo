import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Fruit
{
    /* Firstly, our attributes. StringProperties are needed to use tables, instead of Strings. */
    private int id;
    private StringProperty type;        
    private StringProperty colour;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Fruit(int id, String type, String colour)
    {
        this.id = id;        
        setType(type);
        setColour(colour);
    }

    /* We require getter and setter nethods for the attributes. The String getters hide the StringProperty objects. */    
    public int getId() { return id; }
    
    public String getType() { return type.get(); }
    public void setType(String type) { this.type = new SimpleStringProperty(type); }    
    
    public String getColour() { return colour.get(); }
    public void setColour(String colour) { this.colour = new SimpleStringProperty(colour); }

    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Fruit> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT id, fruit, colour FROM demotable"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Fruit(results.getInt("id"), 
                                results.getString("fruit"), 
                                results.getString("colour")) );
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

}
