package sample.Controllers;

import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

/**
 * Created by maxhe on 19-6-2017.
 */
public class HomeController extends Controller {

    private Button btnUitloggen;
    private Button btnPasswords;
    private Button btnAddPassword;

    public void setLayout(){
        btnUitloggen = new Button();
        btnUitloggen.setLayoutY(10);
        btnUitloggen.setLayoutX(10);
        btnUitloggen.setPrefHeight(27);
        btnUitloggen.setPrefWidth(100);
        btnUitloggen.setText("Log out");
        getAnchorpane().getChildren().add(btnUitloggen);

        btnPasswords = new Button();
        btnPasswords.setLayoutX(50);
        btnPasswords.setLayoutY(50);
        btnPasswords.setPrefWidth(200);
        btnPasswords.setPrefHeight(27);
        btnPasswords.setText("Passwords");
        getAnchorpane().getChildren().add(btnPasswords);

        btnAddPassword = new Button();
        btnPasswords.setLayoutX(50);
        btnPasswords.setLayoutY(90);
        btnPasswords.setPrefWidth(200);
        btnPasswords.setPrefHeight(27);
        btnPasswords.setText("Add Password");
        getAnchorpane().getChildren().add(btnAddPassword);
    }
}
