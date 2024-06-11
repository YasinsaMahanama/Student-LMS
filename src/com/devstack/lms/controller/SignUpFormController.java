package com.devstack.lms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignUpFormController {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane context;

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(resource))));
        stage.setTitle("Log In Form");
        stage.centerOnScreen();
    }

    public void navigateToLogInFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(resource))));
        stage.setTitle("Log In Form");
        stage.centerOnScreen();
    }
}
