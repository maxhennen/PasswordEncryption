package sample.Controllers;

import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Logic.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public abstract class Controller {
    private AnchorPane Anchorpane;
    private User User;

    public void setAnchorpane(AnchorPane anchorpane){Anchorpane = anchorpane;};
    public AnchorPane getAnchorpane(){return Anchorpane;}

    public void setUser(User user){User = user;}
    public User getUser(){return User;}

    public abstract void setLayout();

    public Controller(User user){
        User = user;
    }

    public void newScene(String file, Controller controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/" + file));
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


    public void closeScene(javafx.event.Event event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
