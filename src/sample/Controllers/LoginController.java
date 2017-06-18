package sample.Controllers;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

/**
 * Created by maxhe on 14-6-2017.
 */
public class LoginController extends Controller {

    private TextField tfLoginName;
    private TextField tfLoginPassword;
    private Button btnLogin;
    private Button btnRegister;

    public void setLayout(){
        tfLoginName = new TextField();
        tfLoginName.setLayoutX(50);
        tfLoginName.setLayoutY(50);
        tfLoginName.setPrefHeight(27);
        tfLoginName.setPrefWidth(200);
        tfLoginName.setPromptText("Login Name");
        getAnchorpane().getChildren().add(tfLoginName);

        tfLoginPassword = new TextField();
        tfLoginPassword.setLayoutY(80);
        tfLoginPassword.setLayoutX(50);
        tfLoginPassword.setPrefWidth(200);
        tfLoginPassword.setPrefHeight(27);
        tfLoginPassword.setPromptText("Password");
        getAnchorpane().getChildren().add(tfLoginPassword);

        btnLogin = new Button();
        btnLogin.setLayoutX(50);
        btnLogin.setLayoutY(110);
        btnLogin.setPrefWidth(200);
        btnLogin.setPrefHeight(27);
        btnLogin.setText("Login");
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
                newScene(new RegisterController(),"Register.fxml");
            }
        });
        getAnchorpane().getChildren().add(btnRegister);
    }
}
