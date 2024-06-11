package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Student;
import com.devstack.lms.entity.User;
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
import java.util.UUID;

public class SignUpFormController {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane context;

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        User user = new User(
                UUID.randomUUID().toString(),
                txtUserName.getText().trim(),
                txtPassword.getText().trim()
        );

        DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
        try{
            boolean isSignedUp = databaseAccessCode.signUp(user);

            if (isSignedUp){
                new Alert(Alert.AlertType.INFORMATION, "Successfully Signed Up").show();
                setUi("LogInForm");
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
            }

        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void navigateToLogInFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LogInForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}
