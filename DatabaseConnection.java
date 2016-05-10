import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    //    private final String address = "jdbc:mysql://172.16.0.127:3306/javademodatabase";
    //    private final String user = "javademouser";
    //    private final String password = "Java15Fun";

    private Connection conection = null;

    public DatabaseConnection()
    {
        try 
        {         
            //conection = DriverManager.getConnection(address, user, password);            

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:Test.db");

            System.out.println("Database connection successfully established.");
        } 
        catch (SQLException exception) 
        {                        
            System.out.println("Database connection error: " + exception.getMessage());
        }
        catch (ClassNotFoundException cnfex)
        {
            System.out.println("Class not found exception: " + cnfex.getMessage());
        }

    }

    public PreparedStatement newStatement(String query)
    {
        PreparedStatement statement = null;
        try {
            statement = conection.prepareStatement(query);
        }
        catch (SQLException resultsexception) 
        {
            System.out.println("Database statement error: " + resultsexception.getMessage());
        }
        return statement;
    }

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

    public void disconnect()
    {
        System.out.println("Disconnecting from database.");
        try {
            if (conection != null) conection.close();                        
        } 
        catch (SQLException finalexception) 
        {
            System.out.println("Database disconnection error: " + finalexception.getMessage());
        }        
    }

}