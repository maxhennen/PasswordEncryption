package sample.Controllers;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Logic.User;
import sample.ViewModel.UserUIRepo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by maxhe on 14-6-2017.
 */
public class LoginController extends Controller {

    public LoginController(User user){
        super(user);
    }

    private TextField tfLoginName;
    private PasswordField tfLoginPassword;
    private Button btnLogin;
    private Button btnRegister;
    private UserUIRepo UserRepo;

    public void setLayout(){
        tfLoginName = new TextField();
        tfLoginName.setLayoutX(50);
        tfLoginName.setLayoutY(50);
        tfLoginName.setPrefHeight(27);
        tfLoginName.setPrefWidth(200);
        tfLoginName.setPromptText("Email");
        getAnchorpane().getChildren().add(tfLoginName);

        tfLoginPassword = new PasswordField();
        tfLoginPassword.setLayoutY(90);
        tfLoginPassword.setLayoutX(50);
        tfLoginPassword.setPrefWidth(200);
        tfLoginPassword.setPrefHeight(27);
        tfLoginPassword.setPromptText("Password");
        getAnchorpane().getChildren().add(tfLoginPassword);

        btnLogin = new Button();
        btnLogin.setLayoutX(50);
        btnLogin.setLayoutY(130);
        btnLogin.setPrefWidth(200);
        btnLogin.setPrefHeight(27);
        btnLogin.setText("Login");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login(event);
            }
        });
        getAnchorpane().getChildren().add(btnLogin);

        btnRegister = new Button();
        btnRegister.setLayoutY(10);
        btnRegister.setLayoutX(10);
        btnRegister.setPrefHeight(27);
        btnRegister.setPrefWidth(200);
        btnRegister.setText("Register");
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newScene("Register.fxml", new RegisterController(getUser()));
                closeScene(event);
            }
        });
        getAnchorpane().getChildren().add(btnRegister);
    }

    public void Login(ActionEvent event){
        UserRepo = new UserUIRepo(new User());

        if(UserRepo.Login(tfLoginName.getText(),tfLoginPassword.getText()) != null){
            setUser(UserRepo.Login(tfLoginName.getText(),tfLoginPassword.getText()));
            newScene("Home.fxml", new HomeController(getUser()));
            closeScene(event);
        }
        else {
            JOptionPane.showMessageDialog(null,"Login data is not correct!");
        }
    }
}


