package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import sample.DomainClasses.User;
import sample.ViewModel.UserUIRepo;

import javax.swing.*;


/**
 * Created by maxhe on 16-6-2017.
 */
public class RegisterController extends Controller {

    private TextField tfName;
    private TextField tfEmail;
    private PasswordField tfPassword;
    private PasswordField tfPasswordConfirm;
    private Button btnRegister;
    private UserUIRepo UserRepo;

    public void setLayout(){
        tfName = new TextField();
        tfName.setLayoutX(50);
        tfName.setLayoutY(50);
        tfName.setPrefHeight(27);
        tfName.setPrefWidth(200);
        tfName.setPromptText("First + Last Name");
        getAnchorpane().getChildren().add(tfName);

        tfEmail = new TextField();
        tfEmail.setLayoutY(90);
        tfEmail.setLayoutX(50);
        tfEmail.setPrefWidth(200);
        tfEmail.setPrefHeight(27);
        tfEmail.setPromptText("E-mail adress");
        getAnchorpane().getChildren().add(tfEmail);

        tfPassword = new PasswordField();
        tfPassword.setLayoutX(50);
        tfPassword.setLayoutY(130);
        tfPassword.setPrefHeight(27);
        tfPassword.setPrefWidth(200);
        tfPassword.setPromptText("Password");
        getAnchorpane().getChildren().add(tfPassword);

        tfPasswordConfirm = new PasswordField();
        tfPasswordConfirm.setLayoutY(170);
        tfPasswordConfirm.setLayoutX(50);
        tfPasswordConfirm.setPrefWidth(200);
        tfPasswordConfirm.setPrefHeight(27);
        tfPasswordConfirm.setPromptText("Confirm Password");
        getAnchorpane().getChildren().add(tfPasswordConfirm);

        btnRegister = new Button();
        btnRegister.setLayoutX(50);
        btnRegister.setLayoutY(210);
        btnRegister.setPrefWidth(100);
        btnRegister.setPrefHeight(27);
        btnRegister.setText("Register");
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newUser();
            }
        });
        getAnchorpane().getChildren().add(btnRegister);
    }

    public void newUser(){
        UserRepo = new UserUIRepo(new User());

        if(tfPassword.getText().equals(tfPasswordConfirm.getText())){
            UserRepo.newUser(tfName.getText(),tfEmail.getText(),tfPassword.getText());
        }
        else {
            JOptionPane.showMessageDialog(null,"The two password fields didn't match!");
        }
    }
}