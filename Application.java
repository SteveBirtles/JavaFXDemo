import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application
{

    public static DatabaseConnection database;

    public static void main(String args[])
    {       
        JFXPanel panel = new JFXPanel();               
        Platform.runLater(() -> start());
    }

    private static void start() 
    {
        try
        {
            database = new DatabaseConnection();

            FXMLLoader loader = new FXMLLoader(Application.class.getResource("DemoScene.fxml"));

            Stage stage = new Stage();
            stage.setTitle("JavaFX Demo");
            stage.setScene(new Scene(loader.load()));
            stage.show();           

            SceneController controller = loader.getController();
            controller.prepareStage(stage);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static void terminate()
    {
        System.out.println("Closing database connection and terminating application...");        
        if (database != null) database.disconnect();
        System.exit(0);           
    }

    
    
}