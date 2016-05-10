import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class SceneController
{

    private static Stage stage;

    @FXML   private Pane backgroundPane;    
    @FXML   private Button yesButton;
    @FXML   private Button noButton;
    @FXML   private Button exitButton;
    @FXML   private ListView listView;

    public SceneController()
    {
        System.out.println("Initialising controllers...");

        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

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

        @SuppressWarnings("unchecked")
        List<Fruit> targetList = listView.getItems();        
        Fruit.loadAll(targetList);

    }   

    @FXML   void initialize() 
    {            
        System.out.println("Asserting controls...");

        assert backgroundPane != null : "Can't find background pane.";
        assert yesButton != null : "Can't find yes button.";
        assert noButton != null : "Can't find yes button.";
        assert exitButton != null : "Can't find exit button.";
        assert listView != null : "Can't find list box.";
    }

    @FXML   void yesClicked()
    {
        System.out.println("Yes was clicked!");        
    }

    @FXML   void noClicked()
    {
        System.out.println("No was clicked!");
    }

    @FXML   void exitClicked()
    {
        System.out.println("Exit was clicked!");        
        stage.close();
        Application.terminate(); 
    }

    @FXML   void listViewClicked()
    {
        Fruit selectedItem = (Fruit) listView.getSelectionModel().getSelectedItem();

        if (selectedItem == null)
        {
            System.out.println("Nothing selected!");
        }
        else
        {
            System.out.println(selectedItem + " (id: " + selectedItem.id + ") is selected.");
        }
    }    

}

