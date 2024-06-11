package com.devstack.lms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtStudentName;
    public TextField txtCourseName;
    public TextField txtStudentEmail;
    public TextField txtCourseFee;
    public Button btnSave;
    public ComboBox cmbCourse;
    public ComboBox cmbStudent;
    public RadioButton rBtnCash;
    public ToggleGroup paymentType;

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void registerNowOnAction(ActionEvent actionEvent) {

    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}
