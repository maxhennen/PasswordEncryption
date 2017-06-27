package sample.Controllers;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private javafx.scene.control.Label lblRegister;
    private UserUIRepo UserRepo;
    private CheckBox cbExtraLogin;
    private javafx.scene.control.Label lblForgotPassword;

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

        lblRegister = new javafx.scene.control.Label();
        lblRegister.setLayoutY(10);
        lblRegister.setLayoutX(10);
        lblRegister.setPrefHeight(27);
        lblRegister.setPrefWidth(200);
        lblRegister.setText("Register");
        lblRegister.setStyle("-fx-underline: true;");
        lblRegister.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newScene("Register.fxml",new RegisterController(getUser()));
                closeScene(event);
            }
        });
        getAnchorpane().getChildren().add(lblRegister);

        cbExtraLogin = new CheckBox();
        cbExtraLogin.setLayoutX(50);
        cbExtraLogin.setLayoutY(170);
        cbExtraLogin.setPrefHeight(27);
        cbExtraLogin.setPrefWidth(200);
        cbExtraLogin.setText("Extra control by SMS");
        getAnchorpane().getChildren().add(cbExtraLogin);

        lblForgotPassword = new javafx.scene.control.Label();
        lblForgotPassword.setLayoutX(50);
        lblForgotPassword.setLayoutY(210);
        lblForgotPassword.setPrefHeight(27);
        lblForgotPassword.setPrefWidth(200);
        lblForgotPassword.setText("Forgot Password?");
        lblForgotPassword.setStyle("-fx-underline: true;");
        lblForgotPassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newScene("ForgotPassword.fxml",new ForgotPasswordController(null));
                closeScene(event);
            }
        });
        getAnchorpane().getChildren().add(lblForgotPassword);
    }

    public void Login(ActionEvent event){
        UserRepo = new UserUIRepo(new User());
        setUser(UserRepo.Login(tfLoginName.getText(),tfLoginPassword.getText()));

        if(getUser() != null){
            if(cbExtraLogin.isSelected()){
                newScene("PhoneLogin.fxml",new PhoneLoginController(getUser()));
            }
            else {
                newScene("Home.fxml",new HomeController(getUser()));
            }
        }

        else {
            JOptionPane.showMessageDialog(null,"Login data is not correct!");
        }
    }
}


