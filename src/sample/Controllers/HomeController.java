package sample.Controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Logic.User;
import sample.Repos.UserRepository;
import sample.ViewModel.UserUIRepo;

import javax.jws.soap.SOAPBinding;
import java.awt.event.ActionEvent;

/**
 * Created by maxhe on 19-6-2017.
 */
public class HomeController extends Controller {

    private Label lblUitloggen;
    private Button btnPasswords;
    private Button btnAddPassword;
    private Label lblWelkom;
    private UserUIRepo UserRepo;

    public HomeController(User user){
        super(user);
    }

    public void setLayout(){
        lblUitloggen = new Label();
        lblUitloggen.setLayoutY(10);
        lblUitloggen.setLayoutX(10);
        lblUitloggen.setPrefHeight(27);
        lblUitloggen.setPrefWidth(100);
        lblUitloggen.setStyle("-fx-underline: true;");
        lblUitloggen.setText("Log out");
        lblUitloggen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newScene("Login.fxml",new LoginController(null));
                closeScene(event);
            }
        });
        getAnchorpane().getChildren().add(lblUitloggen);

        lblWelkom = new Label();
        lblWelkom.setLayoutX(130);
        lblWelkom.setLayoutY(10);
        lblWelkom.setPrefHeight(27);
        lblWelkom.setPrefWidth(100);
        lblWelkom.setText("Welkom " + getUser().getName());
        getAnchorpane().getChildren().add(lblWelkom);

        UserRepo = new UserUIRepo(new User());
    }
}
