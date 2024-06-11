package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void OpenDashboardOnAction(ActionEvent actionEvent) throws IOException {
        DatabaseAccessCode db = new DatabaseAccessCode();

        try {
            boolean isLogIn = db.logIn(
              txtUserName.getText().toLowerCase(),
              txtPassword.getText().trim()
            );

            if (isLogIn) {
                new Alert(Alert.AlertType.INFORMATION, "Log In Successfully").show();
                setUi("DashboardForm");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void navigateToRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}
