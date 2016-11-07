import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

public class SceneController
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */
    @FXML   private Pane backgroundPane;    
    @FXML   private TableView<Fruit> mainTable;     // Our table control requires a data type, in this case Fruit.

    public SceneController()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        
        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {
            assert backgroundPane != null : "Can't find background pane.";          
            assert mainTable != null : "Can't find main table.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        /* Next, we load the list of fruit from the database and populate the listView. */
        System.out.println("Populating scene with items from the database...");        

        ObservableList<Fruit> fruitList = FXCollections.observableArrayList();  // Tables require a special type of list.
        Fruit.readAll(fruitList);             // Hand over control to the fruit model to populate this list.

        /* The first column is for the Fruit 'type' values */
        TableColumn<Fruit, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<Fruit, String>("type"));
        typeColumn.setMinWidth(150);
        mainTable.getColumns().add(typeColumn);

        /* The second column is for the Fruit 'colours' */
        TableColumn<Fruit, String> colourColumn = new TableColumn<>("Colour");
        colourColumn.setCellValueFactory(new PropertyValueFactory<Fruit, String>("colour"));
        colourColumn.setMinWidth(150);
        mainTable.getColumns().add(colourColumn);

        /* Finally, set the list to be displayed in the table. The columns are matched up automatically by JavaFX */
        mainTable.setItems(fruitList);

    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) we need
     * to setup event handlers. This happens after the constructor and the initialize methods. Once this is
     * complete, the scene is fully loaded and ready to use. */
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        SceneController.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    Application.terminate();
                }
            });
    }        

    @FXML   void tableClicked()
    {
        Fruit selectedFruit = mainTable.getSelectionModel().getSelectedItem();
        System.out.println("Table was clicked : " + selectedFruit.getId() + " " + selectedFruit.getColour() + " " + selectedFruit.getType());
    }

}

