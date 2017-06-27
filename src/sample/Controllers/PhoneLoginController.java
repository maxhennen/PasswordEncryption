package sample.Controllers;

import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Logic.User;
import sample.ViewModel.UserUIRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 * Created by maxhe on 26-6-2017.
 */
public class PhoneLoginController extends Controller {

    public PhoneLoginController(User user){
        super(user);
    }

    private TextField tfInput;
    private Button btnConfirm;
    private Label lblAgain;

    private UserUIRepo UserRepo;
    private String validationCode;

    public void setLayout(){
        tfInput = new TextField();
        tfInput.setLayoutX(50);
        tfInput.setLayoutY(50);
        tfInput.setPrefHeight(27);
        tfInput.setPrefWidth(200);
        getAnchorpane().getChildren().add(tfInput);

        btnConfirm = new Button();
        btnConfirm.setLayoutY(90);
        btnConfirm.setLayoutX(50);
        btnConfirm.setPrefWidth(200);
        btnConfirm.setPrefHeight(27);
        btnConfirm.setText("Confirm validation");
        btnConfirm.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                checkSMS(event);
            }
        });
        getAnchorpane().getChildren().add(btnConfirm);

        lblAgain = new Label();
        lblAgain.setLayoutY(130);
        lblAgain.setLayoutX(50);
        lblAgain.setPrefWidth(200);
        lblAgain.setPrefHeight(27);
        lblAgain.setText("Send sms again");
        lblAgain.setStyle("-fx-underline: true;");
        lblAgain.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                sendSMS();
            }
        });
        getAnchorpane().getChildren().add(lblAgain);
        sendSMS();
    }

    public void checkSMS(javafx.event.Event event){
        if (tfInput.getText().equals(validationCode)) {
            newScene("Home.fxml",new HomeController(getUser()));
            closeScene(event);
        }
        else {
            JOptionPane.showMessageDialog(null,"Wrong validation code filled in!");
        }
    }

    public void sendSMS(){
        UserRepo = new UserUIRepo(new User());
        validationCode = UserRepo.SendSMS(getUser().getPhoneNumber());
    }
}
