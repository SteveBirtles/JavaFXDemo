import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    private Connection connection = null;

    /* This method is the constructor. When a new DatabaseConnection object is created a connection
     * to the database is established using the filename and database drive. */
    public DatabaseConnection(String dbFile)
    {
        try             // There are many things that can go wrong in establishing a database connection...
        {         
            Class.forName("org.sqlite.JDBC");                               // ... a missing driver class ...
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile); // ... or an error with the file.
            System.out.println("Database connection successfully established.");
        } 
        catch (ClassNotFoundException cnfex)    // Catch any database driver error
        {
            System.out.println("Class not found exception: " + cnfex.getMessage());
        }
        catch (SQLException exception)          // Catch any database file errors.
        {                        
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    /* This method is used to prepare each new query. The query isn't executed until later. */
    public PreparedStatement newStatement(String query)
    {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        }
        catch (SQLException resultsexception) 
        {
            System.out.println("Database statement error: " + resultsexception.getMessage());
        }
        return statement;
    }

    /* This method is used to actually execute a query that has previously been prepared. */
    public ResultSet runQuery(PreparedStatement statement)
    {               
        try {            
            return statement.executeQuery();           
        }
        catch (SQLException queryexception) 
        {
            System.out.println("Database query error: " + queryexception.getMessage());
            return null;
        }
    }

    /* Finally, this method is called when the application is terminating to close the database connection. */
    public void disconnect()
    {
        System.out.println("Disconnecting from database.");
        try {
            if (connection != null) connection.close();                        
        } 
        catch (SQLException finalexception) 
        {
            System.out.println("Database disconnection error: " + finalexception.getMessage());
        }        
    }

}