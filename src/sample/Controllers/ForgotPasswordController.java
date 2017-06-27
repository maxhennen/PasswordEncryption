package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Logic.User;
import sample.ViewModel.UserUIRepo;

/**
 * Created by maxhe on 27-6-2017.
 */
public class ForgotPasswordController extends Controller {

    public ForgotPasswordController(User user){
        super(user);
    }

    private TextField tfInput;
    private Button btnCheck;
    private Label lblAgain;

    private UserUIRepo UserRepo;

    public void setLayout(){
        tfInput = new TextField();
        tfInput.setLayoutY(50);
        tfInput.setLayoutX(50);
        tfInput.setPrefWidth(200);
        tfInput.setPrefHeight(27);
        tfInput.setPromptText("Email adress");
        getAnchorpane().getChildren().add(tfInput);

        btnCheck = new Button();
        btnCheck.setLayoutX(50);
        btnCheck.setLayoutY(90);
        btnCheck.setPrefHeight(27);
        btnCheck.setPrefWidth(200);
        btnCheck.setText("Set new Password");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sendNewPassword();
            }
        });
        getAnchorpane().getChildren().add(btnCheck);

        lblAgain = new Label();
        lblAgain.setLayoutX(50);
        lblAgain.setLayoutY(130);
        lblAgain.setPrefWidth(200);
        lblAgain.setPrefHeight(27);
        lblAgain.setText("Send again");
        getAnchorpane().getChildren().add(lblAgain);
    }

    public void sendNewPassword(){
        UserRepo = new UserUIRepo(new User());
        UserRepo.sendNewPassword(tfInput.getText());
    }
}
