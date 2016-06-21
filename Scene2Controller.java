import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Scene2Controller
{

    private Stage stage;

    @FXML   private Button clickMeButton;

    public Scene2Controller()
    {
        System.out.println("Initialising controllers...");
    } 

    public void prepareStage(Stage stage)
    {
        System.out.println("Preparing stage...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    stage.close();
                    Application.terminate();
                }
            });

    }   

    @FXML   void initialize() 
    {            
        System.out.println("Asserting controls...");
        assert clickMeButton != null : "Can't find 'click me' button.";
    }

    @FXML   void clickMeClicked()
    {
        System.out.println("I'm outta here!");        
        stage.close();
    }

}

