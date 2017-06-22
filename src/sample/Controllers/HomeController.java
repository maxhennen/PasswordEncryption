package sample.Controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Exceptions.PasswordIncorrectException;
import sample.Logic.Password;
import sample.Logic.User;
import sample.Repos.UserRepository;
import sample.ViewModel.PasswordUIRepo;
import sample.ViewModel.UserUIRepo;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by maxhe on 19-6-2017.
 */
public class HomeController extends Controller {

    private Label lblUitloggen;
    private Button btnAddPassword;
    private TextField tfContent;
    private PasswordField tfPassword;
    private PasswordField tfPasswordConfirm;
    private Label lblWelkom;
    private PasswordUIRepo PasswordRepo;

    private int X = 0;
    private int Y = 70;

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


        //foreach

        tfContent = new TextField();
        tfContent.setLayoutX(50);
        tfContent.setLayoutY(50);
        tfContent.setPrefHeight(27);
        tfContent.setPrefWidth(200);
        tfContent.setPromptText("Content");
        getAnchorpane().getChildren().add(tfContent);

        tfPassword = new PasswordField();
        tfPassword.setLayoutX(270);
        tfPassword.setLayoutY(50);
        tfPassword.setPrefWidth(200);
        tfPassword.setPrefHeight(27);
        tfPassword.setPromptText("Password");
        getAnchorpane().getChildren().add(tfPassword);

        tfPasswordConfirm = new PasswordField();
        tfPasswordConfirm.setLayoutX(490);
        tfPasswordConfirm.setLayoutY(50);
        tfPasswordConfirm.setPrefWidth(200);
        tfPasswordConfirm.setPrefHeight(27);
        tfPasswordConfirm.setPromptText("Confirm Password");
        getAnchorpane().getChildren().add(tfPasswordConfirm);

        btnAddPassword = new Button();
        btnAddPassword.setLayoutX(710);
        btnAddPassword.setLayoutY(50);
        btnAddPassword.setPrefHeight(27);
        btnAddPassword.setPrefWidth(200);
        btnAddPassword.setText("Add Password");
        btnAddPassword.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                newPassword();
            }
        });
        getAnchorpane().getChildren().add(btnAddPassword);
    }

    public int calculateY(){
        return Y = Y + 40;
    }

    public void newPassword(){
        try {
            if (tfPassword.getText().equals(tfPasswordConfirm.getText())) {
                PasswordRepo = new PasswordUIRepo(new Password());
                PasswordRepo.newPassword(getUser(),tfContent.getText(),tfPassword.getText());
            }
            else {
                throw new PasswordIncorrectException();
            }
        }
        catch (PasswordIncorrectException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
}
