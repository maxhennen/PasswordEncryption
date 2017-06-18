package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    private AnchorPane Anchorpane;
    public void setAnchorpane(AnchorPane anchorpane){Anchorpane = anchorpane;};
    public AnchorPane getAnchorpane(){return Anchorpane;}
    public abstract void setLayout();

    public void newScene(Controller controller, String file){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Register.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            controller.setAnchorpane(root);
            controller.setLayout();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
