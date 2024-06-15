package com.devstack.lms.controller;

import com.devstack.lms.entity.AllRegistration;
import com.devstack.lms.entity.RegisterDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationDetailsFormController {

    public AnchorPane context;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtEmail;
    public TextField txtCourseName;
    public TextField txtFee;
    public TextField txtDate;
    public TextField txtPaymentType;
//    @FXML
//    private Label label;
//
//    public void initData(AllRegistration registration) {
//        // Use the registration data to set up the UI
//
//        label.setText("Registration details for: " + registration.getStudentName());
//    }

    public void initData(RegisterDetails registerDetails) {
        txtStudentName.setText(registerDetails.getStudentName());
        txtAddress.setText(registerDetails.getAddress());
        txtAge.setText(String.valueOf(registerDetails.getAge()));
        txtEmail.setText(registerDetails.getEmail());
        txtCourseName.setText(registerDetails.getCustomerName());
        txtFee.setText(String.valueOf(registerDetails.getCourseFee()));
        txtDate.setText(registerDetails.getRegisteredDate().toString());
        txtPaymentType.setText(registerDetails.getPaymentType().toString());
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void printOnAction(ActionEvent actionEvent) {
        
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}
