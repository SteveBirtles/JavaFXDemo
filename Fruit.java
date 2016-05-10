import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.List;

public class Fruit
{
    public int id;
    public String type;
    public String colour;

    public Fruit(int id, String type, String colour)
    {
        this.id = id;
        this.type = type;
        this.colour = colour;
    }

    @Override
    public String toString()
    {
        return (colour + " " + type);
    }

    public static void loadAll(List<Fruit> list)
    {

        DatabaseConnection database = Application.database;

        list.clear();

        PreparedStatement statement = database.newStatement("SELECT id, fruit, colour FROM demotable"); 

        if (statement != null) 
        {
            ResultSet results = database.runQuery(statement);

            if (results != null)
            {
                try {
                    while (results.next()) {

                        list.add( new Fruit(
                                results.getInt("id"),
                                results.getString("fruit"),
                                results.getString("colour")
                            )
                        );
                    }
                }
                catch (SQLException resultsexception) 
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

}