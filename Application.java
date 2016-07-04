import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Application
{

    public static void main(String args[])
    {       
        JFXPanel panel = new JFXPanel();        
        Platform.runLater(() -> start());               
    }

    private static void start() 
    {
        try
        {         

            Pane rootPane = new Pane();

            Stage stage = new Stage();
            stage.setTitle("JavaFX Dynamic Scene Demo");
            stage.setResizable(false);
            stage.setScene(new Scene(rootPane));                        
            stage.setWidth(1024);
            stage.setHeight(768);
            stage.setOnCloseRequest((WindowEvent we) -> Application.terminate());
            stage.show(); 

            Button btn = new Button();
            btn.setText("Hello");
            btn.setLayoutX(50);
            btn.setLayoutY(50);
            btn.setOnAction((ActionEvent ae) -> System.out.println("Hello World!"));

            rootPane.getChildren().add(btn);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            terminate();
        }
    }

    public static void terminate()
    {
        System.exit(0);
    }

}