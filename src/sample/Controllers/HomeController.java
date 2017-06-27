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

    private Label lblLogout;
    private Button btnAddPassword;
    private TextField tfAddContent;
    private PasswordField tfAddPassword;
    private PasswordField tfAddPasswordConfirm;
    private Label lblWelkom;
    private PasswordUIRepo PasswordRepo;

    private int Y = 100;

    public HomeController(User user){
        super(user);
    }

    public void setLayout(){
        lblLogout = new Label();
        lblLogout.setLayoutY(10);
        lblLogout.setLayoutX(10);
        lblLogout.setPrefHeight(27);
        lblLogout.setPrefWidth(100);
        lblLogout.setStyle("-fx-underline: true;");
        lblLogout.setText("Log out");
        lblLogout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newScene("Login.fxml",new LoginController(null));
                closeScene(event);
            }
        });
        getAnchorpane().getChildren().add(lblLogout);

        lblWelkom = new Label();
        lblWelkom.setLayoutX(130);
        lblWelkom.setLayoutY(10);
        lblWelkom.setPrefHeight(27);
        lblWelkom.setPrefWidth(100);
        lblWelkom.setText("Welkom " + getUser().getName());
        getAnchorpane().getChildren().add(lblWelkom);

        tfAddContent = new TextField();
        tfAddContent.setLayoutX(50);
        tfAddContent.setLayoutY(50);
        tfAddContent.setPrefHeight(27);
        tfAddContent.setPrefWidth(200);
        tfAddContent.setPromptText("Content");
        getAnchorpane().getChildren().add(tfAddContent);

        tfAddPassword = new PasswordField();
        tfAddPassword.setLayoutX(270);
        tfAddPassword.setLayoutY(50);
        tfAddPassword.setPrefWidth(200);
        tfAddPassword.setPrefHeight(27);
        tfAddPassword.setPromptText("Password");
        getAnchorpane().getChildren().add(tfAddPassword);

        tfAddPasswordConfirm = new PasswordField();
        tfAddPasswordConfirm.setLayoutX(490);
        tfAddPasswordConfirm.setLayoutY(50);
        tfAddPasswordConfirm.setPrefWidth(200);
        tfAddPasswordConfirm.setPrefHeight(27);
        tfAddPasswordConfirm.setPromptText("Confirm Password");
        getAnchorpane().getChildren().add(tfAddPasswordConfirm);

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

        PasswordRepo = new PasswordUIRepo(new Password());


        for (Password p:PasswordRepo.passwordsUser(getUser())){
            TextField tfContent = new TextField();
            tfContent.setLayoutY(Y);
            tfContent.setLayoutX(50);
            tfContent.setPrefWidth(200);
            tfContent.setPrefHeight(27);
            tfContent.setText(p.getContent());
            getAnchorpane().getChildren().add(tfContent);

            PasswordField pfPassword = new PasswordField();
            pfPassword.setLayoutY(Y);
            pfPassword.setLayoutX(270);
            pfPassword.setPrefHeight(27);
            pfPassword.setPrefWidth(200);
            pfPassword.setText(p.getPassword());
            getAnchorpane().getChildren().add(pfPassword);

            PasswordField pfPasswordConfirm = new PasswordField();
            pfPasswordConfirm.setLayoutX(490);
            pfPasswordConfirm.setLayoutY(Y);
            pfPasswordConfirm.setPrefWidth(200);
            pfPasswordConfirm.setPrefHeight(27);
            pfPasswordConfirm.setText(p.getPassword());
            getAnchorpane().getChildren().add(pfPasswordConfirm);

            Button btnChange = new Button();
            btnChange.setLayoutY(Y);
            btnChange.setLayoutX(710);
            btnChange.setPrefWidth(200);
            btnChange.setPrefHeight(27);
            btnChange.setText("Change");
            getAnchorpane().getChildren().add(btnChange);

            Button btnDelete = new Button();
            btnDelete.setLayoutX(930);
            btnDelete.setLayoutY(Y);
            btnDelete.setPrefHeight(27);
            btnDelete.setPrefWidth(200);
            btnDelete.setText("Delete");
            getAnchorpane().getChildren().add(btnDelete);

            Y = Y + 40;
        }
    }

    public void newPassword(){
        try {
            if (tfAddPassword.getText().equals(tfAddPasswordConfirm.getText())) {
                PasswordRepo = new PasswordUIRepo(new Password());
                PasswordRepo.newPassword(getUser(),tfAddContent.getText(),tfAddPassword.getText());
                JOptionPane.showMessageDialog(null,"Password has been added!");
                setLayout();
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
