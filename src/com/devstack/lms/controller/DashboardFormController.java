package com.devstack.lms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public AnchorPane context;

    public void btnOpenStudentFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("StudentForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }

    public void btnOpenCourseFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CourseForm");
    }

    public void btnOpenRegistrationFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegistrationForm");
    }

    public void btnOpenAllRegistrationFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AllRegistrationForm");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
}
